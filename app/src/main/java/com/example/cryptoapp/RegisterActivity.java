package com.example.cryptoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Hashtable;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {


    //ایجاد شئی از یوزر کلس
    UserClass user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //تابع سازنده جدید
        user = new UserClass();
    }


    public void doRegister(View view) {

        EditText usernameEdt, nameEdt, emailEdt, mobileEdt, passwordEdt , rePasswordEdt, addressEdt;
       //متغیر های غیر متنی
        RadioButton rbMale;
        CheckBox appRules;


        rbMale = findViewById(R.id.radioMale); //(RadioButton)findViewById(R.id.radioMale) for old versions
        appRules = findViewById(R.id.appRules); //(CheckBox)findViewById(R.id.appRules) for old versions

        //فیلد متنی هایی که نیازه اطلاعاتشون رو بخونیم
        usernameEdt = (EditText) findViewById(R.id.usernameEdt);
        nameEdt = (EditText) findViewById(R.id.nameEdt);
        emailEdt = (EditText) findViewById(R.id.emailEdt);
        mobileEdt = (EditText) findViewById(R.id.mobileEdt);
        passwordEdt = (EditText) findViewById(R.id.passwordEdt);
        rePasswordEdt = (EditText) findViewById(R.id.rePasswordEdt);
        addressEdt = (EditText) findViewById(R.id.addressEdt);

        //flag bezarim ke bedunam khata vojud dare hain sabtenam ya na
        boolean allowToRegister = true;


        //ذخیره یوزر و پسورد و ....
        //String username = String.valueOf(usernameEdt.getText());
        user.setUsername(String.valueOf(usernameEdt.getText()));
        //String name = String.valueOf(nameEdt.getText());
        user.setName(String.valueOf(nameEdt.getText()));


        //String email = String.valueOf(emailEdt.getText());
       if( user.setEmail(String.valueOf(emailEdt.getText())) == false )
       {
           //Toast.makeText(RegisterActivity.this, "You have entered a wrong email format!", Toast.LENGTH_SHORT).show();
           emailEdt.setError("You have entered a wrong email format!");
           allowToRegister = false;
       }


        //String mobile = String.valueOf(mobileEdt.getText());
        user.setMobile(String.valueOf(mobileEdt.getText()));


        String password = String.valueOf(passwordEdt.getText());
        String rePassword = String.valueOf(rePasswordEdt.getText());
        if(password.equals(rePassword))
            user.setPassword(password);
        else
        {
            //TODO
            rePasswordEdt.setError("Password and repassword are not match!");
            allowToRegister = false;
        }

        //String address = String.valueOf(addressEdt.getText());
        user.setAddress(String.valueOf(addressEdt.getText()));

        //mamulan ettelaat ro string dar nazar migirim chon qarar nist amaliat rush anjam beshe
        //String gender;
        if(rbMale.isChecked())
            user.setGender("0"); //gender = "0";
        else
            user.setGender("1"); //gender = "1";











        if(!appRules.isChecked()) {
            Toast.makeText(RegisterActivity.this, "Accept the rules first to register", Toast.LENGTH_SHORT).show();
            allowToRegister = false;
        }
        if(allowToRegister == true) //if(allowToRegister)
        {
            //TODO
            //پیاده سازی api
            // String API_URL = "http://127.0.0.1/api/submit-register.php"; in ip loop shabake hast bedard nemikhore
            String API_URL = Globals.SERVER+"/api/submit-register.php"; //bayad ip un shabihsazemoon ro peyda konim
            //String API_URL = "http://10.0.2.2/api/submit-register.php"; ip khode gushi as developer option
            //String API_URL = "http://10.0.2.2/api/submit-register.php"; age server vaqei bashe address site ro mizanim
            StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String result) {
                            Toast.makeText(RegisterActivity.this , result ,Toast.LENGTH_SHORT ).show();


                            if (result.equals("success")) {
                                //اگر ثبت نام با موفقیت انجام شد به صفحه اصلی منتقل شود
                                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                //انتقال اطلاعات به کمک اینتنت
                                i.putExtra("USERNAME", user.getUsername());
                                i.putExtra("Password", user.getPassword());
                                startActivity(i);
                            } else {
                                //خطا در ثبت نام
                                TextView txt = (TextView) findViewById(R.id.invalidRegisterTxt);
                                txt.setVisibility(View.VISIBLE);
                            }


                        }
                    },
                    //اگه خطایی اتفاق افتاد هنگام اتصال به سرور تابع ارور ایجاد میشه
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(RegisterActivity.this, "خطا در اتصال به سرور", Toast.LENGTH_SHORT).show();
                            Toast.makeText(RegisterActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show(); //خطایی که در متغیر ذخیره شده رو میگه
                        }
                    }) {
                @Override
                //
                protected Map<String, String> getParams() throws AuthFailureError {
                    //شبیه یک جدوله
                    //کلید ها را معمولا برای درک بهتر با حرفو بزرگ مینویسیم
                    Map<String, String> params = new Hashtable<String, String>();
                    //ارسال اطلاعات به سرور
                    params.put("USERNAME" , user.getUsername());
                    params.put("PASSWORD" , user.getPassword());
                    params.put("NAME"     , user.getName());
                    params.put("EMAIL"    , user.getEmail());
                    params.put("MOBILE"   , user.getMobile());
                    params.put("ADDRESS"  , user.getAddress());
                    params.put("GENDER"   , user.getGender());
                    return params;
                }
            };
            //ایجاد صف و ورود درخواست ها به صف برای ارسال به سرور
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

        }

        else
        {
            Toast.makeText(RegisterActivity.this, "Before the register do the things right bro! ", Toast.LENGTH_SHORT).show();
        }



    }





}