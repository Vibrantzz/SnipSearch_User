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


public class SOffersFragment extends Fragment {
    View view;
    List<SOffers> oData;
    String id;
    SOffersRecyclerViewAdapter myAdapter;
    RecyclerView myrv;
    private static final String url_offers = "http://test.epoqueapparels.com/Salon/Salon_App/offersfragment.php";
    JSONObject jsonObject;
    private static final String TAG_PROFILE = "data";
    private static final String TAG_ID = "id";
    private static final String TAG_OFFER = "offer";
    private static final String TAG_PERIOD = "period";

    public SOffersFragment() {


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_offer, container, false);

        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        id = args.getString("id");
        //id="2";

        oData = new ArrayList<>();

        new WelcomeAsyncTask().execute();
        return view;

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
            jsonObject = httpJsonParser.makeHttpRequest(
                    url_offers , "GET", httpParams);

            return null;
        }

        protected void onPostExecute(final String result) {



            try {
                JSONArray jArray = jsonObject.getJSONArray(TAG_PROFILE);

                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    //Parse the JSON response
                    oData.add(new SOffers( json_data.getString(TAG_OFFER)," from " + json_data.getString(TAG_PERIOD)));
                }


                myrv=(RecyclerView) view.findViewById(R.id.offer_recycler);
                myAdapter=new SOffersRecyclerViewAdapter(getContext(),oData);
                myrv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                myrv.setAdapter(myAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


}







