package com.example.insyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CheckInStart extends AppCompatActivity {

    SharedPreferences timer;
    String REFRESH_DAILY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_start);

        Button startCheckIn = findViewById(R.id.CIBtn);
        TextView dateTextView = findViewById(R.id.dateTextView);

        Calendar currentTime = Calendar.getInstance();
        String monthName = "";
        int month = currentTime.get(Calendar.MONTH) + 1;
        switch (month){
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
                break;
        }
        int day = currentTime.get(Calendar.DAY_OF_MONTH);
        int year = currentTime.get(Calendar.YEAR);
        String date = monthName + " " + day + ", " + year;

        dateTextView.setText(date);

        timer = getSharedPreferences("Check In Timer", Context.MODE_PRIVATE);
        REFRESH_DAILY = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date());
        SharedPreferences.Editor editor = timer.edit();

        //check for first time
        boolean today_Checkin = timer.getBoolean(REFRESH_DAILY, false);
        if (!today_Checkin) {
            startCheckIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent startIntent = new Intent(getApplicationContext(), CheckInQuestions.class);
                    startActivity(startIntent);
                }
            });

        } else {
            startCheckIn.setEnabled(false);
            Toast.makeText(CheckInStart.this, "You have already done the check-in!", Toast.LENGTH_SHORT).show();
        }

        //when you have done for today
        editor = timer.edit();
        editor.putBoolean(REFRESH_DAILY, true);
        editor.apply();
    }

}