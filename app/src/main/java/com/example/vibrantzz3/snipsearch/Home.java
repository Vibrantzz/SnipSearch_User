package com.example.vibrantzz3.snipsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView uemail,uname,hairall,beautyall,spaall,academyall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        hairall=(TextView)findViewById(R.id.hairseeall);
        hairall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this , HairList.class);
                startActivity(intent);

            }
        });

        beautyall=(TextView)findViewById(R.id.beautyseeall);
        beautyall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this , BeautyList.class);
                startActivity(intent);

            }
        });

        spaall=(TextView)findViewById(R.id.spaseeall);
        spaall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this , SpaList.class);
                startActivity(intent);

            }
        });

        academyall=(TextView)findViewById(R.id.academyseeall);
        academyall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this , AcademyList.class);
                startActivity(intent);

            }
        });

        hairFragment hf =new hairFragment();
        android.support.v4.app.FragmentManager manager=getSupportFragmentManager();

        manager.beginTransaction()
                .replace(R.id.hairframe,hf,hf.getTag())
                .commit();

        beautyFragment bf =new beautyFragment();
        android.support.v4.app.FragmentManager bmanager=getSupportFragmentManager();

        bmanager.beginTransaction()
                .replace(R.id.beautyframe,bf,bf.getTag())
                .commit();

        spaFragment sf =new spaFragment();
        android.support.v4.app.FragmentManager smanager=getSupportFragmentManager();

        smanager.beginTransaction()
                .replace(R.id.spaframe,sf,sf.getTag())
                .commit();

        academyFragment af =new academyFragment();
        android.support.v4.app.FragmentManager amanager=getSupportFragmentManager();

        amanager.beginTransaction()
                .replace(R.id.academyframe,af,af.getTag())
                .commit();

        franchiseFragment ff =new franchiseFragment();
        android.support.v4.app.FragmentManager fmanager=getSupportFragmentManager();

        fmanager.beginTransaction()
                .replace(R.id.brandframe,ff,ff.getTag())
                .commit();

        specFragment spf =new specFragment();
        android.support.v4.app.FragmentManager spfmanager=getSupportFragmentManager();

        spfmanager.beginTransaction()


                .replace(R.id.specsframe,spf,spf.getTag())
                .commit();

        OffersFragment offerf =new OffersFragment();
        android.support.v4.app.FragmentManager offersmanager=getSupportFragmentManager();

        offersmanager.beginTransaction()
                .replace(R.id.frame_off,offerf,offerf.getTag())
                .commit();

        StylistFragment stylef =new StylistFragment();
        android.support.v4.app.FragmentManager stfmanager=getSupportFragmentManager();

        stfmanager.beginTransaction()
                .replace(R.id.stylistframe,stylef,stylef.getTag())
                .commit();



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
