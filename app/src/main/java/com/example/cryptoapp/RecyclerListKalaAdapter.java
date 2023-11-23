package com.example.cryptoapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

//ویو هولدری که تعریف کردیم و قراره ازش استفاده کنیم رو تعریف کنه
public class RecyclerListKalaAdapter extends RecyclerView.Adapter<RecyclerListKalaAdapter.KalaViewHolder> {


    //این دو دستور بهم اجازه میده به اکتیویتی هم دسترسی پیدا کنم
    List<KalaClass> listKala;
    Context context;
    //تابع سازنده
    public RecyclerListKalaAdapter(List<KalaClass> listKala, Context context)
    {
        this.listKala = listKala;
        this.context = context;
    }

    //ردیف رو برمیداره و داخل ریسایکلر قرار میده
    @NonNull
    @Override
    public KalaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        //ایجاد ویو
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_list_row, parent , false);
        return new KalaViewHolder(view);
    }


    //اطلاعاتم رو داخل ویو قرار میده
    @Override
    public void onBindViewHolder(@NonNull KalaViewHolder holder, int position)
    {
        //مقداردهی فیلدهای تعریف شده
        //imppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp
        holder.desc.setText(listKala.get(position).getDescription());
        holder.name.setText(listKala.get(position).getkName());
        holder.price.setText(String.valueOf(listKala.get(position).getPrice()));
        holder.date.setText(listKala.get(position).getSubmitDate());

        //برای عکس باید از پیکاسو استفاده کنیم
        Picasso.get().load(Globals.SERVER + "/files/images/" + listKala.get(position).getImage()).into(holder.img);


        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context , ShowKalaActivity.class);
                //ایـــــــن خط پایین که اضاف کردم خطا داده
                i.putExtra("CHOOSED_KALA",listKala.get(position).getkID());
                context.startActivity(i);
            }
        });
    }


    //تعداد عناصری که میخوام نمایش بدم رو اینجا مشخص میکنم
    @Override
    public int getItemCount()
    {
        return listKala.size();
    }


    // این کلس پایین فرزنده RecyclerListKalaAdapter هست
    public class KalaViewHolder extends RecyclerView.ViewHolder{ //کار این تابع مدیریت ویو هامون هست
        ImageView img;
        TextView name,date,desc,price;
        CardView card;


        //تابع سازنده
        public KalaViewHolder(@NonNull View itemView) {
            super(itemView);

            img  = (ImageView)itemView.findViewById(R.id.recyclerListImage);
            name = (TextView)itemView.findViewById(R.id.recyclerListName);
            date = (TextView)itemView.findViewById(R.id.recyclerListSubmitDate);
            desc = (TextView)itemView.findViewById(R.id.recyclerListDescription);
            price = (TextView)itemView.findViewById(R.id.recyclerListPrice);
            card = (CardView)itemView.findViewById(R.id.recyclerListCard);

        }
    }





}
