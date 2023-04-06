package com.sergio.project11;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class GrupoAlumnosActivity extends AppCompatActivity {
    private Button btnConsultar;
    private ListView listViewAlumnos;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> alumnosList;
    private ArrayAdapter<CharSequence> adapter;
    private TextView tvGrupo;
    private Spinner spGrupo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_alumnos);

        btnConsultar = findViewById(R.id.btnConsultarGrupo);
        listViewAlumnos = findViewById(R.id.lvConsultarGrupo);
        tvGrupo =findViewById(R.id.tvGrupo);
        spGrupo =findViewById(R.id.spGrupo);

        adapter = ArrayAdapter.createFromResource(
                this, R.array.spinner_items, R.layout.spinner_item
        );
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spGrupo.setAdapter(adapter);

        alumnosList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alumnosList);
        listViewAlumnos.setAdapter(arrayAdapter);

    }

    public void consultarAlumnos(View view) {
        // Limpiar la lista de alumnos y actualizar el adaptador
        alumnosList.clear();
        arrayAdapter.notifyDataSetChanged();

        String grupo = spGrupo.getSelectedItem().toString();


        // Aquí asumimos que ya tienes una clase para manejar la base de datos SQLite llamada DatabaseHelper
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                "id_al",
                "nombre_al",
                "apellidos_al",
                "grupo"
        };

        String selection = "grupo = ?";
        String[] selectionArgs = {grupo};

        Cursor cursor = db.query(
                "alumno",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                "apellidos_al ASC, nombre_al ASC"
        );

        // Procesa los resultados de la consulta
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String apellidos = cursor.getString(2);

                // Agregar el nombre y apellidos del alumno a la lista y actualizar el adaptador
                alumnosList.add(id + " - " + nombre + " " + apellidos);
                arrayAdapter.notifyDataSetChanged();

            } while (cursor.moveToNext());

            cursor.close();
            tvGrupo.setText("Grupo " + grupo);

        }

        db.close();
        limpiar();
    }

    private void limpiar() {;
        spGrupo.setSelection(0);
    }
}