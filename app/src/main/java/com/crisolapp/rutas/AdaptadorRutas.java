package com.crisolapp.rutas;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.crisolapp.R;
import com.crisolapp.clientes.AdaptadoClientes;

/**
 * Created by jesusesmipastor on 04/02/2015.
 */
public class AdaptadorRutas  extends ArrayAdapter {
    Activity context;
    AdaptadoRutas[] datos;

    public AdaptadorRutas(Activity _context, AdaptadoRutas[] _datos ) {
        super(_context, R.layout.adaptados_rutas_lista,_datos);
        this.datos   = _datos;
        this.context = _context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.adaptados_rutas_lista,null);

        TextView codigo = (TextView)item.findViewById(R.id.textRutaCodigo);
        codigo.setText("Codigo: "+datos[position].getRuta_codigo());

        TextView nombre = (TextView)item.findViewById(R.id.textRutaNombre);
        nombre.setText("Nombre: "+ datos[position].getRuta_nombre());

        TextView dia = (TextView)item.findViewById(R.id.textRutaDia);
        dia.setText("Dia: "+ datos[position].getRuta_dia());

        return item;

    }
}
