package com.restaurant.fooddeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class addProductStater extends AppCompatActivity {
    Toolbar toolbarStater;
    Button addProductButton;
    FirebaseFirestore firestore;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String date;
    FirebaseAuth firebaseAuth;
    ImageView img;
    TextView tn,td,tp,tincrement,tdecrement,tcount;
    RadioButton btnf,btnh;
    RadioGroup group;
    ProgressDialog dialog1;
    int count=1;
    int i;
    String price;
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(addProductStater.this, authentication.class);
            finish();
            startActivity(intent);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_stater);
//        setContentView(R.layout.activity_add_product_Stater);
//        toolbarStater=findViewById(R.id.toolbarCustomStater);
//        setSupportActionBar(toolbarStater);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addProductButton=findViewById(R.id.addProductStaterButton);

        img=findViewById(R.id.addProductStaterImage);
        tn=findViewById(R.id.addProductStaterName);
        td=findViewById(R.id.addProductStaterDescription);
        tp=findViewById(R.id.addProductStaterPrice);
//        btnf=findViewById(R.id.addProductStaterFplate);
//        btnh=findViewById(R.id.addProductStaterhplate);
        tincrement=findViewById(R.id.addProductStaterIncrement);
        tdecrement=findViewById(R.id.addProductStaterDecrement);
        tcount=findViewById(R.id.addProductStaterCount);
//        group=findViewById(R.id.radioGroupStater);
        //  <-----------------------------------------------------------------dialog declaration------------------------------------------------------------------->
        dialog1=new ProgressDialog(this);
        dialog1.setTitle("Adding to cart");
        dialog1.setMessage("adding wait...");
        dialog1.setCanceledOnTouchOutside(false);

        firestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        String image=getIntent().getStringExtra("img");
        String name=getIntent().getStringExtra("name");
        String desc=getIntent().getStringExtra("desc");
        String price=getIntent().getStringExtra("price");
        i=Integer.parseInt(price);
//        String fullPlatePrice=getIntent().getStringExtra("fullPlatePrice");
//        String halfPlatePrice=getIntent().getStringExtra("halfPlatePrice");
        tn.setText(name);
        td.setText(desc);
        tp.setText(price);
//        btnf.setText(fullPlatePrice);
//        btnh.setText(halfPlatePrice);
        Glide.with(img.getContext()).load(image).into(img);
        tincrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                tcount.setText(Integer.toString(count));
            }
        });
        tdecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>0)
                {
                    count--;
                    tcount.setText(Integer.toString(count));
                }else
                {
                    Toast.makeText(addProductStater.this, "the item is discarded", Toast.LENGTH_SHORT).show();
                }
            }
        });

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.show();
                calendar=Calendar.getInstance();
                simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                date=simpleDateFormat.format(calendar.getTime());
                DocumentReference documentReference = firestore.collection("AddCartItem").
                        document(firebaseAuth.getUid()).collection("Add_cart").document(date);
                Map<String, Object> Add_cart = new HashMap<>();
//                    Map<String, Object> OrderDetail = new HashMap<>();
                //order_no=0
                Add_cart.put("ItemCount", Integer.toString(count));
                Add_cart.put("DateOfOrder", date);
                Add_cart.put("ItemName", name);
                Add_cart.put("ItemPlate","Null");
                Add_cart.put("ItemTotalPrice",Integer.toString(count*i));
                Add_cart.put("CheckOutStatus", "not done");
                Add_cart.put("OrderStatus", "not complete");
                Add_cart.put("image", image);
//                    OrderDetail.put("Date Of Order", );
//                    Add_cart.put("PaymentStatus", "not paid");
//                    OrderDetail.put("DriverImage",getIntent().getStringExtra("D_Image"));
//                    OrderDetail.put("Payment",getIntent().getStringExtra("Price"));
//                    OrderDetail.put("DriverName",getIntent().getStringExtra("D_name"));
//                    OrderDetail.put("DriverPhone",getIntent().getStringExtra("DNumber"));
//                    OrderDetail.put("CustomerName", name.getText().toString());
//                    OrderDetail.put("CustomerPhone", phone);
//                    OrderDetail.put("CustomerVillage", village.getText().toString());
//                    OrderDetail.put("CustomerStreet", colony.getText().toString());
//                    OrderDetail.put("CustomerId",auth.getCurrentUser().getUid());
                documentReference.set(Add_cart).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
//                            Toast.makeText(Address.this, "successfully placed", Toast.LENGTH_SHORT).show();
//                            order_no = order_no + 1;
//                            Intent intent=new Intent(Address.this,orderComplete.class);
                        //intent.putExtra("orderId",);
                        //  intent.putExtra("D_Name",getIntent().getStringExtra("D_name"));
                        //  intent.putExtra("D_Image",getIntent().getStringExtra("D_Image"));
                        //intent.putExtra("driverImg",);
                        //intent.putExtra("price",);
//                            startActivity(intent);
//                            finish();
                        dialog1.dismiss();
                        Toast.makeText(addProductStater.this, "added successfully", Toast.LENGTH_SHORT).show();
                        addProductButton.setText("Added");
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(addProductStater.this, "the error is"+e, Toast.LENGTH_SHORT).show();
                    }
                });
                //  Toast.makeText(Address.this, user_id, Toast.LENGTH_SHORT).show();

            }
        });

    }
}