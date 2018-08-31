package com.crisolapp.menuprincipal;

/**
 * Created by jesusesmipastor on 02/02/2015.
 */
public class AdaptadoMenuPrincipal {
    private int img;
    private String titulo;
    private String abreviacion;
    private String numero;

    public AdaptadoMenuPrincipal() {
    }

    public AdaptadoMenuPrincipal(int img, String titulo, String ab, String numero) {
        this.img = img;
        this.titulo = titulo;
        this.abreviacion = ab;
        this.numero=numero;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
