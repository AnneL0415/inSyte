package com.example.insyte;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IntroViewPagerAdapter extends RecyclerView.Adapter<IntroViewPagerAdapter.IntroViewHolder>{

    private List<ScreenItem> screenItems;

    public IntroViewPagerAdapter(List<ScreenItem> screenItems) {
        this.screenItems = screenItems;
    }

    @NonNull
    @Override
    public IntroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IntroViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.intro_screens_layout, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull IntroViewHolder holder, int position) {
        holder.setIntroData(screenItems.get(position));
    }

    @Override
    public int getItemCount() {
        return screenItems.size();
    }

    class IntroViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;
        private ImageView img;

        IntroViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.introTitle);
            description = itemView.findViewById(R.id.introDescription);
            img = itemView.findViewById(R.id.introImage);
        }

        void setIntroData(ScreenItem item){
            title.setText(item.getTitle());
            description.setText(item.getDescription());
            img.setImageResource(item.getScreenImg());
        }
    }
}
