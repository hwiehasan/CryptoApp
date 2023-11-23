package com.example.cryptoapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

//این یه کلس هست
public class MyService extends Service{






    //کلس پخش مدیا
    private MediaPlayer mediaPlayer;
    private NotificationManager notifManager;
    private String CHANNEL_ID = "channelId";
    MyBinder mBinder;

    //Bind Service
    public class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }






    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(this, "Service has been created", Toast.LENGTH_SHORT).show();
        mediaPlayer = MediaPlayer.create(this, R.raw.tearsofasilentheart);

    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {






        //پیاده سازی نوتیفیکیشن تو اندروید 8 به بعد تغییر کرده و لازمه اول که یک کانال ایجاد کنیم و نوتیفیکیشن رو داخل اون کانال اجرا کنیم
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String offerChannelName = "Service Channel";
            String offerChannelDescription= "Music Channel";
            int offerChannelImportance = NotificationManager.IMPORTANCE_DEFAULT;


            NotificationChannel notifChannel = new NotificationChannel(CHANNEL_ID, offerChannelName, offerChannelImportance);
            notifChannel.setDescription(offerChannelDescription);
            notifManager = getSystemService(NotificationManager.class);
            notifManager.createNotificationChannel(notifChannel);
        }

        //پیاده سازی نوتیفیکیشن
        NotificationCompat.Builder sNotifBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Music")
                .setContentText("A music is playing");

        Notification servNotification = sNotifBuilder.build();
        //بصورت فورگروند اجرا میکنه
        startForeground(1, servNotification);
        Toast.makeText(this, "Service has been started", Toast.LENGTH_SHORT).show();
        mediaPlayer.start();




        //سه حالت داریم برای ریترن مــــهــــــم
        //برای انجام یکار موقتی برای یکبار
        //return START_STICKY;
        //برای یکاری که اگه خواستم دوباره بهش وصل بشم
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service has been ended", Toast.LENGTH_SHORT).show();
        mediaPlayer.stop();

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) { //زمانی که بایند باشه مدیاپلیر پاز بشه
        Toast.makeText(this, "Service Bind", Toast.LENGTH_SHORT).show();
        mediaPlayer.pause();
        if (mBinder == null)
        {
            mBinder= new MyBinder();
        }

        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) { //زمانی که انبایند باشه مدیا پلیر استار بشه
        Toast.makeText(this, "Service UnBind ", Toast.LENGTH_SHORT).show();
        mediaPlayer.start();

        //اگه مقدار ترو برگردونه اونوقت میشه سرویس رو دوباره ادامه داد، یه تابع بعدش ساخته
        return super.onUnbind(intent);
    }
    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Toast.makeText(this, "Service ReBind ", Toast.LENGTH_SHORT).show();
        mediaPlayer.pause();
    }













    //---Background Service---
/*

    //کلس پخش مدیا
    MediaPlayer mediaPlayer;


    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(this, "Service has been created", Toast.LENGTH_SHORT).show();
        mediaPlayer = MediaPlayer.create(this , R.raw.HansZimmerDayOne);

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Service has been started", Toast.LENGTH_SHORT).show();
        mediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "Bind Service", Toast.LENGTH_SHORT).show();

        return null;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service has been ended", Toast.LENGTH_SHORT).show();
        mediaPlayer.stop();

    }
                                    //---Background Service Ended---
*/




//Foreground Service
 /*
    //------  تو حالت فورگروند اولویت سرویس میره بالاتر و اندروید اگه منابع سیستم نیاز داشته باشه نمیاد ببندتش ولی بابد تو این حالت نوتیفیکیشن هم به کاربر نمایش بدیم

    //کلس پخش مدیا
    MediaPlayer mediaPlayer;
    NotificationManager notifManager;
    String CHANNEL_ID = "channelId";

    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(this, "Service has been created", Toast.LENGTH_SHORT).show();
        mediaPlayer = MediaPlayer.create(this , R.raw.HansZimmerDayOne);
        mediaPlayer.setLooping(false); //یبار که پخش شد کارش تموم بشه
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Service has been started", Toast.LENGTH_SHORT).show();
        mediaPlayer.start();




        //پیاده سازی نوتیفیکیشن تو اندروید 8 به بعد تغییر کرده و لازمه اول که یک کانال ایجاد کنیم و نوتیفیکیشن رو داخل اون کانال اجرا کنیم
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String offerChannelName = "Service Channel";
            String offerChannelDescription= "Music Channel";
            int offerChannelImportance = NotificationManager.IMPORTANCE_DEFAULT;


            NotificationChannel notifChannel = new NotificationChannel(CHANNEL_ID, offerChannelName, offerChannelImportance);
            notifChannel.setDescription(offerChannelDescription);
            notifManager = getSystemService(NotificationManager.class);
            notifManager.createNotificationChannel(notifChannel);
        }

        //پیاده سازی نوتیفیکیشن
        NotificationCompat.Builder sNotifBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Music")
                .setContentText("A music is playing");

        Notification servNotification = sNotifBuilder.build();
        //بصورت فورگروند اجرا میکنه
        startForeground(1, servNotification);

        //سه حالت داریم برای ریترن
        return START_STICKY;
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "Bind Service", Toast.LENGTH_SHORT).show();

        return null;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service has been ended", Toast.LENGTH_SHORT).show();
        mediaPlayer.stop();

    }
    //---Foreground Service ended---
*/




}
