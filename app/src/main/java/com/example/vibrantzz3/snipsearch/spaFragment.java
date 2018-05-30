package com.example.vibrantzz3.snipsearch;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vibrantzz3 on 2/5/2018.
 */

public class spaFragment extends Fragment {
    View view;
    List <Salon> hairData;
    RecyclerViewAdapter myAdapter;
    RecyclerView myrv;
    private static final String url_spa = "http://test.epoqueapparels.com/Salon_App/spaFragment.php";
    JSONObject jsonObject;
    private static final String TAG_PROFILE = "data";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_LOCATION = "location";
    private static final String TAG_RATING = "rating";
    private static final String TAG_PIC = "profilepic";
    private static final String TAG_RCOUNT = "rcount";
    String id;

    public spaFragment() {


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.spa_fragment,container,false);

        super.onCreate(savedInstanceState);
        hairData=new ArrayList<>();

        new WelcomeAsyncTask().execute();
        return view;

    }



    private class WelcomeAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<>();
            jsonObject = httpJsonParser.makeHttpRequest(
                    url_spa, "GET", null);

            return null;
        }

        protected void onPostExecute(final String result) {


            try {
                JSONArray jArray = jsonObject.getJSONArray(TAG_PROFILE);

                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    //Parse the JSON response
                    hairData.add(new Salon( json_data.getString(TAG_NAME), json_data.getString(TAG_LOCATION), json_data.getString(TAG_RATING), json_data.getString(TAG_PIC),json_data.getString(TAG_ID),"",json_data.getString(TAG_RCOUNT)));
                }
                myrv = (RecyclerView) view.findViewById(R.id.spa_recycler);
                RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(getContext(), hairData);
                myrv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                myrv.setAdapter(myAdapter);



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }}


