package com.crisolapp.DiaRuta;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import com.crisolapp.DB.modeloCiudades;
import com.crisolapp.DB.modeloClientes;
import com.crisolapp.DB.modeloInsumos;
import com.crisolapp.DB.modeloMensajes;
import com.crisolapp.DB.modeloRutas;
import com.crisolapp.DB.modeloTClientes;
import com.crisolapp.DB.modeloTNovedades;
import com.crisolapp.R;
import com.crisolapp.comunicacion.HttpManager;
import com.crisolapp.comunicacion.InterfazComunicacion;
import com.crisolapp.comunicacion.MyTaskConection;
import com.crisolapp.librerias.MyActividad;
import com.crisolapp.librerias.MyAlerta;
import com.crisolapp.menuprincipal.MenuPrincipal;

public class DiaRuta extends MyActividad implements View.OnClickListener,InterfazComunicacion {
    TextView TextNombre ;
    Button btnAceptar;
    CheckBox []dias={null,null,null,null,null,null,null};
    MyAlerta alertaSync;
    MyAlerta alertaError;
    HttpManager httpManager;
    MyTaskConection myTaskConection;
    ProgressDialog progreso = null;
    String SeparateDias;
    Bundle bolsa;

    String procesoRutas;
    String procesoClientes;
    String procesoArticulos;
    String procesoCiudades;
    String procesoTClientes;
    String procesoTNovedades;
    String procesoMensajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diaruta_activity_diaruta);
        dias[0]= (CheckBox)findViewById(R.id.checkLunes);
        dias[1]= (CheckBox)findViewById(R.id.checkMartes);
        dias[2]= (CheckBox)findViewById(R.id.checkMiercoles);
        dias[3]= (CheckBox)findViewById(R.id.checkJueves);
        dias[4]= (CheckBox)findViewById(R.id.checkViernes);
        dias[5]= (CheckBox)findViewById(R.id.checkSabado);
        dias[6]= (CheckBox)findViewById(R.id.checkDomingo);

        TextNombre = (TextView)this.findViewById(R.id.DiaRutaTextView);
        btnAceptar = (Button)findViewById(R.id.btnAceptar);
        btnAceptar.setOnClickListener(this);
        bolsa = getIntent().getExtras();
        TextNombre.setText(bolsa.getString("usua_nombre") );

    }

    @Override
    public void onClick(View v) {
    boolean hayCheck = false;
    SeparateDias = "";
    for(int i=0;i<dias.length;i++){
        if(dias[i].isChecked()){
            hayCheck=true;
            SeparateDias+=i+"-";
        }
    }
     //si hay algun dia selecccionado
     if(hayCheck){
       alertaSync = new MyAlerta(this, "Realmente desea Sincronizar?");
         myTaskConection = new MyTaskConection(this);
         alertaSync.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 httpManager = new HttpManager();
                 myTaskConection.execute();
             }
         } );
         alertaSync.setNegativeButton("Cancelar", null);
         alertaSync.mostrar();
     }else{

     }

    }

    @Override
    public void HacerAntes() {
       progreso = ProgressDialog.show(this, "Procesando", "Espere unos segundos...", true, false);
    }

    @Override
    public void HacerDurante() {
        if(this.HayRed()){
            try {
                String android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
                SeparateDias = SeparateDias.substring(0,SeparateDias.length()-1);
                httpManager.post( getString(R.string.dominio) +"/mobile/syncup/rutas/"+bolsa.getString("usua_id")+"/"+SeparateDias+"/"+android_id);
                ContentValues RespuestaRutas = httpManager.getRespuesta();
                modeloRutas mr = new modeloRutas(this);
                procesoRutas = mr.ProcesarSyncup(RespuestaRutas);

                httpManager.post( getString(R.string.dominio) +"/mobile/syncup/clientes/"+bolsa.getString("usua_id")+"/"+SeparateDias);
                ContentValues RespuestaClientes = httpManager.getRespuesta();
                modeloClientes mc = new modeloClientes(this);
                procesoClientes = mc.ProcesarSyncup(RespuestaClientes);

                httpManager.post( getString(R.string.dominio) +"/mobile/syncup/articulos_android/"+bolsa.getString("usua_id")+"/"+SeparateDias);
                ContentValues RespuestaArticulos = httpManager.getRespuesta();
                modeloInsumos mi = new modeloInsumos(this);
                procesoArticulos = mi.ProcesarSyncup(RespuestaArticulos);

                httpManager.post( getString(R.string.dominio) +"/mobile/syncup/ciudades/"+bolsa.getString("usua_id")+"/"+SeparateDias);
                ContentValues RespuestaCiudades = httpManager.getRespuesta();
                modeloCiudades mci = new modeloCiudades(this);
                procesoCiudades = mci.ProcesarSyncup(RespuestaCiudades);

                httpManager.post( getString(R.string.dominio) +"/mobile/syncup/tclientes/"+bolsa.getString("usua_id")+"/"+SeparateDias);
                ContentValues RespuestaTClientes = httpManager.getRespuesta();
                modeloTClientes mtc = new modeloTClientes(this);
                procesoTClientes = mtc.ProcesarSyncup(RespuestaTClientes);

                httpManager.post( getString(R.string.dominio) +"/mobile/syncup/tnovedades/"+bolsa.getString("usua_id")+"/"+SeparateDias);
                ContentValues RespuestaTNovedades = httpManager.getRespuesta();
                modeloTNovedades mtn = new modeloTNovedades(this);
                procesoTNovedades = mtn.ProcesarSyncup(RespuestaTNovedades);

                httpManager.post( getString(R.string.dominio) +"/mobile/syncup/mensajes/"+bolsa.getString("usua_id"));
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
               Resultado += "Rutas.............. "+procesoRutas+"\n";
               Resultado += "Clientes........... "+procesoClientes+"\n";
               Resultado += "Articulos.......... "+procesoArticulos+"\n";
               Resultado += "Ciudades........... "+procesoCiudades+"\n";
               Resultado += "Tipo de Clientes... "+procesoTClientes+"\n";
               Resultado += "Tipo de Novedades.. "+procesoTNovedades+"\n";
               Resultado += "Mensajes........... "+procesoMensajes+"\n";
        final Context contexto = this;
        alertaSync = new MyAlerta(this, Resultado);
        alertaSync.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(contexto, MenuPrincipal.class);
                startActivity(i);
            }
        } );
        alertaSync.mostrar();
    }

    public void AlertaError(String msg){
        alertaError = new MyAlerta(this, msg);
        alertaError.setPositiveButton("Aceptar", null);
        progreso.dismiss();
        alertaError.mostrar();
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
    public void onBackPressed()
    {

    }

}
