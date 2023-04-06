package com.sergio.project11;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "colegio.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creación de las tabla de datos al iniciar el programa
        db.execSQL("create table alumno (id_al integer primary key, nombre_al text not null, apellidos_al text not null, telefono_al text, correo_al text, grupo text not null)");
        db.execSQL("create table asignatura (id_asig integer primary key, nombre_asig text)");
        db.execSQL("create table profesor (id_prof integer primary key, dni text not null, nombre_prof text not null, apellidos_prof text not null, correo_prof text not null, telefono_prof text not null)");
        db.execSQL("create table nota (alumno_id integer not null, asignatura_id integer not null, trimestre integer not null, nota real default 0.0, foreign key (alumno_id) references alumno(id_al), foreign key (asignatura_id) references asignatura(id_asig), primary key (alumno_id, asignatura_id, trimestre))");
        db.execSQL("create table profesor_asignatura (profesor_id integer, asignatura_id integer, foreign key (profesor_id) references profesor(id_prof), foreign key (asignatura_id) references asignatura(id_asig), primary key (profesor_id, asignatura_id))");
        db.execSQL("insert into asignatura (id_asig, nombre_asig) values (1, 'Matemáticas')");
        db.execSQL("insert into asignatura (id_asig, nombre_asig) values (2, 'Lengua')");
        db.execSQL("insert into asignatura (id_asig, nombre_asig) values (3, 'Biología')");
        db.execSQL("insert into asignatura (id_asig, nombre_asig) values (4, 'Inglés')");
        db.execSQL("insert into asignatura (id_asig, nombre_asig) values (5, 'Educación Física')");

        //Inserción de alumnos de prueba para comprobar el funcionamiento de la aplicación

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS profesor");
        db.execSQL("DROP TABLE IF EXISTS asignatura");
        db.execSQL("DROP TABLE IF EXISTS profesor_asignatura");
        db.execSQL("DROP TABLE IF EXISTS alumno");
        db.execSQL("DROP TABLE IF EXISTS nota");

        onCreate(db);

    }
}
