package com.crisolapp.pedidospendientes;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.crisolapp.DB.modeloPedidos;
import com.crisolapp.DB.modeloUsuarios;
import com.crisolapp.R;
import com.crisolapp.comunicacion.HttpManager;
import com.crisolapp.comunicacion.InterfazComunicacion;
import com.crisolapp.comunicacion.MyTaskConection;
import com.crisolapp.librerias.MyActividad;
import com.crisolapp.librerias.MyAlerta;
import com.crisolapp.menuprincipal.MenuPrincipal;

public class EnviarPedidosPendientes extends MyActividad implements InterfazComunicacion {
    modeloPedidos modelopedidos ;
    modeloUsuarios modelousuarios ;
    boolean HayPedidosPorEnviar = false;
    HttpManager httpManager;
    MyTaskConection myTaskConection;
    MyAlerta alerta;
    ProgressDialog progreso = null;
    Cursor c;
    String CadenaPedido = "";
    String CadenaFinal  = "";
    String IN = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedidospendientes_activity_enviar);
        modelopedidos = new modeloPedidos(this);
        c = modelopedidos.buscar("pedi_estado=?",new String[]{"A"});
        if(c.moveToFirst()){
            CadenaPedido="";
            IN = "";
            do{
              CadenaPedido+=c.getString(5)+"~"+c.getString(1)+"~"+c.getString(2)+"B";
              IN += c.getString(0)+",";
            }while (c.moveToNext());

            CadenaPedido = CadenaPedido.substring(0,CadenaPedido.length()-1);
            IN = IN.substring(0,IN.length()-1);
            modelousuarios = new modeloUsuarios(this);
            Cursor cc =  modelousuarios.BuscartTabla(null,null,null);
            cc.moveToFirst();
            String usua_codigo = cc.getString(1);
                CadenaFinal = usua_codigo+"A"+CadenaPedido;
                httpManager = new HttpManager();
                myTaskConection = new MyTaskConection(this);
                myTaskConection.execute();

            }else{
                alerta = new MyAlerta(this, "No hay pedidos por enviar");
                alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
                    @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(EnviarPedidosPendientes.this, MenuPrincipal.class);
                    startActivity(i);
                }
            } );
            alerta.mostrar();
        }
    }

    @Override
    public void HacerAntes() {
        progreso = ProgressDialog.show(this, "Enviando Pedidos", "Espere unos segundos...", true, false);
    }
    @Override
    public void HacerDurante() {
        if(this.HayRed()){
            try {
                httpManager.post(getString(R.string.dominio) + "/mobile/syncup/guardar_combo/" + CadenaFinal);
            } catch (Exception e) {
                alerta = new MyAlerta(this, "Sin Red");
                progreso.dismiss();
                alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(EnviarPedidosPendientes.this, MenuPrincipal.class);
                        startActivity(i);
                    }
                } );
                alerta.mostrar();
                e.printStackTrace();
            }

        }
    }

    @Override
    public void HacerDespues() {
        if(this.httpManager.getRespuesta().getAsString("COM")=="OK") {
            String Respuesta = httpManager.getRespuesta().getAsString("TEXTO");
            System.out.println(Respuesta);
            if(Respuesta.equals("1")){
               modelopedidos.ModificarIN("pedi_estado","E","pedi_id",IN);
                this.mostrarAlerta("Pedidos Enviados Correctamente");
            }else{
               this.mostrarAlerta("No se Pudo Enviar");
            }
        }else{
           this.mostrarAlerta("No se Pudo Conectar");
        }
    }

    public  void mostrarAlerta(String titulo){
        alerta = new MyAlerta(this, titulo);
        progreso.dismiss();
        alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(EnviarPedidosPendientes.this, MenuPrincipal.class);
                startActivity(i);
            }
        });
        alerta.mostrar();
    }
}
