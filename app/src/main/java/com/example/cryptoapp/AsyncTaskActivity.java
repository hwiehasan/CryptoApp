package com.example.cryptoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AsyncTaskActivity extends AppCompatActivity {

    private Button button;
    private EditText time;
    private TextView finalResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        time = (EditText) findViewById(R.id.in_time);
        button = (Button) findViewById(R.id.btn_run);
        finalResult = (TextView) findViewById(R.id.tv_result);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                //عددی که وارد میشه رو میخونه و داخل متغیر قرار میده
                String sleepTime = time.getText().toString();
                //هر تعداد پارامتر بخوام میتونم بهش بدم
                runner.execute(sleepTime);
            }
        });
    }


    //نوع داده ای جنریک
    // ورودی تابع سوم و دوم و اول
    private class AsyncTaskRunner extends AsyncTask<String, String, String> {
        private String resp;
        ProgressDialog progressDialog;


        //(2)کاری که تو این فرایند انجام میشه، یک پروسس مثل پخش اهنگ
        //  این تابع زمانی اجرا میشه که یک فرایند اجرا کنیم و زمانی خاتمه پیدا میکنه که فرایند تمام شده باشه

        @Override                       //آرایه رشته ای از نوع استرینگ
        protected String doInBackground(String... params) {

            //اعلام میزان پیشرفت، چند درصد مثلا دانلود شده؟
            publishProgress("Sleeping...");
            //چون یه فرایند طولانیه ممکنه هر نوع خطایی ایجاد بشه پس میزارمش تو ترای تا بتونم خطا ها رو مدیریت کنم
            try {
                //*1000 برای اینکه میلی ثانیه تبدیل به ثانیه بشه
                int time = Integer.parseInt(params[0])*1000;
                //ترد برای چند لحظه متوقف بشه
                Thread.sleep(time);
                                    //این اندیس رو میشه تغییر داد
                resp = "Slept for " + params[0] + " seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            //وقتی ریترن کنه ینی فرایند تموم شده
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
            finalResult.setText(result);
        }

        //(1)نمایش پروگرس قبل از اجرا شدن فرایند
        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(AsyncTaskActivity.this,
                    "ProgressDialog",
                    "Wait for "+time.getText().toString()+ " seconds");
        }

        //(3)میزان پیشرفت رو اپدیت میکنه
        @Override
        protected void onProgressUpdate(String... text) {
            finalResult.setText(text[0]);
        }
    }


    /*
    آموزشی: زمانی که یک برنامه میخوام بنویسیم که حالت های مختلف مقادیر رو ساپورت کنه فلوت اینت استرینگ و... (نوع داده ای جنریک)
    class A <  T  >
    {
        T b;
    }

 */
}