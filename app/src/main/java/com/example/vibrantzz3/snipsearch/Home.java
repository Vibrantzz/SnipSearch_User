package com.example.vibrantzz3.snipsearch;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;
import com.vistrav.ask.Ask;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Bundle bundle;
    private GoogleApiClient mGoogleApiClient;
   public static final String KEY_SUCCESS = "success";
    public static final String KEY_DATA = "data";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PROFILEPIC = "profilepic";
    String ServerURL = "http://test.epoqueapparels.com/CRUD/getSalonUserFilterData.php" ;
    public static final String BASE_URL = "http://test.epoqueapparels.com/Salon/Salon_App/welcome.php";
    private String emailStored, GetCityName;
    private String UName,UID,userid,profile;
    private TextView uemail,uname,hairall,beautyall,spaall,academyall;
    String forwardCityText;
    TextView cityText,changeloc;
    ImageView img,touser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);

        Ask.on(this)
                .forPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .go();

        Ask.on(this)
                .forPermissions(Manifest.permission.ACCESS_COARSE_LOCATION)
                .go();




        SharedPreferences pref = getSharedPreferences("loginData", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        emailStored = pref.getString("email", null);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

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

                Intent intent = new Intent(Home.this , User.class);
                intent.putExtra("id",userid);
                startActivity(intent);

                //InsertLocation(UName, GetCityName);
            }
        });
        hairall=(TextView)findViewById(R.id.hairseeall);
        hairall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this , HairList.class);
                startActivity(intent);
                //finish();

            }
        });

        beautyall=(TextView)findViewById(R.id.beautyseeall);
        beautyall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this , BeautyList.class);
                startActivity(intent);
                //finish();

            }
        });

        spaall=(TextView)findViewById(R.id.spaseeall);
        spaall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this , SpaList.class);
                startActivity(intent);
                //finish();
            }
        });

        academyall=(TextView)findViewById(R.id.academyseeall);
        academyall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this , AcademyList.class);
                startActivity(intent);
                //finish();
            }
        });


        cityText = (TextView) findViewById(R.id.txtuloc);
        changeloc = (TextView) findViewById(R.id.txtchangeloc);
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


        new WelcomeAsyncTask().execute();

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
            Intent intent = new Intent(Home.this , ViewAppointments.class);
            startActivity(intent);
        } else if (id == R.id.nav_bm) {

            Intent intent = new Intent(Home.this , ViewBookmarksActivity.class);
            intent.putExtra("id",userid);
            startActivity(intent);

        } else if (id == R.id.nav_fave) {
            Intent intent = new Intent(Home.this , ViewFavouritesActivity.class);
            intent.putExtra("id",userid);
            startActivity(intent);


        } else if (id == R.id.nav_notif) {
            Intent intent = new Intent(Home.this , ViewOffers.class);
            startActivity(intent);

        }else if (id == R.id.nav_settings) {
            Intent intent = new Intent(Home.this , Settings.class);
            startActivity(intent);

        }else if (id == R.id.nav_signout) {
            SharedPreferences preferences =getSharedPreferences("loginData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(Home.this , MainActivity.class);
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
            httpParams.put(KEY_EMAIL, emailStored);
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    BASE_URL , "GET", httpParams);
            try {
                int success = jsonObject.getInt(KEY_SUCCESS);
                JSONObject user;
                if (success == 1) {
                    //Parse the JSON response
                    user = jsonObject.getJSONObject(KEY_DATA);
                    UID = user.getString(KEY_ID);
                    UName = user.getString(KEY_NAME);
                    profile=user.getString(KEY_PROFILEPIC);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {

            runOnUiThread(new Runnable() {
                public void run() {
                    //Populate the Edit Texts once the network activity is finished executing
                    uname.setText(UName);
                    userid=UID;

                    Picasso.get()
                            .load(profile)
                            .into(img);

                    SharedPreferences sharedPreferences = getSharedPreferences("loginData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userid", userid);
                    editor.putString("name", UName);
                    editor.putString("profilepic", profile);
                    editor.apply();

                }
            });
        }


    }}
