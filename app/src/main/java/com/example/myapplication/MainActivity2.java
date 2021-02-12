package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{
// Creo una clase que extiende de la general para realizar actividades y que implementa el método OnClickListener.

    Button b3, b4,b5,b6;
    EditText EditTextID, EditTextNombre,EditTextApellidos,EditTextTelefono;
//Declaro todas las variables: 4 botones y 4 textos editables que serán usados principalmente por 2 botones, para rellenar los datos antes de presionarles.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toast.makeText(this,"Bienvenido a la Actividad 2",Toast.LENGTH_LONG).show();
    //Declaro el método OnCreate y asocio una UI a la actividad 2. Hago aparecer una notificación momentanea informativa (Toast) de bienvenida

        b3= findViewById(R.id.button3);
        b4= findViewById(R.id.button4);
        b5= findViewById(R.id.button5);
        b6= findViewById(R.id.button6);
        EditTextID=findViewById(R.id.EtID);
        EditTextNombre= findViewById(R.id.EtNombre);
        EditTextApellidos=findViewById(R.id.EtApellidos);
        EditTextTelefono=findViewById(R.id.EtTelefono);
    //Asocio las variables declaradas con su elemento en la UI.

        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
    }
    //Acudo al método implementado para asociar a nuestras variables una acción al ser presionadas.

    public void onClick(View view){

            switch (view.getId ()){
                case R.id.button3:
                    Toast.makeText(this,"Has creado una tabla", Toast.LENGTH_LONG).show();
                break;
                case R.id.button4:
                    CrearUsuario();
                break;
                case R.id.button5:
                    ConsultarUsuario();
                break;
                case R.id.button6:
                    Intent intent= new Intent(MainActivity2.this, MainActivity.class);
                    startActivity(intent);
                break;
            }
    }

    /*
    Creo un switch con las diferentes opciones que se pueden presentar dependiendo del botón presionado.
    En el primero se crea la tabla. Que por defecto viene creada en la clase DB que extiende de la BBDD local de SQLite.
    Como previamente a guardar un registro o a consultarlo se debe haber creado la tabla en la BBDD, me limito a informar con un Toast que la tabla ha sido creada.
    Como comento la tabla se crea en BD.java.
    Posteriormente con el botón 4, creamos el usuario de la tabla. Hacemos la llamada al método CrearUsuario() que se explica más abajo.
    Con el botón 5 consultamos el usuario, introduciendo el ID y presionando el botón de consultar del UI. Aquí solo hacemos la llamada al método que invocamos abajo.
    Y con el último botón pasamos con un intent a la actividad principal.
    */
    private void ConsultarUsuario() {
        final DB bd= new DB (getApplicationContext());
        try{
            String [] argsel= {EditTextID.getText().toString()};
            SQLiteDatabase db= bd.getReadableDatabase ();
            String [] projection= {DB.DatosTabla.COLUMNA_NOMBRE, DB.DatosTabla.COLUMNA_APELLIDOS, DB.DatosTabla.COLUMNA_TELEFONO};
            Cursor c= db.query (DB.DatosTabla.NOMBRE_TABLA,projection,DB.DatosTabla.COLUMNA_ID+"=?",argsel ,null,null,null);
            c.moveToFirst();
            EditTextNombre.setText(c.getString(1));
            EditTextApellidos.setText(c.getString(2));
            EditTextTelefono.setText(c.getString(3));
            c.close();
            db.close();
        }catch (Exception e) {
            Toast.makeText(this,"El registro buscado no existe", Toast.LENGTH_LONG).show();
        }
    }
    /*
    Desarrollamos el método ConsultarUsuario. Nuestra base datos llamada bd de la clase BD que implementa el "SQLiteOpenHelper" la hacemos leible a través del método getReadableDatabase.
    Recogemos la információn introducida como ID la hacemos leible llamándola argsel.
     Mientras que llamamos projection a los datos de un registro introducidos tanto en los campos: nombre como apellidos y teléfono.
    Creamos un cursor llamado c, al que le pasamos como parámetros, la tabla sobre la que trabajamos, los campos de un registro sin la clave primaria, el valor de la propia clave primaria a elegir, el campo de la clave primaria ya introducido por el usuario, y otros 3 parámetros que por el momento no son relevantes pues no restringimos por tipos de registro.
    También podríamos haber llamado a rawquery a la que pasariamos solamente 3 parámetros.
    Movemos el cursor a la primera posición, que es la 0.
    Rellenamos los campos de nombre, apellidos y teléfono, con los valores recibidos tanto de la columna 1, como 2, como3.
    Cerramos el cursor, y cerramos la base de datos.
    Si el registro no existiese se informaría con un Toast.
    */

    private void CrearUsuario() {
        final DB bd= new DB (getApplicationContext());
        SQLiteDatabase db= bd.getWritableDatabase ();
        ContentValues valores= new ContentValues();
        valores.put (DB.DatosTabla.COLUMNA_ID, EditTextID.getText().toString());
        valores.put (DB.DatosTabla.COLUMNA_NOMBRE, EditTextNombre.getText().toString());
        valores.put (DB.DatosTabla.COLUMNA_APELLIDOS, EditTextApellidos.getText().toString());
        valores.put (DB.DatosTabla.COLUMNA_TELEFONO, EditTextTelefono.getText().toString());

        long IdGuardado= db.insert  (DB.DatosTabla.NOMBRE_TABLA,DB.DatosTabla.COLUMNA_ID,valores);
        db.close();
        Toast.makeText(getApplicationContext(),"Se ha guardado el dato"+ IdGuardado,Toast.LENGTH_LONG).show();
    }
    /*
     En  el método CrearUsuario, accedo a mi base de datos llamada bd y la hago leible con el método "getWritableDatabase" del "SQLiteDatabase".
    Con ContentValues introduzco la tupla o cada valor recogido en cada uno de los campos (segundo parámetro) con su correspondiente columna o campo de la tabla (primer parámetro).
    Más tarde creo una variable "IdGuardado" que identifica el ID de todo un registro introducido.
    Cierro la db. Y notifico con Toast que se ha creado el registro 1, o 2 o 3 dependiendo del ID.
    */

}




