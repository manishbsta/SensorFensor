package com.manishbista.sensorfensor.services;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import com.manishbista.sensorfensor.R;

public class MyService extends Service {
    NotificationManagerCompat notificationManagerCompat;
    int count = 1;
    public Context context = this;
    public Handler handler = null;
    Runnable runnable = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        notificationManagerCompat = NotificationManagerCompat.from(this);
        Toast.makeText(this, "Service Created!", Toast.LENGTH_SHORT).show();

        handler = new Handler();

        runnable = new Runnable() {
            public void run() {
                count++;
                Toast.makeText(context, ""+count, Toast.LENGTH_LONG).show();

                if(count%2==0){
                    displayNotifOne();
                }

                handler.postDelayed(runnable, 2000);
            }
        };
        handler.postDelayed(runnable, 2000);
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(runnable);
        Toast.makeText(this, "Service Stopped!", Toast.LENGTH_SHORT).show();
    }

    public void displayNotifOne() {
        Notification notification = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_ONE)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Book Submission Successful")
                .setContentText("You have successfully added a new book. Happy BookSwapping!")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(3, notification);
    }
}
