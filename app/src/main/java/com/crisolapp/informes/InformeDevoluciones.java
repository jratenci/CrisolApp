package com.crisolapp.informes;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.crisolapp.DB.modeloUsuarios;
import com.crisolapp.R;
import com.crisolapp.comunicacion.HttpManager;
import com.crisolapp.comunicacion.InterfazComunicacion;
import com.crisolapp.comunicacion.MyTaskConection;
import com.crisolapp.librerias.MyActividad;
import com.crisolapp.librerias.MyAlerta;

import java.util.Calendar;

public class InformeDevoluciones extends MyActividad implements View.OnClickListener,InterfazComunicacion {

    Button btnFI,btnFF,btnConsultar;
    TextView txtFI,txtFF,txtRespuesta;
    Calendar calendario = Calendar.getInstance();

    modeloUsuarios modelousuarios ;
    HttpManager httpManager;
    MyTaskConection myTaskConection;
    MyAlerta alerta;
    ProgressDialog progreso = null;
    String CadenaFI;
    String CadenaFF;
    ContentValues respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informes_activity_informe_devoluciones);

        btnFI        = (Button)findViewById(R.id.btnFI);
        btnFF        = (Button)findViewById(R.id.btnFF);
        btnConsultar = (Button)findViewById(R.id.btnConsultar);

        btnFI.setOnClickListener(this);
        btnFF.setOnClickListener(this);
        btnConsultar.setOnClickListener(this);

        txtFI = (TextView)findViewById(R.id.txtFI);
        txtFF = (TextView)findViewById(R.id.txtFF);
        txtRespuesta = (TextView)findViewById(R.id.txtResultado);
        txtRespuesta.setMovementMethod(new ScrollingMovementMethod());

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFI:
                new DatePickerDialog(this,fiDateSetListener, calendario.get(Calendar.YEAR),calendario.get(Calendar.MONTH),calendario.get(Calendar.DAY_OF_MONTH)  ).show();
                break;

            case R.id.btnFF:
                new DatePickerDialog(this,ffDateSetListener, calendario.get(Calendar.YEAR),calendario.get(Calendar.MONTH),calendario.get(Calendar.DAY_OF_MONTH)  ).show();
                break;

            case R.id.btnConsultar:
                myTaskConection = new MyTaskConection(this);
                httpManager = new HttpManager();
                myTaskConection.execute();
                break;

        }
    }


    private DatePickerDialog.OnDateSetListener fiDateSetListener =
            new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    CadenaFI = String.valueOf(year)+"-"+String.valueOf(monthOfYear+1)+"-"+String.valueOf(dayOfMonth);
                    txtFI.setText( String.valueOf(dayOfMonth)+"/"+String.valueOf(monthOfYear+1)+"/"+String.valueOf(year) );
                }
            };

    private DatePickerDialog.OnDateSetListener ffDateSetListener =
            new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    CadenaFF =String.valueOf(year)+"-"+String.valueOf(monthOfYear+1)+"-"+ String.valueOf(dayOfMonth);
                    txtFF.setText( String.valueOf(dayOfMonth)+"/"+String.valueOf(monthOfYear+1)+"/"+String.valueOf(year) );
                }
            };

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
                String CadenaFinal = CadenaFI+"R"+CadenaFF+"R"+usua_codigo;
                httpManager.post( getString(R.string.dominio) +"/mobile/syncup/informes_devoluciones/"+CadenaFinal);
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
