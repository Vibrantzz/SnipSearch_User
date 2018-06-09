package com.example.vibrantzz3.snipsearch;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
//import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler;

/*import com.example.vibrantzz3.snipsearch.ImageMatrixTouchHandler;*/

/*
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.squareup.picasso.Picasso;
*/

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

/*import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;*/

public class SalonProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TextView maps,typelist,speclist,s_name,s_addr,s_address,s_timings,soffers,s_payment,s_rating,s_contact,saddr,view,fave,bmtxt;
    private ImageView simg,onpark,offpark,onac,offac,onkids,offkids,onwifi,offwifi,onapp,offapp,svisited,sbookmarked,sbook,sbooked,rate,sviewr,sunv,sunbm,sunfav,sfave, scalled, snotcalled;
    private Toolbar toolbar;
    private CardView menucard;

    Button buttonScrollDown;
    private String fname,upic,timing,uname,visited,isbm,isfave,id,pricing,rcount,formatDate,profilepic,name,addr,address,timings,ac,kidsfriendly,wifi,parking,type,payment,rating,contact,uid,offers,stype,servspecs;
    private static final String url_profile = "http://test.epoqueapparels.com/Salon/Salon_App/salondetails.php";
    private static final String url_visited = "http://test.epoqueapparels.com/Salon/Salon_App/addvisited.php";
    private static final String url_unvisited = "http://test.epoqueapparels.com/Salon/Salon_App/removevisited.php";
    private static final String url_addbm = "http://test.epoqueapparels.com/Salon/Salon_App/addbm.php";
    private static final String url_notifs = "http://test.epoqueapparels.com/Salon/Salon_App/sendSalonPush.php";
    private static final String url_removebm = "http://test.epoqueapparels.com/Salon/Salon_App/removebm.php";
    private static final String url_addfave = "http://test.epoqueapparels.com/Salon/Salon_App/addfave.php";
    private static final String url_removefave = "http://test.epoqueapparels.com/Salon/Salon_App/removefave.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PROFILE = "data";
    private static final String TAG_ID = "id";
    private static final String TAG_USER = "uid";
    private static final String TAG_UID = "user_id";
    private static final String TAG_SID = "salon_id";
    private static final String TAG_DATE = "date";
    private static final String TAG_NAME = "name";
    private static final String TAG_ADDR = "addr";
    private static final String TAG_TIMINGS = "timing";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_KIDS = "kidsfriendly";
    private static final String TAG_WIFI = "wifi";
    private static final String TAG_PARK = "parking";
    private static final String TAG_AC = "ac";
    private static final String TAG_APPT = "appointment";
    private static final String TAG_CONTACT = "contactno";
    private static final String TAG_PAY = "payment";
    private static final String TAG_RATE = "rating";
    private static final String TAG_RCOUNT = "rcount";
    private static final String TAG_PIC = "profilepic";
    private static final String TAG_OFFERS = "title";
    private static final String TAG_VISITED = "isvisited";
    private static final String TAG_BOOKMARKED = "isbm";
    private static final String TAG_FAVE = "isfave";
    private static final String TAG_TYPES = "typename";
    private static final String TAG_SPECS = "specs";
    public static final String DEFAULT_IMAGES_FOLDER = "default_images";
    public static final String PICKED_IMAGES = "picked_images";
    private static final BitmapFactory.Options BITMAP_FACTORY_OPTIONS;
    static {
        BITMAP_FACTORY_OPTIONS = new BitmapFactory.Options();
        BITMAP_FACTORY_OPTIONS.inPreferredConfig = Bitmap.Config.RGB_565;
    }
    private ViewPager viewPager;
    public static final String TAG = SalonProfile.class.getSimpleName();
    private ArrayList<Uri> pickedImageUris;
    NestedScrollView myView;
    ScrollView scroll;
    TextView scrollUp,viewreviews,viewservice,viewphotos;
    Spinner sp1;
    ImageView backPress;
    CollapsingToolbarLayout c;
    private int success;
    //    FloatingActionMenu fam;
    Bundle bundle;
    ImageView img, touser;
    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_profile);

        s_name=(TextView) findViewById(R.id.salonName);
        rate = (ImageView) findViewById(R.id.rate);

        s_addr=(TextView) findViewById(R.id.salonAddr);
        s_timings=(TextView) findViewById(R.id.salonTimings);
        s_address=(TextView) findViewById(R.id.saddress);
        //s_contact=(TextView) findViewById(R.id.scontact);
        //saddr=(TextView) findViewById(R.id.addrev);
        simg=(ImageView) findViewById(R.id.logo);

        toolbar=(Toolbar) findViewById(R.id.toolbar);
        menucard=(CardView)findViewById(R.id.card_menu);
        //s_payment=(TextView) findViewById(R.id.spay);
        s_rating=(TextView) findViewById(R.id.txtrate);

        svisited=(ImageView) findViewById(R.id.svisited);
        scrollUp = (TextView) findViewById(R.id.scrollup);
        sunv=(ImageView) findViewById(R.id.sunvisited);
        sfave=(ImageView) findViewById(R.id.sfave);
        sunfav=(ImageView) findViewById(R.id.sunfave);
        sbookmarked=(ImageView) findViewById(R.id.sbm);
        sbook=(ImageView) findViewById(R.id.sbm1);
        sbooked=(ImageView) findViewById(R.id.sbm2);
        sunbm=(ImageView) findViewById(R.id.sunbm);
        scalled=(ImageView) findViewById(R.id.scall);
        snotcalled=(ImageView) findViewById(R.id.snotcall);
       sp1 = (Spinner) findViewById(R.id.sp);
       s_timings=(TextView)findViewById(R.id.timestat);
        viewreviews=(TextView)findViewById(R.id.viewphotos3);
        viewservice=(TextView)findViewById(R.id.viewservice);
        viewphotos=(TextView)findViewById(R.id.viewphotos);
        //buttonScrollDown = (Button)findViewById(R.id.scrolldown);
        onwifi=(ImageView)findViewById(R.id.wifion);
        offwifi=(ImageView)findViewById(R.id.wifioff);
        onac=(ImageView)findViewById(R.id.acon);
        offac=(ImageView)findViewById(R.id.acoff);
        onpark=(ImageView)findViewById(R.id.parkon);
        offpark=(ImageView)findViewById(R.id.parkoff);
        onapp=(ImageView)findViewById(R.id.appon);
        offapp=(ImageView)findViewById(R.id.appoff);
        onkids=(ImageView)findViewById(R.id.kidson);
        offkids=(ImageView)findViewById(R.id.kidsoff);
        typelist=(TextView)findViewById(R.id.typedesc);
        speclist=(TextView)findViewById(R.id.servicedesc);
        maps=(TextView)findViewById(R.id.mapsclick) ;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setImageResource(R.drawable.floatingbutton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent = new Intent(SalonProfile.this, Review.class);
                intent.putExtra("uid",uid);
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                intent.putExtra("loc",addr);
                startActivity(intent);
            }
        });

        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        scrollUp.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                myView.scrollBy(0, +1750);
                //c.scrollBy(0,150);
            }});

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.backarrow); // your drawable
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapstoolbar);
        collapsingToolbarLayout.setTitle("Create Delivery Personnel");
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorPrimary));
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.rgb(0, 0, 0));*/

        final Toolbar tool = (Toolbar)findViewById(R.id.toolbar);
        /*c = (CollapsingToolbarLayout)findViewById(R.id.collapstoolbar);*/

        tool.setTitle("");
        setSupportActionBar(toolbar);

        tool.setNavigationIcon(R.drawable.backarrownew);// your drawable
        tool.setTitleTextColor(Color.rgb(0, 0, 0));

        SharedPreferences pref = getSharedPreferences("loginData", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        uid = pref.getString("userid", null);
        uname = pref.getString("name", null);
        upic = pref.getString("profilepic", null);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, tool, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(false);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        View headview=navigationView.getHeaderView(0);
        img=headview.findViewById(R.id.imageView);
        username=headview.findViewById(R.id.uname);
        touser=headview.findViewById(R.id.imgto);
        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SalonProfile.this , User.class);
                intent.putExtra("id",uid);
                startActivity(intent);

                //InsertLocation(UName, GetCityName);
            }
        });

        Picasso.get()
                .load(upic)
                .into(img);
        username.setText(uname);










        Intent intent = getIntent();
        id = intent.getExtras().getString("id");

        tool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        /*Intent intent = getIntent();
        id = intent.getExtras().getString("id");
        profilepic = intent.getExtras().getString("Thumbnail") ;
        uid = intent.getExtras().getString("user") ;*/

        /*bundle = new Bundle();
        bundle.putString("id",id);*/

        Picasso.get()
                .load(profilepic)
                .placeholder(R.drawable.logo2) // optional
                .into(simg);



        bundle = new Bundle();
        bundle.putString("id",id);


        MenuFragment mf =new MenuFragment();
        android.support.v4.app.FragmentManager mfmanager=getSupportFragmentManager();
        mf.setArguments(bundle);
        mfmanager.beginTransaction()
                .replace(R.id.menuframe,mf,mf.getTag())
                .commit();

        GalleryFragment gf =new GalleryFragment();
        android.support.v4.app.FragmentManager gmanager=getSupportFragmentManager();
        gf.setArguments(bundle);
        gmanager.beginTransaction()
                .replace(R.id.galleryframe,gf,gf.getTag())
                .commit();



        /*saddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SalonProfile.this, AddReview.class);
                intent.putExtra("id", id);
                intent.putExtra("uid", uid);
                intent.putExtra("name", name);
                intent.putExtra("loc", addr);
                startActivity(intent);
                finish();
            }});*/

        /*buttonScrollDown.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                myView.scrollBy(0, -40);
            }});*/
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SalonProfile.this , MapsActivity.class);
                intent.putExtra("addr",address);
                startActivity(intent);
            }});


        svisited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*new AddVisitedAsyncTask().execute();
                new VNotifsAsyncTask().execute();*/
                svisited.setVisibility(INVISIBLE);
                //myView.scrollTo(0,-20);
                sunv.setVisibility(VISIBLE);
                //scroll.fullScroll(View.FOCUS_DOWN);

            }});


        viewreviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SalonProfile.this , ViewReviewActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);

            }});

        viewservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SalonProfile.this , SalonMenuActivity.class);
                intent.putExtra("id",id);

                startActivity(intent);

            }});

        viewphotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SalonProfile.this , SalonGallery.class);
                intent.putExtra("id",id);

                startActivity(intent);

            }});

        /*buttonScrollDown.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                myView.scrollBy(0, -20);
            }});*/


        sunv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // new DeleteVisitedAsyncTask().execute();
                sunv.setVisibility(INVISIBLE);
                svisited.setVisibility(VISIBLE);
            }});

        scalled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contact));
                startActivity(intent);
            }});

        sfave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* new AddFaveAsyncTask().execute();
                new FNotifsAsyncTask().execute();*/
                sfave.setVisibility(INVISIBLE);
                sunfav.setVisibility(VISIBLE);

            }});
        sunfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //new DeleteFaveAsyncTask().execute();
                sunfav.setVisibility(INVISIBLE);
                sfave.setVisibility(VISIBLE);
            }});

        sbookmarked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* new AddBMAsyncTask().execute();
                new BNotifsAsyncTask().execute();*/
                sbookmarked.setVisibility(INVISIBLE);
                sunbm.setVisibility(VISIBLE);

            }});
        sunbm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //new DeleteBMAsyncTask().execute();
                sunbm.setVisibility(INVISIBLE);
                sbookmarked.setVisibility(VISIBLE);
            }});

        sbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SalonProfile.this, BookAppointment.class);
                intent.putExtra("uid",uid);
                intent.putExtra("id",id);

                startActivity(intent);

            }});


        VReviewFragment vf =new VReviewFragment();
        android.support.v4.app.FragmentManager rfmanager=getSupportFragmentManager();
        vf.setArguments(bundle);
        rfmanager.beginTransaction()
                .replace(R.id.framereview,vf,vf.getTag())
                .commit();
        SOffersFragment sof =new SOffersFragment();
        android.support.v4.app.FragmentManager sofmanager=getSupportFragmentManager();
            sof.setArguments(bundle);
        sofmanager.beginTransaction()
                .replace(R.id.offerframe,sof,sof.getTag())
                .commit();

        /*view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SalonProfile.this , SalonReviewActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }});*/
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
            httpParams.put(TAG_USER, uid);
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    url_profile , "GET", httpParams);
            try {
                int success = jsonObject.getInt(TAG_SUCCESS);
                JSONObject user;
                if (success == 1) {
                    //Parse the JSON response
                    user = jsonObject.getJSONObject(TAG_PROFILE);
                    name = user.getString(TAG_NAME);
                    addr = user.getString(TAG_ADDR);
                    address = user.getString(TAG_ADDRESS);
                    timings = user.getString(TAG_TIMINGS);
                    kidsfriendly = user.getString(TAG_KIDS);
                    wifi = user.getString(TAG_WIFI);
                    contact = user.getString(TAG_CONTACT);
                    type = user.getString(TAG_APPT);
                    parking = user.getString(TAG_PARK);
                    payment = user.getString(TAG_PAY);
                    rating = user.getString(TAG_RATE);
                    profilepic=user.getString(TAG_PIC);
                    offers=user.getString(TAG_OFFERS);
                    ac=user.getString(TAG_AC);
                    visited=user.getString(TAG_VISITED);
                    isbm=user.getString(TAG_BOOKMARKED);
                    isfave=user.getString(TAG_FAVE);
                    stype=user.getString(TAG_TYPES);
                    servspecs=user.getString(TAG_SPECS);
                    rcount=user.getString(TAG_RCOUNT);
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
                    s_name.setText(name);
                    s_addr.setText(addr);
                   s_address.setText(address);
                    if(wifi=="1")
                    {
                        offwifi.setImageResource(R.drawable.onwifi);
                    }
                    else
                    {
                        offwifi.setImageResource(R.drawable.offwifi);
                    }
                    if(parking=="1")
                    {
                        offpark.setImageResource(R.drawable.onparking);

                    }
                    else
                    {
                        offpark.setImageResource(R.drawable.offparking);
                    }
                    if(ac=="1")
                    {
                        offac.setImageResource(R.drawable.onac);

                    }
                    else
                    {
                        offac.setImageResource(R.drawable.offac);

                    }
                    if(kidsfriendly=="1")
                    {
                        offkids.setImageResource(R.drawable.onkids);
                    }
                    else
                    {
                        offkids.setImageResource(R.drawable.offkids);
                    }
                    if(type=="1")
                    {
                        offapp.setImageResource(R.drawable.onappt);
                    }
                    else
                    {
                        offapp.setImageResource(R.drawable.offappt);
                    }

                    s_rating.setText(rating);

                    if(Float.parseFloat(rating)<=1.0 && Float.parseFloat(rating)>0.0)
                    {
                        rate.setImageResource(R.drawable.bgred);
                    }
                    if(Float.parseFloat(rating)<2.0 && Float.parseFloat(rating)>=1.0)
                    {
                        rate.setImageResource(R.drawable.bgorange);
                    }
                    else if(Float.parseFloat(rating)<3.0 && Float.parseFloat(rating)>=2.0)
                    {
                        rate.setImageResource(R.drawable.bgyellow);
                    }

                    else if(Float.parseFloat(rating)<4.0 && Float.parseFloat(rating)>=3.0)
                    {
                        rate.setImageResource(R.drawable.ratingellipse);
                    }
                    else if(Float.parseFloat(rating)<=5.0 && Float.parseFloat(rating)>=4.0)
                    {
                        rate.setImageResource(R.drawable.bgdgreen);
                    }
                    else {
                        rate.setImageResource(R.drawable.bgred);
                    }
                    Picasso.get()
                            .load(profilepic)
                            .placeholder(R.drawable.logo2) // optional
                            .into(simg);
                    timing=timings.replace("separate", "\n");


                    if(stype=="null")
                    {
                        typelist.setText("List of services provided by salon");
                    }
                    else
                    {typelist.setText(stype);
                    }

                    if(servspecs=="null")
                    {
                        speclist.setText("Types of services provided by salon");
                    }
                    else
                    {speclist.setText(servspecs);
                    }
                    switch(visited){
                        case "Yes":
                            svisited.setVisibility(INVISIBLE);
                            sunv.setVisibility(VISIBLE);
                            break;  //optional
                        case "No":
                            sunv.setVisibility(INVISIBLE);
                            svisited.setVisibility(VISIBLE);;
                            break;  //optional
                        default:
                            sunv.setVisibility(INVISIBLE);
                            svisited.setVisibility(VISIBLE);;

                    }

                    viewreviews.setText("See All "+"("+rcount+")");

                    switch(isbm){
                        case "Yes":
                            sbookmarked.setVisibility(INVISIBLE);
                            sunbm.setVisibility(VISIBLE);
                            break;  //optional
                        case "No":
                            sunbm.setVisibility(INVISIBLE);
                            sbookmarked.setVisibility(VISIBLE);
                            break;  //optional
                        default:
                            sunbm.setVisibility(INVISIBLE);
                            sbookmarked.setVisibility(VISIBLE);

                    }
                    switch(isfave){
                        case "Yes":
                            sfave.setVisibility(INVISIBLE);
                            sunfav.setVisibility(VISIBLE);
                            break;  //optional
                        case "No":
                            sunfav.setVisibility(INVISIBLE);
                            sfave.setVisibility(VISIBLE);
                            break;  //optional
                        default:
                            sunfav.setVisibility(INVISIBLE);
                            sfave.setVisibility(VISIBLE);
                    }
                }
            });
        }


    }



    private class AddVisitedAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<>();
            //Populating request parameters

            httpParams.put(TAG_UID, uid);
            httpParams.put(TAG_SID, id);
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    url_visited, "POST", httpParams);
            try {
                success = jsonObject.getInt(TAG_SUCCESS);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            runOnUiThread(new Runnable() {
                public void run() {
                    if (success == 1) {
                        //Display success message
                        Toast.makeText(SalonProfile.this,
                                "You have visited this salon!", Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }

    private class DeleteVisitedAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<>();
            //Set movie_id parameter in request
            httpParams.put(TAG_UID, uid);
            httpParams.put(TAG_SID, id);
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    url_unvisited, "POST", httpParams);
            try {
                success = jsonObject.getInt(TAG_SUCCESS);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {

            runOnUiThread(new Runnable() {
                public void run() {
                    if (success == 1) {
                        //Display success message
                        Toast.makeText(SalonProfile.this,
                                "Removed from visited", Toast.LENGTH_LONG).show();


                    } else {
                        Toast.makeText(SalonProfile.this,
                                "Some error occurred",
                                Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }

    private class AddBMAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<>();
            //Populating request parameters

            httpParams.put(TAG_UID, uid);
            httpParams.put(TAG_SID, id);
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    url_addbm, "POST", httpParams);
            try {
                success = jsonObject.getInt(TAG_SUCCESS);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            runOnUiThread(new Runnable() {
                public void run() {
                    if (success == 1) {
                        //Display success message
                        Toast.makeText(SalonProfile.this,
                                "You have bookmarked this salon!", Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }

    private class DeleteBMAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<>();
            //Set movie_id parameter in request
            httpParams.put(TAG_UID, uid);
            httpParams.put(TAG_SID, id);
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    url_removebm, "POST", httpParams);
            try {
                success = jsonObject.getInt(TAG_SUCCESS);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {

            runOnUiThread(new Runnable() {
                public void run() {
                    if (success == 1) {
                        //Display success message
                        Toast.makeText(SalonProfile.this,
                                "Removed from Bookmarks", Toast.LENGTH_LONG).show();


                    } else {


                    }
                }
            });
        }
    }
    private class AddFaveAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<>();
            //Populating request parameters

            httpParams.put(TAG_UID, uid);
            httpParams.put(TAG_SID, id);
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    url_addfave, "POST", httpParams);
            try {
                success = jsonObject.getInt(TAG_SUCCESS);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            runOnUiThread(new Runnable() {
                public void run() {
                    if (success == 1) {
                        //Display success message
                        Toast.makeText(SalonProfile.this,
                                "Salon added to Favourites!", Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }

    private class DeleteFaveAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<>();
            //Set movie_id parameter in request
            httpParams.put(TAG_UID, uid);
            httpParams.put(TAG_SID, id);
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    url_removefave, "POST", httpParams);
            try {
                success = jsonObject.getInt(TAG_SUCCESS);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {

            runOnUiThread(new Runnable() {
                public void run() {
                    if (success == 1) {
                        //Display success message
                        Toast.makeText(SalonProfile.this,
                                "Salon removed to Favourites!", Toast.LENGTH_LONG).show();


                    } else {

                    }
                }
            });
        }
    }
    //sending notifs
    private class BNotifsAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<>();
            //Populating request parameters

            httpParams.put("title", "Bookmarked!");
            httpParams.put("id", id);
            httpParams.put("message", uname + " has bookmarked your salon");


            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    url_notifs, "POST", httpParams);
            try {
                success = jsonObject.getInt(TAG_SUCCESS);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            runOnUiThread(new Runnable() {
                public void run() {
                    if (success == 1) {
                        //Display success message
                        Intent i = getIntent();
                        //send result code 20 to notify about movie update
                        setResult(20, i);
                        //Finish ths activity and go back to listing activity
                        finish();
                    }
                }
            });
        }
    }
    //sending notifs
    private class FNotifsAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<>();
            //Populating request parameters

            httpParams.put("title", "Favourite!");
            httpParams.put("id", id);
            httpParams.put("message", uname + " has marked your salon as Favourite");


            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    url_notifs, "POST", httpParams);
            try {
                success = jsonObject.getInt(TAG_SUCCESS);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            runOnUiThread(new Runnable() {
                public void run() {
                    if (success == 1) {
                        //Display success message
                        Intent i = getIntent();
                        //send result code 20 to notify about movie update
                        setResult(20, i);
                        //Finish ths activity and go back to listing activity
                        finish();
                    }
                }
            });
        }
    }

    //sending notifs
    private class VNotifsAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<>();
            //Populating request parameters

            httpParams.put("title", "Visited!");
            httpParams.put("id", id);
            httpParams.put("message", uname + " has marked your salon as Visited");


            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    url_notifs, "POST", httpParams);
            try {
                success = jsonObject.getInt(TAG_SUCCESS);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            runOnUiThread(new Runnable() {
                public void run() {
                    if (success == 1) {
                        //Display success message
                        Intent i = getIntent();
                        //send result code 20 to notify about movie update
                        setResult(20, i);
                        //Finish ths activity and go back to listing activity
                        finish();
                    }
                }
            });
        }
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
            Intent intent = new Intent(SalonProfile.this , BookAppointment.class);
            intent.putExtra("id",uid);
            startActivity(intent);
        } else if (id == R.id.nav_bm) {

            Intent intent = new Intent(SalonProfile.this , ViewBookmarksActivity.class);
            intent.putExtra("id",uid);
            startActivity(intent);

        } else if (id == R.id.nav_fave) {
            Intent intent = new Intent(SalonProfile.this , ViewFavouritesActivity.class);
            intent.putExtra("id",uid);
            startActivity(intent);


        } else if (id == R.id.nav_notif) {
            Intent intent = new Intent(SalonProfile.this , ViewOffers.class);
            startActivity(intent);

        }else if (id == R.id.nav_settings) {
            Intent intent = new Intent(SalonProfile.this , Settings.class);
            startActivity(intent);

        }else if (id == R.id.nav_signout) {
            SharedPreferences preferences =getSharedPreferences("loginData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(SalonProfile.this , MainActivity.class);
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



