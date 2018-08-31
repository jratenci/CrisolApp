package com.crisolapp.clientes;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.crisolapp.DB.modeloClientes;
import com.crisolapp.R;
import com.crisolapp.librerias.MyActividad;

public class InformacionCliente extends MyActividad {
    modeloClientes modeloclientes;
    Bundle bolsa;

    TextView codigo;
    TextView nombre;
    TextView negocio;
    TextView direccion;
    TextView barrio;
    TextView ciudad;
    TextView telefono;
    TextView ruta;
    TextView cedula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detallecliente_activity_informacion_cliente);
        modeloclientes = new modeloClientes(this);

        codigo   = (TextView)findViewById(R.id.txtinfoCodigo);
        nombre   = (TextView)findViewById(R.id.textinfoNombre);
        negocio  = (TextView)findViewById(R.id.textinfoNegocio);
        direccion= (TextView)findViewById(R.id.textinfoDireccion);
        barrio   = (TextView)findViewById(R.id.textinfoBarrio);
        ciudad   = (TextView)findViewById(R.id.textinfoCiudad);
        telefono = (TextView)findViewById(R.id.textinfoTelefono);
        ruta     = (TextView)findViewById(R.id.textinfoRuta);
        cedula   = (TextView)findViewById(R.id.textinfoCedula);

        bolsa = getIntent().getExtras();

        String where ="clie_codigo=?";
        String []valores_where = new String[]{bolsa.getString("clie_codigo")};
        Cursor c = modeloclientes.buscar(where ,valores_where);

        if(c.moveToFirst()){
         codigo.setText(c.getString(1));
         nombre.setText(c.getString(2));
         negocio.setText(c.getString(3));
         direccion.setText(c.getString(4));
         barrio.setText(c.getString(5));
         ciudad.setText(c.getString(6));
         telefono.setText(c.getString(7));
         ruta.setText(c.getString(8));
         cedula.setText(c.getString(10));
        }

    }

}
