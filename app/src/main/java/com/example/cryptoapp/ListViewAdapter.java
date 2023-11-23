package com.example.cryptoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewAdapter extends BaseAdapter {

    private Context context ;
    private InfoClass[] infolist ;
    LayoutInflater inflater = null ;

    public ListViewAdapter (Context context , InfoClass[] infolist)
    {
        this.context = context;
        this.infolist = infolist;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return infolist.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.list_row,null);

        ImageView img = (ImageView) convertView.findViewById(R.id.listviewImage) ;
        img.setImageResource(infolist[position].getImage());
/*
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"تصویر شماره ی"+position+"کلیک شد" ,Toast.LENGTH_LONG).show();
            }
        });
*/


        TextView name = (TextView)convertView.findViewById(R.id.listviewName);
        name.setText(infolist[position].getName());

        TextView description = (TextView)convertView.findViewById(R.id.listviewDescription);
        description.setText(infolist[position].getDescription());


        return convertView;

    }

}
