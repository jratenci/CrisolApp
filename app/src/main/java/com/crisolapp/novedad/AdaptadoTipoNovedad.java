package com.crisolapp.novedad;

/**
 * Created by jesusesmipastor on 11/02/2015.
 */
public class AdaptadoTipoNovedad {
    String tnov_id,tnov_codigo,tnov_nombre;
   private  boolean color;

    public AdaptadoTipoNovedad(String tnov_id, String tnov_codigo, String tnov_nombre,boolean color) {
        this.tnov_id     = tnov_id;
        this.tnov_codigo = tnov_codigo;
        this.tnov_nombre = tnov_nombre;
        this.color       = color;
    }

    public String getTnov_id() {
        return tnov_id;
    }

    public void setTnov_id(String tnov_id) {
        this.tnov_id = tnov_id;
    }

    public String getTnov_codigo() {
        return tnov_codigo;
    }

    public void setTnov_codigo(String tnov_codigo) {
        this.tnov_codigo = tnov_codigo;
    }

    public String getTnov_nombre() {
        return tnov_nombre;
    }

    public void setTnov_nombre(String tnov_nombre) {
        this.tnov_nombre = tnov_nombre;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }
}
