package com.crisolapp.nuevocliente;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.crisolapp.DB.modeloCiudades;
import com.crisolapp.DB.modeloClientes;
import com.crisolapp.DB.modeloRutas;
import com.crisolapp.DB.modeloTClientes;
import com.crisolapp.DB.modeloUsuarios;
import com.crisolapp.R;
import com.crisolapp.comunicacion.HttpManager;
import com.crisolapp.comunicacion.InterfazComunicacion;
import com.crisolapp.comunicacion.MyTaskConection;
import com.crisolapp.detallecliente.DetalleCliente;
import com.crisolapp.librerias.MyActividad;
import com.crisolapp.librerias.MyAlerta;
import com.crisolapp.menuprincipal.MenuPrincipal;
import com.crisolapp.rutas.Rutas;

public class NuevoCliente extends MyActividad implements View.OnClickListener, InterfazComunicacion {
    Spinner sCiudad,sTipo,sRuta;
    ArrayAdapter<String> adapterCiudad,adapterTipo,adapterRuta;
    String[] opcCiudad,opcTipo,opcRuta;
    String[] codCiudad,codTipo,codRuta;
    modeloCiudades mc;
    modeloTClientes mtc;
    modeloRutas mr;
    modeloClientes mcliente;
    TextView inputCedula,inputNombre,inputNegocio,inputBarrio,inputDireccion,inputTelefono,inputSecuencia;
    Button Aceptar,Cancelar;
    public String Respuesta;

