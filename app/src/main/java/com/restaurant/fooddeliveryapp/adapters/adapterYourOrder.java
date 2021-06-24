package com.restaurant.fooddeliveryapp.adapters;
//
//public class adapterYourOrder {
//}


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.restaurant.fooddeliveryapp.R;
import com.restaurant.fooddeliveryapp.addProductBiryani;
import com.restaurant.fooddeliveryapp.allItemList;
import com.restaurant.fooddeliveryapp.models.modelCheckout;
import com.restaurant.fooddeliveryapp.models.modelYourOrder;
import com.restaurant.fooddeliveryapp.models.modelYourOrder;
import com.restaurant.fooddeliveryapp.models.modelfire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class adapterYourOrder extends RecyclerView.Adapter<adapterYourOrder.MyViewHolder> {
    //    public Object flag;
    Context context;
    List<modelYourOrder> list;
    List<modelCheckout>listmc=new ArrayList<>();
    public int flag=0;
    allItemList items=new allItemList();
//    MainActivity mika;
//    List<modelOrderedItem>items=new ArrayList<>();
//    public adapterYourOrder()
//    {}


    public adapterYourOrder(Context cont,List<modelYourOrder>li)
    {
        this.context=cont;
        this.list=li;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public adapterYourOrder.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_item_yourorder,parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull adapterYourOrder.MyViewHolder holder, int position) {
        modelYourOrder m=list.get(position);
        holder.textDate.setText(list.get(position).getDateOfOrder());
//        holder.textStatus.setTextColor(ContextCompat.getColor(context,R.color.teal_700));
        String st=list.get(position).getOrderCompleteStatus();
        if(st!=null&&st.equals("not complete"))
        {
            holder.textStatus.setTextColor(ContextCompat.getColor(context,R.color.teal_700));
        }else
        {
            holder.textStatus.setTextColor(ContextCompat.getColor(context, R.color.purple_500));
        }
        holder.textStatus.setText(list.get(position).getOrderCompleteStatus());

//        holder.textStatus.setText(list.get(position).getOrderCompleteStatus());
        holder.textGdPrice.setText(list.get(position).getGrandTotal());
        holder.textName.setText(list.get(position).getName().toString());
        holder.textCount.setText(list.get(position).getCount().toString());
        holder.textPrice.setText(list.get(position).getPrice().toString());
        holder.textPlate.setText(list.get(position).getPlate().toString());

//        StringBuilder builder = new StringBuilder();
//                for (modelCheckout details : list.get(position).getList()) {
//                    builder.append(details + "\n");
////        listmc.add(list.get(position).getList());
//        StringBuilder builder = new StringBuilder();
//        String detail;
//        for (int i = 0; i <list.get(position).getList().size(); i++) {
//            detail=list.get(position).getList().get(i).getItemName();
//            builder.append(detail + "\n");
//
//        }m
//        holder.textName.setText(builder.toString());
//                }
        modelfire mf = new modelfire();
//        Log.d("rakakkkbhai", "onBindViewHolder: the String is"+list.get(position).getList().size());
//                holder.tn.setText(builder.toString());
//        holder.textName.setText(builder.toString());
//        holder.textName.setText(list.get(position).getName());
//        holder.text2.setText(list.get(position).getImage());
//        holder.textPrice.setText(list.get(position).getPrice());
//        holder.textDescription.setText(list.get(position).getDescription());
//        holder.text2.setText(list.get(position).getLoc());
//        Glide.with(holder.imgBiryani.getContext()).load(list.get(position).getImage()).into(holder.imgBiryani);
//        if (items.item.equals("added"))
//        {
////            holder.btn.setText("added");
//        }

//        holder.btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(holder.btn.getText().toString().equals("add to cart"))
//                {
//                    Intent intent=new Intent(context, addProductBiryani.class);
//                    intent.putExtra("img",list.get(position).getImage());
//                    intent.putExtra("name",list.get(position).getName());
//                    intent.putExtra("desc",list.get(position).getDescription());
//                    intent.putExtra("fullPlatePrice",list.get(position).getFullPlatePrice());
//                    intent.putExtra("halfPlatePrice",list.get(position).getHalfPlatePrice());
//                    intent.putExtra("fullPrice",list.get(position).getFullPrice());
//                    intent.putExtra("halfPrice",list.get(position).getHalfPrice());
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
//                    holder.btn.setText("added");
//
////                         modelOrderedItem m=new modelOrderedItem(list.get(position).getName(),list.get(position).getPrice(),list.get(position).getImage(),flag);
////                         item.shashikant.add(m);
//                }else
//                {
//                    Toast.makeText(context, "the item is already added", Toast.LENGTH_SHORT).show();
//                }
//
////                     Toast.makeText(context, "the button got clicked", Toast.LENGTH_SHORT).show();
//            }
//        });



    }

    @Override
    public int getItemCount() {

        return list.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder
    {
//        ImageView imgBiryani;
//        Button btn;
        TextView textDate,textStatus,textGdPrice,textName,textPrice,textCount,textPlate;
        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
           textDate=itemView.findViewById(R.id.dateOfOrderYourOrder);
           textStatus=itemView.findViewById(R.id.orderCompleteStatusYourOrder);
           textGdPrice=itemView.findViewById(R.id.grandTotalYourOrder);
           textName=itemView.findViewById(R.id.itemNameYourOrder);
           textPrice=itemView.findViewById(R.id.itemPriceYourOrder);
           textCount=itemView.findViewById(R.id.itemCountYourOrder);
           textPlate=itemView.findViewById(R.id.itemPlateYourOrder);
        }
    }
}