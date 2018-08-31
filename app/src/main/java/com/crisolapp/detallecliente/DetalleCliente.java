package com.crisolapp.detallecliente;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.crisolapp.R;
import com.crisolapp.clientes.Clientes;
import com.crisolapp.clientes.InformacionCliente;
import com.crisolapp.librerias.MyActividad;
import com.crisolapp.novedad.TipoNovedad;
import com.crisolapp.tomapedido.TomaPedido;

public class DetalleCliente extends MyActividad implements AdapterView.OnItemClickListener {
    ListView lista;
    Bundle bolsa;
    TextView textViewCodigo;
    TextView textViewNombre;
    TextView textViewNegocio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detallecliente_activity_detalle_cliente);
        lista = (ListView)findViewById(R.id.listView);
        lista.setOnItemClickListener(this);

        AdaptadoDetalleCliente[] adaptadoDetalleCliente = new  AdaptadoDetalleCliente[]{
                new  AdaptadoDetalleCliente(R.drawable.realizarpedido,"Realizar Pedido","RP"),
                new  AdaptadoDetalleCliente(R.drawable.novedad,"Novedad","NO"),
                new  AdaptadoDetalleCliente(R.drawable.informacion,"Informacion","IN")

        };
        AdaptadorDetalleCliente adc = new AdaptadorDetalleCliente(this, adaptadoDetalleCliente);
        lista.setAdapter(adc);
        bolsa = getIntent().getExtras();
        textViewCodigo = (TextView)this.findViewById(R.id.textCodigoCliente);
        textViewNombre = (TextView)this.findViewById(R.id.textNombreCliente);
        textViewNegocio = (TextView)this.findViewById(R.id.textNegocioCliente);

        textViewCodigo.setText(bolsa.getString("clie_codigo"));
        textViewNombre.setText(bolsa.getString("clie_nombre"));
        textViewNegocio.setText(bolsa.getString("clie_negocio"));


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AdaptadoDetalleCliente item =  (AdaptadoDetalleCliente) parent.getAdapter().getItem(position);

        switch (item.getAbreviacion()){
            case "IN":
                Intent i = new Intent(this, InformacionCliente.class);
                i.putExtra("clie_codigo", bolsa.getString("clie_codigo"));
                startActivity(i);
            break;

            case "RP":
                Intent ip = new Intent(this, TomaPedido.class);
                ip.putExtras(bolsa);
                startActivity(ip);
            break;

            case "NO":
                Intent e = new Intent(this, TipoNovedad.class);
                e.putExtras(bolsa);
                startActivity(e);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, Clientes.class);
        //bolsa.putString("clie_ruta","sinruta");
        i.putExtras(bolsa);
        startActivity(i);
        return;
    }
}