    modeloUsuarios modelousuarios ;
    HttpManager httpManager;
    MyTaskConection myTaskConection;
    MyAlerta alerta;
    ProgressDialog progreso = null;
    Cursor c;
    String CadenaFinal  = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevocliente_activity_nuevo_cliente);

        inputCedula    = (TextView)findViewById(R.id.inputCedula);
        inputNombre    = (TextView)findViewById(R.id.inputNombre);
        inputNegocio   = (TextView)findViewById(R.id.inputNegocio);
        inputBarrio    = (TextView)findViewById(R.id.inputBarrio);
        inputDireccion = (TextView)findViewById(R.id.inputDireccion);
        inputTelefono  = (TextView)findViewById(R.id.inputTelefono);
        inputSecuencia = (TextView)findViewById(R.id.inputSecuencia);

        Aceptar = (Button)findViewById(R.id.btnAceptar);
        Cancelar = (Button)findViewById(R.id.btnCancelar);

        Aceptar.setOnClickListener(this);
        Cancelar.setOnClickListener(this);

        Ciudades();
        TipoClientes();
        Rutas();

    }


     public void Ciudades(){
        sCiudad = (Spinner)findViewById(R.id.inputCiudad);
        mc = new modeloCiudades(this);
        Cursor c = mc.BuscartTabla(null,null,null);
        if(c.moveToFirst()){
            opcCiudad = new String[c.getCount()];
            codCiudad = new String[c.getCount()];
            int i =0;
            do{
                opcCiudad[i]=c.getString(2);
                codCiudad[i]=c.getString(1);
                i++;
            }while (c.moveToNext());

            adapterCiudad = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,opcCiudad);
            sCiudad.setAdapter(adapterCiudad);
        }

    }

    public void TipoClientes(){
        sTipo = (Spinner)findViewById(R.id.inputTipo);
        mtc = new modeloTClientes(this);
        Cursor c = mtc.BuscartTabla(null,null,null);
        if(c.moveToFirst()){
            opcTipo = new String[c.getCount()];
            codTipo = new String[c.getCount()];
            int i =0;
            do{
                opcTipo[i]=c.getString(2);
                codTipo[i]=c.getString(1);
                i++;
            }while (c.moveToNext());

            adapterTipo = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,opcTipo);
            sTipo.setAdapter(adapterTipo);
        }

    }

    public void Rutas(){
        sRuta = (Spinner)findViewById(R.id.inputRuta);
        mr = new modeloRutas(this);
        Cursor c = mr.BuscartTabla(null,null,null);
        if(c.moveToFirst()){
            opcRuta = new String[c.getCount()];
            codRuta = new String[c.getCount()];
            int i =0;
            do{
                opcRuta[i]=c.getString(2);
                codRuta[i]=c.getString(1);
                i++;
            }while (c.moveToNext());

            adapterRuta = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,opcRuta);
            sRuta.setAdapter(adapterRuta);
        }

    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.btnAceptar:
              if(VerificarCampos()){
                  CadenaFinal="";
                  CadenaFinal+=inputCedula.getText().toString().replace(' ','_')+"~";
                  CadenaFinal+=quitar(inputNombre.getText().toString().replace(' ','_'))+"~";
                  CadenaFinal+=quitar(inputNegocio.getText().toString().replace(' ','_'))+"~";
                  CadenaFinal+=codCiudad[ sCiudad.getSelectedItemPosition() ]+"~";
                  CadenaFinal+=quitar(inputBarrio.getText().toString().replace(' ', '_'))+"~";
                  CadenaFinal+=quitar(inputDireccion.getText().toString().replace(' ','X').replace('#','X'))+"~";
                  CadenaFinal+=quitar(inputTelefono.getText().toString().replace(' ','_'))+"~";
                  CadenaFinal+=codRuta[ sRuta.getSelectedItemPosition() ]+"~";
                  CadenaFinal+=codTipo[ sTipo.getSelectedItemPosition() ]+"~";
                  CadenaFinal+=inputSecuencia.getText().toString()+"~";

                  modelousuarios = new modeloUsuarios(this);
                  Cursor cc =  modelousuarios.BuscartTabla(null,null,null);
                  cc.moveToFirst();
                  String usua_codigo = cc.getString(1);
                  CadenaFinal+=usua_codigo;
                  Log.d("debug",CadenaFinal);
                  httpManager = new HttpManager();
                  myTaskConection = new MyTaskConection(this);
                  myTaskConection.execute();

              }else{
                  alerta = new MyAlerta(this, "Todos los campos son obligarorios");
                  alerta.setPositiveButton("Aceptar", null);
                  alerta.mostrar();
              }
          break;

          case R.id.btnCancelar:
              Intent i = new Intent(this, MenuPrincipal.class);
              startActivity(i);
          break;
      }
    }

    public boolean VerificarCampos(){
        boolean estanTodos = true;
        if(inputCedula.getText().toString().equals(""))   estanTodos = false;
        if(inputNombre.getText().toString().equals(""))   estanTodos = false;
        if(inputNegocio.getText().toString().equals(""))  estanTodos = false;
        if(inputBarrio.getText().toString().equals(""))   estanTodos = false;
        if(inputDireccion.getText().toString().equals(""))estanTodos = false;
        if(inputTelefono.getText().toString().equals("")) estanTodos = false;
        if(inputSecuencia.getText().toString().equals(""))estanTodos = false;
        return estanTodos;
    }

    private String quitar(String cadena){
        cadena = cadena.replace('ñ','n');
        cadena = cadena.replace('Ñ','N');
        cadena = cadena.replace('Á','A');
        cadena = cadena.replace('á','a');
        cadena = cadena.replace('É','E');
        cadena = cadena.replace('é','e');
        cadena = cadena.replace('Í','I');
        cadena = cadena.replace('í','i');
        cadena = cadena.replace('Ó','O');
        cadena = cadena.replace('ó','o');
        cadena = cadena.replace('Ú','U');
        cadena = cadena.replace('ú','u');
        cadena = cadena.replace('Ü','U');
        cadena = cadena.replace('ü','u');
        return cadena;
    }

    @Override
    public void HacerAntes() {
       progreso = ProgressDialog.show(this, "Enviando Cliente", "Espere unos segundos...", true, false);
    }

    @Override
    public void HacerDurante() {
        if(this.HayRed()){
            try {
                httpManager.post(getString(R.string.dominio) + "/mobile/syncup/crear_cliente/" + CadenaFinal);
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
            Respuesta = httpManager.getRespuesta().getAsString("TEXTO");
            System.out.println(Respuesta);
            if(Respuesta.equals("1")){
                this.mostrarAlerta("El telefono ya existe!");
            }else if(Respuesta.equals("0")){
                this.mostrarAlerta("Error al guardar!");
            }else{

            try {
                long clie_codigo = Long.parseLong(Respuesta);
                GuardarCliente(Respuesta);
                alerta = new MyAlerta(this,"Codigo de cliente "+Respuesta);
                progreso.dismiss();
                alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(NuevoCliente.this, DetalleCliente.class);
                        i.putExtra("clie_codigo",Respuesta);
                        i.putExtra("clie_nombre",inputNombre.getText().toString());
                        i.putExtra("clie_negocio",inputNegocio.getText().toString());
                        i.putExtra("clie_ruta",codRuta[ sRuta.getSelectedItemPosition()]);
                        startActivity(i);
                    }
                });
                alerta.mostrar();

            }catch ( NumberFormatException e){
                this.mostrarAlerta("Error en Codigo de Usuario");
            }

            }


        }else{
            this.mostrarAlerta("No se Pudo Conectar");
        }
    }

    public  void mostrarAlerta(String titulo){
        alerta = new MyAlerta(this, titulo);
        progreso.dismiss();
        alerta.setPositiveButton("Aceptar",null);
        alerta.mostrar();
    }

    public void GuardarCliente(String clie_codigo){
        ContentValues Cliente = new ContentValues();
        Cliente.put("clie_codigo", clie_codigo);
        Cliente.put("clie_nombre", inputNombre.getText().toString());
        Cliente.put("clie_negocio", inputNegocio.getText().toString());
        Cliente.put("clie_direccion",inputDireccion.getText().toString());
        Cliente.put("clie_barrio",inputBarrio.getText().toString());
        Cliente.put("clie_ciudad",opcCiudad[ sCiudad.getSelectedItemPosition() ]);
        Cliente.put("clie_telefono",inputTelefono.getText().toString());
        Cliente.put("clie_ruta",codRuta[ sRuta.getSelectedItemPosition() ]);
        Cliente.put("clie_visitado","N");
        Cliente.put("clie_cedula",inputCedula.getText().toString());
        mcliente = new modeloClientes(this);
        mcliente.InsertarTabla(Cliente);

    }
}
