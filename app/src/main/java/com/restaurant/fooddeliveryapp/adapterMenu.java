package com.restaurant.fooddeliveryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class adapterMenu extends RecyclerView.Adapter<adapterMenu.MyViewHolder> {
    Context context;
    List<modelMenu> list;
    public adapterMenu(Context cont,List<modelMenu>li)
    {
        this.context=cont;
        this.list=li;
    }
    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public adapterMenu.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.singleitemmenu,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull adapterMenu.MyViewHolder holder, int position) {
        modelMenu m=list.get(position);
        holder.text2.setText(list.get(position).getDish());
//        holder.text2.setText(list.get(position).getLoc());
        Glide.with(holder.img.getContext()).load(list.get(position).getPic()).into(holder.img);

    }

    @Override
    public int getItemCount() {

        return list.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView text2;
        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.imageView);
            text2=itemView.findViewById((R.id.textView));
        }
    }
}
