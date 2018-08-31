package com.crisolapp.detallecliente;

/**
 * Created by jesusesmipastor on 05/02/2015.
 */
public class AdaptadoDetalleCliente {
    private int img;
    private String titulo;
    private String abreviacion;

    public AdaptadoDetalleCliente(int img, String titulo, String abreviacion) {
        this.titulo = titulo;
        this.img = img;
        this.abreviacion = abreviacion;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAbreviacion() {
        return abreviacion;
    }

    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
    }
}
