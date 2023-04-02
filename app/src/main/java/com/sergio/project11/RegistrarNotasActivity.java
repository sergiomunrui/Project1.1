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

public class RegistrarNotasActivity extends AppCompatActivity {

    private Spinner spAsignatura, spTrimestre;
    private ArrayAdapter<CharSequence> adapterAsignatura, adapterTrimestre;

    private EditText etID, etNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_notas);

        etID=findViewById(R.id.etID);
        etNota=findViewById(R.id.etNota);

        spAsignatura = findViewById(R.id.spAsignatura);
        spTrimestre = findViewById(R.id.spTrimestre);


        adapterAsignatura = ArrayAdapter.createFromResource(
                this, R.array.spinner_items_asig, R.layout.spinner_item);
        adapterAsignatura.setDropDownViewResource(R.layout.spinner_item);
        spAsignatura.setAdapter(adapterAsignatura);

        adapterTrimestre = ArrayAdapter.createFromResource(
                this, R.array.spinner_items_tri, R.layout.spinner_item);
        adapterTrimestre.setDropDownViewResource(R.layout.spinner_item);
        spTrimestre.setAdapter(adapterTrimestre);

    }

    public void registrarNotaAsignatura(View view) {
        int idAlumno=0;
        double nota=0;
        int trimestreSeleccionado=0;
        int asignaturaSeleccionada=0;
        String campoId = etID.getText().toString().trim();
        String campoNota = etNota.getText().toString().trim();
        String asignaturaSpinner = spAsignatura.getSelectedItem().toString();
        String trimestreSpinner = spTrimestre.getSelectedItem().toString();


        //Pasamos de cadena de texto a int y double respectivamente
        idAlumno=Integer.parseInt(campoId);
        nota=Double.parseDouble(campoNota);

        //Pasamos de cadena de texto lo seleccionado a int
        if(trimestreSpinner.equals("Primer trimestre")){
            trimestreSeleccionado=1;
        }if(trimestreSpinner.equals("Segundo trimestre")){
            trimestreSeleccionado=2;
        }if(trimestreSpinner.equals("Tercer trimestre")){
            trimestreSeleccionado=3;
        }

        //Pasamos de cadena de texto lo seleccionado a int
        if(asignaturaSpinner.equals("Matemáticas")){
            asignaturaSeleccionada=1;
        }if(asignaturaSpinner.equals("Lengua")){
            asignaturaSeleccionada=2;
        }if(asignaturaSpinner.equals("Biología")){
            asignaturaSeleccionada=3;
        }if(asignaturaSpinner.equals("Inglés")){
            asignaturaSeleccionada=4;
        }if(asignaturaSpinner.equals("Educación Física")){
            asignaturaSeleccionada=5;
        }

        if (idAlumno!= 0 && nota!= 0 && asignaturaSeleccionada != 0 && trimestreSeleccionado != 0) {
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            // Aquí necesitarías realizar la consulta SQL adecuada según tu diseño de base de datos
            String query = "INSERT INTO nota (alumno_id, asignatura_id, trimestre, nota) VALUES (?, ?, ?, ?)";
            db.execSQL(query, new Object[]{idAlumno, asignaturaSeleccionada, trimestreSeleccionado, nota});

            db.close();

            Toast.makeText(this, "Nota registrada correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Por favor, rellena todos los campos de forma correcta", Toast.LENGTH_SHORT).show();
        }

    }

    //Método para pasar a MainActivity
    public void Menu(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    //Método para pasar a RegistrarActivity
    public void Atrás(View view){
        Intent i = new Intent(this, RegistrarActivity.class);
        startActivity(i);
    }

}