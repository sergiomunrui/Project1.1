package com.sergio.project11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ModificarMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_menu);
    }

    //Método para pasar a ModificarDatosActivity
    public void ModificarDatos(View view){
        Intent i = new Intent(this, ModificarActivity.class);
        startActivity(i);
    }

    //Método para pasar a ModificarNotasActivity
    public void ModificarNotas(View view){
        Intent i = new Intent(this, ModificarNotasActivity.class);
        startActivity(i);
    }
}