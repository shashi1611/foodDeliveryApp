package com.restaurant.fooddeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.restaurant.fooddeliveryapp.adapters.adapterBeverages;
import com.restaurant.fooddeliveryapp.adapters.adapterBiryani;
import com.restaurant.fooddeliveryapp.adapters.adapterChinese;
import com.restaurant.fooddeliveryapp.adapters.adapterIceCream;
import com.restaurant.fooddeliveryapp.adapters.adapterMenu;
import com.restaurant.fooddeliveryapp.adapters.adapterPopularItem;
import com.restaurant.fooddeliveryapp.adapters.adapterSoup;
import com.restaurant.fooddeliveryapp.adapters.adapterStater;
import com.restaurant.fooddeliveryapp.models.modelBeverages;
import com.restaurant.fooddeliveryapp.models.modelBiryani;
import com.restaurant.fooddeliveryapp.models.modelChinese;
import com.restaurant.fooddeliveryapp.models.modelIceCream;
import com.restaurant.fooddeliveryapp.models.modelMenu;
import com.restaurant.fooddeliveryapp.models.modelOrderedItem;
import com.restaurant.fooddeliveryapp.models.modelPopularItem;
import com.restaurant.fooddeliveryapp.models.modelSoup;
import com.restaurant.fooddeliveryapp.models.modelStater;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class allItemList extends AppCompatActivity {
    ImageView imageView;
    Button btn;
    NestedScrollView nestedscrollView;
    RecyclerView recyclerView,recyclerView1,recyclerViewSoup,recyclerViewStater,recyclerViewChinese,recyclerViewIceCream;
    FirebaseFirestore firestore;
    List<modelBiryani>list;
    List<modelBeverages>list1;
    List<modelSoup>listSoup;
    List<modelStater>listStater;
    List<modelIceCream>listCream;
    List<modelChinese>listChinese;
    adapterBiryani adapter;
    adapterBeverages adapter1;
    adapterSoup adapterSoup;
    adapterStater stater;
    adapterIceCream iceCream;
    adapterChinese china;
    Toolbar toolbar;
    TextView tb,tc,ts,tsu,tbe,ti;
    CardView cardView;
    FloatingActionButton floatingActionButton;
    public String item;
//    ContentLoadingProgressBar loadingProgressBar;
ProgressDialog dialog;
//List<modelOrderedItem>items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_item_list);
        recyclerView=findViewById(R.id.recyclerViewBiryani);
        recyclerView1=findViewById(R.id.recyclerViewBeverages);
        toolbar=findViewById(R.id.toolbarCustom);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Menu");
        nestedscrollView=findViewById(R.id.nestedScrollView);
        tb=findViewById(R.id.textBiryani);
        tc=findViewById(R.id.textChinese);
        ts=findViewById(R.id.textStater);
        tsu=findViewById(R.id.textSoup);
        tbe=findViewById(R.id.textBeverages);
        ti=findViewById(R.id.textIceCream);
        cardView=findViewById(R.id.cardViewCaculate);
        floatingActionButton=findViewById(R.id.floatingActionButton);
        item=getIntent().getStringExtra("name");


        dialog=new ProgressDialog(this);
        dialog.setTitle("loading");
        dialog.setMessage("the items are loading please wait");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();



//        items=new ArrayList<>();


//        <-------------------------------------floating action button coding------------------------------------------------------>
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(allItemList.this,checkout.class);
                startActivity(intent);
//                Toast.makeText(allItemList.this, "hello fab", Toast.LENGTH_SHORT).show();
//                FragmentManager fragmentManager=getSupportFragmentManager();
//                 FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
////                 a1 obj=new a1();
//                addProductFragment fragment=new addProductFragment();
//                 fragmentTransaction.replace(R.id.scview,fragment);
//                 fragmentTransaction.addToBackStack(null);
//                 fragmentTransaction.commit();
            }
        });


//   <--------------------------------------------recyclerView for the biryani------------------------------------------------->
        recyclerView.hasFixedSize();
        firestore=FirebaseFirestore.getInstance();
//        list=new ArrayList<>();
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
//        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        firebaseFirestore=FirebaseFirestore.getInstance();
        list=new ArrayList<>();
        adapter=new adapterBiryani(getApplicationContext(),list);
//        recyclerView.setAdapter(adapter);
//        ViewCompat.setNestedScrollingEnabled(recyclerView, false);
//        showdata();

