package com.crisolapp.comunicacion;

import android.os.AsyncTask;

/**
 * Created by jesusesmipastor on 29/01/2015.
 */
public class MyTaskConection  extends AsyncTask<Void,Void,Void> {
    InterfazComunicacion ic;
    public MyTaskConection(InterfazComunicacion _ic) {
        ic = _ic;
    }

    @Override
    protected Void doInBackground(Void... params) {
       ic.HacerDurante();
        return null;
    }

    @Override
    protected void onPreExecute() {
       ic.HacerAntes();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
       ic.HacerDespues();
       super.onPostExecute(aVoid);
       }
}
