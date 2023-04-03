package com.sergio.project11;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EliminarActivity extends AppCompatActivity {

    private EditText etIdAlumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        etIdAlumno = findViewById(R.id.etEliminarID);
    }

    public void eliminarAlumno(View view) {
        String idAlumno = etIdAlumno.getText().toString().trim();

        if (!idAlumno.isEmpty()) {
            DatabaseHelper dbHelper = new DatabaseHelper(EliminarActivity.this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            // Verificar si el alumno existe en la base de datos
            String consultaAlumno = "SELECT nombre_al, apellidos_al FROM alumno WHERE id_al = ?";
            Cursor cursor = db.rawQuery(consultaAlumno, new String[]{idAlumno});

            // Actualizar el valor del TextView con el nombre del alumno si se encuentra en la base de datos
            if (cursor.moveToFirst()) {
                String nombreAlumno = cursor.getString(0);
                String apellidosAlumno = cursor.getString(1);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Confirmar eliminación");
                builder.setMessage("¿Estás seguro de eliminar al alumno " + nombreAlumno + " "+ apellidosAlumno + " y sus notas?");

                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Eliminar notas del alumno
                        String queryNotas = "DELETE FROM nota WHERE alumno_id = ?";
                        db.execSQL(queryNotas, new Object[]{idAlumno});

                        // Eliminar alumno
                        String queryAlumno = "DELETE FROM alumno WHERE id_al = ?";
                        db.execSQL(queryAlumno, new Object[]{idAlumno});

                        db.close();

                        Toast.makeText(EliminarActivity.this, "Alumno y sus notas eliminados correctamente", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            } else {
                Toast.makeText(this, "El ID del alumno introducido no existe", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Por favor, introduce el ID del alumno", Toast.LENGTH_SHORT).show();
        }
    }


}