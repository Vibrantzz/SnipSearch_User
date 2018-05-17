package com.example.vibrantzz3.snipsearch;


import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Scroller;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;*/

public class SalonProfile extends AppCompatActivity {
    private TextView s_name,s_addr,s_address,s_timings,soffers,s_payment,s_rating,s_contact,saddr,view,fave,bmtxt;
    private ImageView simg,checkac,checkwifi,checkladies,checkpark,checkkids,svisited,sbookmarked,sbook,sbooked,sviewr,sunv,sunbm,sunfav,sfave, scalled, snotcalled;
    private Toolbar toolbar;
    private CardView menucard;
    Button buttonScrollDown;
    private String uname,visited,isbm,isfave,id,pricing,formatDate,profilepic,name,addr,address,timings,ac,kidsfriendly,wifi,parking,type,payment,rating,contact,uid,offers;
    private static final String url_profile = "http://test.epoqueapparels.com/Salon_App/salondetails.php";
    private static final String url_visited = "http://test.epoqueapparels.com/Salon_App/addvisited.php";
    private static final String url_unvisited = "http://test.epoqueapparels.com/Salon_App/removevisited.php";
    private static final String url_addbm = "http://test.epoqueapparels.com/Salon_App/addbm.php";
    private static final String url_notifs = "http://test.epoqueapparels.com/Salon_App/sendSalonPush.php";
    private static final String url_removebm = "http://test.epoqueapparels.com/Salon_App/removebm.php";
    private static final String url_addfave = "http://test.epoqueapparels.com/Salon_App/addfave.php";
    private static final String url_removefave = "http://test.epoqueapparels.com/Salon_App/removefave.php";
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
    private static final String TAG_TYPE = "type";
    private static final String TAG_CONTACT = "contact";
    private static final String TAG_PAY = "payment";
    private static final String TAG_RATE = "rating";
    private static final String TAG_PIC = "profilepic";
    private static final String TAG_OFFERS = "offers";
    private static final String TAG_PRICE = "pricing";
    private static final String TAG_VISITED = "isvisited";
    private static final String TAG_BOOKMARKED = "isbm";
    private static final String TAG_FAVE = "isfave";
    public static final String DEFAULT_IMAGES_FOLDER = "default_images";
    public static final String PICKED_IMAGES = "picked_images";
    private static final BitmapFactory.Options BITMAP_FACTORY_OPTIONS;
    static {
        BITMAP_FACTORY_OPTIONS = new BitmapFactory.Options();
        BITMAP_FACTORY_OPTIONS.inPreferredConfig = Bitmap.Config.RGB_565;
    }
    private ViewPager viewPager;
    public static final String TAG = SalonProfile.class.getSimpleName();
    private ImageViewPagerAdapter imageViewPagerAdapter;
    private ArrayList<Uri> pickedImageUris;
    NestedScrollView myView;
    ScrollView scroll;
    TextView scrollUp;
    Spinner sp1;
    ImageView backPress, rate;
    CollapsingToolbarLayout c;
    private int success;
    //    FloatingActionMenu fam;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_profile);

        s_name=(TextView) findViewById(R.id.salonName);
        rate = (ImageView) findViewById(R.id.rate);
        backPress = (ImageView) findViewById(R.id.backarrow);
        s_addr=(TextView) findViewById(R.id.salonAddr);
        s_timings=(TextView) findViewById(R.id.salonTimings);
        s_address=(TextView) findViewById(R.id.saddress);
        //s_contact=(TextView) findViewById(R.id.scontact);
        soffers=(TextView) findViewById(R.id.sofferdesc);
        //saddr=(TextView) findViewById(R.id.addrev);
        simg=(ImageView) findViewById(R.id.salonimage);
        checkwifi=(ImageView) findViewById(R.id.checkwifi);
        checkpark=(ImageView) findViewById(R.id.checkparking);
        checkladies=(ImageView) findViewById(R.id.checkladies);
        checkkids=(ImageView) findViewById(R.id.checkids);
        checkac=(ImageView) findViewById(R.id.checkac);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        menucard=(CardView)findViewById(R.id.card_menu);
        /*s_payment=(TextView) findViewById(R.id.spay);
        s_rating=(TextView) findViewById(R.id.salonRate);
        view=(TextView) findViewById(R.id.viewreviews);*/
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
        myView = (NestedScrollView)findViewById(R.id.scrollview1);
        c = (CollapsingToolbarLayout)findViewById(R.id.collapstoolbar);
        sp1 = (Spinner) findViewById(R.id.sp);
        //buttonScrollDown = (Button)findViewById(R.id.scrolldown);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setImageResource(R.drawable.floatingbutton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent = new Intent(SalonProfile.this, Review.class);
                startActivity(intent);
            }
        });

        backPress.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                onBackPressed();
            }});

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
        AppBarLayout appbar = (AppBarLayout)findViewById(R.id.app_bar_layout);
        tool.setTitle("");
        setSupportActionBar(toolbar);
        c.setTitleEnabled(true);

        tool.setNavigationIcon(R.drawable.backarrow3);// your drawable
        tool.setTitleTextColor(Color.rgb(0, 0, 0));
        tool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });

        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            boolean isVisible = true;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    c.setTitle("Happy in the Head Hair Studio");
