package com.sergio.project11;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DatosAlumnoActivity extends AppCompatActivity {

    EditText etID, etNombre, etApellidos;
    TextView tvID, tvNombre, tvApellidos, tvGrupo, tvTelefono, tvCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_alumno);

        etID=findViewById(R.id.etConsultarID);
        etNombre=findViewById(R.id.etConsultarNombre);
        etApellidos=findViewById(R.id.etConsultarApellidos);
        tvGrupo=findViewById(R.id.tvConsultarGrupo);
        tvTelefono=findViewById(R.id.tvConsultarTelefono);
        tvCorreo=findViewById(R.id.tvConsultarCorreo);

    }

    //Método para consultar datos
    public void ConsultarDatos(View view){
        String codigoAlum = etID.getText().toString();
        String nombreAlum = etNombre.getText().toString();
        String apellidosAlum = etApellidos.getText().toString();

        if (!codigoAlum.isEmpty() || (!nombreAlum.isEmpty() && !apellidosAlum.isEmpty())) {
            //Si el código está insertado o el nombre y apellidos insertados:
            DatabaseHelper dbHelper = new DatabaseHelper(DatosAlumnoActivity.this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            //Consulta que busca o por el id o por nombre y apellidos, Ejecuta y guarda los resultados
            String consultaAlumno = "SELECT id_al, nombre_al, apellidos_al, telefono_al, correo_al," +
                    " grupo FROM alumno WHERE id_al = ? OR (nombre_al = ? AND apellidos_al = ?)";
            Cursor cursor = db.rawQuery(consultaAlumno, new String[]{codigoAlum, nombreAlum, apellidosAlum});

            //Si obtenemos resultado y existe el registro, extraemos datos y los mostramos en pantalla
            if (cursor.moveToFirst()) {
                String idAlumno = cursor.getString(0);
                String nombreAlumno = cursor.getString(1);
                String apellidosAlumno = cursor.getString(2);
                String telefonoAlumno = cursor.getString(3);
                String correoAlumno = cursor.getString(4);
                String grupoAlumno = cursor.getString(5);

                etID.setText(idAlumno);
                etNombre.setText(nombreAlumno);
                etApellidos.setText(apellidosAlumno);
                tvTelefono.setText("Teléfono: "+telefonoAlumno);
                tvCorreo.setText("Correo: "+correoAlumno);
                tvGrupo.setText("Grupo: "+grupoAlumno);
            } else {
                //Si no obtenemos registro, lanzamos Toast
                Toast.makeText(
                        DatosAlumnoActivity.this, "Alumno no encontrado", Toast.LENGTH_SHORT
                ).show();
            }
        } else {
            //Si no se ha insertado el ID o el nombre y apellido, lanzamos Toast
            Toast.makeText(DatosAlumnoActivity.this, "Por favor, introduce el ID o el" +
                    " nombre y apellidos del alumno", Toast.LENGTH_SHORT).show();
        }
    }

    //Método para pasar a MainActivity
    public void Menu(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    //Método para pasar a DatosNotasActivity
    public void DatosNotas(View view){
        String idAlumno=etID.getText().toString();
        String nombreAlumno=etNombre.getText().toString() + " " + etApellidos.getText().toString();
        Intent intent = new Intent(DatosAlumnoActivity.this, DatosNotasActivity.class);
        intent.putExtra("idAlumno", idAlumno); // Se agrega el ID del alumno como un extra del Intent
        intent.putExtra("nombreAlumno", nombreAlumno); //Se agrega el nombre y apellidos del alumno como un extra del Intent
        startActivity(intent);
    }
}