package com.example.insyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Meditation extends AppCompatActivity {

    ListView meditList;
    String[] videos;
    String[] videoLinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation_pass);

        Resources res = getResources();
        meditList = (ListView) findViewById(R.id.meditList);
        videos = res.getStringArray(R.array.videos);
        videoLinks = res.getStringArray(R.array.videoLinks);

        MeditAdapter meditAdapter = new MeditAdapter(this, videos, videoLinks);
        meditList.setAdapter(meditAdapter);

    }
}