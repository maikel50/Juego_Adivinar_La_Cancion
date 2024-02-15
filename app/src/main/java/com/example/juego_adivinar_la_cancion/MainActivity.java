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
Button btnPlay,btnReplay,btnStop,btnEnviar;
RadioButton opcion1,opcion2,opcion3;
MediaPlayer media;
private final int[] listaAudios = {R.raw.audio1, R.raw.audio2};

ArrayList<String> nombresPortadas = new ArrayList<>();
Boolean acierto1 = false, acierto2 = false;
private final int REQUEST_CODE_PERMISSIONS = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPlay=findViewById(R.id.btnPlay);
        btnReplay=findViewById(R.id.btnReplay);
        btnStop=findViewById(R.id.btnStop);
        btnEnviar=findViewById(R.id.btnEnviar);

        opcion1=findViewById(R.id.opcion1);
        opcion2=findViewById(R.id.opcion2);
        opcion3=findViewById(R.id.opcion3);

        nombresPortadas.add("portada1");
        nombresPortadas.add("portada2");

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

        String nombrePortada = cambiarPortadaAleatoriamente(nombresPortadas);
        cambiarOpcion(nombrePortada);
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
        btnReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopLocal(view);
            }
        });
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombrePortada = cambiarPortadaAleatoriamente(nombresPortadas);
                if(nombrePortada.equals("portada1")){
                    cambiarOpcion(nombrePortada);
                    if(opcion1.isChecked()){
                        cambiarCancionAleatoriamente();
                        eliminarCancionActual();
                        nombresPortadas.remove(nombrePortada);
                    }
                }else  if(nombrePortada.equals("portada2")){
                    cambiarOpcion(nombrePortada);
                    if(opcion2.isChecked()){
                        cambiarCancionAleatoriamente();
                        eliminarCancionActual();
                        nombresPortadas.remove(nombrePortada);

                    }
                }
                Toast.makeText(MainActivity.this, "Se ha pulsado el botón Enviar", Toast.LENGTH_SHORT).show();

            }
        });

    }
    private String cambiarPortadaAleatoriamente(ArrayList<String> nombresPortadas) {
        Random random = new Random();
        int indiceAleatorio = random.nextInt(nombresPortadas.size());
        String nombreImagen = nombresPortadas.get(indiceAleatorio);

        int idImagen = getResources().getIdentifier(nombreImagen, "drawable", getPackageName());
        ImageView imageViewPortada = findViewById(R.id.imagen);
        imageViewPortada.setImageResource(idImagen);

        return nombreImagen;
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
        }
    }
    private void cambiarCancionAleatoriamente() {
        if (media != null) {
            media.release();
        }
        int numeroAleatorio = new Random().nextInt(listaAudios.length);
        int idAudio = listaAudios[numeroAleatorio];

        media = MediaPlayer.create(this, idAudio);
    }
    private void eliminarCancionActual() {
        int cancionActual = media != null ? media.getAudioSessionId() : -1;

        for (int i = 0; i < listaAudios.length; i++) {
            if (listaAudios[i] == cancionActual) {

                listaAudios[i] = 0;
                break;
            }
        }
    }
    public void playLocal(View view) {
        try {
            if (media == null) {
                cambiarCancionAleatoriamente();
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