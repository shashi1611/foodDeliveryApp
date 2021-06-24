package com.restaurant.fooddeliveryapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.restaurant.fooddeliveryapp.R;
import com.restaurant.fooddeliveryapp.models.modelPopularItem;
import com.restaurant.fooddeliveryapp.models.modelSoup;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

//public class adapterPopularItem extends RecyclerView  {
//}


public class adapterPopularItem extends RecyclerView.Adapter<adapterPopularItem.MyViewHolder> {
    Context context;
    List<modelPopularItem> list;
    public adapterPopularItem(Context cont,List<modelPopularItem>li)
    {
        this.context=cont;
        this.list=li;
    }
    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public adapterPopularItem.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.popularitem,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull adapterPopularItem.MyViewHolder holder, int position) {
        modelPopularItem m=list.get(position);
        holder.textpop.setText(list.get(position).getName());
//        holder.text2.setText(list.get(position).getImage());
//        holder.textPrice.setText(list.get(position).getPrice());
//        holder.textDescription.setText(list.get(position).getDescription());
//        holder.text2.setText(list.get(position).getLoc());
        Glide.with(holder.imgpop.getContext()).load(list.get(position).getImage()).into(holder.imgpop);

    }

    @Override
    public int getItemCount() {

        return list.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder
    {
//          imgpop;
        CircleImageView imgpop;
        TextView textpop;
        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            textpop=itemView.findViewById(R.id.popularName);
            imgpop=itemView.findViewById((R.id.popularImage));
//            textDescription=itemView.findViewById((R.id.productDescription));
//            imgBiryani=itemView.findViewById(R.id.productImage);
        }
    }
}

