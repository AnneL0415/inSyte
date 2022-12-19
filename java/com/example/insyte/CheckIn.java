package com.example.insyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheckIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        if(getIntent().hasExtra("com.example.insyte.QUOTE")){
            TextView quoteView = (TextView) findViewById(R.id.textQuoteCheckIn);
            String quote = getIntent().getExtras().getString("com.example.insyte.QUOTE");
            quoteView.setText(quote);
        }
        if(getIntent().hasExtra("com.example.insyte.SPEAKER")){
            TextView quoteView = (TextView) findViewById(R.id.textQuoteSpeakerCheckIn);
            String quote = getIntent().getExtras().getString("com.example.insyte.SPEAKER");
            quoteView.setText(quote);
        }

        //-----Check-In-----
        Button btn2CheckIn = (Button) findViewById(R.id.btn2CheckIn);

        btn2CheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), CheckInStart.class);
                startActivity(startIntent);
            }
        });

        //-----Graphs-----
        Button btn2Graphs = (Button) findViewById(R.id.btn2Graphs);
        btn2Graphs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ResultsGraphs.class);
                startActivity(startIntent);
            }
        });
    }

}