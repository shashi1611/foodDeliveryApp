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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.restaurant.fooddeliveryapp.R;
import com.restaurant.fooddeliveryapp.addproductBeverages;
import com.restaurant.fooddeliveryapp.models.modelBeverages;

import java.util.List;

//public class adapterBeverages {
//}
public class adapterBeverages extends RecyclerView.Adapter<adapterBeverages.MyViewHolder> {
    Context context;
    List<modelBeverages> list;
//    adapterBiryani biryani=new adapterBiryani();
    FirebaseAuth auth;

    public adapterBeverages(Context cont,List<modelBeverages>li)
    {
        this.context=cont;
        this.list=li;
    }
    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public adapterBeverages.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_item_all_product,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull adapterBeverages.MyViewHolder holder, int position) {
        modelBeverages m=list.get(position);
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

//                    if(auth.getCurrentUser())
                    Intent intent=new Intent(context, addproductBeverages.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("name",list.get(position).getName());
                    intent.putExtra("image",list.get(position).getImage());
                    intent.putExtra("desc",list.get(position).getDescription());
                    intent.putExtra("1L",list.get(position).getL1());
                    intent.putExtra("2L",list.get(position).getL2());
                    intent.putExtra("500ml",list.get(position).getMl500());
                    intent.putExtra("price1L",list.get(position).getPrice1l());
                    intent.putExtra("price2L",list.get(position).getPrice2l());
                    intent.putExtra("price500ml",list.get(position).getPrice500ml());
                    context.startActivity(intent);
                    holder.btn.setText("added");

//                    if(auth.getCurrentUser()!=null)
//                    {
//                        holder.btn.setText("added");
//                    }
//                    else {
//                        holder.btn.setText("added");
//                    }

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
