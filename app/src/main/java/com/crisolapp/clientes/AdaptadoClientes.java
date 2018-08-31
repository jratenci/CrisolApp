package com.crisolapp.clientes;

/**
 * Created by jesusesmipastor on 03/02/2015.
 */
public class AdaptadoClientes {
    private  int clie_id;
    private String clie_codigo,clie_nombre,clie_negocio,clie_direccion,clie_barrio,clie_ciudad,clie_telefono,clie_ruta,clie_visitado
    ,clie_cedula;

    public AdaptadoClientes(int clie_id, String clie_codigo, String clie_nombre, String clie_negocio, String clie_direccion, String clie_barrio, String clie_ciudad, String clie_telefono, String clie_ruta, String clie_visitado, String clie_cedula) {
        this.clie_id = clie_id;
        this.clie_codigo = clie_codigo;
        this.clie_nombre = clie_nombre;
        this.clie_negocio = clie_negocio;
        this.clie_direccion = clie_direccion;
        this.clie_barrio = clie_barrio;
        this.clie_ciudad = clie_ciudad;
        this.clie_telefono = clie_telefono;
        this.clie_ruta = clie_ruta;
        this.clie_visitado = clie_visitado;
        this.clie_cedula = clie_cedula;
    }

    public AdaptadoClientes(String clie_codigo, String clie_nombre, String clie_negocio,String clie_visitado) {
        this.clie_codigo   = clie_codigo;
        this.clie_nombre   = clie_nombre;
        this.clie_negocio  = clie_negocio;
        this.clie_visitado = clie_visitado;
    }

    public int getClie_id() {
        return clie_id;
    }

    public void setClie_id(int clie_id) {
        this.clie_id = clie_id;
    }

    public String getClie_codigo() {
        return clie_codigo;
    }

    public void setClie_codigo(String clie_codigo) {
        this.clie_codigo = clie_codigo;
    }

    public String getClie_nombre() {
        return clie_nombre;
    }

    public void setClie_nombre(String clie_nombre) {
        this.clie_nombre = clie_nombre;
    }

    public String getClie_negocio() {
        return clie_negocio;
    }

    public void setClie_negocio(String clie_negocio) {
        this.clie_negocio = clie_negocio;
    }

    public String getClie_direccion() {
        return clie_direccion;
    }

    public void setClie_direccion(String clie_direccion) {
        this.clie_direccion = clie_direccion;
    }

    public String getClie_barrio() {
        return clie_barrio;
    }

    public void setClie_barrio(String clie_barrio) {
        this.clie_barrio = clie_barrio;
    }

    public String getClie_ciudad() {
        return clie_ciudad;
    }

    public void setClie_ciudad(String clie_ciudad) {
        this.clie_ciudad = clie_ciudad;
    }

    public String getClie_telefono() {
        return clie_telefono;
    }

    public void setClie_telefono(String clie_telefono) {
        this.clie_telefono = clie_telefono;
    }

    public String getClie_ruta() {
        return clie_ruta;
    }

    public void setClie_ruta(String clie_ruta) {
        this.clie_ruta = clie_ruta;
    }

    public String getClie_visitado() {
        return clie_visitado;
    }

    public void setClie_visitado(String clie_visitado) {
        this.clie_visitado = clie_visitado;
    }

    public String getClie_cedula() {
        return clie_cedula;
    }

    public void setClie_cedula(String clie_cedula) {
        this.clie_cedula = clie_cedula;
    }
}
