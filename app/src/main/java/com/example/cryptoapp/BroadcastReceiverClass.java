package com.example.cryptoapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/*
برادکست: برای مثال وقتی که اندروید به وایفای یا دیتا متصل میشه، خوده اندروید یک پیام بصورت برادکست بین برنامه ها پخش میکنه، و اون برنامه هایی که میشنون اپدیت میشن، مثل دریافت پیام های واتساپ و تلگرام بعد از اتصال به اینترنت

یا اتفاقات دیگه ای که داخل سیستم میوفته برادکست میشه، مثل وقتی سطح باتری از یه حدی کمتر میشه میاد پیغام میده که شارژ کمه ، حالت پرواز ، دریافت و ارسال اس ام اس،
 */
public class BroadcastReceiverClass extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if(action.equals("android.intent.action.SCREEN_OFF"))
        {
            Toast.makeText(context, "broadcast received: Screen turned off", Toast.LENGTH_SHORT).show();
        //اینجا میتونم کد بزنم مثلا اگه صفحه خاموش شد برنامه متوقف شه
        }
        else if(action.equals("android.net.conn.CONNECTIVITY_CHANGE"))
        {
            Toast.makeText(context, "broadcast received: Network status changed", Toast.LENGTH_SHORT).show();
        //مثلا اگه به اینترنت وصل شد تو بازی بهش اگهی نشون بده
        }

        Toast.makeText(context, "A broadcast has been received", Toast.LENGTH_SHORT).show();


    }
}
