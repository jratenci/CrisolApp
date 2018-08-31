package com.crisolapp.resumen;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.crisolapp.DB.modeloClientes;
import com.crisolapp.DB.modeloNovedades;
import com.crisolapp.DB.modeloPedidos;
import com.crisolapp.R;
import com.crisolapp.librerias.MyActividad;

import java.text.NumberFormat;

public class Resumen extends MyActividad {
    NumberFormat formato = NumberFormat.getCurrencyInstance();

    modeloClientes  modeloclientes;
    modeloPedidos   modelopedidos;
    modeloNovedades modelonovedades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resumen_activity_resumen);

        modeloclientes = new modeloClientes(this);
        modelopedidos = new modeloPedidos(this);
        modelonovedades = new modeloNovedades(this);

        Cursor cl = modeloclientes.buscar(null, null);
        Cursor pe = modelopedidos.numero_pedidos();
        Cursor no = modelonovedades.BuscartTabla(null, null, null);
        Cursor tp = modelopedidos.total_pedidos();
        Cursor pm = modelopedidos.total_por_marca();

        if (cl.moveToFirst()) {
            ((TextView) findViewById(R.id.txtClientes)).setText(String.valueOf(cl.getCount()));
        }

        if (pe.moveToFirst()) {
            ((TextView) findViewById(R.id.txtNoPedidos)).setText(String.valueOf(pe.getCount()));
        }

        if (no.moveToFirst()) {
            ((TextView) findViewById(R.id.txtNoNovedades)).setText(String.valueOf(no.getCount()));
        }

        if (tp.moveToFirst()) {
            if(tp.getString(0)!=null ) {
                ((TextView) findViewById(R.id.txtTPedidos)).setText(formato.format(Double.parseDouble(tp.getString(0))));
                ((TextView) findViewById(R.id.txtTSinIva)).setText(formato.format(Double.parseDouble(tp.getString(1))));
                ((TextView) findViewById(R.id.txtIVA)).setText(formato.format(Double.parseDouble(tp.getString(0)) - Double.parseDouble(tp.getString(1))));
                ((TextView) findViewById(R.id.txtDrop)).setText(formato.format(Double.parseDouble(tp.getString(1)) / pe.getCount()));
            }
        }
        if(pm.moveToFirst()){
            String pormarca = "";
           do{
              pormarca+=pm.getString(0)+": "+formato.format( Double.parseDouble(pm.getString(1)) )+"\n";
           }while (pm.moveToNext());

            ((TextView) findViewById(R.id.txtPorMarca)).setText(pormarca);
        }

    }

}
