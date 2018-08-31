package com.crisolapp.menuprincipal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.crisolapp.R;

/**
 * Created by jesusesmipastor on 02/02/2015.
 */
public class AdaptadorMenuPrincipal extends ArrayAdapter {

    Activity context;
    AdaptadoMenuPrincipal[] datos;

    public AdaptadorMenuPrincipal(Activity _context, AdaptadoMenuPrincipal[] _datos ) {
        super(_context,R.layout.adaptados_menuprincipal_lista,_datos);
        this.datos   = _datos;
        this.context = _context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.adaptados_menuprincipal_lista,null);

        TextView  titulo = (TextView)item.findViewById(R.id.menutitulo);
        titulo.setText(datos[position].getTitulo());

        TextView  numero = (TextView)item.findViewById(R.id.menunumero);
        numero.setText(datos[position].getNumero() );

        ImageView imagen = (ImageView)item.findViewById(R.id.menuimagen);
        imagen.setImageResource(datos[position].getImg());
        return item;

    }
}
