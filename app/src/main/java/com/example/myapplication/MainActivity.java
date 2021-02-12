package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//Importamos las librerias

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button b1;
    Button b2;
    ImageView iv;
//Declaramos las variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Declaramos el método onCreate que deriva de la clase padre. Y asociamos una UI a la actividad.

        textView=findViewById(R.id.textView);
        b1= findViewById(R.id.button);
        b2= findViewById(R.id.button2);
        iv= findViewById (R.id.imageView);
        //Declaramos dos botones, una imagen y un recuadro para notificaciones

        b1.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra ("Actividad", "Estamos en la Actividad 2");
                startActivity(intent);
            }
        });
        b2.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, Main3Activity.class);
                startActivity(intent);
            }
        });
        /*
        Para cada botón asociamos una acción al ser apretado.
         En el primero, pasamos de la actividad principal a la actividad 2 usando el intent.
        Y en la segunda pasamos al clicar de la actividad principak a la actividad 3.
        */

        textView.append ("Bienvenidos a mi aplicación");
        Toast.makeText(this, "Bienvenidos a mi aplicación", Toast.LENGTH_SHORT).show();
    }
        /*
    En el recuadro de notificaciones doy la bienvenida a mi aplicación.
     Además he creado un Toast, notificación que desaparece en unos 10 segundos pues la uso con LENGTH_SHORT.
    */
}
