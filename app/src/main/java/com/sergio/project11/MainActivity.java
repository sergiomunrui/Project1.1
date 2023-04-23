package com.sergio.project11;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.close();
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

    //Método para pasar a ConsultarActivity
    public void Consultar(View view){
        Intent i = new Intent(this, ConsultarActivity.class);
        startActivity(i);
    }

    //Método para pasar a ModificarActivity
    public void Modificar(View view){
        Intent i = new Intent(this, ModificarMenuActivity.class);
        startActivity(i);
    }
}