package com.sergio.project11;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ModificarActivity extends AppCompatActivity {

    Spinner spCurso;
    private EditText etId, etNombre, etApellidos, etTelefono, etCorreo;

    private ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        spCurso = findViewById(R.id.spCursoMod);

        adapter = ArrayAdapter.createFromResource(
                this, R.array.spinner_items, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spCurso.setAdapter(adapter);

        etId = findViewById(R.id.etId);
        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellidos);
        etTelefono = findViewById(R.id.etTelefono);
        etCorreo = findViewById(R.id.etCorreo);
    }

    public void modificarAlumno(View view) {
        String id = etId.getText().toString().trim();
        String nombre = etNombre.getText().toString().trim();
        String apellidos = etApellidos.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String grupo = spCurso.getSelectedItem().toString();

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (nombre.isEmpty() || apellidos.isEmpty() || telefono.isEmpty() || correo.isEmpty() || grupo.isEmpty() || id.isEmpty()) {
            Toast.makeText(ModificarActivity.this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Consulta para verificar si el usuario existe
        String checkQuery = "SELECT * FROM alumno WHERE id_al = ?";
        Cursor cursor = db.rawQuery(checkQuery, new String[]{String.valueOf(id)});

        // Si el usuario existe, actualizamos la información
        if (cursor.moveToFirst()) {
            ContentValues values = new ContentValues();
            values.put("nombre_al", nombre);
            values.put("apellidos_al", apellidos);
            values.put("telefono_al", telefono);
            values.put("correo_al", correo);
            values.put("grupo", grupo);

            db.update("alumno", values, "id_al = ?", new String[]{String.valueOf(id)});
            Toast.makeText(this, "Alumno modificado correctamente", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Error:alumno no encontrado",Toast.LENGTH_SHORT).show();
        }
    }
}