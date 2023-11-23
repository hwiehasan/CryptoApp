package com.example.cryptoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

public class ListKalaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kala);
        getListKalaByUserID(    String.valueOf(Globals.USER.getUserID())   );
    }


    public void getListKalaByUserID(String userID){
        String API_URL = Globals.SERVER+"/api/listkala.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {
                        Toast.makeText(ListKalaActivity.this, result, Toast.LENGTH_SHORT).show();


                        //سه حالت داریم
                        if (result.equals("fail")) {
                            //اگر در دریافت اطلاعات با مشکل مواجه شوم
                            TextView txt = (TextView) findViewById(R.id.listKalaMessage);
                            txt.setVisibility(View.VISIBLE);
                            txt.setText("Error while getting the information!");
                        } else if (result.equals("noproducts")) {
                            //اگر کاربر هیچ محصولی ثبت نکرده باشد
                            TextView txt = (TextView) findViewById(R.id.listKalaMessage);
                            txt.setVisibility(View.VISIBLE);
                            txt.setText("There is no product to show!");
                        } else {

                            //JSON STARTED : اطلاعات از سرور بگیره و به کاربر نمایش بده
                            try {


                                //یک شئی در قالب فرمت جی سون از سرور ارسال میکنم بعد تو اندروید دریافتش میکنم
                                //نیاز به آرایه ای از کالاها داریم اینجا
                                JSONArray jsonArray = new JSONArray(result);
                                KalaClass[] listKala = new KalaClass[jsonArray.length()]; //نوعش آرایه گذاشتم

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jasonObject = jsonArray.getJSONObject(i);

                                    listKala[i]= new KalaClass(); //کالا i جاش در حافظه مشخص بشه بعد مقداردهی
                                    listKala[i].setkID(jasonObject.getInt("KID"));
                                    listKala[i].setkName(jasonObject.getString("KNAME"));
                                    listKala[i].setDescription(jasonObject.getString("KDESCRIPTION"));
                                    listKala[i].setImage(jasonObject.getString("KIMAGE"));
                                    listKala[i].setSubmitDate(jasonObject.getString("KSUBMITDATE"));
                                }



                                //آداپتر کالا رو ساختیم و حالا ادامه ی مراحل //برای نمایش کالاها
                                ListKalaAdapter adapter = new ListKalaAdapter(ListKalaActivity.this , listKala);
                                ListView list = (ListView)findViewById(R.id.kalaListView);
                                list.setAdapter(adapter);
                                //وقتی رو هر لیست کلیک بشه این رویداد اجرا بشه
                                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent i = new Intent(ListKalaActivity.this , ShowKalaActivity.class);
                                        i.putExtra("CHOOSED_KALA", listKala[position].getkID()); //برای اینکه ایدی کالا از ارایا جیسون بگیره و تتشخیص بده کدوم کالا رو نمایش بده
                                        startActivity(i);
                                    }


                                });



                            } catch (Exception e) {
                                // اگر کد قسمت Try با خطا مواجه شد کدهای قسمت catch خطا را مدیریت میکنه
                                Toast.makeText(ListKalaActivity.this, "Error: json format is invalid!", Toast.LENGTH_SHORT).show();

                            }
                            //پایان جی سون
                        }
                    }
                },
                //اگه خطایی اتفاق افتاد هنگام اتصال به سرور تابع ارور ایجاد میشه
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(ListKalaActivity.this ,"خطا در اتصال به سرور" ,Toast.LENGTH_SHORT ).show();
                    }
                }) {
            @Override
            //
            protected Map<String , String> getParams() throws AuthFailureError {
                //شبیه یک جدوله
                //کلید ها را برای درک بهتر با حرفو بزرگ مینویسم
                Map<String, String> params = new Hashtable<String, String>();
                //ارسال یوزر و پسورد به سرور
                params.put("USERID", userID );
                return params;
            }
        };
        //ایجاد صف و ورود درخواست ها به صف برای ارسال به سرور
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }









/*
        ListView list =(ListView)findViewById(R.id.galleryListView);
        ListViewAdapter adapter = new ListViewAdapter(ListViewActivity.this , infolist);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ListViewActivity.this , ShowImageActivity.class);
                i.putExtra("CHOOSED_IMAGE",position);
                startActivity(i);
            }
        });

*/




}