package com.example.insyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;

public class InfoStress extends AppCompatActivity {

    ListView stressList;
    String[] stressVideos;
    String[] stressLinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_stress);

        Resources res = getResources();
        stressList = (ListView) findViewById(R.id.stressList);
        stressVideos = res.getStringArray(R.array.stress);
        stressLinks = res.getStringArray(R.array.stresslinks);

        MeditAdapter meditAdapter = new MeditAdapter(this, stressVideos, stressLinks);
        stressList.setAdapter(meditAdapter);
    }
}