package com.example.vibrantzz3.snipsearch;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class BookAppointment extends AppCompatActivity {
Calendar startDate, endDate,defaultSelectedDate;
String chosendate,txtone,txttwo,txtthree,txtfour,txtreq,id,sid,chosenSlot;
    private static final String url_appt = "http://test.epoqueapparels.com/Salon_App/addappt.php";
    private static final String url_notifs = "http://test.epoqueapparels.com/Salon_App/sendSalonPush.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PROFILE = "data";
    private static final String TAG_USER = "uid";
    private static final String TAG_UID = "user_id";
    private static final String TAG_SID = "salon_id";
    private static final String TAG_DATE = "date";
    private static final String TAG_REQ = "request";
    private static final String TAG_SLOT = "slot";
    private int success;
    ImageView onslot1,offslot1,onslot2,offslot2,onslot3,offslot3,onslot4,offslot4,bookbtn;
    EditText desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookappointment);

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