//                    c.setTitle();
                    tool.setVisibility(View.VISIBLE);
                    //c.setCollapsedTitleTextColor(Color.rgb(0, 0, 0));
                    isVisible = true;
                } else if(isVisible) {
                    c.setTitle("");
                    tool.setVisibility(View.INVISIBLE);
                    isVisible = false;
                }
            }
        });


        /*Intent intent = getIntent();
        id = intent.getExtras().getString("id");
        profilepic = intent.getExtras().getString("Thumbnail") ;
        uid = intent.getExtras().getString("user") ;*/

        /*bundle = new Bundle();
        bundle.putString("id",id);*/

        /*Picasso.with(this)
                .load(profilepic)
                .placeholder(R.drawable.logo2) // optional
                .into(simg);*/

       /* SharedPreferences pref = getSharedPreferences("loginData", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        uid = pref.getString("userid", null);
        uname = pref.getString("    name", null);*/


       /* MenuFragment mf =new MenuFragment();
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


        SReviewFragment srf =new SReviewFragment();
        android.support.v4.app.FragmentManager srfmanager=getSupportFragmentManager();
        srf.setArguments(bundle);
        srfmanager.beginTransaction() .replace(R.id.reviewframes,srf,srf.getTag()).commit();*/


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

        svisited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*new AddVisitedAsyncTask().execute();
                new VNotifsAsyncTask().execute();*/
                svisited.setVisibility(View.GONE);
                //myView.scrollTo(0,-20);
                sunv.setVisibility(View.VISIBLE);
                //scroll.fullScroll(View.FOCUS_DOWN);

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
                sunv.setVisibility(View.GONE);
                svisited.setVisibility(View.VISIBLE);
            }});

        scalled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*new AddVisitedAsyncTask().execute();
                new VNotifsAsyncTask().execute();*/
                scalled.setVisibility(View.GONE);
                snotcalled.setVisibility(View.VISIBLE);

            }});
        snotcalled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // new DeleteVisitedAsyncTask().execute();
                snotcalled.setVisibility(View.GONE);
                scalled.setVisibility(View.VISIBLE);
            }});


        sfave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* new AddFaveAsyncTask().execute();
                new FNotifsAsyncTask().execute();*/
                sfave.setVisibility(View.GONE);
                sunfav.setVisibility(View.VISIBLE);

            }});
        sunfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //new DeleteFaveAsyncTask().execute();
                sunfav.setVisibility(View.GONE);
                sfave.setVisibility(View.VISIBLE);
            }});

        sbookmarked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* new AddBMAsyncTask().execute();
                new BNotifsAsyncTask().execute();*/
                sbookmarked.setVisibility(View.GONE);
                sunbm.setVisibility(View.VISIBLE);

            }});
        sunbm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //new DeleteBMAsyncTask().execute();
                sunbm.setVisibility(View.GONE);
                sbookmarked.setVisibility(View.VISIBLE);
            }});

        sbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* new AddBMAsyncTask().execute();
                new BNotifsAsyncTask().execute();*/
                sbook.setVisibility(View.GONE);
                sbooked.setVisibility(View.VISIBLE);

            }});
        sbooked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //new DeleteBMAsyncTask().execute();
                sbooked.setVisibility(View.GONE);
                sbook.setVisibility(View.VISIBLE);
            }});
        /*view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SalonProfile.this , SalonReviewActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }});*/
