package com.crisolapp.novedad;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.crisolapp.R;
import com.crisolapp.tomapedido.AdaptadoPedidos;

/**
 * Created by jesusesmipastor on 11/02/2015.
 */
public class AdaptadorTipoNovedad extends ArrayAdapter {
    Activity context;
    AdaptadoTipoNovedad[] datos;

    public AdaptadorTipoNovedad(Activity _context, AdaptadoTipoNovedad[] _datos ) {
        super(_context, R.layout.adaptados_tiponovedad_lista,_datos);
        this.datos   = _datos;
        this.context = _context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.adaptados_tiponovedad_lista, null);

        TextView codigo = (TextView)item.findViewById(R.id.txtCodigo);
        codigo.setText(datos[position].getTnov_codigo());

        if(datos[position].isColor() ){
            TextView nombre = (TextView) item.findViewById(R.id.txtNombre);
            nombre.setText(datos[position].getTnov_nombre());
            nombre.setTextColor(Color.rgb(255, 11, 249));
        }else {
            TextView nombre = (TextView) item.findViewById(R.id.txtNombre);
            nombre.setText(datos[position].getTnov_nombre());
        }
        return  item;
    }
}