//     <-----------------------------------------recyclerView for the beverages---------------------------------------------------->
        recyclerView1.hasFixedSize();
        firestore=FirebaseFirestore.getInstance();
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
//        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
//        firebaseFirestore=FirebaseFirestore.getInstance();
        list1=new ArrayList<>();
        adapter1=new adapterBeverages(getApplicationContext(),list1);
        recyclerView1.setAdapter(adapter1);
        ViewCompat.setNestedScrollingEnabled(recyclerView1, false);
        showdata1();


//        <-----------------------------------------recyclerView for the soup---------------------------------------------------->
        recyclerViewSoup=findViewById(R.id.recyclerViewSoup);
        recyclerViewSoup.hasFixedSize();
//        firestore=FirebaseFirestore.getInstance();
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
//        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerViewSoup.setLayoutManager(new LinearLayoutManager(this));
//        firebaseFirestore=FirebaseFirestore.getInstance();
        listSoup=new ArrayList<>();
        adapterSoup=new adapterSoup(getApplicationContext(),listSoup);
        recyclerViewSoup.setAdapter(adapterSoup);
        ViewCompat.setNestedScrollingEnabled(recyclerViewSoup, false);
        showdataSoup();

//         <-----------------------------------------recyclerView for the stater---------------------------------------------------->

        recyclerViewStater=findViewById(R.id.recyclerViewStater);
        recyclerViewSoup.hasFixedSize();
//        firestore=FirebaseFirestore.getInstance();
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
//        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerViewStater.setLayoutManager(new LinearLayoutManager(this));
//        firebaseFirestore=FirebaseFirestore.getInstance();
        listStater=new ArrayList<>();
        stater=new adapterStater(getApplicationContext(),listStater);
        recyclerViewStater.setAdapter(stater);
        ViewCompat.setNestedScrollingEnabled(recyclerViewStater, false);
        showdataStater();


//         <-----------------------------------------recyclerView for the ice cream---------------------------------------------------->

        recyclerViewIceCream=findViewById(R.id.recyclerViewIceCream);
        recyclerViewIceCream.hasFixedSize();
//        firestore=FirebaseFirestore.getInstance();
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
//        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerViewIceCream.setLayoutManager(new LinearLayoutManager(this));
//        firebaseFirestore=FirebaseFirestore.getInstance();
        listCream=new ArrayList<>();
        iceCream=new adapterIceCream(getApplicationContext(),listCream);
        recyclerViewIceCream.setAdapter(iceCream);
        ViewCompat.setNestedScrollingEnabled(recyclerViewIceCream, false);
        showdataIceCream();


//        <-----------------------------------------recyclerView for the chinese---------------------------------------------------->

        recyclerViewChinese=findViewById(R.id.recyclerViewChinese);
        recyclerViewChinese.hasFixedSize();
//        firestore=FirebaseFirestore.getInstance();
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
//        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerViewChinese.setLayoutManager(new LinearLayoutManager(this));
//        firebaseFirestore=FirebaseFirestore.getInstance();
        listChinese=new ArrayList<>();
        china=new adapterChinese(getApplicationContext(),listChinese);
        recyclerViewChinese.setAdapter(china);
        ViewCompat.setNestedScrollingEnabled(recyclerViewChinese, false);
        showdataChinese();

//        firestore=FirebaseFirestore.getInstance();
//        imageView=findViewById(R.id.secondImage);
//        btn=findViewById(R.id.buttonTest);
//        scrollView=findViewById(R.id.scview);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                focusOnView();
//            }
//        });


//        <-------------------------------------------------------deal to data from main activity intent--------------------------------------------------------------------->

//        tb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(allItemList.this, "hello world", Toast.LENGTH_SHORT).show();
////                nestedscrollView.scrollTo(0, recyclerViewChinese.getTop());
//                nestedscrollView.smoothScrollTo(50,findViewById(R.id.recyclerViewChinese).getBottom());//scrollTo(0, recyclerViewChinese.getBottom());
//            }
//        });

//        Toast.makeText(this,item, Toast.LENGTH_SHORT).show();
//        switch (item)
//        {
//            case "CHINESE":
//                new Handler().post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(allItemList.this, "hello shashi", Toast.LENGTH_SHORT).show();
//                if(item.equals(tb.getText().toString()))
//                {
//                        nestedscrollView.smoothScrollTo(0,tc.getBottom());//scrollTo(0, tc.getTop());
//                }

//                    }
//                });
//        }

//    }

