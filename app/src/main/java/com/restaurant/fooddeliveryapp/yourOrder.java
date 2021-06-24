package com.restaurant.fooddeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.restaurant.fooddeliveryapp.adapters.adapterBiryani;
import com.restaurant.fooddeliveryapp.adapters.adapterCheckout;
import com.restaurant.fooddeliveryapp.adapters.adapterYourOrder;
import com.restaurant.fooddeliveryapp.models.modelCheckout;
import com.restaurant.fooddeliveryapp.models.modelYourOrder;
import com.restaurant.fooddeliveryapp.models.modelfire;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class yourOrder extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView textView;
    List<modelYourOrder> list1 = new ArrayList<>();
    adapterYourOrder adapter;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
//    ArrayList<String> names = new ArrayList<>();
//    List<String>list=new ArrayList<>();
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_order);
        recyclerView = findViewById(R.id.recyclerViewYourOrder);
        textView = findViewById(R.id.yourOrderList);
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
//        DocumentReference reference=firestore.collection("OrderDetail").document();
//        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful() && task.getResult() != null) {
//                    DocumentSnapshot doc = task.getResult();
//                    List<Map<String, Object>> groups = (List<Map<String, Object>>) doc.get("customerOrder");
//                    ArrayList<String> names = new ArrayList<>();
//                    for (Map<String, Object> group : groups) {
//                        String name = (String) group.get("itemName");
//                        names.add(name);
//                    }
//                    Log.d("pan", "onComplete: the name "+names.get(0));
//
////                    List<String> group = (List<String>) doc.get("fleets");
////                    Log.i("pan", "getFleets: " + group);
//
//                }
//            }
//        });
        recyclerView.hasFixedSize();
//        firestore=FirebaseFirestore.getInstance();
//        list=new ArrayList<>();
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
//        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        firebaseFirestore=FirebaseFirestore.getInstance();
//        list=new ArrayList<>();
        adapter=new adapterYourOrder(getApplicationContext(),list1);
//        Log.d("shashikant", "onCreate: the length of the biryani adapter="+adapter.getItemCount());
        recyclerView.setAdapter(adapter);
//        Log.d("shashikant", "onCreate: the length of the biryani adapter after setting the adapter into recycler view="+adapter.getItemCount());
        ViewCompat.setNestedScrollingEnabled(recyclerView, false);
//        showdata();
//        Log.d("shashikant", "onCreate: the length of the biryani adapter after show data="+adapter.getItemCount());
        Query query=firestore.collection("OrderDetail").whereEqualTo("customerId",firebaseAuth.getCurrentUser().getUid())
                .orderBy("DateOfOrder", Query.Direction.DESCENDING);
        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                Log.d("panmasala", "onSuccess: hello micheal");

                for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                    i++;
//                    Log.d("panmasala", "onSuccess: hello micheal");

                    List<Map<String, Object>> groups = (List<Map<String, Object>>) documentSnapshot.get("customerOrder");
                    ArrayList<String> names = new ArrayList<>();
                    ArrayList<String> conuts = new ArrayList<>();
                    ArrayList<String> prices = new ArrayList<>();
                    ArrayList<String> plates = new ArrayList<>();

                    for (Map<String, Object> group : groups) {
                        String name = (String) group.get("itemName");
                        names.add(name);
                        String item=(String) group.get("itemCount");
                        conuts.add(item);
                        String price=(String) group.get("totalPrice");
                        prices.add(price);;
                        String plate=(String) group.get("itemPlate");
                        plates.add(plate);
                    }
                    modelYourOrder m=new modelYourOrder(documentSnapshot.getString("DateOfOrder"),documentSnapshot.getString("OrderCompleteStatus"),
                            documentSnapshot.getString("grandTotalPrice"),names,conuts,prices,plates);
                    list1.add(m);
//                    if(i==2)
//                    {
//                        Log.d("pantasala", "onComplete: the name "+names.get(2));
//                    }



//                    Model2 model2=new Model2(documentSnapshot.getString("DriverName"),
//                            documentSnapshot.getString("DriverImage"),
//                            documentSnapshot.getId(),
//                            documentSnapshot.getString("Payment"),
//                            documentSnapshot.getString("PaymentStatus"));
//                    Log.d("hemu","shashikant");
//                    list1.add(model2);

                }
                adapter.notifyDataSetChanged();
                if (adapter.getItemCount()==0)
                {
                    textView.setVisibility(View.VISIBLE);
                }

//                myAdapter2.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("hariom",e.toString());
            }
        });;



    }






    }
