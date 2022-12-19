package com.example.insyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;

public class InfoCovid extends AppCompatActivity {

    ListView covidList;
    String[] covidVideos;
    String[] covidLinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_covid);

        Resources res = getResources();
        covidList = (ListView) findViewById(R.id.covidList);
        covidVideos = res.getStringArray(R.array.covid);
        covidLinks = res.getStringArray(R.array.covidlinks);

        MeditAdapter meditAdapter = new MeditAdapter(this, covidVideos, covidLinks);
        covidList.setAdapter(meditAdapter);
    }
}