package com.sergio.project11;

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

        if (!id.isEmpty() && !nombre.isEmpty() && !apellidos.isEmpty() && !telefono.isEmpty() && !correo.isEmpty() && !grupo.isEmpty()) {
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            // Aquí necesitarías realizar la consulta SQL adecuada según tu diseño de base de datos
            String query = "UPDATE alumno SET nombre_al = ?, apellidos_al = ?, telefono_al = ?, correo_al = ?, grupo = ? WHERE id_al = ?";
            db.execSQL(query, new Object[]{nombre, apellidos, telefono, correo, grupo, id});

            db.close();

            Toast.makeText(this, "Alumno modificado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}