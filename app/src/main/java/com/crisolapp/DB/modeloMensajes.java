package com.crisolapp.DB;

import android.content.ContentValues;
import android.content.Context;

import com.crisolapp.librerias.MyModelo;

/**
 * Created by jesusesmipastor on 31/01/2015.
 */
public class modeloMensajes extends MyModelo {
    public modeloMensajes(Context context) {
        super(context);
        this.NombreTabla="mensajes";
    }

    public String ProcesarSyncup(ContentValues respuesta){
        String devolver="";
        if(respuesta.getAsString("COM").equals("OK")){
            if (!respuesta.getAsString("TEXTO").equals("0")){
                String[] datos = respuesta.getAsString("TEXTO").split("\\|");
                int numeroRegistros =Integer.parseInt(datos[0]);
                System.out.println("Registros en mesanjes..................."+numeroRegistros);
                if(numeroRegistros>0){
                    for (int i=1;i<=numeroRegistros;i++){
                        String[] Registro = datos[i].split("\\*");
                        ContentValues Mensaje = new ContentValues();
                        Mensaje.put("mens_idd",Registro[0]);
                        Mensaje.put("mens_texto",Registro[1]);
                        Mensaje.put("mens_texto2",Registro[2]);
                        Mensaje.put("mens_texto3",Registro[3]);
                        Mensaje.put("mens_texto4",Registro[4]);
                        Mensaje.put("mens_texto5",Registro[5]);
                        Mensaje.put("mens_fecha",Registro[6]);
                        Mensaje.put("mens_estado",Registro[7]);
                        this.InsertarTabla(Mensaje);
                        Registro = null;
                    }

                    devolver = datos[0]+"/"+datos[0];

                }else {devolver="0/0";}
            }else {devolver="0/0"; System.out.println("Registros en mesanjes...................0");}
        }else{devolver="0/0";}
        return devolver;
    }
}
