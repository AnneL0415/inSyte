package com.example.insyte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioTrack;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class IntroScreens extends AppCompatActivity {

    String prevStarted = "yes";

    private IntroViewPagerAdapter screenPager;
    private LinearLayout layoutIntroIndicators;
    private MaterialButton introButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screens);

        layoutIntroIndicators = findViewById(R.id.tabSwitcher);
        introButton = findViewById(R.id.introButton);

        setUpScreenItems();

        ViewPager2 introScreenViewPager = findViewById(R.id.introViewPager);
        introScreenViewPager.setAdapter(screenPager);

        setupIndicators();
        setCurrentIndicator(0);

        introScreenViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
            @Override
            public void onPageSelected(int position){
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }
        });

        introButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(introScreenViewPager.getCurrentItem() + 1 < 8){
                    introScreenViewPager.setCurrentItem(introScreenViewPager.getCurrentItem() + 1);
                }else{
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        if (!sharedpreferences.getBoolean(prevStarted, false)) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(prevStarted, Boolean.TRUE);
            editor.apply();
        } else {
            moveToSecondary();
        }
    }

    public void moveToSecondary(){
        // use an intent to travel from one activity to another.
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void setUpScreenItems(){
        List<ScreenItem> screenItems = new ArrayList<>();

        ScreenItem item1 = new ScreenItem();
        item1.setTitle("Welcome to InSyte");
        item1.setDescription("Hello! Welcome to inSyte, an app where you can track your mental health progress and find self-care resources.");
        item1.setScreenImg(R.drawable.insyte_logo);

        ScreenItem item2 = new ScreenItem();
        item2.setTitle("Check In");
        item2.setDescription("These are your daily check-in questions. Make sure you answer them as accurately as possible. Graphs will be produced to track your progress.");
        item2.setScreenImg(R.drawable.insyte_hearts);

        ScreenItem item3 = new ScreenItem();
        item3.setTitle("Goals");
        item3.setDescription("Use this page to keep track of your tasks. Check off any goals or activities you have completed for the day.");
        item3.setScreenImg(R.drawable.insyte_goals);

        ScreenItem item4 = new ScreenItem();
        item4.setTitle("Journal and Sketchbook");
        item4.setDescription("Use the journal to write down your thoughts. Use the sketchbook to draw, destress, and have fun!");
        item4.setScreenImg(R.drawable.insyte_journal);

        ScreenItem item5 = new ScreenItem();
        item5.setTitle("Music");
        item5.setDescription("We have provided you with various types of relaxing music for when you want to destress or clear your mind.");
        item5.setScreenImg(R.drawable.insyte_music);

        ScreenItem item6 = new ScreenItem();
        item6.setTitle("Meditation");
        item6.setDescription("Try these quick 10-minute videos for guided meditation for different emotions. Use them as you need!");
        item6.setScreenImg(R.drawable.insyte_meditation);

        ScreenItem item7 = new ScreenItem();
        item7.setTitle("More Information");
        item7.setDescription("Use these videos and article links to learn more about mental health and mental illnesses to help you better understand your feelings or symptoms.");
        item7.setScreenImg(R.drawable.insyte_research);

        ScreenItem item8 = new ScreenItem();
        item8.setTitle("Hotlines");
        item8.setDescription("This app is NOT meant to be a substitute for professional help. Please get help if you need it!");
        item8.setScreenImg(R.drawable.insyte_phone);

        screenItems.add(item1);
        screenItems.add(item2);
        screenItems.add(item3);
        screenItems.add(item4);
        screenItems.add(item5);
        screenItems.add(item6);
        screenItems.add(item7);
        screenItems.add(item8);
        screenPager = new IntroViewPagerAdapter(screenItems);
    }

    private void setupIndicators(){
        ImageView[] indicators = new ImageView[8]; //this is horrible coding
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(8, 0, 8, 0);
        for(int i = 0; i < indicators.length; i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.intro_screen_inactive_indicator));
            indicators[i].setLayoutParams(layoutParams);
            layoutIntroIndicators.addView(indicators[i]);
        }
    }

    private void setCurrentIndicator(int index){
        int childCount = layoutIntroIndicators.getChildCount();
        for(int i = 0; i < childCount; i++){
            ImageView imageview = (ImageView)layoutIntroIndicators.getChildAt(i);
            if(i == index){
                imageview.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.intro_screen_active_indicator)
                );
            } else{
                imageview.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.intro_screen_inactive_indicator)
                );
            }
        }
        if(index == 7){
            introButton.setText("Start");
        }else{
            introButton.setText("Next");
        }
    }
}