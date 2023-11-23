package com.example.cryptoapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;


public class ShowKalaActivity extends AppCompatActivity { //لیست کالای هر یوزر

    private Integer imageID;
    private ImageView bigImage;
    private TextView imageDescription , imagePrice , imageDate , imageView;
    //1111111111111111111111111111111 اضافه کردن متغیرون//TODO
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_kala);

        Intent i = getIntent();
        imageID = i.getIntExtra("CHOOSED_KALA", 0);

        bigImage = (ImageView) findViewById(R.id.bigImage);
        imageDescription = (TextView) findViewById(R.id.imageDescription);
        imagePrice = (TextView) findViewById(R.id.imagePrice);
        imageDate = (TextView) findViewById(R.id.imageDate);
        imageView = (TextView) findViewById(R.id.imageView);
        //222222222222222222222222 اضافه کردن متغیرون//TODO

        getKalaByID(String.valueOf(imageID));



        //درحالتی که ارتباط با سرور قطعه TODO
        //impppppppppppppppppppppppppppppppppppppppppppppppppppppppppp
        getKalaFromDatabaseByID(imageID);







        //برای امکانات بیشتر
        //TODO

        /*

        //اگه رو عکسه بزرگ طولانی مدت کلیک کرد یک منو جدید بصورت پاپ اپ نمایشش بده
        bigImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu menu = new PopupMenu(ShowKalaActivity.this, v);
                menu.getMenuInflater().inflate(R.menu.image_menu, menu.getMenu());
                menu.show();

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.imagemenuShere) {
                            Toast.makeText(ShowKalaActivity.this, "تصویر به اشتراک گذاشته شود ", Toast.LENGTH_LONG).show();
                        }
                        return false;
                    }
                });
                return false;
            }
        });




        */

    }




    public void getKalaFromDatabaseByID(int kID)
    {
        DBAdapter db = new DBAdapter(ShowKalaActivity.this);
        db.open();
        Cursor c = db.getKala(kID);
        if(c.moveToFirst())
        {
            //TODO بصورت افلاین بخونه
            Picasso.get().load(Globals.SERVER + "/files/images/"+c.getString(2)).into(bigImage);
            imageDescription.setText(c.getString(1));
            imageDate.setText(c.getString(3));
        }
        else Toast.makeText(ShowKalaActivity.this, "The product dosen't exist!", Toast.LENGTH_SHORT).show();
        db.close();

    }

    //نوشتن ای پی ای برای دریافت کالا از سرور(یک کالا)
    public void getKalaByID(String kID)
    {
        String API_URL = Globals.SERVER+"/api/getkalainfo.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {
                        Toast.makeText(ShowKalaActivity.this, result, Toast.LENGTH_SHORT).show();


                        //سه حالت داریم
                        if (result.equals("fail"))
                        {
                            //TODO
                            Toast.makeText(ShowKalaActivity.this, "error fail", Toast.LENGTH_SHORT).show();
                            //اگر در دریافت اطلاعات با مشکل مواجه شدم پیغام مناسبی نمایش بده
                        }
                        else {

                            //JSON STARTED : اطلاعات از سرور بگیره و به کاربر نمایش بده
                            try {






                                /*
                                //یک شئی در قالب فرمت جی سون از سرور ارسال میکنم بعد تو اندروید دریافتش میکنم
                                //نیاز به آرایه ای از کالاها داریم اینجا
                                JSONArray jsonArray = new JSONArray(result);
                                KalaClass[] listKala = new KalaClass[jsonArray.length()]; //نوعش آرایه گذاشتم
                                //میشه حذفش کرد بدون حلقه نوشتش چون فقط برای یک کالا هست


                                for (int i = 0; i <= jsonArray.length(); i++)
                                {

                                    JSONObject jasonObject = jsonArray.getJSONObject(i);

                                    listKala[i]= new KalaClass(); //کالا i جاش در حافظه مشخص بشه بعد مقداردهی
                                    listKala[i].setkID(jasonObject.getInt("KID"));
                                    listKala[i].setkName(jasonObject.getString("KNAME"));
                                    listKala[i].setImage(jasonObject.getString("KIMAGE"));
                                    listKala[i].setDescription(jasonObject.getString("KDESCRIPTION"));
                                    listKala[i].setSubmitDate(jasonObject.getString("KSUBMITDATE"));
                                    listKala[i].setPrice(jasonObject.getInt("KPRICE"));
                                    listKala[i].setWeight(jasonObject.getInt("KWEIGHT"));
                                    listKala[i].setNum(jasonObject.getInt("KNUM"));
                                    listKala[i].setColor(jasonObject.getString("KCOLOR"));
                                    listKala[i].setView(jasonObject.getInt("KVIEW"));
                                    listKala[i].setCatID(jasonObject.getInt("KCATID"));
                                    listKala[i].setUserID(jasonObject.getInt("KUSERID"));

                                }*/


                                JSONArray jsonArray = new JSONArray(result);
                                KalaClass[] listKala = new KalaClass[jsonArray.length()]; //نوعش آرایه گذاشتم

                                int i=0;
                                    JSONObject jasonObject = jsonArray.getJSONObject(i);

                                    listKala[i]= new KalaClass(); //کالا i جاش در حافظه مشخص بشه بعد مقداردهی
                                    listKala[i].setPrice(jasonObject.getInt("KPRICE"));
                                    listKala[i].setView(jasonObject.getInt("KVIEW"));
                                    listKala[i].setDescription(jasonObject.getString("KDESCRIPTION"));
                                    listKala[i].setImage(jasonObject.getString("KIMAGE"));
                                    listKala[i].setSubmitDate(jasonObject.getString("KSUBMITDATE"));





                                //تصویر رو از سرور لود کنه و داخله image view قرار بده
                                Picasso.get().load(Globals.SERVER + "/files/images/" + listKala[0].getImage()).into(bigImage);


                                imageDescription.setText(listKala[0].getDescription());
                                imagePrice.setText(String.valueOf(listKala[0].getPrice()));
                                imageDate.setText(listKala[0].getSubmitDate());
                                imageView.setText(String.valueOf(listKala[0].getView()));


                                //3333333333333333 اضافه کردن متغیرون//TODO





                            } catch (Exception e) {
                                // اگر کد قسمت Try با خطا مواجه شد کدهای قسمت catch خطا را مدیریت میکنه
                                Toast.makeText(ShowKalaActivity.this, "Error: json format is invalid!", Toast.LENGTH_SHORT).show();
                            }
                            //پایان جی سون

                        }
                    }
                },
                //اگه خطایی اتفاق افتاد هنگام اتصال به سرور تابع ارور ایجاد میشه
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(ShowKalaActivity.this ,"خطا در اتصال به سرور" ,Toast.LENGTH_SHORT ).show();
                    }
                }) {
            @Override

            protected Map<String , String> getParams() throws AuthFailureError {
                //شبیه یک جدوله
                //کلید ها را برای درک بهتر با حرفو بزرگ مینویسم
                Map<String, String> params = new Hashtable<String, String>();
                //ارسال یوزر و پسورد به سرور
                params.put("KALAID", kID );
                return params;
            }
        };
        //ایجاد صف و ورود درخواست ها به صف برای ارسال به سرور
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public void deleteKala(View view) {
        DBAdapter db = new DBAdapter(ShowKalaActivity.this);
        db.open();
        if(db.deleteKala(imageID))
            Toast.makeText(ShowKalaActivity.this, "Product" + imageID + "successfully removed", Toast.LENGTH_SHORT).show();
        else    Toast.makeText(ShowKalaActivity.this, "Fail! it did not remove", Toast.LENGTH_SHORT).show();
        db.close();

    }

    public void editKala(View view) {
        Intent i = new Intent(ShowKalaActivity.this , EditKalaActivity.class);
        //ایدی کالا رو به صفحه بعد منتقل کنه
        i.putExtra("KALA_ID" , imageID);
        startActivity(i);

    }
}