//    private void focus(String item) {
//        new Handler().post(new Runnable() {
//            @Override
//            public void run() {
////                if(item.equals(tb.getText().toString()))
////                {
//                    scrollView.scrollTo(0, tc.getTop());
////                }
//
//            }
//        });

//

        //    <----------------------------------------------------------------------------thread for delay the code------------------------------------------------------>
        Thread thread=new Thread(){
            @Override
            public void run() {
                super.run();
                try {

                    recyclerView.setAdapter(adapter);
                    ViewCompat.setNestedScrollingEnabled(recyclerView, false);
                    showdata();
                    sleep(1000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    int t=tb.getHeight();
                    int bir=recyclerView.getHeight();
                    int st=recyclerViewStater.getHeight();
                    int sp=recyclerViewSoup.getHeight();
                    int bev=recyclerView1.getHeight();
                    int ice=recyclerViewIceCream.getHeight();
                    int ch=recyclerViewChinese.getHeight();
                      if (item.contains("BIRYANI"))
                      {
                          nestedscrollView.smoothScrollTo(0,0);
                      }else if (item.contains("STATER")){
                          nestedscrollView.smoothScrollTo(0,bir+t+50);

                      }else if (item.contains("SOUP")){
                          nestedscrollView.smoothScrollTo(0,st+t+t+bir+50);
                      }else if(item.contains("CHINESE")){
                          nestedscrollView.smoothScrollTo(0,st+t+t+t+t+t+t+bir+bev+ice+sp);
                      }else if(item.contains("BEVERAGES")){
                          nestedscrollView.smoothScrollTo(0,bir+st+t+t+t+t+sp-13);
                      }else if (item.contains("ICE CREAM")){
                          nestedscrollView.smoothScrollTo(0,st+t+t+t+t+t+bir+bev+sp);
                      }else
                      {
                          nestedscrollView.smoothScrollTo(0,0);
                      }
                }
            }
        };thread.start();
    }

//    private void focus(String item) {
//        if(item.equals("BIRYANI"))
//        {
//            Toast.makeText(this, "the item is "+item, Toast.LENGTH_SHORT).show();
//            toolbar.setTitle("Biryani");
//            nestedscrollView.smoothScrollTo(0,500000);
//        }
//    }

    private void showdataChinese() {
        firestore.collection("Menu").document("AllDishesh").collection(" Chinese")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                listChinese.clear();
                for(DocumentSnapshot snapshot:task.getResult())
                {
                    modelChinese cns=new modelChinese(snapshot.getString("name"),snapshot.getString("image"),snapshot.getString("description"),
                            snapshot.getString("price"));
                    listChinese.add(cns);
                }
                china.notifyDataSetChanged();
                dialog.dismiss();
            }

        });
    }

    private void showdataIceCream() {
        firestore.collection("Menu").document("AllDishesh").collection(" Ice Cream").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                listCream.clear();
                for(DocumentSnapshot snapshot:task.getResult())
                {
                    modelIceCream crm=new modelIceCream(snapshot.getString("name"),snapshot.getString("image"),snapshot.getString("description"),
                            snapshot.getString("price"));
                    listCream.add(crm);
                }
                iceCream.notifyDataSetChanged();
            }

        });
    }

