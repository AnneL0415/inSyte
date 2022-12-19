package com.example.insyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Trace;
import android.view.View;
import android.widget.ImageButton;

import com.example.insyte.musicServices.BeachMusic;
import com.example.insyte.musicServices.BirdsMusic;
import com.example.insyte.musicServices.ElvenForest;
import com.example.insyte.musicServices.FarFuture;
import com.example.insyte.musicServices.LightStroll;
import com.example.insyte.musicServices.Lullaby;
import com.example.insyte.musicServices.RiverMusic;
import com.example.insyte.musicServices.Tranquility;

public class Music extends AppCompatActivity implements View.OnClickListener {

    private ImageButton riverPlay, riverStop;
    private ImageButton beachPlay, beachStop;
    private ImageButton birdsPlay, birdsStop;
    private ImageButton forestPlay, forestStop;
    private ImageButton lightPlay, lightStop;
    private ImageButton lullabyPlay, lullabyStop;
    private ImageButton tranquilityPlay, tranquilityStop;
    private ImageButton futurePlay, futureStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        riverPlay = (ImageButton) findViewById(R.id.riverPlay);
        riverStop = (ImageButton) findViewById(R.id.riverStop);
        riverPlay.setOnClickListener(this);
        riverStop.setOnClickListener(this);

        beachPlay = (ImageButton) findViewById(R.id.beachPlay);
        beachStop = (ImageButton) findViewById(R.id.beachStop);
        beachPlay.setOnClickListener(this);
        beachStop.setOnClickListener(this);

        birdsPlay = (ImageButton) findViewById(R.id.birdsPlay);
        birdsStop = (ImageButton) findViewById(R.id.birdsStop);
        birdsPlay.setOnClickListener(this);
        birdsStop.setOnClickListener(this);

        forestPlay = (ImageButton) findViewById(R.id.elvenForestPlay);
        forestStop = (ImageButton) findViewById(R.id.elvenForestStop);
        forestPlay.setOnClickListener(this);
        forestStop.setOnClickListener(this);

        lightPlay = (ImageButton) findViewById(R.id.lightStrollPlay);
        lightStop = (ImageButton) findViewById(R.id.lightStrollStop);
        lightPlay.setOnClickListener(this);
        lightStop.setOnClickListener(this);

        lullabyPlay = (ImageButton) findViewById(R.id.lullabyPlay);
        lullabyStop = (ImageButton) findViewById(R.id.lullabyStop);
        lullabyPlay.setOnClickListener(this);
        lullabyStop.setOnClickListener(this);

        tranquilityPlay = (ImageButton) findViewById(R.id.tranquilityPlay);
        tranquilityStop = (ImageButton) findViewById(R.id.tranquilityStop);
        tranquilityPlay.setOnClickListener(this);
        tranquilityStop.setOnClickListener(this);

        futurePlay = (ImageButton) findViewById(R.id.farFuturePlay);
        futureStop = (ImageButton) findViewById(R.id.farFutureStop);
        futurePlay.setOnClickListener(this);
        futureStop.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == riverPlay){
            startService(new Intent(this, RiverMusic.class));
        }else if(v == riverStop){
            stopService(new Intent(this, RiverMusic.class));
        }else if(v == beachPlay){
            startService(new Intent(this, BeachMusic.class));
        }else if(v == beachStop){
            stopService(new Intent(this, BeachMusic.class));
        }else if(v == birdsPlay){
            startService(new Intent(this, BirdsMusic.class));
        }else if(v == birdsStop) {
            stopService(new Intent(this, BirdsMusic.class));
        }else if(v == forestPlay){
            startService(new Intent(this, ElvenForest.class));
        }else if(v == forestStop) {
            stopService(new Intent(this, ElvenForest.class));
        }else if(v == lightPlay){
            startService(new Intent(this, LightStroll.class));
        }else if(v == lightStop) {
            stopService(new Intent(this, LightStroll.class));
        }else if(v == lullabyPlay){
            startService(new Intent(this, Lullaby.class));
        }else if(v == lullabyStop) {
            stopService(new Intent(this, Lullaby.class));
        }else if(v == tranquilityPlay){
            startService(new Intent(this, Tranquility.class));
        }else if(v == tranquilityStop) {
            stopService(new Intent(this, Tranquility.class));
        }else if(v == futurePlay){
            startService(new Intent(this, FarFuture.class));
        }else if(v == futureStop) {
            stopService(new Intent(this, FarFuture.class));
        }
    }

}