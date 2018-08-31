package com.crisolapp.tomapedido;

/**
 * Created by jesusesmipastor on 06/02/2015.
 */
public class AdaptadoPedidos {
    private int pedi_id;
    private double pedi_precio, pedi_precioiva,insu_iva;
    private String pedi_insumo, pedi_cantidad, pedi_vendedor, pedi_cliente, pedi_estado, pedi_comentario, pedi_marca,nombre_insumo;

    public AdaptadoPedidos(int pedi_id, double pedi_precio, double pedi_precioiva, String pedi_insumo, String pedi_cantidad, String pedi_vendedor, String pedi_cliente, String pedi_estado, String pedi_comentario, String pedi_marca,String nombre_insumo) {
        this.pedi_id = pedi_id;
        this.pedi_precio = pedi_precio;
        this.pedi_precioiva = pedi_precioiva;
        this.pedi_insumo = pedi_insumo;
        this.pedi_cantidad = pedi_cantidad;
        this.pedi_vendedor = pedi_vendedor;
        this.pedi_cliente = pedi_cliente;
        this.pedi_estado = pedi_estado;
        this.pedi_comentario = pedi_comentario;
        this.pedi_marca = pedi_marca;
        this.nombre_insumo=nombre_insumo;
    }

    public AdaptadoPedidos( int pedi_id, String pedi_insumo,String nombre_insumo,String pedi_cantidad,double pedi_precioiva, String pedi_estado,double insu_iva,String pedi_marca, String pedi_cliente) {
        this.pedi_precioiva = pedi_precioiva;
        this.pedi_insumo = pedi_insumo;
        this.nombre_insumo = nombre_insumo;
        this.pedi_cantidad = pedi_cantidad;
        this.pedi_estado = pedi_estado;
        this.pedi_id = pedi_id;
        this.insu_iva = insu_iva;
        this.pedi_marca = pedi_marca;
        this.pedi_cliente = pedi_cliente;
    }

    public int getPedi_id() {
        return pedi_id;
    }

    public void setPedi_id(int pedi_id) {
        this.pedi_id = pedi_id;
    }

    public double getPedi_precio() {
        return pedi_precio;
    }

    public void setPedi_precio(double pedi_precio) {
        this.pedi_precio = pedi_precio;
    }

    public double getPedi_precioiva() {
        return pedi_precioiva;
    }

    public void setPedi_precioiva(double pedi_precioiva) {
        this.pedi_precioiva = pedi_precioiva;
    }

    public String getPedi_insumo() {
        return pedi_insumo;
    }

    public void setPedi_insumo(String pedi_insumo) {
        this.pedi_insumo = pedi_insumo;
    }

    public String getPedi_cantidad() {
        return pedi_cantidad;
    }

    public void setPedi_cantidad(String pedi_cantidad) {
        this.pedi_cantidad = pedi_cantidad;
    }

    public String getPedi_vendedor() {
        return pedi_vendedor;
    }

    public void setPedi_vendedor(String pedi_vendedor) {
        this.pedi_vendedor = pedi_vendedor;
    }

    public String getPedi_cliente() {
        return pedi_cliente;
    }

    public void setPedi_cliente(String pedi_cliente) {
        this.pedi_cliente = pedi_cliente;
    }

    public String getPedi_estado() {
        return pedi_estado;
    }

    public void setPedi_estado(String pedi_estado) {
        this.pedi_estado = pedi_estado;
    }

    public String getPedi_comentario() {
        return pedi_comentario;
    }

    public void setPedi_comentario(String pedi_comentario) {
        this.pedi_comentario = pedi_comentario;
    }

    public String getPedi_marca() {
        return pedi_marca;
    }

    public void setPedi_marca(String pedi_marca) {
        this.pedi_marca = pedi_marca;
    }

    public String getNombre_insumo() {
        return nombre_insumo;
    }

    public void setNombre_insumo(String nombre_insumo) {
        this.nombre_insumo = nombre_insumo;
    }

    public double getInsu_iva() {
        return insu_iva;
    }

    public void setInsu_iva(double insu_iva) {
        this.insu_iva = insu_iva;
    }
}
