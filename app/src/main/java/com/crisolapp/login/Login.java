package com.crisolapp.login;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.crisolapp.DB.modeloUsuarios;
import com.crisolapp.DiaRuta.DiaRuta;
import com.crisolapp.R;
import com.crisolapp.comunicacion.HttpManager;
import com.crisolapp.comunicacion.InterfazComunicacion;
import com.crisolapp.comunicacion.MyTaskConection;
import com.crisolapp.librerias.MyActividad;
import com.crisolapp.librerias.MyAlerta;



public class Login extends MyActividad implements View.OnClickListener,InterfazComunicacion {
    Button   btnEntrar ;
    EditText textUsuario;
    EditText textClave;
    MyAlerta alertaSync;
    MyAlerta alertaError;
    ProgressDialog progreso = null;
    HttpManager httpManager;
    MyTaskConection myTaskConection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_login);
        btnEntrar   = (Button)findViewById(R.id.btnEntrar);
        textUsuario = (EditText)findViewById(R.id.textUsuario);
        textClave   = (EditText)findViewById(R.id.textClave);
        btnEntrar.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
      if(!textUsuario.getText().toString().equals("") && !textClave.getText().toString().equals("") ) {
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
            httpManager.post( getString(R.string.dominio) +"/mobile/auth/validar/"+textUsuario.getText().toString()+"/"+textClave.getText().toString());
        } catch (Exception e) {
            alertaError = new MyAlerta(this, "Sin Red");
            progreso.dismiss();
            alertaError.setPositiveButton("Aceptar", null);
            alertaError.mostrar();
            e.printStackTrace();
        }

    }

    }

    @Override
    public void HacerDespues() {
        if(this.httpManager.getRespuesta().getAsString("COM")=="OK"){
            String Respuesta = httpManager.getRespuesta().getAsString("TEXTO");
            if(Respuesta.equals("0")){
                AlertaError("Usuario Invalido");
            }else{
                try {
                    String[] datos = Respuesta.split("-");
                    modeloUsuarios mu = new modeloUsuarios(this);
                    mu.LimpiarDB();
                    ContentValues usuario = new ContentValues();
                    usuario.put("usua_codigo",datos[0]);
                    usuario.put("usua_nombre",datos[2]);
                    usuario.put("usua_clave",datos[1]);
                    mu.InsertarTabla(usuario);
                    progreso.dismiss();
                    Intent i = new Intent(this, DiaRuta.class);
                    i.putExtra("usua_nombre",datos[2]);
                    i.putExtra("usua_id",datos[0]);
                    startActivity(i);

                }catch (IllegalArgumentException e){
                    AlertaError("Error de Formato");
                }

                System.out.println(Respuesta);
            }

        }else{
           AlertaError("No Se Pudo Conectar");
        }

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


}
