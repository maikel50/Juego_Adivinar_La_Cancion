package com.example.juego_adivinar_la_cancion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
Button btnPlay,btnStop,btnEnviar,btnStar;
RadioButton opcion1,opcion2,opcion3;

MediaPlayer media;
private final int[] listaAudios = {R.raw.audio1, R.raw.audio2, R.raw.audio3,R.raw.audio4, R.raw.audio5,R.raw.audio6,
        R.raw.audio7,R.raw.audio8, R.raw.audio9,
        R.raw.audio10, R.raw.audio11,R.raw.audio12, R.raw.audio13,R.raw.audio14, R.raw.audio15,R.raw.audio16};
    private int indiceCancionActual = 0;
ArrayList<String> nombresPortadas = new ArrayList<>();
    private int indicePortadaActual = 0;
    String respuesta = "",respuesta2 ="",respuesta3 ="";
    String cambio2 = "";
    String nombrePortada = "";
    boolean respuestaCorrecta = false;
private final int REQUEST_CODE_PERMISSIONS = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPlay=findViewById(R.id.btnPlay);
        btnStop=findViewById(R.id.btnStop);
        btnEnviar=findViewById(R.id.btnEnviar);
        btnStar = findViewById(R.id.btnStar);
        opcion1=findViewById(R.id.opcion1);
        opcion2=findViewById(R.id.opcion2);
        opcion3=findViewById(R.id.opcion3);

