package com.sergio.project11;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegistrarActivity extends AppCompatActivity {

    Spinner spCurso;
    private ArrayAdapter<CharSequence> adapter;

    private EditText etRegistrarNombre, etRegistrarApellidos, etRegistrarTelefono, etRegistrarCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        etRegistrarNombre=findViewById(R.id.etRegistrarNombre);
        etRegistrarApellidos=findViewById(R.id.etRegistrarApellidos);
        etRegistrarTelefono=findViewById(R.id.etRegistrarTelefono);
        etRegistrarCorreo=findViewById(R.id.etRegistrarCorreo);

        spCurso = findViewById(R.id.spTrimestre);

        adapter = ArrayAdapter.createFromResource(
                this, R.array.spinner_items, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spCurso.setAdapter(adapter);
    }

    //Método para el botón Registrar
    public void registrarAlumno (View view){
        String nombre = etRegistrarNombre.getText().toString().trim();
        String apellidos = etRegistrarApellidos.getText().toString().trim();
        String telefono = etRegistrarTelefono.getText().toString().trim();
        String correo = etRegistrarCorreo.getText().toString().trim();
        String grupo = spCurso.getSelectedItem().toString();

        if (!nombre.isEmpty() && !apellidos.isEmpty() && !telefono.isEmpty() && !correo.isEmpty() && !grupo.isEmpty()) {
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            // Aquí necesitarías realizar la consulta SQL adecuada según tu diseño de base de datos
            String query = "INSERT INTO alumno (nombre_al, apellidos_al, telefono_al, correo_al, grupo) VALUES (?, ?, ?, ?, ?)";
            db.execSQL(query, new Object[]{nombre, apellidos, telefono, correo, grupo});

            db.close();
            limpiar();

            Toast.makeText(this, "Alumno registrado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

    private void limpiar() {
        etRegistrarNombre.setText("");
        etRegistrarApellidos.setText("");
        etRegistrarTelefono.setText("");
        etRegistrarCorreo.setText("");
        spCurso.setSelection(0);
    }

    //Método para pasar a RegistrarNotasActivity
    public void PasarRegistrarNotas(View view){
        Intent i = new Intent(this, RegistrarNotasActivity.class);
        startActivity(i);
    }

    //Método para pasar a RegistrarActivity
    public void Menu(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
