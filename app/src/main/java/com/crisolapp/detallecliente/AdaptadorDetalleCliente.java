package com.crisolapp.detallecliente;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.crisolapp.R;
import com.crisolapp.menuprincipal.AdaptadoMenuPrincipal;

/**
 * Created by jesusesmipastor on 05/02/2015.
 */
public class AdaptadorDetalleCliente extends ArrayAdapter {
    Activity context;
    AdaptadoDetalleCliente[] datos;

    public AdaptadorDetalleCliente(Activity _context, AdaptadoDetalleCliente[] _datos ) {
        super(_context, R.layout.adaptados_detallecliente_lista,_datos);
        this.datos   = _datos;
        this.context = _context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.adaptados_detallecliente_lista,null);
        TextView titulo = (TextView)item.findViewById(R.id.detalletitulo);
        titulo.setText(datos[position].getTitulo());

        ImageView imagen = (ImageView)item.findViewById(R.id.detalleimagen);
        imagen.setImageResource(datos[position].getImg());
        return item;

    }
}
