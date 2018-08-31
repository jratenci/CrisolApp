package com.crisolapp.librerias;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.crisolapp.R;
import com.crisolapp.menuprincipal.MenuPrincipal;

/**
 * Created by jesusesmipastor on 29/01/2015.
 */
public class MyActividad extends ActionBarActivity {

    public boolean HayRed() {
        boolean conectado = false;
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            boolean wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            boolean mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if (wifiConnected) {
                conectado = true;

            } else if (mobileConnected) {
               conectado = true;
            }
        } else {
            conectado =  false;
        }
        return conectado;
    }


    public void iraMenuPrincipal(){
        Intent i = new Intent(this, MenuPrincipal.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ir_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Id_ir_menu) {
            this.iraMenuPrincipal();
        }

        return super.onOptionsItemSelected(item);
    }
}
