package com.crisolapp.clientes;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.crisolapp.R;

import java.util.Locale;

/**
 * Created by jesusesmipastor on 03/02/2015.
 */
public class AdaptadorClientes extends ArrayAdapter{
    Activity context;
    AdaptadoClientes[] datos;

    public  AdaptadorClientes(Activity _context, AdaptadoClientes[] _datos ) {
        super(_context, R.layout.adaptados_clientes_lista,_datos);
        this.datos   = _datos;
        this.context = _context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.adaptados_clientes_lista,null);

        String Sestado  = datos[position].getClie_visitado();
        if(Sestado.equals("N")){
            TextView codigo = (TextView)item.findViewById(R.id.inputCodigo);
            codigo.setText(datos[position].getClie_codigo());
        }else{
            TextView codigo = (TextView)item.findViewById(R.id.inputCodigo);
            codigo.setText(datos[position].getClie_codigo());
            codigo.setTextColor(Color.rgb(255,11, 249));
        }

        TextView nombre = (TextView)item.findViewById(R.id.inputNombre);
        nombre.setText(datos[position].getClie_nombre());

        TextView negocio = (TextView)item.findViewById(R.id.textNegocio);
        negocio.setText(datos[position].getClie_negocio());

        return item;

    }



}
