package com.crisolapp.mensajes;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.crisolapp.DB.modeloMensajes;
import com.crisolapp.R;
import com.crisolapp.librerias.MyActividad;
import com.crisolapp.menuprincipal.MenuPrincipal;

public class Mensajes extends MyActividad implements AdapterView.OnItemClickListener {
    modeloMensajes modelomensajes;
    ListView lista;
    AdaptadoMensajes[] adaptadomensajes;
    AdaptadorMensajes adm;
    Bundle bolsa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mensajes_activity_mensajes);
        lista = (ListView)findViewById(R.id.list);
        lista.setOnItemClickListener(this);
        modelomensajes = new modeloMensajes(this);
        Cursor c = modelomensajes.BuscartTabla(null, null, null);

        if(c.moveToFirst()) {
            System.out.println("pasa por estos lares");
            adaptadomensajes = new AdaptadoMensajes[c.getCount()];
            int i = 0;
            do{
                adaptadomensajes[i] = new AdaptadoMensajes(c.getString(0),c.getString(2),c.getString(7),c.getString(8) );
                i++;
            }while(c.moveToNext());

            adm = new AdaptadorMensajes(this, adaptadomensajes);
            lista.setAdapter(adm);
        }

     }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MenuPrincipal.class);
        startActivity(i);
        return;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AdaptadoMensajes item =  (AdaptadoMensajes) parent.getAdapter().getItem(position);
        Intent i = new Intent(this, DetalleMensaje.class);
        i.putExtra("mens_id",item.getMens_id());
        startActivity(i);
    }
}
