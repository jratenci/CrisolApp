package com.crisolapp.mensajes;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.crisolapp.DB.modeloMensajes;
import com.crisolapp.R;
import com.crisolapp.librerias.MyActividad;

public class DetalleMensaje extends MyActividad {
    modeloMensajes modelomensajes;
    Bundle bolsa;
    TextView hora,texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mensajes_activity_detalle_mensaje);
        modelomensajes = new  modeloMensajes(this);

        hora    = (TextView)findViewById(R.id.txtHora);
        texto   = (TextView)findViewById(R.id.txtTexto);
        bolsa   = getIntent().getExtras();

        String where ="mens_id=?";
        String []valores_where = new String[]{bolsa.getString("mens_id")};
        Cursor c = modelomensajes.BuscartTabla(null, where, valores_where);
        String Texto ="";

        if(c.moveToFirst()){
        hora.setText("Hora de Envio "+c.getString(7));
        Texto = c.getString(2)+c.getString(3)+c.getString(4)+c.getString(5)+c.getString(6);
        texto.setText(Texto);
        if(c.getString(8).equals("A")){
            ContentValues content = new ContentValues();
            content.put("mens_estado","L");
            modelomensajes.Modificar(content,"mens_id",bolsa.getString("mens_id") );
        }
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, Mensajes.class);
        startActivity(i);
        return;
    }

}
