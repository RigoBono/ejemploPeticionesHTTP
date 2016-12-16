package com.eldia5.peticionesgetpost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.eldia5.peticionesgetpost.util.peticion1;
import com.eldia5.peticionesgetpost.util.peticion2;

import java.util.Vector;

public class MainActivity extends AppCompatActivity implements peticion1.onPeticionHecha, peticion2.onTerremotosObtenidos {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        peticion2 p2=new peticion2();
        p2.setCallerActivity(this);
        p2.execute();

    }

    @Override
    public void codigoAdmin20(String data) {

    }

    @Override
    public void terremtos(Vector<String> dates) {
        for( String st:dates){
            Log.i("En resultado",st);
        }
    }
}
