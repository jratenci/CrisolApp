package com.crisolapp.librerias;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Build;

import com.crisolapp.R;

/**
 * Created by jesusesmipastor on 30/01/2015.
 */
public class MyAlerta extends AlertDialog.Builder{

    public MyAlerta(Activity activity,String msg) {
        super(activity);
        this.setTitle("Alerta");
        this.setMessage(msg);
        this.setIcon(R.drawable.alerta);

    }

    public  void mostrar(){
        this.show();
    }


}
