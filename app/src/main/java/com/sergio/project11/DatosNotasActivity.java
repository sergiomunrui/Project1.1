package com.sergio.project11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class DatosNotasActivity extends AppCompatActivity {

    private TextView tvIdAlumno, tvMat1, tvMat2, tvMat3, tvLeng1, tvLeng2, tvLeng3, tvBio1, tvBio2, tvBio3, tvIng1, tvIng2, tvIng3, tvEf1, tvEf2, tvEf3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_notas);

        tvIdAlumno = findViewById(R.id.tvIdAlumno);
        tvMat1 = findViewById(R.id.tvMatT1);
        tvMat2 = findViewById(R.id.tvMatT2);
        tvMat3 = findViewById(R.id.tvMatT3);
        tvLeng1 = findViewById(R.id.tvLengT1);
        tvLeng2 = findViewById(R.id.tvLengT2);
        tvLeng3 = findViewById(R.id.tvLengT3);
        tvBio1 = findViewById(R.id.tvBioT1);
        tvBio2 = findViewById(R.id.tvBioT2);
        tvBio3 = findViewById(R.id.tvBioT3);
        tvIng1 = findViewById(R.id.tvIngT1);
        tvIng2 = findViewById(R.id.tvIngT2);
        tvIng3 = findViewById(R.id.tvIngT3);
        tvEf1 = findViewById(R.id.tvEfT1);
        tvEf2 = findViewById(R.id.tvEfT2);
        tvEf3 = findViewById(R.id.tvEfT3);

        // Recuperar el ID del alumno desde la actividad DatosAlumnoActivity
        String idAlumno = getIntent().getStringExtra("idAlumno");
        tvIdAlumno.setText(idAlumno);

        int idAlumnoInt = Integer.parseInt(idAlumno);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        Nota notaMatT1 = new Nota(idAlumnoInt, 1, 1, dbHelper);
        Nota notaMatT2 = new Nota(idAlumnoInt, 1, 2, dbHelper);
        Nota notaMatT3 = new Nota(idAlumnoInt, 1, 3, dbHelper);
        Nota notaLengT1 = new Nota(idAlumnoInt, 2, 1, dbHelper);
        Nota notaLengT2 = new Nota(idAlumnoInt, 2, 2, dbHelper);
        Nota notaLengT3 = new Nota(idAlumnoInt, 2, 3, dbHelper);
        Nota notaBioT1 = new Nota(idAlumnoInt, 3, 1, dbHelper);
        Nota notaBioT2 = new Nota(idAlumnoInt, 3, 2, dbHelper);
        Nota notaBioT3 = new Nota(idAlumnoInt, 3, 3, dbHelper);
        Nota notaIngT1 = new Nota(idAlumnoInt, 4, 1, dbHelper);
        Nota notaIngT2 = new Nota(idAlumnoInt, 4, 2, dbHelper);
        Nota notaIngT3 = new Nota(idAlumnoInt, 4, 3, dbHelper);
        Nota notaEfT1 = new Nota(idAlumnoInt, 5, 1, dbHelper);
        Nota notaEfT2 = new Nota(idAlumnoInt, 5, 2, dbHelper);
        Nota notaEfT3 = new Nota(idAlumnoInt, 5, 3, dbHelper);

        // Obtener las notas y establecerlas en los TextView correspondientes
        tvMat1.setText(String.valueOf(notaMatT1.getNota()));
        tvMat2.setText(String.valueOf(notaMatT2.getNota()));
        tvMat3.setText(String.valueOf(notaMatT3.getNota()));
        tvLeng1.setText(String.valueOf(notaLengT1.getNota()));
        tvLeng2.setText(String.valueOf(notaLengT2.getNota()));
        tvLeng3.setText(String.valueOf(notaLengT3.getNota()));
        tvBio1.setText(String.valueOf(notaBioT1.getNota()));
        tvBio2.setText(String.valueOf(notaBioT2.getNota()));
        tvBio3.setText(String.valueOf(notaBioT3.getNota()));
        tvIng1.setText(String.valueOf(notaIngT1.getNota()));
        tvIng2.setText(String.valueOf(notaIngT2.getNota()));
        tvIng3.setText(String.valueOf(notaIngT3.getNota()));
        tvEf1.setText(String.valueOf(notaEfT1.getNota()));
        tvEf2.setText(String.valueOf(notaEfT2.getNota()));
        tvEf3.setText(String.valueOf(notaEfT3.getNota()));




    }

}