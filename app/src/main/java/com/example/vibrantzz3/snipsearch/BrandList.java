package com.example.vibrantzz3.snipsearch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class BrandList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    List<Salon> brandData;
    String id;
    SalonListRecyclerViewAdapter myAdapter;
    JSONObject jsonObject;
    RecyclerView myrv;
    private static final String url_speclist = "http://test.epoqueapparels.com/Salon/Salon_App/franlist.php";
    private static final String TAG_PROFILE = "data";
    private static final String TAG_ID = "id";
    private static final String TAG_SID = "sid";
    private static final String TAG_NAME = "name";
    private static final String TAG_LOC = "location";
    private static final String TAG_RATE = "rating";
    private static final String TAG_PIC = "profilepic";
    private static final String TAG_RCOUNT = "rcount";
    ImageView img, touser;
    TextView uname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brandlist);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Brands");
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

                Intent intent = new Intent(BrandList.this , User.class);

                startActivity(intent);

                //InsertLocation(UName, GetCityName);
            }
        });
        Intent intent = getIntent();
        id = intent.getExtras().getString("id");

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        brandData=new ArrayList<>();


        new WelcomeAsyncTask().execute();


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
            Intent intent = new Intent(BrandList.this , ViewAppointments.class);
            startActivity(intent);
        } else if (id == R.id.nav_bm) {

            Intent intent = new Intent(BrandList.this , ViewBookmarksActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_fave) {
            Intent intent = new Intent(BrandList.this , ViewFavouritesActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_notif) {
            Intent intent = new Intent(BrandList.this , ViewOffers.class);
            startActivity(intent);

        }else if (id == R.id.nav_settings) {
            Intent intent = new Intent(BrandList.this , Settings.class);
            startActivity(intent);

        }else if (id == R.id.nav_signout) {
            SharedPreferences preferences =getSharedPreferences("loginData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(BrandList.this , MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_feedback) {

        } else if (id == R.id.nav_playstore) {

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private class WelcomeAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar

        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<>();
            httpParams.put(TAG_ID, id);
            jsonObject = httpJsonParser.makeHttpRequest(
                    url_speclist , "GET", httpParams);

            return null;
        }

        protected void onPostExecute(final String result) {

            runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        JSONArray jArray = jsonObject.getJSONArray(TAG_PROFILE);

                        for(int i=0;i<jArray.length();i++){
                            JSONObject json_data = jArray.getJSONObject(i);
                            //Parse the JSON response
                            brandData.add(new Salon(json_data.getString(TAG_NAME), json_data.getString(TAG_LOC), json_data.getString(TAG_RATE), json_data.getString(TAG_PIC), json_data.getString(TAG_SID),id,json_data.getString(TAG_RCOUNT)));

                        }


                        myrv=(RecyclerView) findViewById(R.id.brand_recycler);
                        myAdapter=new SalonListRecyclerViewAdapter(BrandList.this,brandData);
                        myrv.setLayoutManager(new LinearLayoutManager(BrandList.this));
                        myrv.setAdapter(myAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


    }

}