package com.example.insyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class JournalPass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_pass);

        if(getIntent().hasExtra("com.example.insyte.QUOTE")){
            TextView quoteView = (TextView) findViewById(R.id.textQuoteJournal);
            String quote = getIntent().getExtras().getString("com.example.insyte.QUOTE");
            quoteView.setText(quote);
        }
        if(getIntent().hasExtra("com.example.insyte.SPEAKER")){
            TextView quoteView = (TextView) findViewById(R.id.textQuoteSpeakerJournal);
            String quote = getIntent().getExtras().getString("com.example.insyte.SPEAKER");
            quoteView.setText(quote);
        }

        Button btn2Journal = (Button) findViewById(R.id.btn2Journal);
        btn2Journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), JournalJournal.class);
                startActivity(startIntent);
            }
        });

        Button btn2Drawing = (Button) findViewById(R.id.btn2Drawing);
        btn2Drawing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), JournalDraw.class);
                startActivity(startIntent);
            }
        });


    }
}