package com.example.cryptoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AuthenticationRequiredException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //خواندن اطلاعات از sharedprefernces
        SharedPreferences sp = getSharedPreferences("MyPref", MODE_PRIVATE);
        String username = sp.getString("USER_NAME", null);
        String password = sp.getString("PASSWORD", null);
        //ایا قبلا لاگین کرده؟
        boolean isLogin = sp.getBoolean("IS_LOGIN", false);

        if (isLogin == true) {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            //انتقال اطلاعات از اکتیویتی اول به دوم به کمک اینتنت
            i.putExtra("USERNAME", username);
            i.putExtra("PASSWORD", password);
            startActivity(i);
        } else if (username != null) {
            //خواندن اطلاعات از ادیت تکست
            EditText usernameEdt, passwordEdt;
            usernameEdt = (EditText) findViewById(R.id.usernameEdt);
            passwordEdt = (EditText) findViewById(R.id.passwordEdt);

            usernameEdt.setText(username);
            passwordEdt.setText(password);

        }


    }


    public void doLogin(View view) {
        EditText usernameEdt, passwordEdt;
        usernameEdt = (EditText) findViewById(R.id.usernameEdt);
        passwordEdt = (EditText) findViewById(R.id.passwordEdt);
        //ذخیره یوزر و پسورد
        String username = String.valueOf(usernameEdt.getText());
        String password = String.valueOf(passwordEdt.getText());


        //TODO
        //پیاده سازی api
        // String API_URL = "http://127.0.0.1/api/submit-login.php"; in ip loop shabake hast bedard nemikhore
        String API_URL = Globals.SERVER+"/api/submit-login.php"; //bayad ip un shabihsazemoon ro peyda konim
        //String API_URL = "http://10.0.2.2/api/submit-login.php"; ip khode gushi as developer option
        //String API_URL = "http://10.0.2.2/api/submit-login.php"; age server vaqei bashe address site ro mizanim
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {
                        Toast.makeText(LoginActivity.this , result ,Toast.LENGTH_SHORT ).show();



                        if (result.equals("fail")) {
                            //اطلاعات وارد شده اشتباه بوده
                            TextView txt = (TextView) findViewById(R.id.invalidLoginTxt);
                            txt.setVisibility(View.VISIBLE);
                        }
                        else {

                            //JSON STARTED : اطلاعات از سرور بگیره و به کاربر نمایش بده
                            boolean validInput = true;
                            try{//سعی کنه اجراش کنه
                                //یک شئی در قالب فزمت جی سون از سرور ارسال میکنم بعد تو اندروید دریافتش میکنم
                                JSONObject jasonObject = new JSONObject(result);

                                //روش اول
                                Globals.USERNAME = jasonObject.getString("USERNAME");
                                Globals.USERID = jasonObject.getString("USERID");
                                Globals.NAME = jasonObject.getString("NAME");

                                //روش دوم
                                UserClass user = new UserClass();
                                user.setUserID(jasonObject.getInt("USERID"));
                                user.setUsername(jasonObject.getString("USERNAME"));
                                user.setName(jasonObject.getString("NAME"));
                                user.setEmail(jasonObject.getString("EMAIL"));
                                user.setMobile(jasonObject.getString("MOBILE"));
                                user.setGender(jasonObject.getString("JENSIAT"));
                                user.setAddress(jasonObject.getString("ADDRESS"));
                                user.setProfileImage(jasonObject.getString("PROFILEIMAGE"));
                                //.........

                                //روش سوم
                                Globals.USER = user;


                                Toast.makeText(LoginActivity.this, "Username: " + username , Toast.LENGTH_SHORT).show();
                            }
                            catch (Exception e)
                            {
                                // اگر کد قسمت Try با خطا مواجه شد کدهای قسمت catch خطا را مدیریت میکنه
                                Toast.makeText(LoginActivity.this, "Error: json format is invalid!" , Toast.LENGTH_SHORT).show();
                                Toast.makeText(LoginActivity.this, e.getMessage() , Toast.LENGTH_SHORT).show();
                                validInput = false;
                            }
                            finally { //نوشتن این بخش اختیاریه
                                if(validInput)
                                {
                                    // login ....
                                }
                                else
                                {
                                    //...
                                }
                            }//پایان جی سون


                        /*
                        ارسال اطلاعات از سمت سرور به کلاینت این روش مشکل داره
                        بهتره از json و یا چیز استفاده کنیم
                        String[] res = result.split("-");
                        Toast.makeText(LoginActivity.this , "massage:"  + res[0] ,Toast.LENGTH_SHORT ).show();
                        Toast.makeText(LoginActivity.this , "user id:"  + res[1] ,Toast.LENGTH_SHORT ).show();
                        Toast.makeText(LoginActivity.this , "username:" + res[2] ,Toast.LENGTH_SHORT ).show();
                        Toast.makeText(LoginActivity.this , "mobile:"   + res[3] ,Toast.LENGTH_SHORT ).show();
                        */




                            //ذخیره اطلاعات ورود به برنامه
                            CheckBox loginCheckBox = (CheckBox) findViewById(R.id.loginSaveInfo);
                            if (loginCheckBox.isChecked() == true) {
                                //اطلاعات در حافظه sharedprefernces ذخیره شود
                                Toast.makeText(LoginActivity.this, "اطلاعات ذخیره شد", Toast.LENGTH_SHORT).show();
                                SharedPreferences sp = getSharedPreferences("MyPref", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("USER_NAME", username);
                                editor.putString("Password", password);
                                editor.putBoolean("IS_LOGIN", true);
                                editor.apply();

                            } else
                                Toast.makeText(LoginActivity.this, "اطلاعات ذخیره نشد", Toast.LENGTH_SHORT).show();

                            //اطلاعات وارد شده درست بوده
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            //انتقال اطلاعات به کمک اینتنت
                            Globals.USERNAME = username; //i.putExtra("USERNAME", username);
                            i.putExtra("Password", password);
                            startActivity(i);
                        }






                    }
                },
                //اگه خطایی اتفاق افتاد هنگام اتصال به سرور تابع ارور ایجاد میشه
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(LoginActivity.this ,"خطا در اتصال به سرور" ,Toast.LENGTH_SHORT ).show();
                        Toast.makeText(LoginActivity.this ,volleyError.getMessage() ,Toast.LENGTH_SHORT ).show(); //خطایی که در متغیر ذخیره شده رو میگه
                    }
                }) {
            @Override
            //
            protected Map<String , String> getParams() throws AuthFailureError {
                //شبیه یک جدوله
                //کلید ها را معمولا برای درک بهتر با حرفو بزرگ مینویسیم
                Map<String, String> params = new Hashtable<String, String>();
                //ارسال یوزر و پسورد به سرور
                params.put("USERNAME", username);
                params.put("PASSWORD", password);
                return params;
            }
        };
        //ایجاد صف و ورود درخواست ها به صف برای ارسال به سرور
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }


    public void goToRegister(View view) {
        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
    }
}