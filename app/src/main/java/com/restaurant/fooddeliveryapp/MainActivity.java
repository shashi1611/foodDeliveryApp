package com.restaurant.fooddeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.restaurant.fooddeliveryapp.adapters.adapterMenu;
import com.restaurant.fooddeliveryapp.adapters.adapterPopularItem;
import com.restaurant.fooddeliveryapp.models.modelMenu;
import com.restaurant.fooddeliveryapp.models.modelOrderedItem;
import com.restaurant.fooddeliveryapp.models.modelPopularItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

//import com.jaeger.library.StatusBarUtil;

public class MainActivity extends AppCompatActivity {
    ImageSlider mainSlide;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    RecyclerView recyclerView,recyclerViewPopular;
    List<modelMenu>list;
    adapterMenu adapter;
    FirebaseFirestore firestore;
    List<modelPopularItem>listPopItem;
    adapterPopularItem popularItem;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//<--------------------------------------------------code to find out all id------------------------------------------------------------------->

//   toolbar=findViewById(R.id.customeToolbar);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerViewPopular=findViewById(R.id.recyclerViewpopular);
        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();

//<--------------------------------------------------to hide the status bar----------------------------------------------->
//        StatusBarUtil.setTransparent(this);
//        View decorView = getWindow().getDecorView();
// Hide the status bar.
//        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.
//        ActionBar actionBar = getActionBar();
//        actionBar.hide();



//        <--------------------------------------to set the phone number to header-------------------------------------------------------->
        Query query=firestore.collection("user").whereEqualTo("CustomerId",auth.getCurrentUser().getUid());
        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                String phone;
                View hView=navigationView.getHeaderView(0);
                TextView textView=hView.findViewById(R.id.profile_name);
                for(QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                    textView.setText(documentSnapshot.getString("phoneNo"));
                }


            }
        });

//    <---------------------------------------------------- recycler View for popular item------------------------------------------------>

        recyclerViewPopular.hasFixedSize();
        listPopItem=new ArrayList<>();
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

//        firebaseFirestore=FirebaseFirestore.getInstance();
//        list=new ArrayList<>();
        popularItem=new adapterPopularItem(getApplicationContext(),listPopItem);
//        recyclerView.setAdapter(adapter);
        recyclerViewPopular.setAdapter(popularItem);
        showdataPopItem();


//        <--------------------------------code for the hamburger button--------------------------------------->

        toolbar=findViewById(R.id.toolbarCustom);
        setSupportActionBar(toolbar);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        navigationView=(NavigationView) findViewById(R.id.navigation_bar);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


// <-------------------------------------------------------------THE CODING FOR THE SLIDER IMAGE---------------------------------------------------------------->
        mainSlide = findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.bg, null));
        slideModels.add(new SlideModel(R.drawable.bg1, null));
        slideModels.add(new SlideModel(R.drawable.bg3, null));
        slideModels.add(new SlideModel(R.drawable.bg5, null));
        slideModels.add(new SlideModel(R.drawable.bg6, null));
        slideModels.add(new SlideModel(R.drawable.bg4, null));
        mainSlide.setImageList(slideModels, ScaleTypes.CENTER_CROP);

// <--------------------------------code for recyclerView for menu in main activity--------------------------------->
        recyclerView.hasFixedSize();
        firestore=FirebaseFirestore.getInstance();
        list=new ArrayList<>();
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
//        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this));
//        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

//        firebaseFirestore=FirebaseFirestore.getInstance();
        list=new ArrayList<>();
        adapter=new adapterMenu(getApplicationContext(),list);
        recyclerView.setAdapter(adapter);
        showdata();

//        <-------------------------------complete code for navigation drawer-------------------------------------------->

        // COMPLETE CODE FOR NAVIGATION DRAWER
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home:
                        //Toast.makeText(MainActivity.this, "home button got clicked", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_cart:
                        // Toast.makeText(MainActivity.this, "price button got clicked", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(MainActivity.this,checkout.class);
                        startActivity(i);
                        // drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_orders:
                        // Toast.makeText(MainActivity.this, "order button got clicked", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this,yourOrder.class);
                        startActivity(intent);
                        //  drawerLayout.closeDrawer(GravityCompat.START);
                        break;
//                    case R.id.menu_notification:
//                        Intent intent1=new Intent(MainActivity.this,Notification.class);
//                        startActivity(intent1);
//                        // drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
                    case R.id.menu_contact:
                        Intent intent2=new Intent(MainActivity.this,contactUs.class);
                        startActivity(intent2);
                        // drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_logout:
                        //Toast.makeText(MainActivity.this, "logout button got clicked", Toast.LENGTH_SHORT).show();
                        auth.signOut();
                        Intent intent3=new Intent(MainActivity.this,authentication.class);
                        startActivity(intent3);
                        finish();
                        // drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    //  case R.id.profile_phoneNo:
                    //    Toast.makeText(MainActivity.this, "number got gjygyggygyggygkffyhvhyvfyhclicked", Toast.LENGTH_SHORT).show();
                    //    Log.d("manikant","not working");
                    //    break;
                }
                return true;
            }
        });
    }

    private void showdataPopItem() {
        firestore.collection("popularItem").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                listPopItem.clear();
                for(DocumentSnapshot snapshot:task.getResult())
                {
                    modelPopularItem popItem=new modelPopularItem(snapshot.getString("itemName"),snapshot.getString("itemImage"));
                    listPopItem.add(popItem);
                }
                popularItem.notifyDataSetChanged();
            }

        });
    }

    private void showdata() {
        firestore.collection("Menu").document("MenuItem").collection("AllMenu").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                list.clear();
                for(DocumentSnapshot snapshot:task.getResult())
                {
                    modelMenu m=new modelMenu(snapshot.getString("menuImage"),snapshot.getString("menuName"));
                    list.add(m);
                }
                adapter.notifyDataSetChanged();
            }

        });
    }
}