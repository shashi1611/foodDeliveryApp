package com.restaurant.fooddeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.restaurant.fooddeliveryapp.models.modelAllItemItemCount;
import com.restaurant.fooddeliveryapp.models.modelCheckout;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class confirmOrder extends AppCompatActivity {
    EditText en,em,es,el;
    Button btnPlaceOrder;
    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;
    List<modelAllItemItemCount>list;
    List<modelCheckout>list1;
    int price=0;
    String p;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String date;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        en=findViewById(R.id.fullNameConfirmOrder);
        em=findViewById(R.id.mobileNoConfirmOrder);
        es=findViewById(R.id.streetConfirmOrder);
        el=findViewById(R.id.landmarkConfirmOrder);
        btnPlaceOrder=findViewById(R.id.placeOrderConfirmOrder);
//        <----------------------------------------------custom dialog box coding---------------------------------------------------->
        dialog=new Dialog(confirmOrder.this);
        dialog.setContentView(R.layout.custom_dialog);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations=R.style.animation;
        TextView ok=dialog.findViewById(R.id.buttonOK);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(confirmOrder.this,allItemList.class);
                intent.putExtra("name","hello");
                startActivity(intent);
                finish();
                dialog.dismiss();
            }
        });
//        <-------------------------------------------------------ends here-------------------------------------------------------------->

        firebaseAuth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        list=new ArrayList<>();
        list1=new ArrayList<>();

        en.setText(getIntent().getStringExtra("name"));
        em.setText(getIntent().getStringExtra("number"));
        es.setText(getIntent().getStringExtra("street"));
        el.setText(getIntent().getStringExtra("landmark"));
        String flag=getIntent().getStringExtra("flag");

        Parcelable parcelable=getIntent().getParcelableExtra("DATA_KEY");
        list1= Parcels.unwrap(parcelable);
