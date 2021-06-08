package com.restaurant.fooddeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

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
    RecyclerView recyclerView;
    List<modelMenu>list;
    adapterMenu adapter;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//<--------------------------------------------------code to find out all id------------------------------------------------------------------->

//   toolbar=findViewById(R.id.customeToolbar);
        recyclerView=findViewById(R.id.recyclerView);

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
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        firebaseFirestore=FirebaseFirestore.getInstance();
        list=new ArrayList<>();
        adapter=new adapterMenu(getApplicationContext(),list);
        recyclerView.setAdapter(adapter);
        showdata();

    }

    private void showdata() {
        firestore.collection("menu").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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