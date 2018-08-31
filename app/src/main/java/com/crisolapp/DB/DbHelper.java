package com.crisolapp.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jesusesmipastor on 27/01/2015.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME         = "crisoldb.sqlite";
    private static final int DB_SCHEME_VERSION  = 1;

    public DbHelper(Context context) {
        super(context, DB_NAME,null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
    db.execSQL(EsquemaDB.CREAR_TABLA_USUARIOS);
    db.execSQL(EsquemaDB.CREAR_TABLA_INSUMOS);
    db.execSQL(EsquemaDB.CREAR_TABLA_CIUDADES);
    db.execSQL(EsquemaDB.CREAR_TABLA_CLIENTES);
    db.execSQL(EsquemaDB.CREAR_TABLA_MENSAJES);
    db.execSQL(EsquemaDB.CREAR_TABLA_NOVEDADES);
    db.execSQL(EsquemaDB.CREAR_TABLA_PEDIDOS);
    db.execSQL(EsquemaDB.CREAR_TABLA_RUTAS);
    db.execSQL(EsquemaDB.CREAR_TABLA_TIPOCLIENTES);
    db.execSQL(EsquemaDB.CREAR_TABLA_TIPONOVEDADES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
