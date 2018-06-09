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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

public class UserPicsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    List<userPhotos> uData;
    String id,uid,fname,upic,userid,sid;
    userPhotosRecyclerViewAdapter myAdapter;
    RecyclerView myrv;
    private static final String url_menu = "http://test.epoqueapparels.com/Salon/Salon_App/usergallery.php";
    JSONObject jsonObject;
    private static final String TAG_PROFILE = "data";
    private static final String TAG_ID = "id";
    private static final String TAG_PHOTO = "photo";
    private static final String TAG_UNAME = "uname";
    private static final String TAG_SNAME = "sname";
    private static final String TAG_USERID = "userid";
    private static final String TAG_SID = "salonid";
    ImageView img, touser;
    TextView uname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpics);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("User Name's Gallery");
        mToolbar.setNavigationIcon(R.drawable.backarrow1);
        setSupportActionBar(mToolbar);



        SharedPreferences pref = getSharedPreferences("loginData", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        uid = pref.getString("userid", null);
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

                Intent intent = new Intent(UserPicsActivity.this , User.class);
                intent.putExtra("id",uid)
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
        Intent intent = getIntent();
        id = intent.getExtras().getString("id");
        uData=new ArrayList<>();


        new WelcomeAsyncTask().execute();



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
                    url_menu , "GET", httpParams);

            return null;
        }

        protected void onPostExecute(final String result) {



            try {
                JSONArray jArray = jsonObject.getJSONArray(TAG_PROFILE);

                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    //Parse the JSON response
                    uData.add(new userPhotos( json_data.getString(TAG_ID),json_data.getString(TAG_PHOTO),json_data.getString(TAG_UNAME)+ " at ",json_data.getString(TAG_SNAME),json_data.getString(TAG_USERID),json_data.getString(TAG_SID)));
                }


                myrv=(RecyclerView) findViewById(R.id.uphotos_recycler);
                myAdapter=new userPhotosRecyclerViewAdapter(UserPicsActivity.this,uData);
                myrv.setLayoutManager(new GridLayoutManager(UserPicsActivity.this, 4,GridLayoutManager.VERTICAL, false));
                myrv.setAdapter(myAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }






    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_appointment) {
            Intent intent = new Intent(UserPicsActivity.this , ViewAppointments.class);
            intent.putExtra("id",uid);
            startActivity(intent);
        } else if (id == R.id.nav_bm) {

            Intent intent = new Intent(UserPicsActivity.this , ViewBookmarksActivity.class);
            intent.putExtra("id",uid);
            startActivity(intent);

        } else if (id == R.id.nav_fave) {
            Intent intent = new Intent(UserPicsActivity.this , ViewFavouritesActivity.class);
            intent.putExtra("id",uid);
            startActivity(intent);


        } else if (id == R.id.nav_notif) {
            Intent intent = new Intent(UserPicsActivity.this , ViewOffers.class);
            startActivity(intent);

        }else if (id == R.id.nav_settings) {
            Intent intent = new Intent(UserPicsActivity.this , Settings.class);
            startActivity(intent);

        }else if (id == R.id.nav_signout) {
            SharedPreferences preferences =getSharedPreferences("loginData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(UserPicsActivity.this , MainActivity.class);
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




