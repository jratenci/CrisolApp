package com.crisolapp.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Vibrator;

import com.crisolapp.DB.modeloMensajes;
import com.crisolapp.DB.modeloUsuarios;
import com.crisolapp.R;
import com.crisolapp.comunicacion.HttpManager;
import com.crisolapp.comunicacion.InterfazComunicacion;
import com.crisolapp.comunicacion.MyTaskConection;

/**
 * Created by jesusesmipastor on 19/02/2015.
 */
public class Sync extends Thread implements InterfazComunicacion{
    modeloUsuarios modelousuarios ;
    HttpManager httpManager;
    MyTaskConection myTaskConection;
    Context contexto;
    String usua_codigo;

    public Sync(Context con){
        contexto = con;
    }

    public void run() {
        modelousuarios = new modeloUsuarios(contexto);
        myTaskConection = new MyTaskConection(this);
        Cursor c =  modelousuarios.BuscartTabla(null,null,null);
        if(c.moveToFirst()){
        usua_codigo = c.getString(1);
        httpManager = new HttpManager();
        myTaskConection.execute();
        }

    }

    @Override
    public void HacerAntes() {

    }

    @Override
    public void HacerDurante() {

        if(this.HayRed()){
            try {
                httpManager.post(contexto.getString(R.string.dominio) +"/mobile/syncup/mensajes/"+usua_codigo);
                ContentValues RespuestaMensajes = httpManager.getRespuesta();
                modeloMensajes mm = new modeloMensajes(contexto);
                String respuesta = mm.ProcesarSyncup(RespuestaMensajes);
                if(!respuesta.equals("0/0")){
                    long[] patron = {0,200,500,500,500,700,500,1200};
                    Vibrator v = (Vibrator) contexto.getSystemService(contexto.VIBRATOR_SERVICE);
                    v.vibrate(patron, -1);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void HacerDespues() {

    }

    public boolean HayRed() {
        boolean conectado = false;
        ConnectivityManager connMgr = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            boolean wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            boolean mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if (wifiConnected) {
                conectado = true;

            } else if (mobileConnected) {
                conectado = true;
            }
        } else {
            conectado =  false;
        }
        return conectado;
    }

}
