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

public class AddNewKalaActivity extends AppCompatActivity {


    //ایجاد شئی از یوزر کلس
    KalaClass kala;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_kala);

        //تابع سازنده جدید
        kala = new KalaClass();

    }


    public void doAddKala(View view) {

        EditText knameEdt, kpriceEdt, kcatidEdt, kimageEdt, kdescriptionEdt;


        //فیلد متنی هایی که نیازه اطلاعاتشون رو بخونیم
        knameEdt = (EditText) findViewById(R.id.knameEdt);
        kpriceEdt = (EditText) findViewById(R.id.kpriceEdt);
        kcatidEdt = (EditText) findViewById(R.id.kcatidEdt);
        kimageEdt = (EditText) findViewById(R.id.kimageEdt);
        kdescriptionEdt = (EditText) findViewById(R.id.kdescriptionEdt);

        //flag bezarim ke bedunam khata vojud dare hain sabtenam ya na
        boolean allowToRegister = true;




        kala.setkName(String.valueOf(knameEdt.getText()));
        kala.setPrice(Integer.valueOf(String.valueOf(kpriceEdt.getText())));
        kala.setCatID(Integer.valueOf(String.valueOf(kcatidEdt.getText())));
        kala.setImage(String.valueOf(kimageEdt.getText()));
        kala.setDescription(String.valueOf(kdescriptionEdt.getText()));








            //TODO
            //پیاده سازی api

            String API_URL = Globals.SERVER+"/api/submit-newkala.php";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String result) {
                            Toast.makeText(AddNewKalaActivity.this , result ,Toast.LENGTH_SHORT ).show();


                            if (result.equals("success")) {

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
                            Toast.makeText(AddNewKalaActivity.this, "خطا در اتصال به سرور", Toast.LENGTH_SHORT).show();
                            Toast.makeText(AddNewKalaActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show(); //خطایی که در متغیر ذخیره شده رو میگه
                        }
                    }) {
                @Override
                //
                protected Map<String, String> getParams() throws AuthFailureError {
                    //شبیه یک جدوله
                    //کلید ها را معمولا برای درک بهتر با حرفو بزرگ مینویسیم
                    Map<String, String> params = new Hashtable<String, String>();
                    //ارسال اطلاعات به سرور
                    params.put("KNAME" , kala.getkName());
                    params.put("KPRICE" , String.valueOf(kala.getPrice()));
                    params.put("KCATID"     , String.valueOf(kala.getCatID()));
                    params.put("KIMAGE"    , kala.getImage());
                    params.put("DESCRIPTION"   , kala.getDescription());
                    params.put("USERID"  , String.valueOf(Globals.USERID));
                    return params;
                }
            };
            //ایجاد صف و ورود درخواست ها به صف برای ارسال به سرور
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

        }




    }

