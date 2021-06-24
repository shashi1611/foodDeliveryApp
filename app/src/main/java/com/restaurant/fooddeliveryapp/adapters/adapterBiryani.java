package com.restaurant.fooddeliveryapp.adapters;

//public class adapterBiryani {
//}

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.restaurant.fooddeliveryapp.R;
import com.restaurant.fooddeliveryapp.addProductBiryani;
import com.restaurant.fooddeliveryapp.allItemList;
import com.restaurant.fooddeliveryapp.models.modelBiryani;
import com.restaurant.fooddeliveryapp.models.modelOrderedItem;

import java.util.ArrayList;
import java.util.List;

public class adapterBiryani extends RecyclerView.Adapter<adapterBiryani.MyViewHolder> {
//    public Object flag;
    Context context;
    List<modelBiryani> list;
      public int flag=0;
    allItemList items=new allItemList();
//    MainActivity mika;
//    List<modelOrderedItem>items=new ArrayList<>();
//    public adapterBiryani()
//    {}


    public adapterBiryani(Context cont,List<modelBiryani>li)
    {
        this.context=cont;
        this.list=li;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public adapterBiryani.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_item_all_product,parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull adapterBiryani.MyViewHolder holder, int position) {
        modelBiryani m=list.get(position);
        holder.textName.setText(list.get(position).getName());
//        holder.text2.setText(list.get(position).getImage());
        holder.textPrice.setText(list.get(position).getPrice());
        holder.textDescription.setText(list.get(position).getDescription());
//        holder.text2.setText(list.get(position).getLoc());
        Glide.with(holder.imgBiryani.getContext()).load(list.get(position).getImage()).into(holder.imgBiryani);
//        if (items.item.equals("added"))
//        {
////            holder.btn.setText("added");
//        }

             holder.btn.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     if(holder.btn.getText().toString().equals("add to cart"))
                     {
                         Intent intent=new Intent(context, addProductBiryani.class);
                         intent.putExtra("img",list.get(position).getImage());
                         intent.putExtra("name",list.get(position).getName());
                         intent.putExtra("desc",list.get(position).getDescription());
                         intent.putExtra("fullPlatePrice",list.get(position).getFullPlatePrice());
                         intent.putExtra("halfPlatePrice",list.get(position).getHalfPlatePrice());
                         intent.putExtra("fullPrice",list.get(position).getFullPrice());
                         intent.putExtra("halfPrice",list.get(position).getHalfPrice());
                         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                         context.startActivity(intent);
                         holder.btn.setText("added");

//                         modelOrderedItem m=new modelOrderedItem(list.get(position).getName(),list.get(position).getPrice(),list.get(position).getImage(),flag);
//                         item.shashikant.add(m);
                     }else
                     {
                         Toast.makeText(context, "the item is already added", Toast.LENGTH_SHORT).show();
                     }

//                     Toast.makeText(context, "the button got clicked", Toast.LENGTH_SHORT).show();
                 }
             });



    }

    @Override
    public int getItemCount() {

        return list.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imgBiryani;
        Button btn;
        TextView textName,textPrice,textDescription;
        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            textName=itemView.findViewById(R.id.productName);
            textPrice=itemView.findViewById((R.id.productPrice));
            textDescription=itemView.findViewById((R.id.productDescription));
            imgBiryani=itemView.findViewById(R.id.productImage);
            btn=itemView.findViewById(R.id.addcartbutton);
        }
    }
}

