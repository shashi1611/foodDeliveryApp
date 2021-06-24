package com.restaurant.fooddeliveryapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.restaurant.fooddeliveryapp.adapters.adapterBiryani;
import com.restaurant.fooddeliveryapp.adapters.adapterCheckout;
import com.restaurant.fooddeliveryapp.models.modelBiryani;
import com.restaurant.fooddeliveryapp.models.modelCheckout;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcel;
import org.parceler.Parcels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class checkout extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseFirestore firestore;
   public List<modelCheckout>list=new ArrayList<>();
   public List<modelCheckout>list1=new ArrayList<>();
    adapterCheckout adapter;
    ProgressDialog dialog,dialog1;
    TextView tPrice,tDelveryCharge,tGrandTotal,tcart;
    FirebaseAuth firebaseAuth;
    Button btnCeckout;
    CardView cardView;
    int totalPrice;
    int a;
    String grandTotal;
    SwipeRefreshLayout swp;
    String deliveryPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        recyclerView=findViewById(R.id.recyclerViewCheckout);
        tPrice=findViewById(R.id.totalPriceCalculation);
        tDelveryCharge=findViewById(R.id.deliveryCharge);
        tGrandTotal=findViewById(R.id.grandTotal);
        firebaseAuth=FirebaseAuth.getInstance();
        btnCeckout=findViewById(R.id.buttonCheckout);
        tcart=findViewById(R.id.cartEmpty);
        cardView=findViewById(R.id.calculationCheckout);
//        list1=new ArrayList<>();
        firestore=FirebaseFirestore.getInstance();
        swp=findViewById(R.id.swiperefresh);

        //  <-----------------------------------------------------------------dialog declaration------------------------------------------------------------------->
        dialog1=new ProgressDialog(this);
        dialog1.setTitle("Deleting");
        dialog1.setMessage("deleting wait...");
        dialog1.setCanceledOnTouchOutside(false);
        swp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter=new adapterCheckout(getApplicationContext(),list,dialog1);
                recyclerView.setAdapter(adapter);
                a=showdata();
                swp.setRefreshing(false);
            }
        });
//        <--------------------------------------------------------updating the value of list1 to pass to the intent---------------------------------------->

        firestore.collection("AddCartItem").document(firebaseAuth.getCurrentUser().getUid()).collection("Add_cart")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
              for(DocumentSnapshot snapshot:task.getResult())
              {
                  if(snapshot.getString("CheckOutStatus").equals("not done"))
                  {
                      modelCheckout m=new modelCheckout(snapshot.getString("ItemName"),snapshot.getString("ItemTotalPrice")
                              ,snapshot.getString("image"),snapshot.getString("ItemCount"),snapshot.getString("ItemPlate"));
                      list1.add(m);
                  }
              }
//                Log.d("janak", "onCreate: the size of list ="+list1.size());
//                intent.putExtra("list",(Serializable)list1);
                btnCeckout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        firestore.collection("AddCartItem").document(firebaseAuth.getUid()).collection("Add_cart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                                for (DocumentSnapshot snapshot:task.getResult())
                                {

                                    if(snapshot.getString("CheckOutStatus").equals("not done"))
                                    {
                                        String documentId=snapshot.getId();
                                        Map<String, Object> userDetail = new HashMap<>();
                                        userDetail.put("CheckOutStatus", "done");
                                        firestore.collection("AddCartItem").document(firebaseAuth.getUid()).collection("Add_cart").document(documentId).update(userDetail)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
//                                                        Toast.makeText(checkout.this, "sucess fully updated", Toast.LENGTH_SHORT).show();
//                                        dialog1.show();
                                                    }
                                                });
                                    }
                                }

//                intent.putExtra("list",(Serializable)list1);

                            }
                        });
                        Intent intent=new Intent(checkout.this,placeOrder.class);
                        Parcelable parcelable= Parcels.wrap(list1);
//                        intent.putExtra("list",(Serializable)list1);
                        intent.putExtra("DATA_KEY",parcelable);
                        intent.putExtra("grandTotal",grandTotal);
//                        Log.d("grand", "onClick: the total amount= "+grandTotal);
                        startActivity(intent);
                        finish();




//            Map<String, Object> userDetail = new HashMap<>();
//            userDetail.put("Status", "done");
//            firestore.collection("AddCartItem").document(firebaseAuth.getUid()).collection("Add_cart").whereEqualTo("Status", "not done")
//                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                @Override
//                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
//                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
//                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
//                        String documentId = documentSnapshot.getId();
//                        firestore.collection("AddCartItem").document(firebaseAuth.getUid()).collection("Add_cart").document(documentId).update(userDetail)
//                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void unused) {
//                                        Toast.makeText(checkout.this, "the data is success fully updated", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                    }
//                }
//            });
                    }


                });

            }
        });





//<--------------------------------------fetching the data of delivery charge----------------------------------------------------->
  DocumentReference reference=firestore.collection("DeliveryCharge").document("DLA99qAONALzmah0Xi5g");
  reference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
      @Override
      public void onEvent(@Nullable @org.jetbrains.annotations.Nullable DocumentSnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
          deliveryPrice=value.getString("DeliveryPrice");
      }
  });
