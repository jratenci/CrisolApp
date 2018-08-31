package com.crisolapp.mensajes;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.crisolapp.R;

/**
 * Created by jesusesmipastor on 17/02/2015.
 */
public class AdaptadorMensajes extends ArrayAdapter {
    Activity context;
    AdaptadoMensajes[] datos;

    public AdaptadorMensajes(Activity _context, AdaptadoMensajes[] _datos ) {
        super(_context, R.layout.adaptados_mensajes_lista,_datos);
        this.datos   = _datos;
        this.context = _context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.adaptados_mensajes_lista, null);

        TextView fecha = (TextView)item.findViewById(R.id.txtFecha);
        fecha.setText(datos[position].getMens_fecha());
        String Estado="Leido";

        if( datos[position].getMens_estado().equals("A") ){
            Estado = "Activo";
            TextView texto = (TextView) item.findViewById(R.id.txtTexto);
            texto.setText( datos[position].getMens_texto()+"..." );
            texto.setTextColor(Color.rgb(255, 11, 249));

            TextView estado = (TextView)item.findViewById(R.id.txtEstado);
            estado.setText(Estado);
            estado.setTextColor(Color.rgb(54, 137, 12));

        }else {
            TextView texto = (TextView) item.findViewById(R.id.txtTexto);
            texto.setText( datos[position].getMens_texto()+"..." );

            TextView estado = (TextView)item.findViewById(R.id.txtEstado);
            estado.setText(Estado);
        }

        return  item;
    }
}
