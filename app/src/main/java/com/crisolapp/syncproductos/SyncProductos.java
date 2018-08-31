package com.crisolapp.syncproductos;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.crisolapp.DB.modeloInsumos;
import com.crisolapp.DB.modeloMensajes;
import com.crisolapp.DB.modeloUsuarios;
import com.crisolapp.R;
import com.crisolapp.comunicacion.HttpManager;
import com.crisolapp.comunicacion.HttpManagerOk;
import com.crisolapp.comunicacion.InterfazComunicacion;
import com.crisolapp.comunicacion.MyTaskConection;
import com.crisolapp.librerias.MyActividad;
import com.crisolapp.librerias.MyAlerta;
import com.crisolapp.menuprincipal.MenuPrincipal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class SyncProductos extends MyActividad implements InterfazComunicacion {
    modeloUsuarios modelousuarios ;
    HttpManager httpManager;
    HttpManagerOk httpManagerok;
    MyTaskConection myTaskConection;
    MyAlerta alerta;
    ProgressDialog progreso = null;
    String procesoArticulos;
    String procesoMensajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syncproductos_activity_sync_productos);
        alerta = new MyAlerta(this, "Realmente desea Sincronizar?");
        myTaskConection = new MyTaskConection(this);
        alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                httpManager = new HttpManager();
                httpManagerok = new HttpManagerOk();
                myTaskConection.execute();
            }
        } );
        alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(SyncProductos.this, MenuPrincipal.class);
                startActivity(i);
            }
        });
        alerta.mostrar();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       return true;
    }

    @Override
    public void HacerAntes() {
        progreso = ProgressDialog.show(this, "Procesando", "Espere unos segundos...", true, false);
    }


    @Override
    public void HacerDurante() {
        /*

         try {

            JSONObject json = new JSONObject();
            JSONObject json2 = new JSONObject();

            json2.put("name2", "jhon");
            json2.put("username2", "elias");

                json.put("name", "emil");
                json.put("username", "emil111");
                json.put("age", "111");
                json.put("json2", json2);

            String resp1 = httpManagerok.post(getString(R.string.dominio) +"/mobile/syncup/ping",json);
            JSONObject ja = new JSONObject(resp1);

            System.out.println(ja.getJSONObject("valor1").get("val2"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

         */


        if(this.HayRed()){
      //   if(false){
            try {
                modelousuarios = new modeloUsuarios(this);
                Cursor c =  modelousuarios.BuscartTabla(null,null,null);
                c.moveToFirst();
                String usua_codigo = c.getString(1);

                httpManager.post( getString(R.string.dominio) +"/mobile/syncup/articulos_android/"+usua_codigo+"/1");
                ContentValues RespuestaArticulos = httpManager.getRespuesta();
                modeloInsumos mi = new modeloInsumos(this);
                procesoArticulos = mi.ProcesarSyncup(RespuestaArticulos);

                httpManager.post( getString(R.string.dominio) +"/mobile/syncup/mensajes/"+usua_codigo);
                ContentValues RespuestaMensajes = httpManager.getRespuesta();
                modeloMensajes mm = new modeloMensajes(this);
                procesoMensajes = mm.ProcesarSyncup(RespuestaMensajes);

            } catch (Exception e) {
                AlertaError("Sin Red");
                e.printStackTrace();
            }

        }
    }

    @Override
    public void HacerDespues() {
        progreso.dismiss();
        String Resultado  = "Proceso Terminado"+"\n";
        Resultado += "Articulos.......... "+procesoArticulos+"\n";
        Resultado += "Mensajes........... "+procesoMensajes+"\n";
        final Context contexto = this;
        alerta = new MyAlerta(this, Resultado);
        alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(contexto, MenuPrincipal.class);
                startActivity(i);
            }
        } );
        alerta.mostrar();
    }

    public void AlertaError(String msg){
        alerta = new MyAlerta(this, msg);
        alerta.setPositiveButton("Aceptar", null);
        progreso.dismiss();
        alerta.mostrar();
    }
}
