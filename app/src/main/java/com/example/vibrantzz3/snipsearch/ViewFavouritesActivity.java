package com.example.vibrantzz3.snipsearch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewFavouritesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    List<Favourites> fData;
    String id;
    FavouritesRecyclerViewAdapter myAdapter;
    RecyclerView myrv;
    private String uName;
    private String uID;
    private String uCity;
    private String uFcount;
    private String uRcount;
    private String uVcount;
    private static final String url_fav = "http://test.epoqueapparels.com/Salon_App/favourites.php";
    JSONObject jsonObject;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PROFILE = "data";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_CITY = "city";
    private static final String TAG_PIC = "profilepic";
    private static final String TAG_RATE = "rating";
    private static final String TAG_RCOUNT = "rcount";

    ImageView img, touser;
    TextView uname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewfavourites);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Favourites");
        mToolbar.setNavigationIcon(R.drawable.backarrow1);
        setSupportActionBar(mToolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(false);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        View headview=navigationView.getHeaderView(0);
        img=headview.findViewById(R.id.imageView);
        uname=headview.findViewById(R.id.uname);
        touser=headview.findViewById(R.id.imgto);
        uname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ViewFavouritesActivity.this , User.class);

                startActivity(intent);

                //InsertLocation(UName, GetCityName);
            }
        });
        //Intent intent = getIntent();
        // id = intent.getExtras().getString("id");

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewFavouritesActivity.this , Home.class);

                startActivity(intent);

            }
        });
        //Intent intent = getIntent();
        //id = intent.getExtras().getString("id");

        fData=new ArrayList<>();


        for(int i=0;i<5;i++){

            //Parse the JSON response
            fData.add(new Favourites( TAG_NAME,TAG_CITY,TAG_RATE,TAG_PIC,TAG_ID,id,TAG_RCOUNT));
        }


        myrv=(RecyclerView) findViewById(R.id.favourites_recycler);
        myAdapter=new FavouritesRecyclerViewAdapter(ViewFavouritesActivity.this,fData);
        myrv.setLayoutManager(new LinearLayoutManager(ViewFavouritesActivity.this));
        myrv.setAdapter(myAdapter);




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


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_appointment) {
            Intent intent = new Intent(ViewFavouritesActivity.this , ViewAppointments.class);
            startActivity(intent);
        } else if (id == R.id.nav_bm) {

            Intent intent = new Intent(ViewFavouritesActivity.this , ViewBookmarksActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_fave) {
            Intent intent = new Intent(ViewFavouritesActivity.this , ViewFavouritesActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_notif) {
            Intent intent = new Intent(ViewFavouritesActivity.this , ViewOffers.class);
            startActivity(intent);

        }else if (id == R.id.nav_settings) {
            Intent intent = new Intent(ViewFavouritesActivity.this , Settings.class);
            startActivity(intent);

        }else if (id == R.id.nav_signout) {
            SharedPreferences preferences =getSharedPreferences("loginData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(ViewFavouritesActivity.this , MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_feedback) {

        } else if (id == R.id.nav_playstore) {

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
