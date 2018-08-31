package com.crisolapp.rutas;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.crisolapp.DB.modeloRutas;
import com.crisolapp.R;
import com.crisolapp.clientes.AdaptadoClientes;
import com.crisolapp.clientes.Clientes;
import com.crisolapp.librerias.MyActividad;
import com.crisolapp.menuprincipal.MenuPrincipal;

public class Rutas extends MyActividad implements AdapterView.OnItemClickListener{
    modeloRutas modelorutas ;
    ListView lista;
    AdaptadoRutas[] adaptadoRutas=null;
    AdaptadorRutas adr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rutas_activity_rutas);
        lista = (ListView)findViewById(R.id.rutaList);
        lista.setOnItemClickListener(this);
        modelorutas = new modeloRutas(this);
        Cursor c  = modelorutas.buscar(null, null);
        if(c.moveToFirst()){
            System.out.println("pasa por aqui");
            int i=0;
            System.out.println("cantidad.........."+c.getCount());
            adaptadoRutas = new AdaptadoRutas[c.getCount()];
            do{
                adaptadoRutas[i] = new AdaptadoRutas(c.getString(1),c.getString(2),c.getString(3));
                System.out.println(c.getString(1)+"--"+c.getString(2)+"----"+c.getString(3));
                i++;
            }while (c.moveToNext());

            adr = new AdaptadorRutas(this, adaptadoRutas);
            lista.setAdapter(adr);
            setTitle("Rutas["+  String.valueOf(c.getCount())+"]");
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AdaptadoRutas item =  (AdaptadoRutas) parent.getAdapter().getItem(position);
        Intent i = new Intent(this, Clientes.class);
        i.putExtra("clie_ruta",item.getRuta_codigo());
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
            Intent i = new Intent(this, MenuPrincipal.class);
            startActivity(i);
            return;

    }
}