//    private void showdataStater() {
//            firestore.collection("Menu").document("AllDishesh").collection("Stater").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                @Override
//                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
////                    Log.d("shashikant", "onComplete: hello shashiikant kumar bhosdi k");
//
//                    listStater.clear();
//                    for(DocumentSnapshot snapshot:task.getResult())
//                    {
//                        modelStater statr=new modelStater(snapshot.getString("name"),snapshot.getString("image"),snapshot.getString("description"),
//                                snapshot.getString("price"));
//                        Log.d("shashikant", "onComplete: hello shashiikant kumar bhosdi k");
//                        listStater.add(statr);
//                    }
//                    stater.notifyDataSetChanged();
//                }
//
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull @NotNull Exception e) {
//                    Log.d("shashikant", "onFailure: the failure is due to "+e);
//                }
//            });
//    }

    private void showdataStater()
    {
//        FirebaseFirestore firestore1;
//        firestore1=FirebaseFirestore.getInstance();
        firestore.collection("Menu").document("AllDishesh").collection(" Stater").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                listStater.clear();
                for (DocumentSnapshot snapshot:task.getResult())
                {
                    modelStater st=new modelStater(snapshot.getString("name"),snapshot.getString("image"),
                            snapshot.getString("description"),snapshot.getString("price"));
                    listStater.add(st);
                }
                stater.notifyDataSetChanged();
            }
        });
    }


    private void showdataSoup() {
        firestore.collection("Menu").document("AllDishesh").collection("Soup").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                listSoup.clear();
                for(DocumentSnapshot snapshot:task.getResult())
                {
                    modelSoup soup=new modelSoup(snapshot.getString("name"),snapshot.getString("image"),snapshot.getString("description"),
                            snapshot.getString("price"));
                    listSoup.add(soup);
                }
                adapterSoup.notifyDataSetChanged();
            }

        });
    }

    private void showdata1() {
        firestore.collection("Menu").document("AllDishesh")
                .collection("Beverages").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                list1.clear();
                for(DocumentSnapshot snapshot:task.getResult())
                {
                    modelBeverages m1=new modelBeverages(snapshot.getString("name"),snapshot.getString("image"),snapshot.getString("description"),
                            snapshot.getString("price"),snapshot.getString("1L"),snapshot.getString("2L")
                            ,snapshot.getString("500ml"),snapshot.getString("price1L"),snapshot.getString("price2L")
                    ,snapshot.getString("price500"));
                    list1.add(m1);
                }
                adapter1.notifyDataSetChanged();
            }

        });
    }

    private void showdata() {
        firestore.collection("Menu").document("AllDishesh").collection("Biryani").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                list.clear();
//                Log.d("shashikant", "onCreate: the length of the biryani adapter before 4="+adapter.getItemCount());

                for(DocumentSnapshot snapshot:task.getResult())
                {
                    modelBiryani m=new modelBiryani(snapshot.getString("Name"),snapshot.getString("image"),snapshot.getString("description"),
                            snapshot.getString("price"),snapshot.getString("fullPlatePrice"),snapshot.getString("halfPlatePrice"),
                            snapshot.getString("priceFullPlate"),snapshot.getString("priceHalfPlate"));
                    list.add(m);
                }
//                Log.d("shashikant", "onCreate: the length of the biryani adapter before notify="+adapter.getItemCount());
                adapter.notifyDataSetChanged();
//                Log.d("shashikant", "onCreate: the length of the biryani adapter inside show data function="+adapter.getItemCount());
            }

        });
    }

//    private void focusOnView(){
//        new Handler().post(new Runnable() {
//            @Override
//            public void run() {
//                scrollView.scrollTo(0, imageView.getTop());
//            }
//        });
//    }

//<------------------------------------------------------------handeling the menu in bar in the itemlist---------------------------------------------------------->
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
//        getMenuInflater().inflate(R.menu.cart,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int t=tb.getHeight();
        int bir=recyclerView.getHeight();
        int st=recyclerViewStater.getHeight();
        int sp=recyclerViewSoup.getHeight();
        int bev=recyclerView1.getHeight();
        int ice=recyclerViewIceCream.getHeight();
        int ch=recyclerViewChinese.getHeight();
        switch (item.getItemId())
        {
            case R.id.biryani:
                toolbar.setTitle("Biryani");
                nestedscrollView.smoothScrollTo(0,0);

//                Toast.makeText(this, "the item addeed is ="+shashikant.size(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.stater:
                toolbar.setTitle("Stater");
//                Toast.makeText(this, "the added item is"+ stater.order().get(0), Toast.LENGTH_SHORT).show();

                nestedscrollView.smoothScrollTo(0,bir+t);
                break;
            case R.id.soup:
                toolbar.setTitle("Soup");
                nestedscrollView.smoothScrollTo(0,st+t+t+bir);
                break;
            case R.id.beverages:
                toolbar.setTitle("Beverages");
                nestedscrollView.smoothScrollTo(0,bir+st+t+t+t+t+sp-13);
                break;
            case R.id.icecream:
                toolbar.setTitle("Ice cream");
                nestedscrollView.smoothScrollTo(0,st+t+t+t+t+t+bir+bev+sp);
                break;
            case R.id.chinese:
                toolbar.setTitle("Chinese");
                nestedscrollView.smoothScrollTo(0,st+t+t+t+t+t+t+bir+bev+ice+sp);
                break;
            case R.id.cart:
                Intent intent=new Intent(allItemList.this,checkout.class);
                startActivity(intent);


        }
        return super.onOptionsItemSelected(item);
    }
//    public List<modelOrderedItem>shashikant=new ArrayList<>();
}