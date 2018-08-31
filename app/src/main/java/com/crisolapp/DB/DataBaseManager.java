package com.crisolapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Enumeration;

/**
 * Created by jesusesmipastor on 27/01/2015.
 */
public class DataBaseManager {
    private DbHelper helper;
    private SQLiteDatabase db;
    private Enumeration claves;

    public DataBaseManager( Context context) {
        helper  = new DbHelper(context);
        db = helper.getWritableDatabase();
     }

    public void Insertar(String NombreTabla,  ContentValues contentvalues ){

       if(contentvalues.size()>0){
            db.insert(NombreTabla,null,contentvalues);
        }
    }

    public void Eliminar(String NombreTabla,String Campo, String Valor){
     db.delete(NombreTabla,Campo+"=?",new String[]{Valor});
    }

    public void Modificar(String NombreTabla, ContentValues contentvalues,String Campo, String Valor){
        db.update(NombreTabla, contentvalues, Campo + "=?", new String[]{Valor});
    }

    public void ModificarIN(String NombreTabla,String Campo, String Valor,String CampoWhere,String IN){
        String query = "UPDATE "+NombreTabla+" SET "+Campo+" = \""+Valor+"\" WHERE "+CampoWhere+" IN("+IN+")";
        System.out.println(query);
        db.execSQL(query);
    }

    public Cursor Buscar(String NombreTabla, String[] Campos, String ClausulaWhere, String[] ValoresWhere  ){
        Cursor c = db.query(NombreTabla, Campos, ClausulaWhere, ValoresWhere, null, null, null);
        return c;
    }

    public void TruncarTabla(String NombreTabla){
        db.execSQL("delete from sqlite_sequence where name='"+NombreTabla+"'");
        db.delete(NombreTabla,null,null);
    }

    public Cursor rawQuery(String query){
        Cursor c = db.rawQuery(query,null);
        return c;
    }

}
