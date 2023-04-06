package com.sergio.project11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ConsultarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
    }


    //Método para pasar a DatosAlumnoActivity
    public void DatosAlumno(View view){
        Intent i = new Intent(this, DatosAlumnoActivity.class);
        startActivity(i);
    }

    //Método para pasar a GrupoAlumnosActivity
    public void GrupoAlumnos(View view){
        Intent i = new Intent(this, GrupoAlumnosActivity.class);
        startActivity(i);
    }
}