package com.crisolapp.informes;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.crisolapp.DB.modeloUsuarios;
import com.crisolapp.R;
import com.crisolapp.comunicacion.HttpManager;
import com.crisolapp.comunicacion.InterfazComunicacion;
import com.crisolapp.comunicacion.MyTaskConection;
import com.crisolapp.librerias.MyActividad;
import com.crisolapp.librerias.MyAlerta;

public class InformePedidos extends MyActividad implements View.OnClickListener,InterfazComunicacion {
    Spinner sDia;
    Button btnConsultar;
    TextView txtRespuesta;
    ArrayAdapter<String> adapterDia;
    String[] opcDia,codDia;

    modeloUsuarios modelousuarios ;
    HttpManager httpManager;
    MyTaskConection myTaskConection;
    MyAlerta alerta;
    ProgressDialog progreso = null;
    ContentValues respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informes_activity_informe_pedidos);
        codDia = new String[]{"L","M","MI","J","V","S","D"};
        opcDia = new String[]{"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};

        adapterDia = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,opcDia);
        sDia    = (Spinner)findViewById(R.id.inputDia);
        sDia.setAdapter(adapterDia);

        txtRespuesta = (TextView)findViewById(R.id.txtResultado);
        txtRespuesta.setMovementMethod(new ScrollingMovementMethod());
        btnConsultar = (Button)findViewById(R.id.btnConsultar);
        btnConsultar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        myTaskConection = new MyTaskConection(this);
        httpManager = new HttpManager();
        myTaskConection.execute();
    }

    @Override
    public void HacerAntes() {
        respuesta = null;
        progreso = ProgressDialog.show(this, "Procesando", "Espere unos segundos...", true, false);
    }

    @Override
    public void HacerDurante() {
        if(this.HayRed()){
            try {
                modelousuarios = new modeloUsuarios(this);
                Cursor c =  modelousuarios.BuscartTabla(null,null,null);
                c.moveToFirst();
                String usua_codigo = c.getString(1);
                String CadenaFinal = codDia[ sDia.getSelectedItemPosition() ]+"R"+usua_codigo;
                httpManager.post( getString(R.string.dominio) +"/mobile/syncup/informes_pedidos/"+CadenaFinal);
                respuesta = httpManager.getRespuesta();
            } catch (Exception e) {
                AlertaError("Sin Red");
                e.printStackTrace();
            }

        }
    }

    @Override
    public void HacerDespues() {
        progreso.dismiss();
        if(respuesta!=null) {
            if (respuesta.getAsString("COM").equals("OK")) {
                txtRespuesta.setText(respuesta.getAsString("TEXTO"));
            }
        }else{
            AlertaError("Sin Comunicacion");
        }
    }

    public void AlertaError(String msg){
        alerta = new MyAlerta(this, msg);
        alerta.setPositiveButton("Aceptar", null);
        progreso.dismiss();
        alerta.mostrar();
    }

}
