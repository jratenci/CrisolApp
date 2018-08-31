package com.crisolapp.rutas;

/**
 * Created by jesusesmipastor on 04/02/2015.
 */
public class AdaptadoRutas {
    private int ruta_id;
    private String ruta_codigo,ruta_nombre,ruta_dia;

    public AdaptadoRutas(int ruta_id, String ruta_codigo, String ruta_nombre, String ruta_dia) {
        this.ruta_id = ruta_id;
        this.ruta_codigo = ruta_codigo;
        this.ruta_nombre = ruta_nombre;
        this.ruta_dia = ruta_dia;
    }

    public AdaptadoRutas(String ruta_codigo, String ruta_nombre, String ruta_dia) {
        this.ruta_codigo = ruta_codigo;
        this.ruta_nombre = ruta_nombre;
        this.ruta_dia = ruta_dia;
    }

    public int getRuta_id() {
        return ruta_id;
    }

    public void setRuta_id(int ruta_id) {
        this.ruta_id = ruta_id;
    }

    public String getRuta_codigo() {
        return ruta_codigo;
    }

    public void setRuta_codigo(String ruta_codigo) {
        this.ruta_codigo = ruta_codigo;
    }

    public String getRuta_nombre() {
        return ruta_nombre;
    }

    public void setRuta_nombre(String ruta_nombre) {
        this.ruta_nombre = ruta_nombre;
    }

    public String getRuta_dia() {
        return ruta_dia;
    }

    public void setRuta_dia(String ruta_dia) {
        this.ruta_dia = ruta_dia;
    }
}
