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

public class addproductBeverages extends AppCompatActivity {
    Toolbar toolbarBeverages;
    Button addProductButton;
    FirebaseFirestore firestore;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String date;
    FirebaseAuth firebaseAuth;
    ImageView img;
    TextView tn,td,tf,th,tincrement,tdecrement,tcount;
    RadioButton btn1L,btn2L,btn500ml;
    RadioGroup group;
    ProgressDialog dialog1;
    int count=1;
    int i;
    String price;
    String bottle;
    int flag=0;
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(addproductBeverages.this, authentication.class);
            finish();
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct_beverages);
//        setContentView(R.layout.activity_add_product_biryani);
//        toolbarBeverages=findViewById(R.id.toolbarCustomBeverages);
//        setSupportActionBar(toolbarBeverages);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addProductButton=findViewById(R.id.addProductBeveragesButton);

        img=findViewById(R.id.addProductBeveragesImage);
        tn=findViewById(R.id.addProductBeveragesName);
        td=findViewById(R.id.addProductBeveragesPrice);
        btn1L=findViewById(R.id.addProductBeverages1L);
        btn2L=findViewById(R.id.addProductBeverages2L);
        btn500ml=findViewById(R.id.addProductBeverages500ml);
        tincrement=findViewById(R.id.addProductBeveragesIncrement);
        tdecrement=findViewById(R.id.addProductBeveragesDecrement);
        tcount=findViewById(R.id.addProductBeveragesCount);
        group=findViewById(R.id.radioGroupBeverages);


        //  <-----------------------------------------------------------------dialog declaration------------------------------------------------------------------->
        dialog1=new ProgressDialog(this);
        dialog1.setTitle("Adding To Cart");
        dialog1.setMessage("adding wait...");
        dialog1.setCanceledOnTouchOutside(false);

        String name=getIntent().getStringExtra("name");
        String image=getIntent().getStringExtra("image");
        String desc=getIntent().getStringExtra("desc");
        String l1=getIntent().getStringExtra("1L");
        String l2=getIntent().getStringExtra("2L");
        String ml500=getIntent().getStringExtra("500ml");
        String price1l=getIntent().getStringExtra("price1L");
        String price2l=getIntent().getStringExtra("price2L");
        String price500ml=getIntent().getStringExtra("price500ml");

        firestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();


        tn.setText(name);
        td.setText(desc);
        btn1L.setText(l1);
        btn2L.setText(l2);
        btn500ml.setText(ml500);
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
                    Toast.makeText(addproductBeverages.this, "the item is discard", Toast.LENGTH_SHORT).show();
                }
            }
        });

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.addProductBeverages500ml:
                        price=price500ml;
                        bottle=ml500;
                        i=Integer.parseInt(price);
                        flag=1;
                        break;
                    case R.id.addProductBeverages1L:
                        price=price1l;
                        bottle=l1;
                        i=Integer.parseInt(price);
                        flag=1;
                        break;
                    case R.id.addProductBeverages2L:
                        price=price2l;
                        bottle=l2;
                        i=Integer.parseInt(price);
                        flag=1;
                        break;

                }
            }
        });

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.show();
                if (flag==0)
                {
                    btn1L.setError("the field is required");
                }
                else {

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
                Add_cart.put("ItemPlate", bottle);
                Add_cart.put("ItemTotalPrice", Integer.toString(count*i));
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
                        dialog1.dismiss();
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
                        Toast.makeText(addproductBeverages.this, "added successfully", Toast.LENGTH_SHORT).show();
                        addProductButton.setText("Added");
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(addproductBeverages.this, "the error is"+e, Toast.LENGTH_SHORT).show();
                    }
                });
                }
                //  Toast.makeText(Address.this, user_id, Toast.LENGTH_SHORT).show();

            }

        });

    }
}