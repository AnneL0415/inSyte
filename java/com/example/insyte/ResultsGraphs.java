package com.example.insyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ResultsGraphs extends AppCompatActivity {

    ArrayList<Answers> userAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_graphs);

        SharedPreferences sharedPreferences = getSharedPreferences("User Answers", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("User Answers", null);
        Type type = new TypeToken<ArrayList<Answers>>() {}.getType();
        userAnswers = gson.fromJson(json, type);

        Button sleepGraph = (Button) findViewById(R.id.checkInSleepButton);
        Button stressGraph = (Button) findViewById(R.id.checkInStressButton);
        Button socialGraph = (Button) findViewById(R.id.checkInTalkButton);
        Button exerciseGraph = (Button) findViewById(R.id.checkInExerciseButton);
        Button dietGraph = (Button) findViewById(R.id.checkInDietButton);

        sleepGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), GraphSleep.class);
                startActivity(startIntent);
            }
        });

        stressGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), GraphStress.class);
                startActivity(startIntent);
            }
        });

        socialGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), GraphSocialization.class);
                startActivity(startIntent);
            }
        });

        exerciseGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), GraphExercise.class);
                startActivity(startIntent);
            }
        });

        dietGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), GraphDiet.class);
                startActivity(startIntent);
            }
        });

    }
}