package com.eldia5.peticionesgetpost.util;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

/**
 * Created by rigobono on 16/12/16.
 */

public class peticion3 extends AsyncTask<JSONArray, Integer, JSONObject> {
    onCorreosObtenidos callerActivity;
    public void setCallerActivity(onCorreosObtenidos callerActivity) {
        this.callerActivity = callerActivity;
    }


    @Override
    protected JSONObject doInBackground(JSONArray... jsonArrays) {
        return network.doGetCall("https://jsonplaceholder.typicode.com/posts/1/comments","",10500,new JSONObject());
    }

    @Override
    protected  void onPostExecute(JSONObject result){
        Log.i("RESULTADO",result.toString());

    }
    public interface  onCorreosObtenidos{
        public void terremtos(Vector<String> dates);
    }
}
