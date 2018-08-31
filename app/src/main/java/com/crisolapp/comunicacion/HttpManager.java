package com.crisolapp.comunicacion;

import android.content.ContentValues;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by jesusesmipastor on 28/01/2015.
 */
public class HttpManager{
    public String postUrl;
    public ContentValues respuesta;

    public HttpManager() {
        respuesta = new ContentValues();
        respuesta.put("TEXTO","");
        respuesta.put("COM","NO");
    }

    public void post(String _postUrl) throws Exception {
        postUrl = _postUrl;
        respuesta.put("TEXTO","");
        respuesta.put("COM","NO");
         System.out.println(postUrl);
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(postUrl);
            try {
                HttpResponse resp = httpclient.execute(httppost);
                HttpEntity entity = resp.getEntity();
                respuesta.put("TEXTO",EntityUtils.toString(entity));
                respuesta.put("COM","OK");
            }catch (Exception e){
                respuesta.put("TEXTO","");
                respuesta.put("COM","NO");

            }


        }

    public ContentValues getRespuesta(){
        return respuesta;
    }


}
