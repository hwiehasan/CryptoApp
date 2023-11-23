package com.example.cryptoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


//توضیحات
/*

* (1)دسترسی زمان نصب
* (2) دسترسی ران تایم هنگام اجرای برنامه

دسترسی های نرمال:
دسترسی هایی هستن که میتونیم تو منیفست قرار بدیم و از لحاظ امنیتی مشکل ساز نیستن

دسترسی های خطرناک یا حساس:
مثل دسترسی به دوربین، برای اینگونه دسترسی ها هنگام اجرای برنامه از کاربر پرسیده میشود

 */


public class PermissionsActivity extends AppCompatActivity {

    private final int CAMERA_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);



    }

    public void doPermissionCheck(View view) {

        //تابع بررسیی
        if (ContextCompat.checkSelfPermission(PermissionsActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestCameraPermission();
        }
        else {
            Toast.makeText(PermissionsActivity.this, "Permission already granted!", Toast.LENGTH_SHORT).show();
        }
    }



    private void requestCameraPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(PermissionsActivity.this, Manifest.permission.CAMERA)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permission Request")
                    .setMessage("You need to grant the access to use the camera!")
                    .setPositiveButton("Agree", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            reqPermission();

                        }
                    })
                    .setNegativeButton("Disagree", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create()
                    .show();
        }
        else
        {
            reqPermission();
        }

    }

    private void reqPermission() {

        ActivityCompat.requestPermissions(PermissionsActivity.this, new String[] {Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);

    }





}