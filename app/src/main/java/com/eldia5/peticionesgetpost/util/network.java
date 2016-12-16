package com.eldia5.peticionesgetpost.util;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by rigobono on 16/12/16.
 */

public class network {

    private static final String ENCODING = "application/json; charset=UTF-8";
    private static final String LOG_TAG = network.class.getSimpleName();


    public static JSONObject doGetCall(String URL, String method, int timeout, JSONObject data) {
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, timeout);
        HttpConnectionParams.setSoTimeout(params, timeout);
        HttpClient httpclient = new DefaultHttpClient(params);
        HttpPost httppost = new HttpPost(URL + method);

        httppost.setHeader("Content-Type", ENCODING);
        StringEntity se;
        try {
            se = new StringEntity(data.toString(), HTTP.UTF_8);
        } catch (UnsupportedEncodingException e2) {
            return jsonRespuesta(10001, "Unsupported EncodingException", null);

        }
        httppost.setEntity(se);
        InputStream in;

        try {
            Log.i(LOG_TAG, "HTTP[" + method + "]>>" + convertStreamToString(httppost.getEntity().getContent()));
            HttpResponse response = httpclient.execute(httppost);
            in = response.getEntity().getContent();
        } catch (IllegalStateException e2) {
            Log.e(LOG_TAG, "001" + e2.toString());
            return jsonRespuesta(10002, "Illegal state exception", null);
        } catch (HttpHostConnectException e2) {
            Log.e(LOG_TAG, "002" + e2.toString());
            return jsonRespuesta(10003, "HTTP Host Connect exception.", null);
        } catch (IOException e2) {
            Log.e(LOG_TAG, "003" + e2.toString());
            return jsonRespuesta(10004, "Error con la comunicación al servidor, intentelo nuevamente.", null);
        }
        String a = "";
        try {
            a = convertStreamToString(in);
            Log.i(LOG_TAG, "HTTP[" + method + "]<<" + a);
        } catch (OutOfMemoryError ex) {
            Log.i(LOG_TAG, "HTTP[" + method + "]<< Out of Memory Exception!");
            return jsonRespuesta(10006, "Sin memoria para descargar datos.", null);
        }


        try {
            return new JSONObject(a);
        } catch (JSONException e1) {
            Log.e(LOG_TAG, "ERROR COM: " + e1.toString());
            return jsonRespuesta(10005, "El servidor respondio de manera incorrecta", null);
        }

    }


    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    public static JSONObject jsonRespuesta(int Error, String Mensaje, JSONObject Data){
        JSONObject res;
        try {
            String sJSON;
            if(Data!=null)
            {
                sJSON = Data.toString();
            }else{

                sJSON = "{}";
            }
            res = new JSONObject("{Respuesta:{\"Código\":" + String.valueOf(Error) + ",\"Mensaje\":\""+ Mensaje +"\", \"Data\": " + sJSON + " }}");
        } catch (JSONException e) {
            try {
                res = new JSONObject("{Respuesta:{\"Código\": 10009,\"Mensaje\":\"[10009] Error crítico\", \"Data\":{}}}");
            } catch (JSONException e1) {
                e.printStackTrace();
                return null;
            }

        }
        return res;
    }

    }





