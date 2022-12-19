package com.example.insyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;

public class InfoDepression extends AppCompatActivity {

    ListView depressionList;
    String[] depressionVideos;
    String[] depressionLinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_depression);

        Resources res = getResources();
        depressionList = (ListView) findViewById(R.id.depressionList);
        depressionVideos = res.getStringArray(R.array.depression);
        depressionLinks = res.getStringArray(R.array.depressionlinks);

        MeditAdapter meditAdapter = new MeditAdapter(this, depressionVideos, depressionLinks);
        depressionList.setAdapter(meditAdapter);
    }
}