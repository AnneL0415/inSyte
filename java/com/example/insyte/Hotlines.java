package com.example.insyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Hotlines extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotlines);

        Button btnCall1 = (Button) findViewById(R.id.btnCall1);
        TextView numberSuicide = (TextView) findViewById(R.id.numberSuicide);
        btnCall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:800-273-8255"));
                startActivity(intent);
            }
        });

        Button btnCall2 = (Button) findViewById(R.id.btnCall2);
        TextView numberSAMHSA = (TextView) findViewById(R.id.numberSAMHSA);
        btnCall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:800-662-4357"));
                startActivity(intent);
            }
        });

        Button btnCall3 = (Button) findViewById(R.id.btnCall3);
        TextView numberLGBTQ = (TextView) findViewById(R.id.numberLGBTQ);
        btnCall3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:866-488-7386"));
                startActivity(intent);
            }
        });

    }
}