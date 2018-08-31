package com.crisolapp.tomapedido;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.crisolapp.R;
import com.crisolapp.clientes.AdaptadoClientes;

import java.text.Format;
import java.text.NumberFormat;

/**
 * Created by jesusesmipastor on 06/02/2015.
 */
public class AdaptadorPedidos extends ArrayAdapter {
    Activity context;
    AdaptadoPedidos[] datos;

    public AdaptadorPedidos(Activity _context, AdaptadoPedidos[] _datos ) {
        super(_context, R.layout.adaptados_pedidos_lista,_datos);
        this.datos   = _datos;
        this.context = _context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.adaptados_pedidos_lista,null);

        TextView codigo = (TextView)item.findViewById(R.id.txtCodigo);
        codigo.setText(datos[position].getPedi_insumo() );

        TextView nombre = (TextView)item.findViewById(R.id.txtNombre);
        nombre.setText(datos[position].getNombre_insumo());

        String Sestado  = datos[position].getPedi_estado();

        if(Sestado.equals("A")){
            Sestado = "Activo";
            TextView estado = (TextView)item.findViewById(R.id.txtEstado);
            estado.setText(Sestado);
        }else{
            TextView estado = (TextView)item.findViewById(R.id.txtEstado);
            Sestado = "Enviado";
            estado.setTextColor(Color.rgb(255, 154, 249));
            estado.setText(Sestado);
        }



        TextView precio = (TextView)item.findViewById(R.id.txtPrecio);
        double cantidad = Double.parseDouble(datos[position].getPedi_cantidad());
        double precioUnitario = datos[position].getPedi_precioiva();
        double total = cantidad*precioUnitario;
        NumberFormat formato = NumberFormat.getCurrencyInstance();
        precio.setText(datos[position].getPedi_cantidad()+" X "+formato.format(precioUnitario)+" = " + formato.format(total) );

        return item;

    }
}
