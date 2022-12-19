package com.example.insyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoPass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pass);

        if(getIntent().hasExtra("com.example.insyte.QUOTE")){
            TextView quoteView = (TextView) findViewById(R.id.textQuoteInfo);
            String quote = getIntent().getExtras().getString("com.example.insyte.QUOTE");
            quoteView.setText(quote);
        }
        if(getIntent().hasExtra("com.example.insyte.SPEAKER")){
            TextView quoteView = (TextView) findViewById(R.id.textQuoteSpeakerInfo);
            String quote = getIntent().getExtras().getString("com.example.insyte.SPEAKER");
            quoteView.setText(quote);
        }

        Button btn2Stress = (Button) findViewById(R.id.btn2Stress);
        btn2Stress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), InfoStress.class);
                startActivity(startIntent);
            }
        });

        Button btn2Depression = (Button) findViewById(R.id.btn2Depression);
        btn2Depression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), InfoDepression.class);
                startActivity(startIntent);
            }
        });

        Button btn2Covid = (Button) findViewById(R.id.btn2Covid);
        btn2Covid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), InfoCovid.class);
                startActivity(startIntent);
            }
        });

    }
}