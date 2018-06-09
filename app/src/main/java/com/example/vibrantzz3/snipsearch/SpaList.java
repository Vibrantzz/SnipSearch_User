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

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpaList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    List<Salon> hData;
    String id,fname,upic;
    SalonListRecyclerViewAdapter myAdapter;
    RecyclerView myrv;
    private static final String url_spalist = "http://test.epoqueapparels.com/Salon/Salon_App/spaFragment.php";
    JSONObject jsonObject;
    private static final String TAG_PROFILE = "data";
    private static final String TAG_ID = "id";
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
        setContentView(R.layout.activity_spalist);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Spa");
        mToolbar.setNavigationIcon(R.drawable.backarrow1);
        setSupportActionBar(mToolbar);



        SharedPreferences pref = getSharedPreferences("loginData", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        id = pref.getString("userid", null);
        fname = pref.getString("name", null);
        upic = pref.getString("profilepic", null);


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

                Intent intent = new Intent(SpaList.this , User.class);
                intent.putExtra("id",id)
                ;                startActivity(intent);
                //InsertLocation(UName, GetCityName);
            }
        });
        Picasso.get()
                .load(upic)
                .into(img);
        uname.setText(fname);



        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        hData=new ArrayList<>();


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
            Intent intent = new Intent(SpaList.this , ViewAppointments.class);
            intent.putExtra("id",id);
            startActivity(intent);
        } else if (id == R.id.nav_bm) {

            Intent intent = new Intent(SpaList.this , ViewBookmarksActivity.class);
            intent.putExtra("id",id);
            startActivity(intent);

        } else if (id == R.id.nav_fave) {
            Intent intent = new Intent(SpaList.this , ViewFavouritesActivity.class);
            intent.putExtra("id",id);
            startActivity(intent);


        } else if (id == R.id.nav_notif) {
            Intent intent = new Intent(SpaList.this , ViewOffers.class);
            startActivity(intent);

        }else if (id == R.id.nav_settings) {
            Intent intent = new Intent(SpaList.this , Settings.class);
            startActivity(intent);

        }else if (id == R.id.nav_signout) {
            SharedPreferences preferences =getSharedPreferences("loginData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(SpaList.this , MainActivity.class);
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
        jsonObject = httpJsonParser.makeHttpRequest(
                url_spalist, "GET", null);

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
                        hData.add(new Salon( json_data.getString(TAG_NAME), json_data.getString(TAG_LOC), json_data.getString(TAG_RATE), json_data.getString(TAG_PIC),json_data.getString(TAG_ID),id,json_data.getString(TAG_RCOUNT) ));
                    }


                    myrv=(RecyclerView) findViewById(R.id.spl_recycler);
                    myAdapter=new SalonListRecyclerViewAdapter(SpaList.this,hData);
                    myrv.setLayoutManager(new LinearLayoutManager(SpaList.this));
                    myrv.setAdapter(myAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}

}
