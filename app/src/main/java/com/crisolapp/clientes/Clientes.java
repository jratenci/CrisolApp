package com.crisolapp.clientes;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.crisolapp.DB.modeloClientes;
import com.crisolapp.Principal;
import com.crisolapp.R;
import com.crisolapp.detallecliente.DetalleCliente;
import com.crisolapp.librerias.MyActividad;
import com.crisolapp.menuprincipal.AdaptadorMenuPrincipal;
import com.crisolapp.menuprincipal.MenuPrincipal;
import com.crisolapp.rutas.Rutas;

import java.util.ArrayList;
import java.util.Locale;

public class Clientes extends MyActividad implements AdapterView.OnItemClickListener,TextWatcher{
    modeloClientes modeloclientes ;
    ListView lista;
    AdaptadoClientes[] adaptadoClientes=null;
    TextView inputCodigo;
    TextView inputNombre;
    TextView inputNegocio;
    AdaptadorClientes adc;
    Bundle bolsa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientes_activity_clientes);
        lista = (ListView)findViewById(R.id.clieList);
        lista.setOnItemClickListener(this);
        inputCodigo = (TextView)findViewById(R.id.inputCodigo);
        inputNombre = (TextView)findViewById(R.id.inputNombre);
        inputNegocio = (TextView)findViewById(R.id.inputNegocio);

        modeloclientes = new modeloClientes(this);

        bolsa = getIntent().getExtras();
        Cursor c;
        if(bolsa.getString("clie_ruta").equals("sinruta")) {
             c = modeloclientes.buscar(null, null);
        }else{
             String where ="clie_ruta=?";
             String []valores_where = new String[]{bolsa.getString("clie_ruta")};
             c = modeloclientes.buscar(where ,valores_where);
        }

        if(c.moveToFirst()){
            int i=0;
            System.out.println("cantidad.........."+c.getCount());
            adaptadoClientes = new AdaptadoClientes[c.getCount()];
            do{
            adaptadoClientes[i] = new AdaptadoClientes(c.getString(1),c.getString(2),c.getString(3),c.getString(9));
                System.out.println(c.getString(1)+"--"+c.getString(2)+"----"+c.getString(3));
            i++;
            }while (c.moveToNext());

            adc = new AdaptadorClientes(this, adaptadoClientes);
            lista.setAdapter(adc);
        }


        inputCodigo.addTextChangedListener(this);
        inputNombre.addTextChangedListener(this);
        inputNegocio.addTextChangedListener(this);
        setTitle("Clientes["+  String.valueOf(c.getCount())+"]");
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AdaptadoClientes item =  (AdaptadoClientes) parent.getAdapter().getItem(position);
        Intent i = new Intent(this, DetalleCliente.class);
        i.putExtra("clie_codigo",item.getClie_codigo());
        i.putExtra("clie_nombre",item.getClie_nombre());
        i.putExtra("clie_negocio",item.getClie_negocio());
        i.putExtra("clie_ruta",bolsa.getString("clie_ruta"));
        startActivity(i);

    }

    public void filtro(String codigo,String nombre,String negocio){
    if(adaptadoClientes!=null) {
        codigo = codigo.toLowerCase();
        nombre = nombre.toLowerCase(Locale.getDefault());
        negocio = negocio.toLowerCase(Locale.getDefault());
        System.out.println("coigo " + codigo);
        int count = 0;
        if (nombre.length() > 0 || codigo.length() > 0 || negocio.length() > 0) {
            for (AdaptadoClientes ac : adaptadoClientes) {
                if (((ac.getClie_codigo().toLowerCase().indexOf(codigo) != -1) && (codigo.length() > 0))
                        || ((ac.getClie_nombre().toLowerCase().indexOf(nombre) != -1) && (nombre.length() > 0))
                        || ((ac.getClie_negocio().toLowerCase().indexOf(negocio) != -1) && (negocio.length() > 0))
                        ) {
                    System.out.println(ac.getClie_codigo().toLowerCase(Locale.getDefault()));
                    System.out.println(ac.getClie_nombre().toLowerCase());

                    count++;
                }
            }
            AdaptadoClientes[] aclie = new AdaptadoClientes[count];
            int contador = 0;
            for (AdaptadoClientes ac : adaptadoClientes) {
                if ((ac.getClie_codigo().toLowerCase().indexOf(codigo) != -1) && (codigo.length() > 0)
                        || ((ac.getClie_nombre().toLowerCase().indexOf(nombre) != -1) && (nombre.length() > 0))
                        || ((ac.getClie_negocio().toLowerCase().indexOf(negocio) != -1) && (negocio.length() > 0))
                        ) {
                    aclie[contador] = ac;
                    contador++;
                }
            }

            adc = new AdaptadorClientes(this, aclie);
            lista.setAdapter(adc);
            setTitle("Clientes[" + String.valueOf(count) + "]");
        } else {
            adc = new AdaptadorClientes(this, adaptadoClientes);
            lista.setAdapter(adc);
            setTitle("Clientes[" + String.valueOf(adaptadoClientes.length) + "]");
        }

    }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Clientes.this.filtro(this.inputCodigo.getText().toString(),this.inputNombre.getText().toString(),this.inputNegocio.getText().toString());
    }
    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onBackPressed() {
        if(bolsa.getString("clie_ruta").equals("sinruta")) {
            Intent i = new Intent(this, MenuPrincipal.class);
            i.putExtras(bolsa);
            startActivity(i);
            return;
        }else{
            Intent i = new Intent(this, Rutas.class);
            i.putExtras(bolsa);
            startActivity(i);
            return;
        }
    }
}
