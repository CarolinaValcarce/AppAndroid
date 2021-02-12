package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DB extends SQLiteOpenHelper{

    public static abstract class DatosTabla implements BaseColumns{

    public static final String NOMBRE_TABLA = "Alumnosinformática";
    public static final String COLUMNA_ID ="ID";
    public static final String COLUMNA_NOMBRE = "Nombre";
    public static final String COLUMNA_APELLIDOS = "Apellidos";
    public static final String COLUMNA_TELEFONO = "Teléfono";
    /*
        La clase DB extiende y por tanto aplica el SQLiteOpenHelper. Creamos la clase "DatosTabla" para tras declarar las variables que son cada uno de los campos de la tabla pasarles como columnas.
         Pues, la clase DatosTabla implementa BaseColumns.
         Una observación, el código es correcto pero debería aparecer el String entre comillas en verde, pues son los nombres de los campos del registro en verde para recogerlos como tal.
          Y por algún motivo el programa no lo reconoce, es decir que ni siquiera llega a crear la tabla, a pesar de que al pasar el AVD con el toast de que la tabla está creada.
          Tampoco reconoce en la columna de ID el tipo de variable integer pues también debería de aparecer en color verde como "PRIMARY KEY".
          Y como TEXT. Pero he repasado el código varias veces y no veo error.
        */

    public static final String CrearTabla2="CREATE TABLE "+ DatosTabla.NOMBRE_TABLA + "(" + DatosTabla.COLUMNA_ID + " INTEGER PRIMARY KEY, " + DatosTabla.COLUMNA_NOMBRE + " TEXT , " + DatosTabla.COLUMNA_APELLIDOS + " TEXT, " + DatosTabla.COLUMNA_TELEFONO + " INTEGER )";
    public static final String SQL_DELETE_ENTRIES="DROP TABLE IF EXISTS " + DatosTabla.NOMBRE_TABLA;
    }
    //Creamos la tabla como se haría en SQL y lo guardamos como "CrearTabla2". Llamamos la clave primaria ID que se autoincremnte automáticamente, añadimos otras claves como "Nombre", "Apellidos" y "Teléfono".
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME= "Estudiantes.db";

    //Instanciamos la versión y el nombre de la BBDD.

    public DB(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    //Pasamos los parámetros de la BD heredados de SQLiteOpenHelper, el contexto, el nombre de la BBDD, "cursofactory" y la versión.

    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL(DatosTabla.CrearTabla2);
    }
    //En el método "onCreate" ejecutamos la instrucción SQL de crear la tabla.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DatosTabla.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    /*
    El método onUpgrade se crea para cuando se actualizan las BBDD, pasando la versión anterior y la nueva como parámetros.
     Se borran completamente las tablas ya creadas y se vuelve a llamar al método "onCreate" para crearlas con la nueva versión.
    */
}
