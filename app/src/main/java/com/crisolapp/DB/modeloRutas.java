package com.crisolapp.DB;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.crisolapp.R;
import com.crisolapp.librerias.MyModelo;

/**
 * Created by jesusesmipastor on 31/01/2015.
 */
public class modeloRutas extends MyModelo{
    public modeloRutas(Context context) {
        super(context);
        this.NombreTabla="rutas";
    }

    public Cursor buscar( String where,String[] valoresWhere){
        String[] campos = new String[]{"ruta_id,ruta_codigo","ruta_nombre","ruta_dia"};
        Cursor c =  this.BuscartTabla(campos,where,valoresWhere);
        return c;
    }

    public String ProcesarSyncup(ContentValues respuesta){
        String devolver="";
        if(respuesta.getAsString("COM").equals("OK")){
            if (!respuesta.getAsString("TEXTO").equals("0")){
                String[] datos = respuesta.getAsString("TEXTO").split("\\|");
                int numeroRegistros =Integer.parseInt(datos[0]);
                System.out.println("Registros en rutas..................."+numeroRegistros);
                if(numeroRegistros>0){
                    this.BorrarTabla();
                    for (int i=1;i<=numeroRegistros;i++){
                        String[] Registro = datos[i].split("\\*");
                        ContentValues Ruta = new ContentValues();
                        Ruta.put("ruta_codigo",Registro[0]);
                        Ruta.put("ruta_nombre",Registro[1]);
                        Ruta.put("ruta_dia",Registro[2]);
                        this.InsertarTabla(Ruta);
                        Registro = null;
                    }

                    devolver = datos[0]+"/"+datos[0];

                }else {devolver="0/0";}
            }else {devolver="0/0";}
        }else{devolver="0/0";}
        return devolver;
    }
}
