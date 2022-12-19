package com.example.insyte;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class Notifs extends Application {
    public static final String CHANNEL_1_ID = "inSyteNotifs";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();
    }

    private void createNotificationChannels() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID, "inSyte Check-In", NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("Have you done your check-in today?");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);

        }
    }
}
