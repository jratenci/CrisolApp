package com.crisolapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.crisolapp.DB.modeloNovedades;
import com.crisolapp.DB.modeloPedidos;
import com.crisolapp.DB.modeloUsuarios;
import com.crisolapp.librerias.MyActividad;
import com.crisolapp.librerias.MyAlerta;
import com.crisolapp.menuprincipal.MenuPrincipal;

public class Terminar extends MyActividad {
    modeloPedidos   modelopedidos ;
    modeloNovedades modelonovedades ;
    MyAlerta alerta;

    boolean terminar;
    String Mensaje="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terminar_activity_terminar);
        alerta = new MyAlerta(this, "Realmente desea Terminar Labores?");
        alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                terminar = true;
                modelopedidos = new modeloPedidos(Terminar.this);
                modelonovedades = new modeloNovedades(Terminar.this);
               /**
                Cursor p = modelopedidos.buscar("pedi_estado=?",new String[]{"A"});
                Cursor n = modelonovedades.BuscartTabla(null,"nove_estado=?",new String[]{"A"});

                if(p.moveToFirst()){
                  Mensaje = "Hay Pedidos por Enviar"  ;
                  terminar = false;
                }

                if(n.moveToFirst()){
                    Mensaje = "Hay Novedades por Enviar";
                    terminar = false;
                }

                if(p.moveToFirst() && n.moveToFirst()){
                    Mensaje = "Hay Pedidos y Novedades por Enviar";
                    terminar = false;
                }
                 */
    terminar=true;
                if (terminar){
                    modelopedidos.LimpiarDB();
                    Intent i = new Intent(Terminar.this, Principal.class);
                    startActivity(i);
                }else{
                    alerta = new MyAlerta(Terminar.this, Mensaje);
                    alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(Terminar.this, MenuPrincipal.class);
                            startActivity(i);
                        }
                    } );
                    alerta.mostrar();

                }

            }
        } );

        alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Terminar.this, MenuPrincipal.class);
                startActivity(i);
            }
        });
        alerta.mostrar();

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
