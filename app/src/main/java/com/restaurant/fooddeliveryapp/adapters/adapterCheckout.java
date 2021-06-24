package com.restaurant.fooddeliveryapp.adapters;

//public class adapterCheckout {
//}


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.restaurant.fooddeliveryapp.R;
import com.restaurant.fooddeliveryapp.addProductIceCream;
import com.restaurant.fooddeliveryapp.checkout;
import com.restaurant.fooddeliveryapp.models.modelCheckout;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class adapterCheckout extends RecyclerView.Adapter<adapterCheckout.MyViewHolder> {


    Context context;
    List<modelCheckout> list;
    FirebaseFirestore firestore=FirebaseFirestore.getInstance();
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    ProgressDialog dialog1;
    public adapterCheckout(Context cont,List<modelCheckout>li,ProgressDialog dialog1)
    {
        this.context=cont;
        this.list=li;
        this.dialog1=dialog1;
    }

//    dialog1=new ProgressDialog(context);
//dialog1.setTitle("Deleting");
//dialog1.setMessage("deleting wait...");
//dialog1.setCanceledOnTouchOutside(false);
    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public adapterCheckout.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_item_checkout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull adapterCheckout.MyViewHolder holder, int position) {
        modelCheckout m=list.get(position);
        holder.itemNameCheckout.setText(list.get(position).getItemName());
//        holder.text2.setText(list.get(position).getImage());
        holder.itemPriceCheckout.setText(list.get(position).getTotalPrice());
        holder.itemCountCheckout.setText(list.get(position).getItemCount());
//        holder.text2.setText(list.get(position).getLoc());
        Glide.with(holder.itemImgCheckout.getContext()).load(list.get(position).getItemImage()).into(holder.itemImgCheckout);


        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.show();
                firestore.collection("AddCartItem").document(firebaseAuth.getCurrentUser().getUid()).collection("Add_cart")
                        .whereEqualTo("ItemName",list.get(position).getItemName()).whereEqualTo("CheckOutStatus","not done")
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()&&!task.getResult().isEmpty())
                        {
                            DocumentSnapshot snapshot=task.getResult().getDocuments().get(0);
                            String documentId=snapshot.getId();
                            firestore.collection("AddCartItem").document(firebaseAuth.getCurrentUser().getUid()).collection("Add_cart")
                                    .document(documentId).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(context, "successfully deleted", Toast.LENGTH_SHORT).show();
                                    dialog1.dismiss();
//                                    Intent intent=new Intent(context, checkout.class);
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                    context.finish();
//                                    context.startActivity(intent);
//                                    ((AppCompatActivity)context).finish();
                                }
                            });
                        }
                    }
                });

            }
        });

//        holder.btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(holder.btn.getText().toString().equals("add to cart"))
//                {
//                    Intent intent=new Intent(context, addProductIceCream.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
//                    holder.btn.setText("added");
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
        ImageView itemImgCheckout;
         ImageButton btn;
        TextView itemNameCheckout,itemPriceCheckout,itemCountCheckout;
        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            itemNameCheckout=itemView.findViewById(R.id.nameItemCheckout);
            itemPriceCheckout=itemView.findViewById((R.id.totalPriceItemCheckout));
            itemCountCheckout=itemView.findViewById((R.id.countItemCheckout));
            itemImgCheckout=itemView.findViewById(R.id.imageItemCheckout);
            btn=itemView.findViewById(R.id.deleteButton);
        }
    }
}