package com.sergio.project11;

import android.database.Cursor;

public class Nota {

    private int alumnoId;
    private int asignaturaId;
    private int trimestre;
    private double nota;
    private DatabaseHelper dbHelper;

    public Nota(int alumnoId, int asignaturaId, int trimestre, DatabaseHelper dbHelper) {
        this.alumnoId = alumnoId;
        this.asignaturaId = asignaturaId;
        this.trimestre = trimestre;
        this.dbHelper = dbHelper;
    }

    public double getNota() {
        Cursor cursor = dbHelper.getReadableDatabase().query("nota", new String[]{"nota"}, "alumno_id = ? AND asignatura_id = ? AND trimestre = ?", new String[]{String.valueOf(alumnoId), String.valueOf(asignaturaId), String.valueOf(trimestre)}, null, null, null);

        if (cursor.moveToFirst()) {
            nota = cursor.getDouble(0);
        }

        cursor.close();

        return nota;
    }
}
