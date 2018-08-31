package com.crisolapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.crisolapp.librerias.MyModelo;

/**
 * Created by jesusesmipastor on 31/01/2015.
 */
public class modeloClientes extends MyModelo {
    public modeloClientes(Context context) {
        super(context);
        this.NombreTabla="clientes";
    }

    public Cursor buscar( String where,String[] valoresWhere){
      String[] campos = new String[]{"clie_id,clie_codigo","clie_nombre","clie_negocio","clie_direccion","clie_barrio","clie_ciudad","clie_telefono","clie_ruta","clie_visitado","clie_cedula"};
      Cursor c =  this.BuscartTabla(campos,where,valoresWhere);
      return c;
    }

    public String ProcesarSyncup(ContentValues respuesta){
        String devolver="";
        if(respuesta.getAsString("COM").equals("OK")){
            if (!respuesta.getAsString("TEXTO").equals("0")){
                String[] datos = respuesta.getAsString("TEXTO").split("\\|");
                int numeroRegistros =Integer.parseInt(datos[0]);
                System.out.println("Registros en clientes..................."+numeroRegistros);
                if(numeroRegistros>0){
                    this.BorrarTabla();
                    for (int i=numeroRegistros;i>=1;i--){
                        String[] Registro = datos[i].split("\\*");
                        System.out.println("-----"+datos[i]);
                        ContentValues Cliente = new ContentValues();
                        Cliente.put("clie_codigo", Registro[0]);
                        Cliente.put("clie_nombre", Registro[1]);
                        Cliente.put("clie_negocio", Registro[2]);
                        Cliente.put("clie_direccion",Registro[3]);
                        Cliente.put("clie_barrio",Registro[4]);
                        Cliente.put("clie_ciudad",Registro[5]);
                        Cliente.put("clie_telefono",Registro[6]);
                        Cliente.put("clie_ruta",Registro[7]);
                        Cliente.put("clie_visitado",Registro[8]);
                        Cliente.put("clie_cedula",Registro[9]);
                        this.InsertarTabla(Cliente);
                        Registro = null;
                    }

                    devolver = datos[0]+"/"+datos[0];

                }else {devolver="0/0";}
            }else {devolver="0/0";}
        }else{devolver="0/0";}
        return devolver;
    }
}
