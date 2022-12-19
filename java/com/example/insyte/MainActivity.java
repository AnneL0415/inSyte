package com.example.insyte;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SharedElementCallback;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> quotes;
    ArrayList<String> speakers;
    String[] quotesList;
    TextView quoteView;
    TextView quoteViewSpeaker;

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor editor;
    String REFRESH_DAILY;
    String quote;
    String speaker;

    ArrayList<Quote> dailyQuote = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //-----Quote-----
        quoteView = (TextView) findViewById(R.id.textQuote);
        quoteViewSpeaker = (TextView) findViewById(R.id.textQuoteSpeaker);
        Resources res = getResources();
        quotesList = res.getStringArray(R.array.quotes);
        quotes = new ArrayList<>();
        speakers = new ArrayList<>();

        quotes = getQuoteArray(quotesList, quotes);
        speakers = getSpeakerArray(quotesList, speakers);

        mPrefs = getSharedPreferences("Prefs", Context.MODE_PRIVATE);
        REFRESH_DAILY = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date());

        SharedPreferences sharedPreferencesDailyQuote = getSharedPreferences("Daily Quote", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencesDailyQuote.edit();

        //check for first time
        boolean today_Checkin = mPrefs.getBoolean(REFRESH_DAILY, false);
        if (!today_Checkin) {
            editor.clear();
            editor.apply();
            int rand = (int) (Math.random() * quotesList.length-1);
            quote = quotes.get(rand);
            speaker = speakers.get(rand);
            quoteView.setText(quote);
            quoteViewSpeaker.setText(speaker);
            Quote dQuote = new Quote(quote, speaker);
            dailyQuote.add(dQuote);
            saveQuoteData();
        } else {
            loadQuoteData();
            quoteView.setText(dailyQuote.get(0).getTheQuote());
            quoteViewSpeaker.setText(dailyQuote.get(0).getTheSpeaker());
        }

        //when you have done for today
        SharedPreferences.Editor dateCheckEditor = mPrefs.edit();
        dateCheckEditor.putBoolean(REFRESH_DAILY, true);
        dateCheckEditor.apply();


        //-----Help Button-----
        ImageButton btnHelp = findViewById(R.id.helpButton);
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prevStarted = "yes";
                SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(prevStarted, Boolean.FALSE);
                editor.apply();
                Intent startIntent = new Intent(getApplicationContext(), IntroScreens.class);
                startActivity(startIntent);
            }
        });

        ImageButton btnSettings = findViewById(R.id.settingsButton);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Settings.class);
                startActivity(startIntent);
            }
        });

        //-----Check-In-----
        Button btnCheckIn = (Button) findViewById(R.id.btnCheckIn);

        btnCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadQuoteData();
                quote = dailyQuote.get(0).getTheQuote();
                speaker = dailyQuote.get(0).getTheSpeaker();

                Intent startIntent = new Intent(getApplicationContext(), CheckIn.class);
                startIntent.putExtra("com.example.insyte.QUOTE", quote);
                startIntent.putExtra("com.example.insyte.SPEAKER", speaker);
                startActivity(startIntent);
            }
        });

        //-----Goals-----
        Button btnGoals = (Button) findViewById(R.id.btnGoals);

        btnGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Goals.class);
                startActivity(startIntent);
            }
        });

        //-----Journal-----
        Button btnJournal = (Button) findViewById(R.id.btnJournal);

        btnJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), JournalPass.class);
                startIntent.putExtra("com.example.insyte.QUOTE", quote);
                startIntent.putExtra("com.example.insyte.SPEAKER", speaker);
                startActivity(startIntent);
            }
        });

        //-----Music-----
        Button btnMusic = (Button) findViewById(R.id.btnMusic);
        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Music.class);
                startActivity(startIntent);
            }
        });

        //-----Meditation-----
        Button btnMedit = (Button) findViewById(R.id.btnMedit);
        btnMedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Meditation.class);
                startActivity(startIntent);
            }
        });

        //-----Info-----
        Button btnVideos = (Button) findViewById(R.id.btnVideos);
        btnVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadQuoteData();
                quote = dailyQuote.get(0).getTheQuote();
                speaker = dailyQuote.get(0).getTheSpeaker();

                Intent startIntent = new Intent(getApplicationContext(), InfoPass.class);
                startIntent.putExtra("com.example.insyte.QUOTE", quote);
                startIntent.putExtra("com.example.insyte.SPEAKER", speaker);
                startActivity(startIntent);
            }
        });

        //-----Hotlines-----
        Button btnHotlines = (Button) findViewById(R.id.btnHotlines);
        btnHotlines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Hotlines.class);
                startActivity(startIntent);
            }
        });
    }

    public ArrayList<String> getQuoteArray(String[] quotesList, ArrayList<String> quotes) {
        for(int i = 0; i < quotesList.length -1; i++){
            String q0 = quotesList[i];
            int split = q0.indexOf(" -");
            String phrase = q0.substring(0, split);
            quotes.add(phrase);
        }
        return quotes;
    }

    public ArrayList<String> getSpeakerArray(String[] quotesList, ArrayList<String> speakers) {
        for(int i = 0; i < quotesList.length -1; i++){
            String q0 = quotesList[i];
            int split = q0.indexOf(" -");
            String speaker = q0.substring(split + 1);
            speakers.add(speaker);
        }
        return speakers;
    }

    private void saveQuoteData(){
        SharedPreferences sharedPreferences = getSharedPreferences("Daily Quote", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(dailyQuote);
        editor.putString("Daily Quote", json);
        editor.apply();
    }

    private void loadQuoteData(){
        SharedPreferences sharedPreferences = getSharedPreferences("Daily Quote", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Daily Quote", null);
        Type type = new TypeToken<ArrayList<Quote>>() {}.getType();
        dailyQuote = gson.fromJson(json, type);

        if(dailyQuote == null){
            dailyQuote = new ArrayList<>();
        }
    }

}