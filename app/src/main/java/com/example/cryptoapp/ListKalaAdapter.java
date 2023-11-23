package com.example.cryptoapp;
//یه اداپتر برای پر کردن لیست ویو
//کارش پر کردن اطلاعات در لیست ویوه
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ListKalaAdapter extends BaseAdapter {

    private Context context ; //محتوای صفحه رو براش ارسال میکنیم
    private KalaClass[] kalaList ;
    LayoutInflater inflater = null ;
    // تابع سازنده:مقدار دهی اولیه را انجام میده و اطلاعاتی که لازمه رو میتونیم با تابع سازنده دریافت و تو لیست ویو قرار بدیم
    public ListKalaAdapter (Context context , KalaClass[] kalaList)
    {
        this.context = context;
        this.kalaList = kalaList;
        //اطلاعات رو داخل ردیف ها چیدمانش رو انجام بده
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //تابع: تعداد عناصر رو باید براش مشخص کنیم چندتا کالاست؟
    @Override
    public int getCount() {
        return kalaList.length;
    }

    //یکی از عناصر رو بهمون میده مثل کالای اول
    @Override
    public Object getItem(int position) {
        return null;
    }

    //ایدی عناصر رو بهمون میده
    @Override
    public long getItemId(int position) {
        return 0;
    }

    //کار پر کردن ردیف ها با اطلاعات رو انجام میده
    //این تابع به ازای تک تک ردیف ها اجرا میشه
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //یک قالب لیست رو داریم بخونش
        convertView = inflater.inflate(R.layout.list_row,null);

        //تعیین عکس از سرور و نمایش آن
        ImageView img = (ImageView) convertView.findViewById(R.id.listviewImage) ;
        Picasso.get().load(Globals.SERVER + "/files/images/" + kalaList[position].getImage()).into(img);

/*
        //رویداد کلیک برای عیکس
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"تصویر شماره ی"+position+"کلیک شد" ,Toast.LENGTH_LONG).show();
            }
        });
*/
        //نام
        TextView name = (TextView)convertView.findViewById(R.id.listviewName);
        name.setText(kalaList[position].getkName());

        //توضیحات
        TextView description = (TextView)convertView.findViewById(R.id.listviewDescription);
        description.setText(kalaList[position].getDescription());


        return convertView;

    }

}