package com.crisolapp.tomapedido;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.crisolapp.DB.modeloPedidos;
import com.crisolapp.DB.modeloUsuarios;
import com.crisolapp.R;
import com.crisolapp.clientes.Clientes;
import com.crisolapp.comunicacion.HttpManager;
import com.crisolapp.comunicacion.InterfazComunicacion;
import com.crisolapp.comunicacion.MyTaskConection;
import com.crisolapp.detallecliente.DetalleCliente;
import com.crisolapp.librerias.MyActividad;
import com.crisolapp.librerias.MyAlerta;
import com.crisolapp.menuprincipal.MenuPrincipal;

import java.text.NumberFormat;

public class TomaPedido extends MyActividad implements View.OnClickListener,AdapterView.OnItemClickListener, InterfazComunicacion {
    modeloPedidos modelopedidos ;
    modeloUsuarios modelousuarios ;
    Button btnAdicionar;
    Button btnEnviar;
    Bundle bolsa;
    ListView lista;
    AdaptadoPedidos[] adaptadoPedidos=null;
    AdaptadorPedidos adp;
    Cursor c;
    NumberFormat formato;
    String Cadena_Pedido="";
    String Cadena_Final = "";
    String Cliente_id;
    boolean HayPedidosPorEnviar = false;
    HttpManager httpManager;
    MyTaskConection myTaskConection;
    MyAlerta alerta;
    ProgressDialog progreso = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tomapedido_activity_toma_pedido);

        btnAdicionar = (Button)this.findViewById(R.id.buttonAdicionar);
        btnEnviar    = (Button)this.findViewById(R.id.buttonEnviar);
        lista        = (ListView)findViewById(R.id.listView);
        lista.setOnItemClickListener(this);

        btnAdicionar.setOnClickListener(this);
        btnEnviar.setOnClickListener(this);
        bolsa = getIntent().getExtras();
        modelopedidos = new modeloPedidos(this);
        c = modelopedidos.buscar_pedido(bolsa.getString("clie_codigo"));
        int totalRef = 0;
        int totalArt = 0;
        double totalPedido = 0;
        double totalSinIva = 0;

        if(c.moveToFirst()){
            int i=0;
            totalRef = c.getCount();
            Cadena_Pedido = "";
            System.out.println("cantidad.........."+c.getCount());
            adaptadoPedidos = new AdaptadoPedidos[c.getCount()];
            do{
               adaptadoPedidos[i] = new AdaptadoPedidos(Integer.parseInt(c.getString(0)),c.getString(1),c.getString(2),c.getString(3),Double.parseDouble(c.getString(4)),c.getString(5),Double.parseDouble(c.getString(6)),c.getString(7),c.getString(8) );
                i++;
                totalArt   += Integer.parseInt(c.getString(3));
                double PrecioIva = Double.parseDouble(c.getString(4)) * Integer.parseInt(c.getString(3));
                totalPedido+= PrecioIva;
                double Precio = PrecioIva*100/(100+ Integer.parseInt(c.getString(6)) );
                totalSinIva+=Precio;

                if(c.getString(5).equals("A")){
                    HayPedidosPorEnviar = true;
                    Cliente_id = c.getString(8);
                    Cadena_Pedido+=c.getString(1)+"~"+c.getString(3)+"B";
                }

            }while (c.moveToNext());
            ((TextView)findViewById(R.id.ref)).setText(String.valueOf(totalRef));
            ((TextView)findViewById(R.id.totalArt)).setText(String.valueOf(totalArt));
            formato = NumberFormat.getCurrencyInstance();
            ((TextView)findViewById(R.id.totalPedido)).setText( formato.format(totalPedido) );
            ((TextView)findViewById(R.id.totalPedidoSinIva)).setText( formato.format(totalSinIva) );

            adp = new AdaptadorPedidos(this, adaptadoPedidos);
            lista.setAdapter(adp);
        }

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonAdicionar:
                Intent i = new Intent(this, AdicionarArticulo.class);
                i.putExtras(bolsa);
                startActivity(i);
                break;

            case R.id.buttonEnviar:
                 if(HayPedidosPorEnviar){
                     Cadena_Pedido  = Cadena_Pedido.substring(0,Cadena_Pedido.length()-1);
                     System.out.println(Cadena_Pedido);
                     modelousuarios = new modeloUsuarios(this);
                    Cursor c =  modelousuarios.BuscartTabla(null,null,null);
                    c.moveToFirst();
                    String usua_codigo = c.getString(1);
                    Cadena_Final =Cliente_id+"-"+usua_codigo+"A"+Cadena_Pedido;

                     alerta = new MyAlerta(this, "Realmente desea Enviar Este Pedido?");
                     myTaskConection = new MyTaskConection(this);
                     alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                       httpManager = new HttpManager();
                       myTaskConection.execute();
                       }
                     } );
                      alerta.setNegativeButton("Cancelar", null);
                      alerta.mostrar();


                 }else{
                     alerta = new MyAlerta(this, "No hay Productos por enviar");
                     alerta.setPositiveButton("Aceptar", null);
                     alerta.mostrar();
                 }

                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AdaptadoPedidos item =  (AdaptadoPedidos) parent.getAdapter().getItem(position);
        Intent i = new Intent(this, EliminarCantidad.class);
        bolsa.putString("insu_codigo",item.getPedi_insumo());
        bolsa.putString("insu_nombre",item.getNombre_insumo());
        bolsa.putString("insu_precio", String.valueOf(item.getPedi_precioiva()));
        bolsa.putString("insu_iva",String.valueOf(item.getInsu_iva()));
        bolsa.putString("insu_marca",item.getPedi_marca());
        bolsa.putInt("pedi_id", item.getPedi_id());
        bolsa.putString("pedi_cliente", item.getPedi_cliente());
        bolsa.putString("pedi_estado", item.getPedi_estado());
        i.putExtras(bolsa);
        startActivity(i);


        bolsa.putInt("pedi_id", item.getPedi_id() );
        bolsa.putString("pedi_cantidad", item.getPedi_cantidad());

        i.putExtras(bolsa);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, DetalleCliente.class);
        i.putExtras(bolsa);
        startActivity(i);
        return;
    }

    @Override
    public void HacerAntes() {
        progreso = ProgressDialog.show(this, "Enviando Pedido", "Espere unos segundos...", true, false);
    }

    @Override
    public void HacerDurante() {
        if(this.HayRed()){
            try {
                httpManager.post(getString(R.string.dominio) + "/mobile/syncup/guardar_pedido/" + Cadena_Final);
            } catch (Exception e) {
                alerta = new MyAlerta(this, "Sin Red");
                progreso.dismiss();
                alerta.setPositiveButton("Aceptar", null);
                alerta.mostrar();
                e.printStackTrace();
            }

        }
    }

    @Override
    public void HacerDespues() {
        if(this.httpManager.getRespuesta().getAsString("COM")=="OK") {
            String Respuesta = httpManager.getRespuesta().getAsString("TEXTO");
            System.out.println(Respuesta);
            if(Respuesta.equals("1")){
             for (AdaptadoPedidos ap: adaptadoPedidos){
                 if(ap.getPedi_estado().equals("A")){
                     ContentValues content = new ContentValues();
                     content.put("pedi_estado","E");
                     modelopedidos.Modificar(content,"pedi_id",String.valueOf( ap.getPedi_id()));
                 }
             }

                alerta = new MyAlerta(this, "Pedido Enviado Correctamente");
                alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(TomaPedido.this, Clientes.class);
                        i.putExtras(bolsa);
                        startActivity(i);
                    }
                } );
                progreso.dismiss();
                alerta.mostrar();

            }else{
                alerta = new MyAlerta(this, "No se Pudo Enviar");
                progreso.dismiss();
                alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(TomaPedido.this, Clientes.class);
                        i.putExtras(bolsa);
                        startActivity(i);
                    }
                });
                alerta.mostrar();
            }
        }else{
            alerta = new MyAlerta(this, "No se Pudo Conectar");
            progreso.dismiss();
            alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(TomaPedido.this, Clientes.class);
                    i.putExtras(bolsa);
                    startActivity(i);
                }
            });
            alerta.mostrar();
        }
    }


}
