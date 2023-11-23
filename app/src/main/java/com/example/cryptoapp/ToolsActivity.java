package com.example.cryptoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class ToolsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);







        //پیاده سازی Broadcast
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            BroadcastReceiverClass myReceiver = new BroadcastReceiverClass();
            //خاموش شدن صفحه نمایش
            registerReceiver(myReceiver, new IntentFilter("android.intent.action.SCREEN_OFF"));
            //شبکه
            registerReceiver(myReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
    }

    public void sendMyBroadcast(View view) {
        Intent intent = new Intent();
        intent.setAction("com.example.myapplication.MY_TEST_BROADCAST");
        //مثلا برنامه اب و هوا، میخوام به بقیه برنامه هام بگم امروز هوا چطوریه
        intent.putExtra("weather", "Cloudy.");
        sendBroadcast(intent);

    }


    public void goToTestServiceActivity(View view) {
        Intent i = new Intent(ToolsActivity.this, TestServiceActivity.class);
        startActivity(i);
    }

    public void goToAsyncTaskActivity(View view) {
        Intent i = new Intent(ToolsActivity.this, AsyncTaskActivity.class);
        startActivity(i);
    }

    public void goToPermissionsTaskActivity(View view) {
        Intent i = new Intent(ToolsActivity.this, PermissionsActivity.class);
        startActivity(i);
    }
}