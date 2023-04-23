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
        db.execSQL("create table alumno (id_al integer primary key, nombre_al text not null, apellidos_al text not null," +
                " telefono_al text, correo_al text, grupo text not null)");
        db.execSQL("create table asignatura (id_asig integer primary key, nombre_asig text)");
        db.execSQL("create table nota (alumno_id integer not null, asignatura_id integer not null, trimestre integer not null," +
                " nota real default 0.0, foreign key (alumno_id) references alumno(id_al)," +
                " foreign key (asignatura_id) references asignatura(id_asig), primary key (alumno_id, asignatura_id, trimestre))");
        db.execSQL("insert into asignatura (id_asig, nombre_asig) values (1, 'Matemáticas')");
        db.execSQL("insert into asignatura (id_asig, nombre_asig) values (2, 'Lengua')");
        db.execSQL("insert into asignatura (id_asig, nombre_asig) values (3, 'Biología')");
        db.execSQL("insert into asignatura (id_asig, nombre_asig) values (4, 'Inglés')");
        db.execSQL("insert into asignatura (id_asig, nombre_asig) values (5, 'Educación Física')");

        //Inserción de alumnos de prueba para comprobar el funcionamiento de la aplicación
        db.execSQL("insert into alumno (nombre_al, apellidos_al, telefono_al, correo_al, grupo) values ('Carla', 'León López', '678129322', 'carlaleon@cogsr.es', '1A')"); //id1
        db.execSQL("insert into alumno (nombre_al, apellidos_al, telefono_al, correo_al, grupo) values ('Juan Carlos', 'Pastor Puig', '677485328', 'juancarlospastor@cogsr.es', '1A')"); //id2
        db.execSQL("insert into alumno (nombre_al, apellidos_al, telefono_al, correo_al, grupo) values ('Manuela', 'Cortés de la Fuente', '689471335', 'manuelacortes@cogsr.es', '1A')"); //id3
        db.execSQL("insert into alumno (nombre_al, apellidos_al, telefono_al, correo_al, grupo) values ('Verónica', 'Hernández Pérez', '698471124', 'veronicahernandez@cogsr.es', '1B')"); //id4
        db.execSQL("insert into alumno (nombre_al, apellidos_al, telefono_al, correo_al, grupo) values ('Valeria', 'Márquez Morón', '689741255', 'valeriamarquez@cogsr.es', '1B')"); //id5
        db.execSQL("insert into alumno (nombre_al, apellidos_al, telefono_al, correo_al, grupo) values ('Manuel', 'Jimenez Navarro', '655214376', 'manuelnavarro@cogsr.es', '1B')"); //id6
        db.execSQL("insert into alumno (nombre_al, apellidos_al, telefono_al, correo_al, grupo) values ('Miguel', 'Piña López', '615477238', 'miguelpinan@cogsr.es', '1C')"); //id7
        db.execSQL("insert into alumno (nombre_al, apellidos_al, telefono_al, correo_al, grupo) values ('Margarita', 'Herrero Santana', '663422188', 'margaritaherrero@cogsr.es', '1C')"); //id8
        db.execSQL("insert into alumno (nombre_al, apellidos_al, telefono_al, correo_al, grupo) values ('Sofía', 'Cabrera Rodríguez', '633451278', 'sofiacabrera@cogsr.es', '1C')"); //id9
        db.execSQL("insert into alumno (nombre_al, apellidos_al, telefono_al, correo_al, grupo) values ('Martín', 'Gimenez del Pinar', '621545523', 'martingimenez@cogsr.es', '2A')"); //id10
        db.execSQL("insert into alumno (nombre_al, apellidos_al, telefono_al, correo_al, grupo) values ('Noelia', 'Serrano Román', '671482549', 'noeliaserrano@cogsr.es', '2A')"); //id11
        db.execSQL("insert into alumno (nombre_al, apellidos_al, telefono_al, correo_al, grupo) values ('Sonia', 'Soler Nieto', '6887493154', 'soniasoler@cogsr.es', '2A')"); //id12
        db.execSQL("insert into alumno (nombre_al, apellidos_al, telefono_al, correo_al, grupo) values ('Mateo', 'Carmona Muñoz', '624871214', 'mateocarmona@cogsr.es', '2B')"); //id13
        db.execSQL("insert into alumno (nombre_al, apellidos_al, telefono_al, correo_al, grupo) values ('Carlos', 'Sánchez García', '632325489', 'carlossanchez@cogsr.es', '2B')"); //id14
        db.execSQL("insert into alumno (nombre_al, apellidos_al, telefono_al, correo_al, grupo) values ('Emilio', 'Ibañez Zaragoza', '645665684', 'emilioibanez@cogsr.es', '2B')"); //id15
        db.execSQL("insert into alumno (nombre_al, apellidos_al, telefono_al, correo_al, grupo) values ('María Cristina', 'Madrid Fuentes', '685469321', 'mariacristinamadrid@cogsr.es', '2C')"); //id16
        db.execSQL("insert into alumno (nombre_al, apellidos_al, telefono_al, correo_al, grupo) values ('Celia', 'Díaz Rodríguez', '698563248', 'celiadiazn@cogsr.es', '2C')"); //id17
        db.execSQL("insert into alumno (nombre_al, apellidos_al, telefono_al, correo_al, grupo) values ('Agustín', 'Domínguez Ríos', '654126558', 'agustindominguez@cogsr.es', '2C')"); //id18
        db.execSQL("insert into alumno (nombre_al, apellidos_al, telefono_al, correo_al, grupo) values ('Rosa María', 'García', '632584636', 'rosamariagarcia@cogsr.es', '3A')"); //id19
        db.execSQL("insert into alumno (nombre_al, apellidos_al, telefono_al, correo_al, grupo) values ('Guillermo', 'Muñoz Gómez', '658977452', 'guillermomuñoz@cogsr.es', '3A')"); //id20
        db.execSQL("insert into alumno (nombre_al, apellidos_al, telefono_al, correo_al, grupo) values ('Beatriz', 'Pacual Garrido', '689452122', 'beatrizpacual@cogsr.es', '3A')"); //id21
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
