package com.crisolapp.informes;

/**
 * Created by jesusesmipastor on 02/02/2015.
 */
public class AdaptadoInformes {
    private int img;
    private String titulo;
    private String abreviacion;

    public AdaptadoInformes(int img, String titulo, String ab) {
        this.img = img;
        this.titulo = titulo;
        this.abreviacion = ab;
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
