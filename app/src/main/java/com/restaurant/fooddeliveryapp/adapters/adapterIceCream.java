package com.restaurant.fooddeliveryapp.adapters;

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
import com.restaurant.fooddeliveryapp.addProductIceCream;
import com.restaurant.fooddeliveryapp.models.modelIceCream;

import java.util.List;

//public class adapterIceCream {
//}
public class adapterIceCream extends RecyclerView.Adapter<adapterIceCream.MyViewHolder> {
    Context context;
    List<modelIceCream> list;
    public adapterIceCream(Context cont,List<modelIceCream>li)
    {
        this.context=cont;
        this.list=li;
    }
    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public adapterIceCream.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_item_all_product,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull adapterIceCream.MyViewHolder holder, int position) {
        modelIceCream m=list.get(position);
        holder.textName.setText(list.get(position).getName());
//        holder.text2.setText(list.get(position).getImage());
        holder.textPrice.setText(list.get(position).getPrice());
        holder.textDescription.setText(list.get(position).getDescription());
//        holder.text2.setText(list.get(position).getLoc());
        Glide.with(holder.imgBiryani.getContext()).load(list.get(position).getImage()).into(holder.imgBiryani);

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.btn.getText().toString().equals("add to cart"))
                {
                    Intent intent=new Intent(context, addProductIceCream.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("price",list.get(position).getPrice());
                    intent.putExtra("name",list.get(position).getName());
                    intent.putExtra("desc",list.get(position).getDescription());
                    intent.putExtra("img",list.get(position).getImage());
                    context.startActivity(intent);
                    holder.btn.setText("added");
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