//        Log.d("honey", "onCreate: the item is "+list1.toString());




        if(flag.equals("true"))
        {
//            Toast.makeText(this, "coming from address", Toast.LENGTH_SHORT).show();
            btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(en.getText().toString().isEmpty())
                    {
                        en.setError("required");
                    }else if (em.getText().toString().isEmpty()) {
                        em.setError("phone number is required");
                    } else if (em.getText().toString().length()!=10){
                        em.setError("insert valid phone number");
                    }else if (es.getText().toString().isEmpty()){
                        es.setError("required");
                    }else if (el.getText().toString().isEmpty()) {
                        el.setError("required");
                    }
                    else {


//                    else if(em.getText().toString().isEmpty()&&em.getText().toString().length()!=10)
//                    {
//                        em.setError("enter valid phone number");
//                    }
//                     else if(es.getText().toString().isEmpty())
//                    {
//                        es.setError("required");
//                    }
//                    else if(el.getText().toString().isEmpty())
//                    {
//                        el.setError("required");
//                    }


//                    firestore.collection("AddCartItem").document(firebaseAuth.getCurrentUser().getUid()).collection("Add_cart")
//                            .whereEqualTo("OrderStatus","not complete").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
//                            for (DocumentSnapshot snapshot:task.getResult())
//                            {
//                                modelAllItemItemCount m=new modelAllItemItemCount(snapshot.getString("ItemName"),snapshot.getString("Status"),
//                                        snapshot.getString("ItemPlate"),snapshot.getString("Status"));
//                                list.add(m);
//                                p=snapshot.getString("ItemTotalPrice");
//                                price=price+Integer.parseInt(p);
//
//                            }
//                        }
//                    });
                        calendar = Calendar.getInstance();
                        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        date = simpleDateFormat.format(calendar.getTime());

                        Map<String, Object> userDetail = new HashMap<>();
                        userDetail.put("grandTotalPrice", getIntent().getStringExtra("grandTotal"));
                        userDetail.put("DateOfOrder", date);
                        userDetail.put("customerName", en.getText().toString());
                        userDetail.put("customerPhone", em.getText().toString());
                        userDetail.put("customerStreet", es.getText().toString());
                        userDetail.put("customerLandmark", el.getText().toString());
                        userDetail.put("customerOrder", list1);
                        userDetail.put("OrderCompleteStatus", "not complete");
//                    userDetail.put("totalPrice",Integer.toString(price));
                        userDetail.put("customerId", firebaseAuth.getCurrentUser().getUid());
                        DocumentReference reference = firestore.collection("OrderDetail").document();
                        reference.set(userDetail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(confirmOrder.this, "succesfully inserted", Toast.LENGTH_SHORT).show();
                            }
                        });

                        Map<String, Object> add = new HashMap<>();
                        add.put("name", en.getText().toString());
                        add.put("number", em.getText().toString());
                        add.put("street", es.getText().toString());
                        add.put("landmark", el.getText().toString());
                        DocumentReference reference1 = firestore.collection("UserAddress")
                                .document(firebaseAuth.getCurrentUser().getUid()).collection("Address").document();
                        reference1.set(add).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                            }
                        });
                        dialog.show();
                    }
                }

            });
        }
        else
        {
//            Toast.makeText(this, "not coming", Toast.LENGTH_SHORT).show();
            en.setEnabled(false);
            en.setTextColor(ContextCompat.getColor(confirmOrder.this, R.color.black));
            em.setEnabled(false);
            em.setTextColor(ContextCompat.getColor(confirmOrder.this, R.color.black));
            es.setEnabled(false);
            es.setTextColor(ContextCompat.getColor(confirmOrder.this, R.color.black));
            el.setEnabled(false);
            el.setTextColor(ContextCompat.getColor(confirmOrder.this, R.color.black));
            btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(en.getText().toString().isEmpty())
                    {
                        en.setError("required");
                    }
                    if(em.getText().toString().isEmpty()&&em.getText().toString().length()!=10)
                    {
                        em.setError("enter valid phone number");
                    }
                    if(es.getText().toString().isEmpty())
                    {
                        es.setError("required");
                    }
                    if(el.getText().toString().isEmpty())
                    {
                        el.setError("required");
                    }
//                    firestore.collection("AddCartItem").document(firebaseAuth.getCurrentUser().getUid()).collection("Add_cart")
//                            .whereEqualTo("OrderStatus","not complete").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
//                            for (DocumentSnapshot snapshot:task.getResult())
//                            {
//                                modelAllItemItemCount m=new modelAllItemItemCount(snapshot.getString("ItemName"),snapshot.getString("Status"),
//                                        snapshot.getString("ItemPlate"),snapshot.getString("Status"));
//                                list.add(m);
////                                p=snapshot.getString("ItemTotalPrice");
////                                price=price+Integer.parseInt(p);
//
//                            }
//                        }
//                    });
                    calendar= Calendar.getInstance();
                    simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    date=simpleDateFormat.format(calendar.getTime());
                    Map<String,Object>userDetail=new HashMap<>();
                    userDetail.put("grandTotalPrice",getIntent().getStringExtra("grandTotal"));
                    userDetail.put("DateOfOrder",date);
                    userDetail.put("customerName",en.getText().toString());
                    userDetail.put("customerPhone",em.getText().toString());
                    userDetail.put("customerStreet",es.getText().toString());
                    userDetail.put("customerLandmark",el.getText().toString());
                    userDetail.put("customerOrder",list1);
                    userDetail.put("OrderCompleteStatus","not complete");
//                    userDetail.put("totalPrice",price);
//                Integer.toString(price));
                    userDetail.put("customerId",firebaseAuth.getCurrentUser().getUid());
                    DocumentReference reference=firestore.collection("OrderDetail").document();
                    reference.set(userDetail).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
//                            Toast.makeText(confirmOrder.this, "succesfully inserted", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.show();

                }
            });
        }
    }
}