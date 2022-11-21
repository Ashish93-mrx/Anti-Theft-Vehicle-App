package com.cambotutorial.sovary.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class NoficationActivity extends AppCompatActivity
{
    TextView textView4, textView5, textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nofication);
        setContentView(R.layout.activity_nofication);
        setContentView(R.layout.activity_main);
        textView4= (TextView) findViewById(R.id.textView12);
        textView5 = (TextView) findViewById(R.id.textView14);
        textView6 = (TextView) findViewById(R.id.textView16);

       // textView4.setText("" + adc);

    }
}