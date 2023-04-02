package com.sergio.project11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Método para pasar a RegistrarActivity
    public void Registrar(View view){
        Intent i = new Intent(this, RegistrarActivity.class);
        startActivity(i);
    }

    //Método para pasar a EliminarActivity
    public void Eliminar(View view){
        Intent i = new Intent(this, EliminarActivity.class);
        startActivity(i);
    }
}