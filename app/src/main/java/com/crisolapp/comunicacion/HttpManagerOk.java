package com.crisolapp.comunicacion;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.os.Build;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpManagerOk {
    OkHttpClient client = new OkHttpClient();
    public String postUrl;
    public ContentValues respuesta;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");



    public HttpManagerOk() {
        respuesta = new ContentValues();
        respuesta.put("TEXTO","");
        respuesta.put("COM","NO");
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String post(String url, JSONObject json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json.toString());

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public ContentValues getRespuesta(){
        return respuesta;
    }
}
