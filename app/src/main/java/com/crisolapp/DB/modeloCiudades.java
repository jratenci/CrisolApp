package com.crisolapp.DB;

import android.content.ContentValues;
import android.content.Context;

import com.crisolapp.librerias.MyModelo;

/**
 * Created by jesusesmipastor on 31/01/2015.
 */
public class modeloCiudades extends MyModelo {
    public modeloCiudades(Context context) {
        super(context);
        this.NombreTabla="ciudades";
    }

    public String ProcesarSyncup(ContentValues respuesta){
        String devolver="";
        if(respuesta.getAsString("COM").equals("OK")){
            if (!respuesta.getAsString("TEXTO").equals("0")){
                String[] datos = respuesta.getAsString("TEXTO").split("\\|");
                int numeroRegistros =Integer.parseInt(datos[0]);
                System.out.println("Registros en ciudades..................."+numeroRegistros);
                if(numeroRegistros>0){
                    this.BorrarTabla();
                    for (int i=1;i<=numeroRegistros;i++){
                        String[] Registro = datos[i].split("\\*");
                        ContentValues Ciudad = new ContentValues();
                        Ciudad.put("ciud_codigo",Registro[0]);
                        Ciudad.put("ciud_nombre",Registro[1]);
                        this.InsertarTabla(Ciudad);
                        Registro = null;
                    }

                    devolver = datos[0]+"/"+datos[0];

                }else {devolver="0/0";}
            }else {devolver="0/0";}
        }else{devolver="0/0";}
        return devolver;
    }
}
