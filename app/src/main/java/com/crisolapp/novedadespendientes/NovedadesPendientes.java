package com.crisolapp.novedadespendientes;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.crisolapp.DB.modeloNovedades;
import com.crisolapp.DB.modeloPedidos;
import com.crisolapp.DB.modeloUsuarios;
import com.crisolapp.R;
import com.crisolapp.comunicacion.HttpManager;
import com.crisolapp.comunicacion.InterfazComunicacion;
import com.crisolapp.comunicacion.MyTaskConection;
import com.crisolapp.librerias.MyActividad;
import com.crisolapp.librerias.MyAlerta;
import com.crisolapp.menuprincipal.MenuPrincipal;

public class NovedadesPendientes extends MyActividad implements InterfazComunicacion {
    modeloNovedades modelonovedades ;
    boolean HayNovedadesPorEnviar = false;
    HttpManager httpManager;
    MyTaskConection myTaskConection;
    MyAlerta alerta;
    ProgressDialog progreso = null;
    Cursor c;
    String CadenaNovedad = "";
    String CadenaFinal  = "";
    String IN = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novedadespendientes_activity_novedades_pendientes);
        modelonovedades = new modeloNovedades(this);
        c = modelonovedades.BuscartTabla(new String[]{"nove_id", "nove_tiponovedad", "nove_cliente", "nove_estado"}, "nove_estado=?", new String[]{"A"});

        if(c.moveToFirst()){
            CadenaNovedad="";
            IN = "";
            do{
                CadenaNovedad+=c.getString(2)+"~"+c.getString(1)+"B";
                IN += c.getString(0)+",";
            }while (c.moveToNext());
            CadenaNovedad = CadenaNovedad.substring(0,CadenaNovedad.length()-1);
            IN = IN.substring(0,IN.length()-1);
            CadenaFinal = CadenaNovedad;
            httpManager = new HttpManager();
            myTaskConection = new MyTaskConection(this);
            myTaskConection.execute();
        }else{
            alerta = new MyAlerta(this, "No hay novedades por enviar");
            alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(NovedadesPendientes.this, MenuPrincipal.class);
                    startActivity(i);
                }
            } );
            alerta.mostrar();
        }

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
      progreso = ProgressDialog.show(this, "Enviando Novedades", "Espere unos segundos...", true, false);
    }

    @Override
    public void HacerDurante() {
        if(this.HayRed()){
            try {
                httpManager.post(getString(R.string.dominio) + "/mobile/syncup/guardar_novedad_combo/" + CadenaFinal);
            } catch (Exception e) {
                alerta = new MyAlerta(this, "Sin Red");
                progreso.dismiss();
                alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(NovedadesPendientes.this, MenuPrincipal.class);
                        startActivity(i);
                    }
                } );
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
                modelonovedades.ModificarIN("nove_estado","E","nove_id",IN);
                this.mostrarAlerta("Novedades Enviadas Correctamente");
            }else{
                this.mostrarAlerta("No se Pudo Enviar");
            }
        }else{
            this.mostrarAlerta("No se Pudo Conectar");
        }
    }

    public  void mostrarAlerta(String titulo){
        alerta = new MyAlerta(this, titulo);
        progreso.dismiss();
        alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(NovedadesPendientes.this, MenuPrincipal.class);
                startActivity(i);
            }
        });
        alerta.mostrar();
    }
}
