package com.crisolapp.DB;

import android.content.ContentValues;
import android.content.Context;

import com.crisolapp.librerias.MyModelo;

/**
 * Created by jesusesmipastor on 31/01/2015.
 */
public class modeloTClientes extends MyModelo {
    public modeloTClientes(Context context) {
        super(context);
        this.NombreTabla = "tipoclientes";
    }

    public String ProcesarSyncup(ContentValues respuesta){
        String devolver="";
        if(respuesta.getAsString("COM").equals("OK")){
            if (!respuesta.getAsString("TEXTO").equals("0")){
                String[] datos = respuesta.getAsString("TEXTO").split("\\|");
                int numeroRegistros =Integer.parseInt(datos[0]);
                System.out.println("Registros en tipo de clientes..................."+numeroRegistros);
                if(numeroRegistros>0){
                    this.BorrarTabla();
                    for (int i=1;i<=numeroRegistros;i++){
                        String[] Registro = datos[i].split("\\*");
                        ContentValues TCliente = new ContentValues();
                        TCliente.put("tcli_codigo",Registro[0]);
                        TCliente.put("tcli_nombre",Registro[1]);
                        this.InsertarTabla(TCliente);
                        Registro = null;
                    }

                    devolver = datos[0]+"/"+datos[0];

                }else {devolver="0/0";}
            }else {devolver="0/0";}
        }else{devolver="0/0";}
        return devolver;
    }
}
