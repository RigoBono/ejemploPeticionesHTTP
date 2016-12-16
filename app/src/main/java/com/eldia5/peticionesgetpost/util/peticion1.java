package com.eldia5.peticionesgetpost.util;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rigobono on 16/12/16.
 */

public class peticion1 extends AsyncTask<JSONObject, Integer, JSONObject> {

    onPeticionHecha onCalleractivity;

    public void setOnCalleractivity(onPeticionHecha onCalleractivity) {
        this.onCalleractivity = onCalleractivity;
    }

    @Override
    protected JSONObject doInBackground(JSONObject... jsonObjects) {
        return network.doGetCall("http://api.geonames.org/postalCodeLookupJSON?postalcode=6600&country=AT&username=demo","",10500,new JSONObject());
    }

    @Override
    protected  void onPostExecute(JSONObject result){
        Log.i("RESULTADO",result.toString());

        try {
            JSONArray postalCodes=result.getJSONArray("postalcodes");

            JSONObject jo=postalCodes.getJSONObject(0);
            String admin=jo.getString("adminCode2");

            onCalleractivity.codigoAdmin20(admin);



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public interface onPeticionHecha{
        public void codigoAdmin20(String data);
    }


}
