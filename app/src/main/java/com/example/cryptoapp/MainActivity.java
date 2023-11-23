package com.example.cryptoapp;

import static java.net.Proxy.Type.HTTP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.Hashtable;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //دریافت اطلاعات ارسال شده از طریف اینتنت ها
        Intent i = getIntent();
        String username = i.getStringExtra("USERNAME");
        String password = i.getStringExtra("PASSWORD");



        getUserProfileImage();



/*
        //db
        DBAdapter dbAdapter = new DBAdapter(MainActivity.this);
        dbAdapter.open();
        dbAdapter.insertContact("admin" , "admin@gmail.com");
        dbAdapter.insertContact("user1" , "user1@yahoo.com");
        dbAdapter.close();
        */


    }

    //نمایش تصویر پروفایل کاربر
    void getUserProfileImage() {
        String API_URL = Globals.SERVER + "/api/getProfileImage.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {
                        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                        ImageView loadImage = (ImageView) findViewById(R.id.loadedImageFromNet);

                        Picasso.get().load(Globals.SERVER + result).into(loadImage);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(MainActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();

                if (Globals.USERNAME != null) {
                    params.put("USERNAME", Globals.USERNAME);
                }
                return params;
            }
        };
        //ایجاد صف و ورود درخواست ها به صف برای ارسال به سرور
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public void goToUserKalaListActivity(View view) {
        Intent i = new Intent(MainActivity.this, ListKalaActivity.class);
        startActivity(i);

    }

    public void goToKalaListActivity(View view) {
        Intent i = new Intent(MainActivity.this, ListKalaByCategoryActivity.class);
        startActivity(i);
    }









    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menuToolsBtn) {
            // برو به صفحه ی ابزار ها
            Intent i = new Intent(MainActivity.this, ToolsActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.menuProfileBtn) {
            Intent i = new Intent(MainActivity.this, UserProfileActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.menuLogoutApp) {
            // خروج از حساب کاربری
            // پیاده سازی ALert Dialog
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("Logout from account");
            alert.setMessage("Do you want to logout from your account?");
            alert.setIcon(R.drawable.ic_launcher_background);
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SharedPreferences sp = getSharedPreferences("MyLoginInfo", MODE_PRIVATE);
                    sp.edit().clear().commit();

                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            alert.setCancelable(false);
            alert.show();

        } else if (item.getItemId() == R.id.menuAboutUs) {

            Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
            startActivity(intent);

        } else if (item.getItemId() == R.id.menuContactWithUs) {

            Uri email = Uri.parse("mailto:h.nasrollahi@outlook.de");
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, email);

            // Add email subject
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");

             // Add email body
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi i am...");

            startActivity(Intent.createChooser(emailIntent, "Send Email"));

        }
        return super.onOptionsItemSelected(item);

    }

    public void goToProfile(View view) {
        Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
        startActivity(intent);
    }
}