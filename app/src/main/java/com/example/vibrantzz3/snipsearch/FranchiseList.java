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

public class FranchiseList extends AppCompatActivity {
    List<FranList> franData;
    String id;
    FranchiseListRecyclerViewAdapter myAdapter;
    RecyclerView myrv;
 private static final String url_franlist = "http://test.epoqueapparels.com/Salon_App/franlist.php";
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
        setContentView(R.layout.activity_franchiselist);


        Intent intent = getIntent();
        id = intent.getExtras().getString("id");

        franData=new ArrayList<>();


        for(int i=0;i<5;i++){

            //Parse the JSON response
            franData.add(new FranList( "","","","","",""));
        }


        myrv=(RecyclerView) findViewById(R.id.fl_recycler);
        myAdapter=new FranchiseListRecyclerViewAdapter(FranchiseList.this,franData);
        myrv.setLayoutManager(new LinearLayoutManager(FranchiseList.this));
        myrv.setAdapter(myAdapter);



    }




}