//        new WelcomeAsyncTask().execute();
        List<Drawable> drawables = new ArrayList<>();
        addDefaultImages(drawables);

        imageViewPagerAdapter = new ImageViewPagerAdapter(drawables);
        viewPager = findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(imageViewPagerAdapter);

        if(savedInstanceState != null) {
            pickedImageUris = savedInstanceState.getParcelableArrayList(PICKED_IMAGES);
        }

        if(pickedImageUris != null) {
            for(Uri uri: pickedImageUris) {
                try {
                    addDrawableByUri(uri);
                } catch (FileNotFoundException e) {
                    Log.e(TAG, "File not found", e);
                }
            }
        } else {
            pickedImageUris = new ArrayList<>();
        }
    }

    private void addDrawableByUri(Uri uri) throws FileNotFoundException {
        InputStream is = getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(is, null, BITMAP_FACTORY_OPTIONS);
        Drawable drawable = new BitmapDrawable(getResources(), bitmap);

        // Add drawable to end of list
        imageViewPagerAdapter.drawables.add(drawable);
        imageViewPagerAdapter.notifyDataSetChanged();
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(R.id.add_photo == id) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.add_photo)), PICK_IMAGE);
        } else if(R.id.clear == id) {
            List<Drawable> drawables = imageViewPagerAdapter.drawables;
            drawables.clear();
            addDefaultImages(drawables);
            imageViewPagerAdapter.notifyDataSetChanged();
            pickedImageUris.clear();
        } else if(R.id.info == id) {
            DialogFragment infoDialogFragment = new InfoDialogFragment();
            infoDialogFragment.show(getFragmentManager(), "info");
        }
        return super.onOptionsItemSelected(item);
    }*/

    private void addDefaultImages(List<Drawable> drawables) {
        // Note: Images are stored as assets instead of as resources
        // This because content should be in its raw format as opposed to UI elements
        // and to have more control over the decoding of image files

        AssetManager assets = getAssets();
        Resources resources = getResources();
        try {
            List<String> images = Arrays.asList(assets.list(DEFAULT_IMAGES_FOLDER));
            Collections.sort(images);
            for(String image: images) {
                InputStream is = null;
                try {
                    is = assets.open(DEFAULT_IMAGES_FOLDER + "/" + image);
                    Bitmap bitmap = BitmapFactory.decodeStream(is, null, BITMAP_FACTORY_OPTIONS);
                    //drawables.add(new BitmapDrawable(resources, bitmap));
                } finally {
                    if(is != null) {
                        try {
                            is.close();
                        } catch(IOException ignored) {
                        }
                    }
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static class ImageViewPagerAdapter extends PagerAdapter {

        private List<Drawable> drawables;

        public ImageViewPagerAdapter(List<Drawable> drawables) {
            this.drawables = drawables;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = container.getContext();
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.activity_salon_profile, null);
            container.addView(view);

            ImageView imageView = view.findViewById(R.id.salonimage);
            imageView.setImageDrawable(drawables.get(position));

            /*ImageMatrixTouchHandler imageMatrixTouchHandler = new ImageMatrixTouchHandler(context);
            imageView.setOnTouchListener(imageMatrixTouchHandler);*/

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;

            ImageView imageView = view.findViewById(R.id.image);
            imageView.setImageResource(0);

            container.removeView(view);
        }

        @Override
        public int getCount() {
            return drawables.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getItemPosition (Object object) {
            return POSITION_NONE;
        }
    }


    /*private class WelcomeAsyncTask extends AsyncTask<String, String, String> {
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
                    type = user.getString(TAG_TYPE);
                    parking = user.getString(TAG_PARK);
                    payment = user.getString(TAG_PAY);
                    rating = user.getString(TAG_RATE);
                    profilepic=user.getString(TAG_PIC);
                    offers=user.getString(TAG_OFFERS);
                    pricing=user.getString(TAG_PRICE);
                    ac=user.getString(TAG_AC);
                    visited=user.getString(TAG_VISITED);
                    isbm=user.getString(TAG_BOOKMARKED);
                    isfave=user.getString(TAG_FAVE);
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
                    s_timings.setText(timings);
                    s_address.setText(address);
                    if(wifi=="1")
                    {
                        checkwifi.setImageResource(R.drawable.check);
                    }
                    else
                    {
                        checkwifi.setImageResource(R.drawable.uncheck);
                    }
                    if(parking=="1")
                    {
                        checkpark.setImageResource(R.drawable.check);
                    }
                    else
                    {
                        checkpark.setImageResource(R.drawable.uncheck);
                    }
                    if(ac=="1")
                    {
                        checkac.setImageResource(R.drawable.check);
                    }
                    else
                    {
                        checkac.setImageResource(R.drawable.uncheck);
                    }
                    if(kidsfriendly=="1")
                    {
                        checkkids.setImageResource(R.drawable.check);
                    }
                    else
                    {
                        checkkids.setImageResource(R.drawable.uncheck);
                    }
                    if(type=="0")
                    {
                        checkladies.setImageResource(R.drawable.check);
                    }
                    else
                    {
                        checkladies.setImageResource(R.drawable.uncheck);
                    }
                    s_contact.setText(contact);
                    s_payment.setText(payment);
                    s_rating.setText(rating);


                    if(Float.parseFloat(rating)<2.0 && Float.parseFloat(rating)>=1.0)
                    {
                        s_rating.setBackgroundResource(R.color.coloronetwo);
                        s_rating.setTextColor(Color.parseColor("#eeeeee"));
                    }
                    else if(Float.parseFloat(rating)<3.0 && Float.parseFloat(rating)>=2.0)
                    {
                        s_rating.setBackgroundResource(R.color.colortwothree);
                        s_rating.setTextColor(Color.parseColor("#eeeeee"));
                    }

                    else if(Float.parseFloat(rating)<4.0 && Float.parseFloat(rating)>=3.0)
                    {
                        s_rating.setBackgroundResource(R.color.colorthreefour);
                        s_rating.setTextColor(Color.parseColor("#eeeeee"));
                    }
                    else if(Float.parseFloat(rating)<=5.0 && Float.parseFloat(rating)>=4.0)
                    {
                        s_rating.setBackgroundResource(R.color.colorfourfive);
                        s_rating.setTextColor(Color.parseColor("#eeeeee"));
                    }
                    else {
                        s_rating.setBackgroundResource(R.color.colorna);
                        s_rating.setTextColor(Color.parseColor("#eeeeee"));
                    }
                    Picasso.with(SalonProfile.this)
                            .load(profilepic)
                            .placeholder(R.drawable.logo2) // optional
                            .into(simg);
                    soffers.setText(offers + " From "+ pricing);

                    switch(visited){
                        case "Yes":
                            svisited.setVisibility(View.GONE);
                            sunv.setVisibility(View.VISIBLE);
                            break;  //optional
                        case "No":
                            sunv.setVisibility(View.GONE);
                            svisited.setVisibility(View.VISIBLE);;
                            break;  //optional
                        default:
                            sunv.setVisibility(View.GONE);
                            svisited.setVisibility(View.VISIBLE);;

                    }
                    switch(isbm){
                        case "Yes":
                            sbookmarked.setVisibility(View.GONE);
                            sunbm.setVisibility(View.VISIBLE);
                            break;  //optional
                        case "No":
                            sunbm.setVisibility(View.GONE);
                            sbookmarked.setVisibility(View.VISIBLE);
                            break;  //optional
                        default:
                            sunbm.setVisibility(View.GONE);
                            sbookmarked.setVisibility(View.VISIBLE);

                    }
                    switch(isfave){
                        case "Yes":
                            sfave.setVisibility(View.GONE);
                            sunfav.setVisibility(View.VISIBLE);
                            break;  //optional
                        case "No":
                            sunfav.setVisibility(View.GONE);
                            sfave.setVisibility(View.VISIBLE);
                            break;  //optional
                        default:
                            sunfav.setVisibility(View.GONE);
                            sfave.setVisibility(View.VISIBLE);
                    }
                }
            });
        }


    }*/



    /*private class AddVisitedAsyncTask extends AsyncTask<String, String, String> {
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
    }*/

    /*private class DeleteVisitedAsyncTask extends AsyncTask<String, String, String> {
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
    }*/
    /*private class AddBMAsyncTask extends AsyncTask<String, String, String> {
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
*/
    /*private class DeleteBMAsyncTask extends AsyncTask<String, String, String> {
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
    }*/
    /*private class AddFaveAsyncTask extends AsyncTask<String, String, String> {
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
    }*/

    /*private class DeleteFaveAsyncTask extends AsyncTask<String, String, String> {
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
    }*/
    //sending notifs
    /*private class BNotifsAsyncTask extends AsyncTask<String, String, String> {
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
    }*/
    //sending notifs
    /*private class FNotifsAsyncTask extends AsyncTask<String, String, String> {
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
*/
    //sending notifs
    /*private class VNotifsAsyncTask extends AsyncTask<String, String, String> {
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
    }*/
}


