package com.crisolapp.menuprincipal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.crisolapp.DB.modeloMensajes;
import com.crisolapp.DB.modeloNovedades;
import com.crisolapp.DB.modeloPedidos;
import com.crisolapp.Principal;
import com.crisolapp.R;
import com.crisolapp.Terminar;
import com.crisolapp.clientes.Clientes;
import com.crisolapp.informes.Informes;
import com.crisolapp.librerias.MyActividad;
import com.crisolapp.mensajes.Mensajes;
import com.crisolapp.novedadespendientes.NovedadesPendientes;
import com.crisolapp.nuevocliente.NuevoCliente;
import com.crisolapp.pedidospendientes.EnviarPedidosPendientes;
import com.crisolapp.resumen.Resumen;
import com.crisolapp.rutas.Rutas;
import com.crisolapp.syncproductos.SyncProductos;
import com.crisolapp.util.Util;

public class MenuPrincipal extends MyActividad implements AdapterView.OnItemClickListener {
    ListView lista;
    modeloPedidos   modelopedidos;
    modeloNovedades modelonovedades;
    modeloMensajes  modelomensajes;

    String NumeroMensajes,NumeroPedidosPendientes,NumeroNovedadesPendientes="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity_menu_principal);
        lista = (ListView)findViewById(R.id.menuLista);
        lista.setOnItemClickListener(this);

        modelonovedades = new modeloNovedades(this);
        Cursor c = modelonovedades.BuscartTabla(null,"nove_estado=?", new String[]{"A"} );
        if(c.getCount()>0){
            NumeroNovedadesPendientes = String.valueOf(c.getCount());
        }

        modelopedidos = new modeloPedidos(this);
        c = modelopedidos.BuscartTabla(null,"pedi_estado=?", new String[]{"A"} );
        if(c.getCount()>0){
            NumeroPedidosPendientes = String.valueOf(c.getCount());
        }

        modelomensajes = new modeloMensajes(this);
        c = modelomensajes.BuscartTabla(null,"mens_estado=?", new String[]{"A"} );
        if(c.getCount()>0){
            NumeroMensajes = String.valueOf(c.getCount());
        }


        AdaptadoMenuPrincipal[] adaptadoMenuPrincipal = new AdaptadoMenuPrincipal[]{
              new AdaptadoMenuPrincipal(R.drawable.buscar_cliente,"Buscar Cliente","BC",""),
              new AdaptadoMenuPrincipal(R.drawable.rutas,"Rutas","RU",""),
              new AdaptadoMenuPrincipal(R.drawable.pedidos_pendientes,"Pedidos Pendientes","PP",NumeroPedidosPendientes),
              new AdaptadoMenuPrincipal(R.drawable.novedades_pendientes,"Novedades Pendientes","NP",NumeroNovedadesPendientes),
              new AdaptadoMenuPrincipal(R.drawable.nuevo_cliente,"Nuevo Cliente","NC",""),
              new AdaptadoMenuPrincipal(R.drawable.resumen_dia,"Resumen del Dia","RE",""),
              new AdaptadoMenuPrincipal(R.drawable.mensajes,"Mensajes","ME",NumeroMensajes),
              new AdaptadoMenuPrincipal(R.drawable.sync_productos,"Sincronizar Productos","SP",""),
              new AdaptadoMenuPrincipal(R.drawable.informes,"Informes","IN",""),
              new AdaptadoMenuPrincipal(R.drawable.terminar,"Terminar Labores","TL","")
        };
        AdaptadorMenuPrincipal adm = new AdaptadorMenuPrincipal(this, adaptadoMenuPrincipal);
        lista.setAdapter(adm);
        Util.SyncData(this, 600000);
    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(this, Principal.class);
        startActivity(i);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AdaptadoMenuPrincipal item =  (AdaptadoMenuPrincipal) parent.getAdapter().getItem(position);

        switch (item.getAbreviacion()){
            case "BC":
                Intent i = new Intent(this, Clientes.class);
                i.putExtra("clie_ruta", "sinruta");
                startActivity(i);
            break;

            case "RU":
                Intent ir = new Intent(this, Rutas.class);
                startActivity(ir);
                break;

            case "PP":
                Intent pp = new Intent(this, EnviarPedidosPendientes.class);
                startActivity(pp);
                break;

            case "NP":
               Intent np = new Intent(this,NovedadesPendientes.class);
               startActivity(np);
               break;

            case "NC":
                Intent nc = new Intent(this,NuevoCliente.class);
                startActivity(nc);
                break;

            case "RE":
                Intent re = new Intent(this,Resumen.class);
                startActivity(re);
                break;

            case "ME":
                Intent me = new Intent(this,Mensajes.class);
                startActivity(me);
                break;

            case "SP":
                Intent sp = new Intent(this,SyncProductos.class);
                startActivity(sp);
                break;

            case "IN":
                Intent in = new Intent(this,Informes.class);
                startActivity(in);
                break;

            case "TL":
                Intent tl = new Intent(this,Terminar.class);
                startActivity(tl);
                break;

    }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }


}
