package com.crisolapp.novedad;

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
import android.widget.ListView;

import com.crisolapp.DB.modeloClientes;
import com.crisolapp.DB.modeloNovedades;
import com.crisolapp.DB.modeloTNovedades;
import com.crisolapp.R;
import com.crisolapp.clientes.Clientes;
import com.crisolapp.comunicacion.HttpManager;
import com.crisolapp.comunicacion.InterfazComunicacion;
import com.crisolapp.comunicacion.MyTaskConection;
import com.crisolapp.librerias.MyActividad;
import com.crisolapp.librerias.MyAlerta;

public class TipoNovedad extends MyActividad  implements AdapterView.OnItemClickListener,InterfazComunicacion {

    modeloTNovedades modelotiponovedades;
    modeloNovedades modelonovedades;
    ListView lista;
    AdaptadoTipoNovedad[] adaptadoTipoNovedad;
    AdaptadorTipoNovedad adt;
    Bundle bolsa;
    HttpManager httpManager;
    MyTaskConection myTaskConection;
    MyAlerta alerta;
    ProgressDialog progreso = null;
    String Cadena_Final="";
    String TipoNovedad = "";
    Cursor  c;
    boolean haynovedad = false;
    String NovedadEnviada = "";
    String TipoNovedadEnviada = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novedad_activity_tipo_novedad);
        lista = (ListView)findViewById(R.id.list);
        lista.setOnItemClickListener(this);
        modelotiponovedades = new modeloTNovedades(this);
        bolsa = getIntent().getExtras();
        c =modelotiponovedades.BuscartTabla(null, null, null);
        modelonovedades = new modeloNovedades(this);
        Cursor newness =  modelonovedades.BuscartTabla(new String[]{"nove_tiponovedad","nove_estado"},"nove_cliente=?",new String[]{bolsa.getString("clie_codigo")});

        if(newness.moveToFirst()){
            haynovedad         = true;
            NovedadEnviada     = "Novedad enviada: "+newness.getString(0)+" Estado: "+newness.getString(1);
            TipoNovedadEnviada = newness.getString(0);

        }

        if(c.moveToFirst()){
            adaptadoTipoNovedad = new AdaptadoTipoNovedad[c.getCount()];
            int i=0;
            do{
                if(haynovedad){
                    if(TipoNovedadEnviada.equals( c.getString(1) )){
                        adaptadoTipoNovedad[i] = new AdaptadoTipoNovedad(c.getString(0), c.getString(1), c.getString(2),true);
                    }else{
                        adaptadoTipoNovedad[i] = new AdaptadoTipoNovedad(c.getString(0), c.getString(1), c.getString(2),false);
                    }
                }else {
                    adaptadoTipoNovedad[i] = new AdaptadoTipoNovedad(c.getString(0), c.getString(1), c.getString(2),false);
                }
                i++;
            }while (c.moveToNext());
        }
        adt = new AdaptadorTipoNovedad(this, adaptadoTipoNovedad);
        lista.setAdapter(adt);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AdaptadoTipoNovedad item =  (AdaptadoTipoNovedad) parent.getAdapter().getItem(position);

        if(!haynovedad) {
            TipoNovedad = item.getTnov_codigo();
            Cadena_Final = bolsa.getString("clie_codigo") + "-" + TipoNovedad;
            alerta = new MyAlerta(this, "Realmente desea Enviar Esta Novedad?");
            myTaskConection = new MyTaskConection(this);
            alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    httpManager = new HttpManager();
                    myTaskConection.execute();
                }
            });
            alerta.setNegativeButton("Cancelar", null);
            alerta.mostrar();
        }else{
            alerta = new MyAlerta(this, NovedadEnviada);
            alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(TipoNovedad.this, Clientes.class);
                    i.putExtras(bolsa);
                    startActivity(i);
                }
            });

            alerta.mostrar();
        }

    }

    @Override
    public void HacerAntes() {
        progreso = ProgressDialog.show(this, "Enviando Novedad", "Espere unos segundos...", true, false);
    }

    @Override
    public void HacerDurante() {
        if(this.HayRed()){
            try {
                httpManager.post(getString(R.string.dominio) + "/mobile/syncup/guardar_novedad/" + Cadena_Final);
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
        ContentValues novedad = new ContentValues();
        novedad.put("nove_tiponovedad",TipoNovedad);
        novedad.put("nove_cliente",bolsa.getString("clie_codigo"));
        novedad.put("nove_estado","A");
        modelonovedades.InsertarTabla(novedad);

        ContentValues content = new ContentValues();
        content.put("clie_visitado","S");
        modeloClientes  modeloclientes = new modeloClientes(this);
        modeloclientes.Modificar(content,"clie_codigo",bolsa.getString("clie_codigo") );

        if(this.httpManager.getRespuesta().getAsString("COM")=="OK") {
            String Respuesta = httpManager.getRespuesta().getAsString("TEXTO");
            System.out.println(Respuesta);
            if(Respuesta.equals("1")){
                ContentValues nove = new ContentValues();
                nove.put("nove_estado","E");
                modelonovedades.Modificar(nove,"nove_cliente",bolsa.getString("clie_codigo"));
                progreso.dismiss();
                Intent i = new Intent(TipoNovedad.this, Clientes.class);
                i.putExtras(bolsa);
                startActivity(i);
            }else{
                alerta = new MyAlerta(this, "No se Pudo Enviar");
                progreso.dismiss();
                alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(TipoNovedad.this, Clientes.class);
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
                    Intent i = new Intent(TipoNovedad.this, Clientes.class);
                    i.putExtras(bolsa);
                    startActivity(i);
                }
            });
            alerta.mostrar();
        }
    }

}
