package com.crisolapp.DB;

import android.content.ContentValues;
import android.content.Context;

import com.crisolapp.librerias.MyModelo;

/**
 * Created by jesusesmipastor on 31/01/2015.
 */
public class modeloTNovedades extends MyModelo {
    public modeloTNovedades(Context context) {
        super(context);
        this.NombreTabla="tiponovedades";
    }

    public String ProcesarSyncup(ContentValues respuesta){
        String devolver="";
        if(respuesta.getAsString("COM").equals("OK")){
            if (!respuesta.getAsString("TEXTO").equals("0")){
                String[] datos = respuesta.getAsString("TEXTO").split("\\|");
                int numeroRegistros =Integer.parseInt(datos[0]);
                System.out.println("Registros en tipo de novedades..................."+numeroRegistros);
                if(numeroRegistros>0){
                    this.BorrarTabla();
                    for (int i=1;i<=numeroRegistros;i++){
                        String[] Registro = datos[i].split("\\*");
                        ContentValues TNovedad = new ContentValues();
                        TNovedad.put("tnov_codigo",Registro[0]);
                        TNovedad.put("tnov_nombre",Registro[1]);
                        this.InsertarTabla(TNovedad);
                        Registro = null;
                    }

                    devolver = datos[0]+"/"+datos[0];

                }else {devolver="0/0";}
            }else {devolver="0/0";}
        }else{devolver="0/0";}
        return devolver;
    }
}