//      @Override
//      public void onSuccess(DocumentSnapshot documentSnapshot) {
//          if(documentSnapshot.exists()&&!documentSnapshot.equals(null)){
//              deliveryPrice=documentSnapshot.getString("DeliveryPrice");
//          }

//      }
//  });
//      @Override
//      public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
//          for(DocumentSnapshot snapshot:task.getResult())
//          {
//              deliveryPrice=snapshot.getString("DeliveryPrice");
//          }
//      }
//  });
//<--------------------------------------------------------------------checkout button handeled---------------------------------------------------------->

//btnCeckout.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        firestore.collection("AddCartItem").document(firebaseAuth.getUid()).collection("Add_cart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
//
//                for (DocumentSnapshot snapshot:task.getResult())
//                {
//
//                    if(snapshot.getString("CheckOutStatus").equals("not done"))
//                    {
//                        String documentId=snapshot.getId();
//                        Map<String, Object> userDetail = new HashMap<>();
//                        userDetail.put("CheckOutStatus", "done");
//                        firestore.collection("AddCartItem").document(firebaseAuth.getUid()).collection("Add_cart").document(documentId).update(userDetail)
//                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void unused) {
//                                        Toast.makeText(checkout.this, "sucess fully updated", Toast.LENGTH_SHORT).show();
////                                        dialog1.show();
//                                    }
//                                });
//                    }
//                }
//
////                intent.putExtra("list",(Serializable)list1);
//
//            }
//        });
//        startActivity(intent);
//
//
//
//
////            Map<String, Object> userDetail = new HashMap<>();
////            userDetail.put("Status", "done");
////            firestore.collection("AddCartItem").document(firebaseAuth.getUid()).collection("Add_cart").whereEqualTo("Status", "not done")
////                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
////                @Override
////                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
////                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
////                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
////                        String documentId = documentSnapshot.getId();
////                        firestore.collection("AddCartItem").document(firebaseAuth.getUid()).collection("Add_cart").document(documentId).update(userDetail)
////                                .addOnSuccessListener(new OnSuccessListener<Void>() {
////                                    @Override
////                                    public void onSuccess(Void unused) {
////                                        Toast.makeText(checkout.this, "the data is success fully updated", Toast.LENGTH_SHORT).show();
////                                    }
////                                });
////                    }
////                }
////            });
//        }
//
//
//});









//<------------------------------------------------------------------------------recycler View to fetch all items from cart--------------------------->
        recyclerView.hasFixedSize();
        firestore= FirebaseFirestore.getInstance();
//        list=new ArrayList<>();
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
//        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        firebaseFirestore=FirebaseFirestore.getInstance();
//        list=new ArrayList<>();
        adapter=new adapterCheckout(getApplicationContext(),list,dialog1);
//        Log.d("shashikant", "onCreate: the length of the biryani adapter="+adapter.getItemCount());
        recyclerView.setAdapter(adapter);
//        Log.d("shashikant", "onCreate: the length of the biryani adapter after setting the adapter into recycler view="+adapter.getItemCount());
//        ViewCompat.setNestedScrollingEnabled(recyclerView, false);
         a=showdata();
//        Log.d("ravikant", "onCreate: the size of the list is"+a);
//        Log.d("shashikant", "onCreate: the length of the biryani adapter after show data="+adapter.getItemCount());


        dialog=new ProgressDialog(this);
        dialog.setTitle("loading");
        dialog.setMessage("the items are loading please wait");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
//    List<modelCheckout>

    private int showdata() {
        firestore.collection("AddCartItem").document(firebaseAuth.getUid()).collection("Add_cart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                list.clear();
                for(DocumentSnapshot snapshot:task.getResult())
                {
                    if(snapshot.getString("CheckOutStatus").equals("not done"))
                    {


                        modelCheckout m = new modelCheckout(snapshot.getString("ItemName"), snapshot.getString("ItemTotalPrice"),
                                snapshot.getString("image"),
                                snapshot.getString("ItemCount"),snapshot.getString("ItemPlate"));
                        totalPrice=totalPrice+Integer.parseInt(snapshot.getString("ItemTotalPrice"));
//                    ,snapshot.getString("fullPlatePrice"),snapshot.getString("halfPlatePrice"),
//                            snapshot.getString("priceFullPlate"),snapshot.getString("priceHalfPlate"));
                        list.add(m);
                    }
                }
//                Log.d("ravikant", "onComplete: the lenghth of the list is");

//                Log.d("shashikant", "onCreate: the length of the biryani adapter before notify="+adapter.getItemCount());
                adapter.notifyDataSetChanged();
                dialog.dismiss();
                tPrice.setText(Integer.toString(totalPrice));
                tDelveryCharge.setText(deliveryPrice);
                int k=Integer.parseInt(deliveryPrice);
                tGrandTotal.setText(Integer.toString(totalPrice+k));
                grandTotal=(Integer.toString(totalPrice+50));

                if(adapter.getItemCount()==0)
                {
                    tcart.setVisibility(View.VISIBLE);
                    cardView.setVisibility(View.INVISIBLE);

                }
//                Log.d("shashikant", "onCreate: the length of the biryani adapter inside show data function="+adapter.getItemCount());
            }

        });
        return list.size();
    }
}