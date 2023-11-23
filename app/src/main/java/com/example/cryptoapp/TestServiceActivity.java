package com.example.cryptoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class TestServiceActivity extends AppCompatActivity {

    Intent i;


    MyService mService;
    boolean mServiceBound = false;
    ServiceConnection mServiceConnection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_service);


        i = new Intent(TestServiceActivity.this, MyService.class);


        //بدونم وصل شده یا نه
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                MyService.MyBinder binder = (MyService.MyBinder) iBinder;
                mService = binder.getService();
                mServiceBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                mServiceBound = false;
            }
        };






    }

    public void startServiceBtn(View view) {

        //background service
        /*
        //startService(i); //برای حالت بک گرانداز کامنت در میاد
        */


        //foreground service
        /*
        //برای اندروید 8 و بالاتر: فورگراند
        //روش1
        ContextCompat.startForegroundService(TestServiceActivity.this, i);

        //روش 2
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {startForegroundService(i);}
        else    {startService(i);}

        */
        ContextCompat.startForegroundService(TestServiceActivity.this, i);
    }



    public void stopServiceBtn(View view) {
        stopService(i);
    }




    //ازایــــنجا ببعد برای بحش Bind هستـــــــــــــــــت
    public void pauseMusic(View view) {

        //Intent serviceIntent = new Intent(TestServiceActivity.this,MyService.class);
        bindService(i,mServiceConnection,BIND_AUTO_CREATE);
    }

    public void resumeMusic(View view) {
        unbindService(mServiceConnection);
    }
}