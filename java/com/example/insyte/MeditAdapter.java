package com.example.insyte;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;


public class MeditAdapter extends ArrayAdapter<String> {

    Context context;
    String[] videos;
    String[] videoLinks;

    public MeditAdapter(@NonNull Context context, String[] videos, String[] videoLinks) {
        super(context, R.layout.meditation_listview, R.id.meditName, videos);
        this.context = context;
        this.videos = videos;
        this.videoLinks = videoLinks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View meditationView = convertView;
        ProgramViewHolder holder = null;
        if(meditationView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            meditationView = layoutInflater.inflate(R.layout.meditation_listview, parent, false);
            holder = new ProgramViewHolder(meditationView);
            meditationView.setTag(holder);
        }
        else{
            holder = (ProgramViewHolder) meditationView.getTag();
        }

        holder.meditName.setText(videos[position]);
        meditationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openLinksIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoLinks[position]));
                context.startActivity(openLinksIntent);
            }
        });
        return meditationView;
    }
}

