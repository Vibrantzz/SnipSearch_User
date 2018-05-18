package com.example.vibrantzz3.snipsearch;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpecList extends AppCompatActivity {
    List<Specifics> specData;
    String id;
    SpecListRecyclerViewAdapter myAdapter;
    RecyclerView myrv;
    private static final String url_speclist = "http://test.epoqueapparels.com/Salon_App/speclist.php";
    JSONObject jsonObject;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PROFILE = "data";
    private static final String TAG_ID = "id";
    private static final String TAG_SID = "sid";
    private static final String TAG_NAME = "name";
    private static final String TAG_LOC = "location";
    private static final String TAG_RATE = "rating";
    private static final String TAG_PIC = "logo";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spec_list);


        Intent intent = getIntent();
        id = intent.getExtras().getString("id");

        specData=new ArrayList<>();


        for(int i=0;i<5;i++){

            //Parse the JSON response
            specData.add(new Specifics( "","","","","",""));
        }


        myrv=(RecyclerView) findViewById(R.id.spl_recycler);
        myAdapter=new SpecListRecyclerViewAdapter(SpecList.this,specData);
        myrv.setLayoutManager(new LinearLayoutManager(SpecList.this));
        myrv.setAdapter(myAdapter);




    }




}