for(int i=0; i<16 ; i++){


        nombresPortadas.add("portada1");
        nombresPortadas.add("portada2");
        nombresPortadas.add("portada3");
        nombresPortadas.add("portada4");
        nombresPortadas.add("portada5");
        nombresPortadas.add("portada6");
        nombresPortadas.add("portada7");
        nombresPortadas.add("portada8");
        nombresPortadas.add("portada9");
        nombresPortadas.add("portada10");
        nombresPortadas.add("portada11");
        nombresPortadas.add("portada12");
        nombresPortadas.add("portada13");
        nombresPortadas.add("portada14");
        nombresPortadas.add("portada15");
        nombresPortadas.add("portada16");
        if(ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{
                            android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            android.Manifest.permission.RECORD_AUDIO,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },REQUEST_CODE_PERMISSIONS);
        }
    btnStar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            nombrePortada = cambiarPortadaAleatoriamente(nombresPortadas);
            cambiarOpcion(nombrePortada);
            btnStar.setVisibility(View.INVISIBLE);
        }
    });

    btnStop.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            pauseLocal(view);
        }
    });
    btnPlay.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            playLocal(view);

        }
    });

    opcion1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            respuesta = opcion1.getText().toString();

        }
    });
    opcion2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            respuesta = opcion2.getText().toString();

        }
    });
    opcion3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            respuesta = opcion3.getText().toString();

        }
    });
    btnEnviar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

             respuestaCorrecta = false;

            if (nombrePortada.equals("portada1")) {
                if (respuesta.equals("Bichiyal")) {
                    respuestaCorrecta = true;
                }
            } else if (nombrePortada.equals("portada2")) {
                if (respuesta.equals("Moscow Mule")) {
                    respuestaCorrecta = true;
                }
            } else  if (nombrePortada.equals("portada3")) {
                if (respuesta.equals("Se fue")) {
                    respuestaCorrecta = true;
                }
            }else if (nombrePortada.equals("portada4")) {
                if (respuesta.equals("Club Cant Hanlde Me")) {
                    respuestaCorrecta = true;
                }
            } else  if (nombrePortada.equals("portada5")) {
                if (respuesta.equals("International Love")) {
                    respuestaCorrecta = true;
                }
            }else if (nombrePortada.equals("portada6")) {
                if (respuesta.equals("The Nights")) {
                    respuestaCorrecta = true;
                }
            } else  if (nombrePortada.equals("portada7")) {
                if (respuesta.equals("Piel De Cordero")) {
                    respuestaCorrecta = true;
                }
            }else if (nombrePortada.equals("portada8")) {
                if (respuesta.equals("Mami")) {
                    respuestaCorrecta = true;
                }
            } else  if (nombrePortada.equals("portada9")) {
                if (respuesta.equals("Tokyo")) {
                    respuestaCorrecta = true;
                }
            }else if (nombrePortada.equals("portada10")) {
                if (respuesta.equals("Borraxxa")) {
                    respuestaCorrecta = true;
                }
            } else  if (nombrePortada.equals("portada11")) {
                if (respuesta.equals("Fantasias")) {
                    respuestaCorrecta = true;
                }
            }else if (nombrePortada.equals("portada12")) {
                if (respuesta.equals("Seya")) {
                    respuestaCorrecta = true;
                }
            } else  if (nombrePortada.equals("portada13")) {
                if (respuesta.equals("Casanova")) {
                    respuestaCorrecta = true;
                }
            }else if (nombrePortada.equals("portada14")) {
                if (respuesta.equals("El Cuarto De Ferxoo")) {
                    respuestaCorrecta = true;
                }
            } else  if (nombrePortada.equals("portada15")) {
                if (respuesta.equals("No Te Quieren Conmigo")) {
                    respuestaCorrecta = true;
                }
            }else if (nombrePortada.equals("portada16")) {
                if (respuesta.equals("Corazon Partio")) {
                    respuestaCorrecta = true;
                }
            }
            if (respuestaCorrecta == true) {
                nombrePortada = cambiarPortadaAleatoriamente(nombresPortadas);
                cambiarOpcion(nombrePortada);
                siguienteCancion();
                nombresPortadas.remove(nombrePortada);
                Toast.makeText(MainActivity.this, "Opción correcta.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Opción incorrecta, intenta de nuevo.", Toast.LENGTH_SHORT).show();
            }
        }
    });
}
}

    private String cambiarPortadaAleatoriamente(ArrayList<String> nombresPortadas) {
        if (indicePortadaActual < nombresPortadas.size()) {
            String nombreImagen = "portada" + (indicePortadaActual + 1);
            int idImagen = getResources().getIdentifier(nombreImagen, "drawable", getPackageName());

            ImageView imageViewPortada = findViewById(R.id.imagen);
            imageViewPortada.setImageResource(idImagen);

            indicePortadaActual++;

            return nombreImagen;
        } else {
            return null;
        }
    }

    private void cambiarOpcion(String nombresPortadas) {

        if(nombresPortadas.equals("portada1")){
            opcion1.setText("Bichiyal");
            opcion2.setText("La Zona");
            opcion3.setText("Soy Peor");
        }else if(nombresPortadas.equals("portada2")){
            opcion1.setText("Party");
            opcion2.setText("Moscow Mule");
            opcion3.setText("Amanece");
        }else if(nombresPortadas.equals("portada3")){
            opcion1.setText("Gente");
            opcion2.setText("Se fue");
            opcion3.setText("Amori infiniti");
        }else if(nombresPortadas.equals("portada4")){
            opcion1.setText("Club Cant Hanlde Me");
            opcion2.setText("Low ");
            opcion3.setText("Wild One");
        }else if(nombresPortadas.equals("portada5")){
            opcion1.setText("Give Me Everything");
            opcion2.setText("Hey Baby");
            opcion3.setText("International Love");
        }
        else if(nombresPortadas.equals("portada6")){
            opcion1.setText("The Nights");
            opcion2.setText("The Days");
            opcion3.setText("Amanece");
        }else if(nombresPortadas.equals("portada7")){
            opcion1.setText("Columbia");
            opcion2.setText("Piel De Cordero");
            opcion3.setText("Punto G");
        }else if(nombresPortadas.equals("portada8")){
            opcion1.setText("Salvaje");
            opcion2.setText("Mi Luz");
            opcion3.setText("Mami");
        }else if(nombresPortadas.equals("portada9")){
            opcion1.setText("Dilema");
            opcion2.setText("Tokyo");
            opcion3.setText("Ley Seca");
        }else if(nombresPortadas.equals("portada10")){
            opcion1.setText("Ateo");
            opcion2.setText("Borraxxa");
            opcion3.setText("Fresh Kerias");
        }else if(nombresPortadas.equals("portada11")){
            opcion1.setText("Fantasias");
            opcion2.setText("Desesperados");
            opcion3.setText("Detective");
        }else if(nombresPortadas.equals("portada12")){
            opcion1.setText("Pelele");
            opcion2.setText("Seya");
            opcion3.setText("Sigue");
        }else if(nombresPortadas.equals("portada13")){
            opcion1.setText("Gente");
            opcion2.setText("Sans Visa");
            opcion3.setText("Casanova");
        }else if(nombresPortadas.equals("portada14")){
            opcion1.setText("50 Palos");
            opcion2.setText("Alakran");
            opcion3.setText("El Cuarto De Ferxoo");
        }else if(nombresPortadas.equals("portada15")){
            opcion1.setText("No Te Quieren Conmigo");
            opcion2.setText("Si no te quiere");
            opcion3.setText("Soltera");
        }else if(nombresPortadas.equals("portada16")){
            opcion1.setText("Si hay Dios...");
            opcion2.setText("Corazon Partio");
            opcion3.setText("Amiga mia");
        }
    }
    private void siguienteCancion() {
        if (media != null) {
            media.release();
        }
        if (indiceCancionActual < listaAudios.length) {
            int idAudio = listaAudios[indiceCancionActual];
            media = MediaPlayer.create(this, idAudio);
            indiceCancionActual++;
        }
        if (media != null) {
            media.start();
        }
    }

    public void playLocal(View view) {
        try {
            if (media == null) {
                siguienteCancion();
            }

            if (media != null && !media.isPlaying()) {
                media.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("MediaPlayerError", "Error al reproducir audio: " + e.getMessage());
        }
    }


    public void pauseLocal(View view){
        if(media.isPlaying()){
            media.pause();
        }
    }
    public void stopLocal(View view){
        media.stop();
        media.release();
        media = null;
    }
}