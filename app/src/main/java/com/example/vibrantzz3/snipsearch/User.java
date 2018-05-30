package com.example.vibrantzz3.snipsearch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class User extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Context context;
    private LinearLayout fave,been,pic;
    private ViewPager viewPager;
    private TextView username,uname, city, fcount,rcount,vcount;
    private ImageView uprofile,ufollow,img,touser;
    private String uName,uPic, uEmail;
    private String uID;
    private String uCity;
    private String uFcount;
    private String uRcount;
    private String uVcount;
    private static final String url_profile = "http://test.epoqueapparels.com/Salon_App/userdetails.php";
    public String email,id,user_id,user_name;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PROFILE = "data";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_CITY = "city";
    private static final String TAG_FCOUNT = "fcount";
    private static final String TAG_VCOUNT = "vcount";
    private static final String TAG_RCOUNT = "rcount";
    private static final String TAG_PIC = "profilepic";
    private static final String TAG_EMAIL = "email";

    @Override
    protected void onCreate(final Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(uName);
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

                Intent intent = new Intent(User.this , User.class);

                startActivity(intent);

                //InsertLocation(UName, GetCityName);
            }
        });
        //Intent intent = getIntent();
        // id = intent.getExtras().getString("id");

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        username = (TextView) findViewById(R.id.uname);
        city = (TextView) findViewById(R.id.ucity);
        fcount = (TextView) findViewById(R.id.ufollowcount);
        vcount = (TextView) findViewById(R.id.uvisitedcount);
        uprofile = (ImageView) findViewById(R.id.uimage);
        fave=(LinearLayout)findViewById(R.id.favelayout);
        been=(LinearLayout)findViewById(R.id.visitedlayout);
        pic=(LinearLayout)findViewById(R.id.piclayout);
        rcount=(TextView)findViewById(R.id.rcount);



        Intent intent = getIntent();
        id = intent.getExtras().getString("id");

        Bundle bundle = new Bundle();
       bundle.putString("id",id);
        bundle.putString("uid",user_id);
        bundle.putString("uname",user_name);


        fave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User.this, ViewFavouritesActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);

            }
        });


        been.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User.this, ViewVisitedActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);

            }
        });

       pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User.this, UserPicsActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);

            }
        });

        UReviewFragment rf =new UReviewFragment();
        android.support.v4.app.FragmentManager rmanager=getSupportFragmentManager();
        rf.setArguments(bundle);
        rmanager.beginTransaction()
                .replace(R.id.rframe,rf,rf.getTag())
                .commit();

        new WelcomeAsyncTask().execute();
    }


    public void GoToEdit(View view){

        Intent intent = new Intent(User.this , EditProfile.class);
        startActivity(intent);

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
            Intent intent = new Intent(User.this , ViewAppointments.class);
            startActivity(intent);
        } else if (id == R.id.nav_bm) {

            Intent intent = new Intent(User.this , ViewBookmarksActivity.class);
            intent.putExtra("id",id);
            startActivity(intent);

        } else if (id == R.id.nav_fave) {
            Intent intent = new Intent(User.this , ViewFavouritesActivity.class);
            intent.putExtra("id",id);
            startActivity(intent);


        } else if (id == R.id.nav_notif) {
            Intent intent = new Intent(User.this , ViewOffers.class);
            startActivity(intent);

        }else if (id == R.id.nav_settings) {
            Intent intent = new Intent(User.this , Settings.class);
            startActivity(intent);

        }else if (id == R.id.nav_signout) {
            SharedPreferences preferences =getSharedPreferences("loginData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(User.this , MainActivity.class);
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
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    url_profile , "GET", httpParams);
            try {
                int success = jsonObject.getInt(TAG_SUCCESS);
                JSONObject user;
                if (success == 1) {
                    //Parse the JSON response
                    user = jsonObject.getJSONObject(TAG_PROFILE);
                    uName = user.getString(TAG_NAME);
                    uCity = user.getString(TAG_CITY);
                    uFcount = user.getString(TAG_FCOUNT);
                    uRcount=user.getString(TAG_RCOUNT);
                    uVcount = user.getString(TAG_VCOUNT);
                    uPic=user.getString(TAG_PIC);
                    uEmail=user.getString(TAG_EMAIL);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {

            runOnUiThread(new Runnable() {
                public void run() {
                    //Populate the Edit Texts once the network activity is finished executing
                    if(uName==null)
                    {username.setText("User Name");}
                    else
                    {username.setText(uName);}
                    if(uCity==null)
                    {city.setText("City");}
                    else
                    {city.setText(uCity);}


                    fcount.setText("Favourites" +" ("+uFcount+")");
                    rcount.setText("My Reviews" + " ("+uRcount+")");
                    vcount.setText("VIsited"+" ("+uVcount+")");
                    Picasso.get()
                            .load(uPic)
                            .placeholder(R.drawable.uicon) // optional
                            .into(uprofile);
                    if(uEmail==email)
                    {
                        city.setVisibility(View.INVISIBLE);
                    }

                }
            });
        }


    }

}






