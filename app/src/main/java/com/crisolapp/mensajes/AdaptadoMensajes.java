package com.crisolapp.mensajes;

/**
 * Created by jesusesmipastor on 16/02/2015.
 */
public class AdaptadoMensajes {
    String mens_id;
    String mens_idd;
    String mens_texto;
    String mens_texto2;
    String mens_texto3;
    String mens_texto4;
    String mens_texto5;
    String mens_fecha;
    String mens_estado;

    public AdaptadoMensajes(String mens_id, String mens_idd, String mens_texto, String mens_texto2, String mens_texto3, String mens_texto4, String mens_texto5, String mens_fecha, String mens_estado) {
        this.mens_id = mens_id;
        this.mens_idd = mens_idd;
        this.mens_texto = mens_texto;
        this.mens_texto2 = mens_texto2;
        this.mens_texto3 = mens_texto3;
        this.mens_texto4 = mens_texto4;
        this.mens_texto5 = mens_texto5;
        this.mens_fecha = mens_fecha;
        this.mens_estado = mens_estado;
    }

    public AdaptadoMensajes(String mens_id, String mens_texto, String mens_fecha, String mens_estado) {
        this.mens_id = mens_id;
        this.mens_texto = mens_texto;
        this.mens_fecha = mens_fecha;
        this.mens_estado = mens_estado;
    }

    public String getMens_id() {
        return mens_id;
    }

    public void setMens_id(String mens_id) {
        this.mens_id = mens_id;
    }

    public String getMens_idd() {
        return mens_idd;
    }

    public void setMens_idd(String mens_idd) {
        this.mens_idd = mens_idd;
    }

    public String getMens_texto() {
        return mens_texto;
    }

    public void setMens_texto(String mens_texto) {
        this.mens_texto = mens_texto;
    }

    public String getMens_texto2() {
        return mens_texto2;
    }

    public void setMens_texto2(String mens_texto2) {
        this.mens_texto2 = mens_texto2;
    }

    public String getMens_texto3() {
        return mens_texto3;
    }

    public void setMens_texto3(String mens_texto3) {
        this.mens_texto3 = mens_texto3;
    }

    public String getMens_texto4() {
        return mens_texto4;
    }

    public void setMens_texto4(String mens_texto4) {
        this.mens_texto4 = mens_texto4;
    }

    public String getMens_texto5() {
        return mens_texto5;
    }

    public void setMens_texto5(String mens_texto5) {
        this.mens_texto5 = mens_texto5;
    }

    public String getMens_fecha() {
        return mens_fecha;
    }

    public void setMens_fecha(String mens_fecha) {
        this.mens_fecha = mens_fecha;
    }

    public String getMens_estado() {
        return mens_estado;
    }

    public void setMens_estado(String mens_estado) {
        this.mens_estado = mens_estado;
    }
}
