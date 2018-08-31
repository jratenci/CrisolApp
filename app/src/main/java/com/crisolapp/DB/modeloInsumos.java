package com.crisolapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.crisolapp.librerias.MyModelo;

/**
 * Created by jesusesmipastor on 31/01/2015.
 */
public class modeloInsumos extends MyModelo {
    public modeloInsumos(Context context) {
        super(context);
        this.NombreTabla="insumos";
    }

    public Cursor buscar( String where,String[] valoresWhere){
        String[] campos = new String[]{"insu_id","insu_codigo","insu_nombre","insu_precio","insu_iva","insu_marca,insu_inventario"};
        Cursor c =  this.BuscartTabla(campos,where,valoresWhere);
        return c;
    }


    public String ProcesarSyncup(ContentValues respuesta){
        String devolver="";
        if(respuesta.getAsString("COM").equals("OK")){
            if (!respuesta.getAsString("TEXTO").equals("0")){
                String[] datos = respuesta.getAsString("TEXTO").split("\\|");
                int numeroRegistros =Integer.parseInt(datos[0]);
                System.out.println("Registros en articulos..................."+numeroRegistros);
                if(numeroRegistros>0){
                    this.BorrarTabla();
                    for (int i=1;i<=numeroRegistros;i++){
                        String[] Registro = datos[i].split("\\*");
                        ContentValues Insumo = new ContentValues();
                        Insumo.put("insu_codigo",Registro[0]);
                        Insumo.put("insu_nombre",Registro[1]);
                        Insumo.put("insu_precio",Registro[2]);
                        Insumo.put("insu_iva",Registro[3]);
                        Insumo.put("insu_marca",Registro[4]);
                        Insumo.put("insu_inventario",Registro[5]);
                        this.InsertarTabla(Insumo);
                        Registro = null;
                    }

                    devolver = datos[0]+"/"+datos[0];

                }else {devolver="0/0";}
            }else {devolver="0/0";}
        }else{devolver="0/0";}
        return devolver;
    }
}
