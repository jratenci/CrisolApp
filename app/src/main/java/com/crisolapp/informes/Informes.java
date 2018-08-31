package com.crisolapp.informes;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.crisolapp.R;
import com.crisolapp.librerias.MyActividad;

public class Informes extends MyActividad implements AdapterView.OnItemClickListener {
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informes_activity_informes);
        lista = (ListView)findViewById(R.id.list);
        lista.setOnItemClickListener(this);

        AdaptadoInformes[] adaptadoInformeses = new AdaptadoInformes[]{
                new AdaptadoInformes(R.drawable.money,"VENTAS","VE"),
                new AdaptadoInformes(R.drawable.pedidos,"PEDIDOS","PE"),
                new AdaptadoInformes(R.mipmap.devoluciones,"DEVOLUCIONES","DE")
        };
        AdaptadorInformes adi = new AdaptadorInformes(this, adaptadoInformeses);
        lista.setAdapter(adi);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AdaptadoInformes item =  (AdaptadoInformes) parent.getAdapter().getItem(position);

        switch (item.getAbreviacion()) {
            case "VE":
                Intent i = new Intent(this, InformeVentas.class);
                startActivity(i);
                break;

            case "PE":
                Intent p = new Intent(this, InformePedidos.class);
                startActivity(p);
                break;

            case "DE":
                Intent de = new Intent(this, InformeDevoluciones.class);
                startActivity(de);
                break;
        }

    }
}
