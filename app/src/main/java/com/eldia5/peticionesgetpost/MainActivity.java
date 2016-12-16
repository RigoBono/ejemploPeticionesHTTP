package com.eldia5.peticionesgetpost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.eldia5.peticionesgetpost.util.peticion1;

public class MainActivity extends AppCompatActivity implements peticion1.onPeticionHecha {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        peticion1 p1=new peticion1();
        p1.setOnCalleractivity(this);
        p1.execute();
    }

    @Override
    public void codigoAdmin20(String data) {
        Log.i("En resultado",data);
    }
}
