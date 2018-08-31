package com.crisolapp.tomapedido;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.crisolapp.DB.modeloPedidos;
import com.crisolapp.R;
import com.crisolapp.clientes.Clientes;
import com.crisolapp.comunicacion.HttpManager;
import com.crisolapp.comunicacion.InterfazComunicacion;
import com.crisolapp.comunicacion.MyTaskConection;
import com.crisolapp.librerias.MyActividad;
import com.crisolapp.librerias.MyAlerta;

import java.text.NumberFormat;

public class EliminarCantidad extends MyActividad implements View.OnClickListener, InterfazComunicacion {

    TextView textCodigo,textNombre,textPrecio,textSubtotal;
    EditText editCantidad;
    NumberFormat formato;
    MyAlerta alerta;
    Button btnCancelar, btnEliminar;
    HttpManager httpManager;
    MyTaskConection myTaskConection;
    Bundle bolsa;
    ProgressDialog progreso = null;
    String Cadena_Final = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tomapedido_activity_eliminar_cantidad);
        bolsa = getIntent().getExtras();

        textCodigo   = (TextView)findViewById(R.id.txtAdicionarCodigo);
        textNombre   = (TextView)findViewById(R.id.txtAdicionarNombre);
        textPrecio   = (TextView)findViewById(R.id.txtAdicionarPrecio);
        textSubtotal = (TextView)findViewById(R.id.txtAdcionarSubtotal);
        editCantidad = (EditText)findViewById(R.id.editAdicionarCantidad);

        btnCancelar = (Button)findViewById(R.id.buttonCancelar);
        btnEliminar  = (Button)findViewById(R.id.buttonEliminar);

        btnCancelar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);

        textCodigo.setText(bolsa.getString("insu_codigo"));
        textNombre.setText(bolsa.getString("insu_nombre"));

        formato = NumberFormat.getCurrencyInstance();
        textPrecio.setText( formato.format( Double.parseDouble(bolsa.getString("insu_precio")) ));

        editCantidad.setText(bolsa.getString("pedi_cantidad"));
        textSubtotal.setText(formato.format(  Double.parseDouble(bolsa.getString("insu_precio"))*Double.parseDouble(bolsa.getString("pedi_cantidad"))   ));


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonCancelar:
                Intent i = new Intent(this, TomaPedido.class);
                i.putExtras(bolsa);
                startActivity(i);
            break;
            case R.id.buttonEliminar:

                alerta = new MyAlerta(this, "Realmente desea Eliminar este Producto?");
                alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       //proceso de eliminar el proucto del pedido
                        modeloPedidos mp = new modeloPedidos(EliminarCantidad.this);
                        if(bolsa.getString("pedi_estado").equals("A")){
                            mp.Eliminar("pedi_id",( String.valueOf(bolsa.getInt("pedi_id"))  ));
                            Intent e = new Intent(EliminarCantidad.this, TomaPedido.class);
                            e.putExtras(bolsa);
                            startActivity(e);
                        }else{
                            myTaskConection = new MyTaskConection(EliminarCantidad.this);
                            httpManager = new HttpManager();
                            myTaskConection.execute();
                            Cadena_Final = bolsa.getString("pedi_cliente")+"Z"+bolsa.getString("insu_codigo");
                        }

                    }
                } );
                alerta.setNegativeButton("Cancelar", null);
                alerta.mostrar();

                break;
        }

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, TomaPedido.class);
        i.putExtras(bolsa);
        startActivity(i);
        return;
    }

    @Override
    public void HacerAntes() {
        progreso = ProgressDialog.show(this, "Elminando Articulo", "Espere unos segundos...", true, false);
    }

    @Override
    public void HacerDurante() {
        if(this.HayRed()){
            try {
                httpManager.post(getString(R.string.dominio) + "/mobile/syncup/eliminar_pedido/" + Cadena_Final);
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
                modeloPedidos mp = new modeloPedidos(EliminarCantidad.this);
                mp.Eliminar("pedi_id",( String.valueOf(bolsa.getInt("pedi_id"))  ));
                Intent e = new Intent(EliminarCantidad.this, TomaPedido.class);
                e.putExtras(bolsa);
                progreso.dismiss();
                startActivity(e);
            }else{
                alerta = new MyAlerta(this, "No se Pudo Eliminar");
                progreso.dismiss();
                alerta.setPositiveButton("Aceptar", null);
                alerta.mostrar();
            }


        }else{
            alerta = new MyAlerta(this, "No se Pudo Conectar");
            progreso.dismiss();
            alerta.setPositiveButton("Aceptar", null);
            alerta.mostrar();
        }
    }
}
