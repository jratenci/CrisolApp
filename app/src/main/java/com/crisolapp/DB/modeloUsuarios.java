package com.crisolapp.DB;

import android.content.Context;

import com.crisolapp.librerias.MyModelo;

/**
 * Created by jesusesmipastor on 30/01/2015.
 */
public class modeloUsuarios extends MyModelo {

    public modeloUsuarios(Context context) {
      super(context);
        this.NombreTabla="usuarios";
    }


}
