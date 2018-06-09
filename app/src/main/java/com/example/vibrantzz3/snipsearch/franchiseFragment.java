package com.example.vibrantzz3.snipsearch;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class franchiseFragment extends Fragment {
    View view;
    List<Franchise> franData;
    RecyclerViewAdapter myAdapter;
    RecyclerView myrv;
    private static final String url_fran = "http://test.epoqueapparels.com/Salon/Salon_App/franchisefragment.php";
    JSONObject jsonObject;
    private static final String TAG_PROFILE = "data";
    private static final String TAG_ID = "id";
    private static final String TAG_PIC = "logo";
    private static final String TAG_COUNT = "sfcount";
    String id;

    public franchiseFragment() {


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_franchise, container, false);

        super.onCreate(savedInstanceState);
       //final Bundle args = getArguments();
       //id = args.getString("id");

        franData=new ArrayList<>();
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
                    url_fran, "GET", null);

            return null;
        }

        protected void onPostExecute(final String result) {


            try {
                JSONArray jArray = jsonObject.getJSONArray(TAG_PROFILE);

                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    //Parse the JSON response
                    franData.add(new Franchise( json_data.getString(TAG_ID), json_data.getString(TAG_PIC), json_data.getString(TAG_COUNT)));
                }
                myrv = (RecyclerView) view.findViewById(R.id.franchise_recycler);
                FranchiseRecyclerViewAdapter myAdapter = new FranchiseRecyclerViewAdapter(getContext(), franData);
                myrv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                myrv.setAdapter(myAdapter);



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }}










