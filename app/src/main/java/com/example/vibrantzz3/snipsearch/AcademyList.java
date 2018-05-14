package com.example.vibrantzz3.snipsearch;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AcademyList extends AppCompatActivity {
    List<Academy> hData;
    String id;
    AcademyListRecyclerViewAdapter myAdapter;
    RecyclerView myrv;
    private static final String url_academylist = "http://test.epoqueapparels.com/Salon_App/academyfragment.php";
    JSONObject jsonObject;
    private static final String TAG_PROFILE = "data";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_LOC = "location";
    private static final String TAG_RATE = "rating";
    private static final String TAG_PIC = "profilepic";
    private static final String TAG_RCOUNT = "rcount";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academylist);


        hData=new ArrayList<>();


        hData=new ArrayList<>();


        for(int i=0;i<5;i++){
            //JSONObject json_data = jArray.getJSONObject(i);
            //Parse the JSON response
            hData.add(new Academy(TAG_NAME, TAG_LOC, TAG_RATE, TAG_PIC, TAG_ID, "string", TAG_RCOUNT));
        }

        myrv=(RecyclerView) findViewById(R.id.al_recycler);
        myAdapter=new AcademyListRecyclerViewAdapter(AcademyList.this,hData);
        myrv.setLayoutManager(new LinearLayoutManager(AcademyList.this));
        myrv.setAdapter(myAdapter);

    }




}