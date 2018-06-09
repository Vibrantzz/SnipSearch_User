package com.example.vibrantzz3.snipsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class SalonGallery extends AppCompatActivity {
    String id;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_gallery);


        Intent intent = getIntent();
        id = intent.getExtras().getString("id");

        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        tabLayout = (TabLayout) findViewById(R.id.menutabs);
        viewPager = (ViewPager) findViewById(R.id.menuviewpager);
        GalleryViewPagerAdapter adapter = new GalleryViewPagerAdapter(getSupportFragmentManager(),bundle);
        adapter.AddFragment(new OwnPicsFragment(), "By Salon");
        adapter.AddFragment(new UserPicsFragment(), "By Users");


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
