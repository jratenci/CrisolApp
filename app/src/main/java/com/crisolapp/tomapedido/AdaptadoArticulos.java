package com.crisolapp.tomapedido;

/**
 * Created by jesusesmipastor on 06/02/2015.
 */
public class AdaptadoArticulos {
    private int insu_id;
    private String insu_codigo,insu_nombre,insu_marca,insu_inventario;
    private double insu_precio, insu_iva,insu_precioConIva;


    public AdaptadoArticulos(int insu_id, String insu_codigo, String insu_nombre, String insu_marca, double insu_precio, double insu_iva) {
        this.insu_id = insu_id;
        this.insu_codigo = insu_codigo;
        this.insu_nombre = insu_nombre;
        this.insu_marca = insu_marca;
        this.insu_precio = insu_precio;
        this.insu_iva = insu_iva;
    }

    public AdaptadoArticulos(String insu_codigo, String insu_nombre, double insu_precio,double insu_iva,String insu_marca, String insu_inventario) {
        this.insu_codigo     = insu_codigo;
        this.insu_nombre     = insu_nombre;
        this.insu_precio     = insu_precio;
        this.insu_iva        = insu_iva;
        this.insu_marca      = insu_marca;
        this.insu_inventario = insu_inventario;
    }

    public int getInsu_id() {
        return insu_id;
    }

    public void setInsu_id(int insu_id) {
        this.insu_id = insu_id;
    }

    public String getInsu_codigo() {
        return insu_codigo;
    }

    public void setInsu_codigo(String insu_codigo) {
        this.insu_codigo = insu_codigo;
    }

    public String getInsu_marca() {
        return insu_marca;
    }

    public void setInsu_marca(String insu_marca) {
        this.insu_marca = insu_marca;
    }

    public String getInsu_nombre() {
        return insu_nombre;
    }

    public void setInsu_nombre(String insu_nombre) {
        this.insu_nombre = insu_nombre;
    }

    public double getInsu_precio() {
        return insu_precio;
    }

    public void setInsu_precio(double insu_precio) {
        this.insu_precio = insu_precio;
    }

    public double getInsu_iva() {
        return insu_iva;
    }

    public void setInsu_iva(double insu_iva) {
        this.insu_iva = insu_iva;
    }

    public double getInsu_precioConIva() {
        insu_precioConIva = insu_precio;
        return insu_precioConIva;
    }

    public String getInsu_inventario() {
        return insu_inventario;
    }

    public void setInsu_inventario(String insu_inventario) {
        this.insu_inventario = insu_inventario;
    }
}
