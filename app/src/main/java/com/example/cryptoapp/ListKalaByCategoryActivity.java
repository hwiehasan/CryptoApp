package com.example.cryptoapp;
////////////TODO
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
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
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
/*
تو این اکتیویتی برای گرفتن لیست کالا از سرور از فرمت xml استفاده کردم
و بجای لیست ویو از ریسایکلرویو استفاده کردم
 */
public class ListKalaByCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kala_by_category);

        getListCategory(); //api1: لیست دسته بندیها رو لود میکنه
        //getListKalaByCategoryID("0"); //api2: لیست تمام کالاها رو لود میکنه


        //درحالتی که دسترسی به سرور وجود ندارد TODO
        List<KalaClass> listkala = new ArrayList<KalaClass>();
        DBAdapter  db = new DBAdapter(ListKalaByCategoryActivity.this);
        db.open();
        Cursor c = db.getAllKala();
        if (c.moveToFirst()) //رفتن به اولین رکورد ینی حداقل رکوردی وجود داره
        {
            do{
                //TODO استفاده از اطلاعات
                KalaClass kala = new KalaClass();
                kala.setkID(c.getInt(0));
                kala.setkName(c.getString(1));
                kala.setImage(c.getString(2));
                kala.setSubmitDate(c.getString(3));
                kala.setCatID(c.getInt(4));

                listkala.add(kala); //هرکالایی که از دیتا بیس خونده شده به لیست کالا اضافه شه
            }while(c.moveToNext()); //از اطلاعات که استفاده کردی برو رکورد بعدی تا زمانی که اطلاعات تمام بشن
        }
        db.close();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerListKalaAdapter adapter = new RecyclerListKalaAdapter(listkala , ListKalaByCategoryActivity.this);
        recyclerView.setAdapter(adapter);






/* این پیاده سازی تو حالت دستیه و نه api گذاشتمش شاید یه روز لازمم شه
        //کاربر بتونه دسته بندی های مختلفیو انتخاب کنه با استفاده از اسپینر
        //پیاده سازی Spinner
        Spinner spinner = (Spinner)findViewById(R.id.categorySpinner);
        //حالا باید لیست دسته بندی هارو با استفاده از api از سرور بخونه و در اسپینر قرار بده
        //اسپینر بطور پیش فرض با استرینگ کار میکنه ولی میشه تغییرش داد
        List<String> listCategory = new ArrayList<>();
        listCategory.add("Electric");
        listCategory.add("clouse");
        listCategory.add("food");
        listCategory.add("sport");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ListKalaByCategoryActivity.this , android.R.layout.simple_spinner_item , listCategory);
        //قالب باز شدن اداپتر لیست مه از بالا به پایین باز بشه
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view , int i , long l )
            {
                getListKalaByCategoryID(String.valueOf(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }

        });
*/
    }





