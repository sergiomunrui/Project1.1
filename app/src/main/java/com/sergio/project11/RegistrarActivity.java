package com.sergio.project11;

import android.content.ContentValues;
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

            // Inserta al alumno
            ContentValues contentValues = new ContentValues();
            contentValues.put("nombre_al", nombre);
            contentValues.put("apellidos_al", apellidos);
            contentValues.put("telefono_al", telefono);
            contentValues.put("correo_al", correo);
            contentValues.put("grupo", grupo);
            long newRowId = db.insert("alumno", null, contentValues);

            // Si el alumno se insertó correctamente
            if (newRowId != -1) {
                // Registra las notas con valor 0 para cada asignatura y trimestre
                for (int asignatura = 1; asignatura <= 5; asignatura++) {
                    for (int trimestre = 1; trimestre <= 3; trimestre++) {
                        String query = "INSERT INTO nota (alumno_id, asignatura_id, trimestre, nota) VALUES (?, ?, ?, ?)";
                        db.execSQL(query, new Object[]{newRowId, asignatura, trimestre, null});
                    }
                }
                Toast.makeText(this, "Alumno registrado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al registrar el alumno", Toast.LENGTH_SHORT).show();
            }

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
