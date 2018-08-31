package com.crisolapp.DB;

import android.content.Context;
import android.database.Cursor;

import com.crisolapp.librerias.MyModelo;

/**
 * Created by jesusesmipastor on 07/02/2015.
 */
public class modeloPedidos extends MyModelo {
    public modeloPedidos(Context context) {
        super(context);
        this.NombreTabla="pedidos";
    }

    public Cursor buscar( String where,String[] valoresWhere){
        String[] campos = new String[]{"pedi_id","pedi_insumo","pedi_cantidad","pedi_precio",
                                       "pedi_vendedor","pedi_cliente","pedi_estado","pedi_comentario",
                                       "pedi_precioiva","pedi_marca,pedi_nombreinsumo"};

        Cursor c =  this.BuscartTabla(campos,where,valoresWhere);
        return c;
    }


    public Cursor buscar_pedido(String clie_codigo){
     String sql = "SELECT pedi_id, pedi_insumo,pedi_nombreinsumo as insu_nombre,pedi_cantidad,pedi_precioiva, pedi_estado, pedi_insu_iva as insu_iva, pedi_marca as insu_marca,pedi_cliente FROM pedidos WHERE pedi_cliente="+clie_codigo;
     Cursor c = this.Query(sql);
     return c;
    }

    public Cursor numero_pedidos(){
        String sql = "SELECT  pedi_cliente  FROM pedidos GROUP BY pedi_cliente";
        Cursor c = this.Query(sql);
        return c;
    }

    public Cursor total_pedidos(){
        String sql = "SELECT  SUM(pedi_precioiva*pedi_cantidad) AS total_iva, SUM(pedi_precio*pedi_cantidad) AS total_sin_iva  FROM pedidos";
        Cursor c = this.Query(sql);
        return c;
    }

    public Cursor total_por_marca(){
        String sql = "SELECT pedi_marca, SUM(pedi_precio*pedi_cantidad) AS total_sin_iva  FROM pedidos GROUP BY pedi_marca";
        Cursor c = this.Query(sql);
        return c;
    }


}
