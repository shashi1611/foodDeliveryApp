package com.restaurant.fooddeliveryapp.adapters;

//public class adapterPlaceOrder {
//}

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.restaurant.fooddeliveryapp.R;
import com.restaurant.fooddeliveryapp.addProductBiryani;
import com.restaurant.fooddeliveryapp.allItemList;
import com.restaurant.fooddeliveryapp.confirmOrder;
import com.restaurant.fooddeliveryapp.models.modelCheckout;
import com.restaurant.fooddeliveryapp.models.modelPlaceOrder;
import com.restaurant.fooddeliveryapp.placeOrder;

import org.parceler.Parcels;

import java.util.List;

public class adapterPlaceOrder extends RecyclerView.Adapter<adapterPlaceOrder.MyViewHolder> {
    //    public Object flag;
    Context context;
    List<modelPlaceOrder> list;
    List<modelCheckout>list1;
    String grandTotal;
//    public int flag=0;
//    allItemList items=new allItemList();
//    MainActivity mika;
//    List<modelOrderedItem>items=new ArrayList<>();
//    public adapterPlaceOrder()
//    {}


    public adapterPlaceOrder(Context cont,List<modelPlaceOrder>li,List<modelCheckout>list1,String grandTotal)
    {
        this.context=cont;
        this.list=li;
        this.list1=list1;
        this.grandTotal=grandTotal;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public adapterPlaceOrder.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_item_place_order,parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull adapterPlaceOrder.MyViewHolder holder, int position) {
        modelPlaceOrder m=list.get(position);
        holder.tn.setText(list.get(position).getName());
//        holder.text2.setText(list.get(position).getImage());
        holder.ts.setText(list.get(position).getStreet());
        holder.tm.setText(list.get(position).getNumber());
        holder.tl.setText(list.get(position).getLandmark());
//        holder.text2.setText(list.get(position).getLoc());
//        Glide.with(holder.imgBiryani.getContext()).load(list.get(position).getImage()).into(holder.imgBiryani);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                holder.en.setText(list.get(position).getName());
//                holder.em.setText(list.get(position).getNumber());
//                holder.es.setText(list.get(position).getStreet());
//                holder.el.setText(list.get(position).getLandmark());
//                holder.btn.setVisibility(View.VISIBLE);
//                holder.el.setVisibility(View.VISIBLE);
//                holder.en.setVisibility(View.VISIBLE);
//                holder.es.setVisibility(View.VISIBLE);
//                holder.em.setVisibility(View.VISIBLE);
//                holder.ad.setVisibility(View.INVISIBLE);
////                Log.d("maniyo", "onClick: the list item is "+list.get(position).getList());
//                StringBuilder builder = new StringBuilder();
//                for (String details : list.get(position).getList()) {
//                    builder.append(details + "\n");
//                }
//                holder.tn.setText(builder.toString());

                Intent intent=new Intent(context, confirmOrder.class);
                intent.putExtra("name",list.get(position).getName());
                intent.putExtra("number",list.get(position).getNumber());
                intent.putExtra("street",list.get(position).getStreet());
                intent.putExtra("landmark",list.get(position).getLandmark());
                intent.putExtra("flag","false");
                intent.putExtra("grandTotal",grandTotal);
                Parcelable parcelable= Parcels.wrap(list1);
//                        intent.putExtra("list",(Serializable)list1);
                intent.putExtra("DATA_KEY",parcelable);
//                intent.putExtra("desc",list.get(position).getDescription());
//                intent.putExtra("fullPlatePrice",list.get(position).getFullPlatePrice());
//                intent.putExtra("halfPlatePrice",list.get(position).getHalfPlatePrice());
//                intent.putExtra("fullPrice",list.get(position).getFullPrice());
//                intent.putExtra("halfPrice",list.get(position).getHalfPrice());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });





    }

    @Override
    public int getItemCount() {

        return list.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tn,tm,ts,tl,ad;
        EditText en,em,es,el;
        Button btn;
        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            tn=itemView.findViewById(R.id.namePlaceOrderSingle);
            tm=itemView.findViewById(R.id.numberPlaceOrderSingle);
            ts=itemView.findViewById(R.id.streetPlaceOrderSingle);
            tl=itemView.findViewById(R.id.landmarkPlaceOrderSingle);
            ad=itemView.findViewById(R.id.addAddressPlaceOrder);
//            en=itemView.findViewById(R.id.fullNamePlaceOrder);
//            em=itemView.findViewById(R.id.mobileNoPlaceOrder);
//            es=itemView.findViewById(R.id.streetPlaceOrder);
//            el=itemView.findViewById(R.id.landmarkPlaceOrder);
//            btn=itemView.findViewById(R.id.placeOrder);
        }
    }
}



