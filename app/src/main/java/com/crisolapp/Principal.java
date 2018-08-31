package com.crisolapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.crisolapp.DB.modeloUsuarios;
import com.crisolapp.librerias.MyActividad;
import com.crisolapp.librerias.MyAlerta;
import com.crisolapp.login.Login;
import com.crisolapp.menuprincipal.MenuPrincipal;
import com.crisolapp.util.Util;

import static android.view.View.OnClickListener;

public class Principal extends MyActividad implements OnClickListener {
    Button btnSincronizar;
    Button btnVentas;
    Button btnActualizar;
    MyAlerta alerta;
    modeloUsuarios modelousuarios ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        btnSincronizar = (Button)this.findViewById(R.id.btnSincronizar);
        btnVentas      = (Button)this.findViewById(R.id.btnVentas);
        btnActualizar  = (Button)this.findViewById(R.id.btnActualizar);

        btnSincronizar.setOnClickListener(this);
        btnVentas.setOnClickListener(this);
        btnActualizar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        modelousuarios = new modeloUsuarios(this);
        Cursor u = modelousuarios.BuscartTabla(null,null,null);

    switch (v.getId()){
        case R.id.btnSincronizar :

            if(u.moveToFirst()){
                alerta = new MyAlerta(this,"Debe Terminar Labores.");
                alerta.setPositiveButton("Aceptar",null);
                alerta.mostrar();
            }else{
                Intent i = new Intent(this, Login.class);
                startActivity(i);
            }

        break;

        case R.id.btnVentas:

            if(u.moveToFirst()){
                Intent i1 = new Intent(this, MenuPrincipal.class);
                startActivity(i1);
            }else{
                alerta = new MyAlerta(this,"Debe Sincronizar Primero.");
                alerta.setPositiveButton("Aceptar",null);
                alerta.mostrar();
            }

            break;

        case R.id.btnActualizar:
            String url = "http://aplicativovirtual.com/crisol.apk";
            Intent ii = new Intent(Intent.ACTION_VIEW);
            ii.setData(Uri.parse(url));
            startActivity(ii);
            break;
    }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @Override
    public void onBackPressed()
    {
        /**
        GPS gps = new GPS(this);
        String android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.d("debug","..............."+String.valueOf( gps.getLatitud()) +","+String.valueOf( gps.getLongitud())  );
         */
    }

}
