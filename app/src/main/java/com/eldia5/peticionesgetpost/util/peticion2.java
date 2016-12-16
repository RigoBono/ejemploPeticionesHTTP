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

public class peticion2 extends AsyncTask<JSONObject, Integer, JSONObject> {
    onTerremotosObtenidos callerActivity;

    public void setCallerActivity(onTerremotosObtenidos callerActivity) {
        this.callerActivity = callerActivity;
    }

    @Override
    protected JSONObject doInBackground(JSONObject... jsonObjects) {
        return network.doGetCall("http://api.geonames.org/earthquakesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&username=demo","",10500,new JSONObject());
    }

    @Override
    protected  void onPostExecute(JSONObject result){
        Log.i("RESULTADO",result.toString());
        try {
            JSONArray postalCodes=result.getJSONArray("earthquakes");
            Vector<String> data=new Vector<String>();
            for(int i=0;i<postalCodes.length();i++){
                JSONObject jo=postalCodes.getJSONObject(i);
                data.add(jo.getString("datetime"));
            }
            callerActivity.terremtos(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public interface  onTerremotosObtenidos{
        public void terremtos(Vector<String> dates);
    }

}
