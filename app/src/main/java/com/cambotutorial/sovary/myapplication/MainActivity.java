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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements ValueEventListener {
    TextView textView, textView1, textView2, textView4;
    Button btn;
    String g;
    String t;
    String gb;
    int n=0;
   /* int cnt=0;

    {
        cnt = cnt + 1;
    }
    */
    //tring a = Integer.toString(cnt);
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference().child("Detected");
    private DatabaseReference firstDatabase = databaseReference.child("Time");
    //private DatabaseReference root = db.getReference()
    private DatabaseReference secondDatabase = databaseReference.child("VehicleNo");
    private DatabaseReference goodDatabase = databaseReference.child("Location");
    private DatabaseReference thirdDatabase = databaseReference.child("VehicleStatus");

    private void createNotif(String vn,String gb,int n,String t)
    {
        String id = "my_channel_id_01";
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel =manager.getNotificationChannel(id);
            if(channel ==null)
            {
                channel = new NotificationChannel(id,"Channel Title", NotificationManager.IMPORTANCE_HIGH);
                //config nofication channel
                channel.setDescription("[Channel description]");
                channel.enableVibration(true);
                channel.setVibrationPattern(new long[]{100,1000,200,340});
                channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
                manager.createNotificationChannel(channel);
            }
        }

        Intent notificationIntent = new Intent(this,NoficationActivity.class);
        //notificationIntent.putExtra("VehicleNo :", adc);;
        //startActivity(notificationIntent);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,notificationIntent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,id)
                .setSmallIcon(R.drawable.icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.car01))
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.car01))
                        .bigLargeIcon(null))
                .setGroup(vn)
                .setContentTitle(vn)
                .setContentText("Detected Time: "+ gb + " Location: "+ t)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(new long[]{100,1000,200,340})
                .setAutoCancel(false)//true touch on notificaiton menu dismissed, but swipe to dismiss
                .setTicker("Nofiication");
        builder.setContentIntent(contentIntent);
        NotificationManagerCompat m = NotificationManagerCompat.from(getApplicationContext());
        //id to generate new notification in list notifications menu
        m.notify(new Random().nextInt(),builder.build());
        gb="";
        t="";
        n=0;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView12);
        textView1 = (TextView) findViewById(R.id.textView14);
        textView2 = (TextView) findViewById(R.id.textView16);
        textView4= (TextView) findViewById(R.id.textView9);
        btn = (Button) findViewById(R.id.button3);
        Button btn = findViewById(R.id.button3);

    }

    public void onDataChange(@NonNull DataSnapshot snapshot) {
        //Object dataSnapshot;

        if (snapshot.getValue(Object.class) != null) {
            Object key = snapshot.getKey();

            if (key.equals("Time")) {
                //  String ex = toString(String.class)
                 String tm = snapshot.getValue(String.class);

                 gb = tm;
                 //tm="";
                textView.setText("" + tm);
            }


            if (key.equals("VehicleNo")) {
                String adc = snapshot.getValue(String.class);
                g=adc;
                textView1.setText("" + adc);
            }

            if (key.equals("Location")) {
                String loco = snapshot.getValue(String.class);
                t=loco;
                textView4.setText("" + loco);
            }

            if (key.equals("VehicleStatus")) {
                String efg = snapshot.getValue(String.class);

                int n = efg.length();
                textView2.setText("" + efg);
                if (n > 9) {
                    createNotif(g,gb,n,t);
                }
            }
        }
    }


    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }

    protected void onStart() {

        super.onStart();
        firstDatabase.addValueEventListener(this);
        secondDatabase.addValueEventListener(this);
        thirdDatabase.addValueEventListener(this);
        goodDatabase.addValueEventListener(this);

    }

}