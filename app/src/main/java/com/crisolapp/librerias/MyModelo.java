package com.crisolapp.librerias;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.crisolapp.DB.DataBaseManager;

/**
 * Created by jesusesmipastor on 30/01/2015.
 */
public class MyModelo {
    public String NombreTabla;
    DataBaseManager dm;
    public MyModelo(Context context) {
       dm = new DataBaseManager(context);
    }

    public void BorrarTabla(){
        dm.TruncarTabla(NombreTabla);
    }

    public void InsertarTabla(ContentValues values){
        dm.Insertar(NombreTabla,values);
    }

    public Cursor BuscartTabla(String[] campos,String clausulaWhere,String[] valoresWhere){
     Cursor c = dm.Buscar(NombreTabla,campos,clausulaWhere,valoresWhere);
     return c;
    }

    public Cursor Query(String sql){
    return dm.rawQuery(sql);
    }

    public void Eliminar(String campo, String valor){
     dm.Eliminar(NombreTabla,campo,valor);
    }

    public void Modificar(ContentValues contentvalues,String Campo, String Valor){
        dm.Modificar(NombreTabla,contentvalues,Campo,Valor);
    }

    public void ModificarIN(String Campo, String Valor,String CampoWhere,String IN){
        dm.ModificarIN(NombreTabla,Campo,Valor,CampoWhere,IN);
    }


    public void LimpiarDB(){
        dm.TruncarTabla("usuarios");
        dm.TruncarTabla("insumos");
        dm.TruncarTabla("ciudades");
        dm.TruncarTabla("clientes");
        dm.TruncarTabla("mensajes");
        dm.TruncarTabla("novedades");
        dm.TruncarTabla("pedidos");
        dm.TruncarTabla("rutas");
        dm.TruncarTabla("tipoclientes");
        dm.TruncarTabla("tiponovedades");

    }

}
