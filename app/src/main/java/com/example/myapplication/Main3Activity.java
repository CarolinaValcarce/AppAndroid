package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;



public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer player;
    Button b7;
    Button b8;
    Button b9;
    Spinner spinner;
    //Declaro 3 botones un combobox desplegable y un mediaplayer.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        b7= findViewById(R.id.button7);
        b8= findViewById(R.id.button8);
        spinner= findViewById(R.id.spinner1);
        b9= findViewById(R.id.button9);
       //Relaciono las variables con las declaradas en el UI

        ArrayList <String> list= new ArrayList <>();
        list.add("OPCIONES DEL SERVICIO CREADO:");
        list.add("Sonar mp3");
        list.add("Parar mp3");
        list.add("Congelar aplicación 15 segundos");
        // Creo un array de strings con las líneas que apareceran en el desplegable

        ArrayAdapter <String> aa=new ArrayAdapter <>(this ,android.R.layout.simple_dropdown_item_1line,list) ;
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
        //Creo un ArrayAdapter para asociar cada línea del arrayList al spinner o combo box.

        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
    }
        //Asocio a cada variable el método onClick para que funcione al apretar.

    public void onClick (View view){
        switch (view.getId()){

            case R.id.button8:
                startService(new Intent (this,MyService.class));

                spinner.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (parent.getItemAtPosition(position).equals("Sonar mp3" )){
                            player= new MediaPlayer();
                            player= MediaPlayer.create(getBaseContext(),R.raw.carlitosdj );
                            try{
                                player.setLooping(true);
                                player.start();
                            } catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                        if (parent.getItemAtPosition(position).equals("Parar mp3" )){
                            if (player!=null && player.isPlaying())
                                player.stop();
                        }
                        if (parent.getItemAtPosition(position).equals("Congelar aplicación 15 segundos" )){
                                try{
                                    Thread.sleep (15000);}
                                catch (InterruptedException e){
                                    e.printStackTrace();}
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
                break;

        /*
            Creo un switch dependiendo del botón que apriete el usuario y como me pide crear servicio con el primer botón muestro un Toast al crear servicio.
            Tras crear el servicio apretando el botón podemos desplegar el spinner y seleccionar el servicio.
            Hay tres disponibles. Si el item seleccionado al cliclar coincide con "Sonar mp3", activo el mediaplayer.
            Si el item clicado es "Parar mp3" paro el media player.
            Y si es el último item, paralizo toda la aplicación por 15 segundos.
            */
            case R.id.button7:
                stopService(new Intent (this, MyService.class));
                if (player!=null){
                    try{
                        player.release();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
            /*
             Con este botón detengo el servicio solicitado en la PAC y lo notifico con un Toast.
             Una vez detenido el servicio, aunque desplegemos las opciones de servicio creado, estas no funcionarán.
            Si tenemos el Servicio activo en cualquiera de las tres opciones este se detendrá, excepto la opción de congelar la aplicación que funcionará siempre.
            */
            case R.id.button9:
                startActivity(new Intent(Main3Activity.this, MainActivity.class));
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }
}           //Por último, con el último botón pasamos a la Actividad principal a través de un "intent".
