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
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class BookAppointment extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
Calendar startDate, endDate,defaultSelectedDate;
String chosendate,txtone,txttwo,txtthree,txtfour,txtreq,id,sid,chosenSlot,fname,upic;
    private static final String url_appt = "http://test.epoqueapparels.com/Salon/Salon_App/addappt.php";
    private static final String url_notifs = "http://test.epoqueapparels.com/Salon/Salon_App/sendSalonPush.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PROFILE = "data";
    private static final String TAG_USER = "uid";
    private static final String TAG_UID = "user_id";
    private static final String TAG_SID = "salon_id";
    private static final String TAG_DATE = "date";
    private static final String TAG_REQ = "request";
    private static final String TAG_SLOT = "slot";
    private int success;
    ImageView onslot1,offslot1,onslot2,offslot2,onslot3,offslot3,onslot4,offslot4,bookbtn,img,touser;
    EditText desc;
    TextView uname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookappointment);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Beauty");
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

                Intent intent = new Intent(BookAppointment.this , User.class);
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

        desc=(EditText)findViewById(R.id.desctxt);
        bookbtn=(ImageView)findViewById(R.id.bookbtn);
        onslot1=(ImageView)findViewById(R.id.slot1on);
        offslot1=(ImageView)findViewById(R.id.slot1off);
        onslot2=(ImageView)findViewById(R.id.slot2on);
        offslot2=(ImageView)findViewById(R.id.slot2off);
        onslot3=(ImageView)findViewById(R.id.slot3on);
        offslot3=(ImageView)findViewById(R.id.slot3off);
        onslot4=(ImageView)findViewById(R.id.slot4on);
        offslot4=(ImageView)findViewById(R.id.slot4off);

        txtone="9am-12pm";
        txttwo="12pm-3pm";
        txtthree="3pm-6pm";
        txtfour="6pm-9pm";
        startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH,-1);


/* ends after 1 month from now */
        endDate = Calendar.getInstance();

        endDate.add(Calendar.MONTH,1);
defaultSelectedDate = Calendar.getInstance();

    HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
            .range(startDate, endDate)
            .defaultSelectedDate(defaultSelectedDate)
    .datesNumberOnScreen(7)
            .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                chosendate= DateFormat.format("yyyy/MM/dd", date).toString();
            }
        });

        Intent intent = getIntent();
        id = intent.getExtras().getString("uid");
        sid = intent.getExtras().getString("id");

        offslot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offslot1.setVisibility(INVISIBLE);
                onslot1.setVisibility(VISIBLE);
                chosenSlot=txtone;
            }});

        onslot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onslot1.setVisibility(INVISIBLE);
                offslot1.setVisibility(VISIBLE);
                chosenSlot="";
            }});

        offslot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offslot2.setVisibility(INVISIBLE);
                onslot2.setVisibility(VISIBLE);
                chosenSlot=txttwo;
            }});

        onslot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onslot2.setVisibility(INVISIBLE);
                offslot2.setVisibility(VISIBLE);
                chosenSlot="";
            }});

        offslot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offslot3.setVisibility(INVISIBLE);
                onslot3.setVisibility(VISIBLE);
                chosenSlot=txtthree;
            }});

        onslot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onslot3.setVisibility(INVISIBLE);
                offslot3.setVisibility(VISIBLE);
                chosenSlot="";
            }});
        offslot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offslot4.setVisibility(INVISIBLE);
                onslot4.setVisibility(VISIBLE);
                chosenSlot=txtfour;
            }});

        onslot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onslot4.setVisibility(INVISIBLE);
                offslot4.setVisibility(VISIBLE);
                chosenSlot="";
            }});



        bookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMovie();

            }
        });



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
            Intent intent = new Intent(BookAppointment.this , ViewAppointments.class);
            intent.putExtra("id",id);
            startActivity(intent);
        } else if (id == R.id.nav_bm) {

            Intent intent = new Intent(BookAppointment.this , ViewBookmarksActivity.class);
            intent.putExtra("id",id);
            startActivity(intent);

        } else if (id == R.id.nav_fave) {
            Intent intent = new Intent(BookAppointment.this , ViewFavouritesActivity.class);
            intent.putExtra("id",id);
            startActivity(intent);


        } else if (id == R.id.nav_notif) {
            Intent intent = new Intent(BookAppointment.this , ViewOffers.class);
            startActivity(intent);

        }else if (id == R.id.nav_settings) {
            Intent intent = new Intent(BookAppointment.this , Settings.class);
            startActivity(intent);

        }else if (id == R.id.nav_signout) {
            SharedPreferences preferences =getSharedPreferences("loginData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(BookAppointment.this , MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_feedback) {

        } else if (id == R.id.nav_playstore) {

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addMovie() {
        txtreq=desc.getText().toString();
        new CallAsyncTask().execute();
        new CNotifsAsyncTask().execute();
    }

    private class CallAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<>();
            //Populating request parameters

            httpParams.put(TAG_UID, id);
            httpParams.put(TAG_SID, sid);
            httpParams.put(TAG_SLOT, chosenSlot);
            httpParams.put(TAG_DATE, chosendate);
            httpParams.put(TAG_REQ, txtreq);

            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    url_appt, "POST", httpParams);
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
                        finish();

                    }
                }
            });
        }
    }
    private class CNotifsAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<>();
            //Populating request parameters

            httpParams.put("title", "Appointment");
            httpParams.put("id", sid);
            httpParams.put("message", "You have a new appointment request!");


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

                    }
                }
            });
        }
    }
}



