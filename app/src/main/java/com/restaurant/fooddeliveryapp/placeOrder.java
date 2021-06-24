package com.restaurant.fooddeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import com.restaurant.fooddeliveryapp.adapters.adapterChinese;
import com.restaurant.fooddeliveryapp.adapters.adapterPlaceOrder;
import com.restaurant.fooddeliveryapp.models.modelCheckout;
import com.restaurant.fooddeliveryapp.models.modelPlaceOrder;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class placeOrder extends AppCompatActivity {
    EditText name,number,landmark,street;
    Button btnPlaceOrder;
    TextView newAddress,select;
    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;
    RecyclerView recyclerView;
   public int flag=0;
    List<modelPlaceOrder>list;
    adapterPlaceOrder adapterPlaceOrder;
    List<String>tr;
   public List<modelCheckout>list1=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
//        name=findViewById(R.id.fullNamePlaceOrder);
//        number=findViewById(R.id.mobileNoPlaceOrder);
//        landmark=findViewById(R.id.landmarkPlaceOrder);
//        street=findViewById(R.id.streetPlaceOrder);
        newAddress=findViewById(R.id.addAddressPlaceOrder);
//        btnPlaceOrder=findViewById(R.id.placeOrder);
        firebaseAuth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        recyclerView=findViewById(R.id.recyclerViewPlaceOrder);
        select=findViewById(R.id.select);
//        list1=new ArrayList<>();

//        <------------------------------------------------------try storing array----------------------------------------------------------->
//        tr=new ArrayList<>();
//        tr.add("shashikant");
//        tr.add("shashikant");
//        tr.add("shashikant");
//        tr.add("shashikant");
//        tr.add("shashikant");
//        tr.add("shashikant");

//        list1= (List<modelCheckout>) getIntent().getSerializableExtra("list");
        Parcelable parcelable=getIntent().getParcelableExtra("DATA_KEY");
        list1= Parcels.unwrap(parcelable);
//        Log.d("devnag", "onCreate: the item is "+list1.toString());
//        Log.d("devnag", "onCreate: the item is ");




        recyclerView.hasFixedSize();
//        firestore=FirebaseFirestore.getInstance();
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
//        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        firebaseFirestore=FirebaseFirestore.getInstance();
        list=new ArrayList<>();
        adapterPlaceOrder=new adapterPlaceOrder(getApplicationContext(),list,list1,getIntent().getStringExtra("grandTotal"));
        recyclerView.setAdapter(adapterPlaceOrder);
        ViewCompat.setNestedScrollingEnabled(recyclerView, false);
        showdata();


        newAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                flag=1;
//                name.setVisibility(View.VISIBLE);
//                number.setVisibility(View.VISIBLE);
//                street.setVisibility(View.VISIBLE);
//                landmark.setVisibility(View.VISIBLE);
//                btnPlaceOrder.setVisibility(View.VISIBLE);
                Intent intent=new Intent(placeOrder.this,confirmOrder.class);
                intent.putExtra("flag","true");
                Parcelable parcelable= Parcels.wrap(list1);
//                        intent.putExtra("list",(Serializable)list1);
                intent.putExtra("DATA_KEY",parcelable);
                intent.putExtra("grandTotal",getIntent().getStringExtra("grandTotal"));
                startActivity(intent);


            }
        });


//        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(flag==1)
//                {
//                    Map<String,Object>address=new HashMap<>();
//                    address.put("name",name.getText().toString());
//                    address.put("number",number.getText().toString());
//                    address.put("street",street.getText().toString());
//                    address.put("landmark",landmark.getText().toString());
//                    address.put("items",tr);
//                    DocumentReference documentReference= firestore.collection("User").document(firebaseAuth.getCurrentUser().getUid()).collection("Address")
//                            .document();
//                    documentReference.set(address).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void unused) {
//                            Toast.makeText(placeOrder.this, "the data got inserted", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//
//
//                InputMethodManager imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(v.getWindowToken(),0);
//            }
//        });
    }

    private void showdata() {
        firestore.collection("UserAddress").document(firebaseAuth.getCurrentUser().getUid()).collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot snapshot:task.getResult())
                {
                    modelPlaceOrder placeOrder=new modelPlaceOrder(snapshot.getString("name"),snapshot.getString("number"),snapshot.getString("street"),
                            snapshot.getString("landmark"));
//                            (List<String>)snapshot.get("items"));
                    list.add(placeOrder);
                }
                adapterPlaceOrder.notifyDataSetChanged();
                if(adapterPlaceOrder.getItemCount()==0)
                {
//                    select.setVisibility(View.INVISIBLE);
                    select.setText("NO saved address found add new address");
                }
//                Log.d("maniyo", "onComplete: the list is "+list.get());
            }
        });
    }
}