package com.example.vibrantzz3.snipsearch;

import android.content.Intent;
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

public class HairList extends AppCompatActivity {
    List<Hair> hData;
    String id;
    HairListRecyclerViewAdapter myAdapter;
    RecyclerView myrv;
    private static final String url_hairlist = "http://test.epoqueapparels.com/Salon_App/hairfragment.php";
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
        setContentView(R.layout.activity_hairlist);


        hData=new ArrayList<>();

        hData=new ArrayList<>();


        for(int i=0;i<5;i++){
            //JSONObject json_data = jArray.getJSONObject(i);
            //Parse the JSON response
            hData.add(new Hair(TAG_NAME, TAG_LOC, TAG_RATE, TAG_PIC, TAG_ID, "string", TAG_RCOUNT));
        }

        myrv=(RecyclerView) findViewById(R.id.hl_recycler);
        myAdapter=new HairListRecyclerViewAdapter(HairList.this,hData);
        myrv.setLayoutManager(new LinearLayoutManager(HairList.this));
        myrv.setAdapter(myAdapter);

    }




}