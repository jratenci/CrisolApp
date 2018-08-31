package com.crisolapp.tomapedido;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.crisolapp.R;

import java.text.NumberFormat;

/**
 * Created by jesusesmipastor on 06/02/2015.
 */
public class AdaptadorArticulos extends ArrayAdapter {
    Activity context;
    AdaptadoArticulos[] datos;

    public AdaptadorArticulos(Activity _context, AdaptadoArticulos[] _datos) {
        super(_context, R.layout.adaptados_articulos_lista,_datos);
        this.datos   = _datos;
        this.context = _context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.adaptados_articulos_lista,null);

        TextView codigo = (TextView)item.findViewById(R.id.txtCodigo);
        codigo.setText(datos[position].getInsu_codigo() );

        TextView nombre = (TextView)item.findViewById(R.id.txtNombre);
        nombre.setText(datos[position].getInsu_nombre());

        TextView inventario = (TextView)item.findViewById(R.id.txtInventario);
        inventario.setText(datos[position].getInsu_inventario());

        TextView precio = (TextView)item.findViewById(R.id.txtPrecio);

        double ValorPrecio =datos[position].getInsu_precioConIva();
        NumberFormat formato = NumberFormat.getCurrencyInstance();


        precio.setText( formato.format(ValorPrecio) );

        return item;

    }
}
