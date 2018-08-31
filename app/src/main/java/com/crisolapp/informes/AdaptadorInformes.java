package com.crisolapp.informes;

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
public class AdaptadorInformes extends ArrayAdapter {

    Activity context;
    AdaptadoInformes[] datos;

    public AdaptadorInformes(Activity _context, AdaptadoInformes[] _datos) {
        super(_context,R.layout.adaptados_informes_lista,_datos);
        this.datos   = _datos;
        this.context = _context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.adaptados_informes_lista,null);

        TextView  titulo = (TextView)item.findViewById(R.id.menutitulo);
        titulo.setText(datos[position].getTitulo());

        ImageView imagen = (ImageView)item.findViewById(R.id.menuimagen);
        imagen.setImageResource(datos[position].getImg());
        return item;

    }
}
