package com.example.cryptoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

public class UserProfileActivity extends AppCompatActivity {



    private TextView userNameEdt , nameEdt , userEmailEdt , userNumberEdt ,userAddressEdt, userGenderEdt  ,listUserMessage ;

    private ImageView userPicture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getListKalaByUserID(    String.valueOf(Globals.USER.getUserID())   );



        userPicture = (ImageView) findViewById(R.id.userPicture);
        userNameEdt = (TextView) findViewById(R.id.userNameEdt);
        nameEdt = (TextView) findViewById(R.id.nameEdt);
        userEmailEdt = (TextView) findViewById(R.id.userEmailEdt);
        userNumberEdt = (TextView) findViewById(R.id.userNumberEdt);
        userAddressEdt = (TextView) findViewById(R.id.userAddressEdt);
        userGenderEdt = (TextView) findViewById(R.id.userGenderEdt);
        listUserMessage = (TextView) findViewById(R.id.listUserMessage);

    }


    public void getListKalaByUserID(String userID){
        String API_URL = Globals.SERVER+"/api/getuserinfo.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {
                        Toast.makeText(UserProfileActivity.this, result, Toast.LENGTH_SHORT).show();


                        //سه حالت داریم
                        if (result.equals("fail")) {
                            //اگر در دریافت اطلاعات با مشکل مواجه شوم
                            TextView txt = (TextView) findViewById(R.id.listUserMessage);
                            txt.setVisibility(View.VISIBLE);
                            txt.setText("Error while getting the information!");
                        } else if (result.equals("noproducts")) {
                            //اگر کاربر هیچ محصولی ثبت نکرده باشد
                            TextView txt = (TextView) findViewById(R.id.listUserMessage);
                            txt.setVisibility(View.VISIBLE);
                            txt.setText("There is no user to show!");
                        } else {

                            //JSON STARTED : اطلاعات از سرور بگیره و به کاربر نمایش بده
                            try {


                                //یک شئی در قالب فرمت جی سون از سرور ارسال میکنم بعد تو اندروید دریافتش میکنم
                                //نیاز به آرایه ای از کالاها داریم اینجا

                                JSONArray jsonArray = new JSONArray(result);
                                UserClass[] listUser = new UserClass[jsonArray.length()]; //نوعش آرایه گذاشتم

                                int i=0;
                                    JSONObject jasonObject = jsonArray.getJSONObject(i);

                                listUser[i]= new UserClass(); //کالا i جاش در حافظه مشخص بشه بعد مقداردهی
                                listUser[i].setProfileImage(jasonObject.getString("UIMAGE"));
                                listUser[i].setUsername(jasonObject.getString("USERNAME"));
                                listUser[i].setName(jasonObject.getString("UNAME"));
                                listUser[i].setEmail(jasonObject.getString("UEMAIL"));
                                listUser[i].setMobile(jasonObject.getString("UMOBILE"));
                                listUser[i].setGender(jasonObject.getString("UGENDER"));
                                listUser[i].setAddress(jasonObject.getString("UADDRESS"));


                                Picasso.get().load(Globals.SERVER + "/files/userimages/" + listUser[0].getProfileImage()).into(userPicture);
                                userNameEdt.setText(listUser[0].getUsername());

                                nameEdt.setText(listUser[0].getName());
                                userEmailEdt.setText(listUser[0].getEmail());
                                userNumberEdt.setText(listUser[0].getMobile());
                                userGenderEdt.setText(listUser[0].getGender());
                                if(userGenderEdt.equals("0"))
                                    userGenderEdt.setText("Female");
                                else
                                    userGenderEdt.setText("Male");

                                userAddressEdt.setText(listUser[0].getAddress());




                            } catch (Exception e) {
                                // اگر کد قسمت Try با خطا مواجه شد کدهای قسمت catch خطا را مدیریت میکنه
                                Toast.makeText(UserProfileActivity.this, "Error: json format is invalid!", Toast.LENGTH_SHORT).show();

                            }
                            //پایان جی سون
                        }
                    }
                },
                //اگه خطایی اتفاق افتاد هنگام اتصال به سرور تابع ارور ایجاد میشه
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(UserProfileActivity.this ,"خطا در اتصال به سرور" ,Toast.LENGTH_SHORT ).show();
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


    public void goToAddnewKalaActivity(View view) {
        Intent i = new Intent(UserProfileActivity.this , AddNewKalaActivity.class);
        startActivity(i);
    }
}