//لیست رو بر حسب دسته بندی دریافت میکنه و اطلاعاتش رو از طریق ایکس ام ال دریافت میکنه و پارس میکنه
    public void getListKalaByCategoryID(String categoryID){
        String API_URL = Globals.SERVER+"/api/listkalaxml.php"; //بجای جیسون از xml استفاده کردم
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {
                        Toast.makeText(ListKalaByCategoryActivity.this, result, Toast.LENGTH_SHORT).show();
                        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
                        TextView txt = (TextView) findViewById(R.id.listKalaCategoryMessage);
                        //سه حالت داریم
                        if (result.equals("fail"))
                        {
                            //اگر در دریافت اطلاعات با مشکل مواجه شوم
                            txt.setVisibility(View.VISIBLE);
                            txt.setText("Error while getting the information!");
                            recyclerView.setVisibility(View.GONE);
                        }
                        else if (result.equals("noproducts"))
                        {
                            //اگر کاربر هیچ محصولی ثبت نکرده باشد
                            txt.setVisibility(View.VISIBLE);
                            txt.setText("There is no product to show!");
                            recyclerView.setVisibility(View.GONE);
                        }
                        else {

                            recyclerView.setVisibility(View.VISIBLE);
                            txt.setVisibility(View.GONE);


                            //XML STARTED : اطلاعات از سرور بگیره و به کاربر نمایش بده
                            try {
                                XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
                                XmlPullParser myParser = xmlFactoryObject.newPullParser();
                                myParser.setInput(new StringReader(result));
                                int event = myParser.getEventType();
                                List<KalaClass> listkala = new ArrayList<KalaClass>();
                                String kid="1", price="0" , name="a" ,image="b" , desc="c" , submitDate="d";
                                do {    //کد xml رو پارس کردم و اطلاعات رو استخراج کردم

                                    String tagName = myParser.getName();
                                    if (tagName != null)
                                    {
                                        if (tagName.equals("KID"))
                                        {
                                            //kala.setkID(Integer.valueOf(myParser.nextText()));
                                            kid = myParser.nextText();
                                        }


                                        else if (tagName.equals("KPRICE"))
                                        {
                                            price = myParser.nextText();
                                        }



                                        else if (tagName.equals("KNAME"))
                                        {
                                            //kala.setkName(myParser.nextText());
                                            name = myParser.nextText();
                                        }
                                        else if(tagName.equals("KIMAGE"))
                                        {
                                            //kala.setkImage(myParser.nextText());
                                            image = myParser.nextText();
                                        }
                                        else if(tagName.equals("KDESCRIPTION"))
                                        {
                                            //kala.setkDescription(myParser.nextText());
                                            desc = myParser.nextText();
                                        }
                                        else if(tagName.equals("KSUBMITDATE"))
                                        {
                                            //kala.setkSubmitDate(myParser.nextText());
                                            submitDate = myParser.nextText();
                                            KalaClass kala = new KalaClass();
                                            kala.setkID(Integer.valueOf(kid));
                                            kala.setPrice(Integer.valueOf(price));
                                            kala.setkName(name);
                                            kala.setImage(image);
                                            kala.setDescription(desc);
                                            kala.setSubmitDate(submitDate);
                                            listkala.add(kala); //داخل یک ارایه از کلاس قرار دادمش



                                            //افزودن کالا به پایگاه داده
                                            DBAdapter db = new DBAdapter(ListKalaByCategoryActivity.this);
                                            db.open();
                                            //TODO catID

                                            //روش اول
                                            //db.insertKala(Integer.valueOf(kid) , name , submitDate , image , 0);
                                            //روش دوم
                                            db.insertKala2(kala);
                                            db.close();

                                        }
                                    }
                                    event = myParser.next();
                                }while (event != XmlPullParser.END_DOCUMENT);

                                Toast.makeText(ListKalaByCategoryActivity.this, "The list has been complited", Toast.LENGTH_SHORT).show();

                                //پیاده سازی اداپتر ریسایکلر ویو

                                RecyclerListKalaAdapter adapter = new RecyclerListKalaAdapter(listkala , ListKalaByCategoryActivity.this);
                                recyclerView.setAdapter(adapter);






                            }
                            catch (XmlPullParserException xmlPullParserException)
                            {

                            }
                            catch (IOException ioException){


                            }


                        }
                    }
                },
                //اگه خطایی اتفاق افتاد هنگام اتصال به سرور تابع ارور ایجاد میشه
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(ListKalaByCategoryActivity.this ,"خطا در اتصال به سرور" ,Toast.LENGTH_SHORT ).show();
                    }
                }) {
            @Override
            //
            protected Map<String , String> getParams() throws AuthFailureError {
                //شبیه یک جدوله
                //کلید ها را برای درک بهتر با حرفو بزرگ مینویسم
                Map<String, String> params = new Hashtable<String, String>();
                //ارسال یوزر و پسورد به سرور

                params.put("CATEGORYID", categoryID );
                return params;
            }
        };
        //ایجاد صف و ورود درخواست ها به صف برای ارسال به سرور
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

















    //api برای اینکه همه دسته بندی های کالا رو برام بیاره
    public void getListCategory()
    {
        String API_URL = Globals.SERVER+"/api/listcategory.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {
                        Toast.makeText(ListKalaByCategoryActivity.this, result, Toast.LENGTH_SHORT).show();

                            //JSON STARTED : اطلاعات از سرور بگیره و به کاربر نمایش بده
                            try {

                                //یک شئی در قالب فرمت جی سون از سرور ارسال میکنم بعد تو اندروید دریافتش میکنم
                                JSONArray jsonArray = new JSONArray(result);



                                //کاربر بتونه دسته بندی های مختلفیو انتخاب کنه با استفاده از اسپینر
                                //پیاده سازی Spinner
                                Spinner spinner = (Spinner)findViewById(R.id.categorySpinner);
                                //اسپینر بطور پیش فرض با استرینگ کار میکنه ولی میشه تغییرش داد
                                List<String> listCategory = new ArrayList<>();
                                List<String> listCategoryID = new ArrayList<>(); //این روش بهینه نیست برای ایدی


                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jasonObject = jsonArray.getJSONObject(i);

                                    listCategoryID.add(jasonObject.getString("CATID"));
                                    listCategory.add(jasonObject.getString("CATNAME"));


                                }

                                ArrayAdapter<String> adapter = new ArrayAdapter<>(ListKalaByCategoryActivity.this , android.R.layout.simple_spinner_item , listCategory);
                                //قالب باز شدن اداپتر لیست مه از بالا به پایین باز بشه
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner.setAdapter(adapter);

                                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                                {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view , int i , long l )
                                    {
                                        getListKalaByCategoryID(listCategoryID.get(i));
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView)
                                    {


                                    }

                                });




                            } catch (Exception e) {
                                // اگر کد قسمت Try با خطا مواجه شد کدهای قسمت catch خطا را مدیریت میکنه
                                Toast.makeText(ListKalaByCategoryActivity.this, "Error: json format is invalid!", Toast.LENGTH_SHORT).show();

                            }
                            //پایان جی سون
                        }
                },
                //اگه خطایی اتفاق افتاد هنگام اتصال به سرور تابع ارور ایجاد میشه
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(ListKalaByCategoryActivity.this ,"Error while connecting to the server!" ,Toast.LENGTH_SHORT ).show();
                    }
                }) {
            @Override
            //
            protected Map<String , String> getParams() throws AuthFailureError {
                //شبیه یک جدوله
                //کلید ها را برای درک بهتر با حرفو بزرگ مینویسم
                Map<String, String> params = new Hashtable<String, String>();
                return params;
            }
        };
        //ایجاد صف و ورود درخواست ها به صف برای ارسال به سرور
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }




}