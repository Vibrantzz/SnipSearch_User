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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class VReviewFragment extends Fragment {
    View view;
    List<UReview> uData;
    VReviewRecyclerViewAdapter myAdapter;
    RecyclerView myrv;
    String id,rid,u_id,u_name;
    private static final String url_ureview = "http://test.epoqueapparels.com/Salon_App/vreviewrecent.php";
    private static final String url_like = "http://test.epoqueapparels.com/Salon_App/like.php";
    private static final String url_unlike = "http://test.epoqueapparels.com/Salon_App/unlike.php";

    JSONObject jsonObject;
    private static final String TAG_PROFILE = "data";
    private static final String TAG_ID = "id";
    private static final String TAG_UID = "uid";
    private static final String TAG_UNAME = "uname";

    private static final String TAG_RIDS = "review_id";
    private static final String TAG_SID = "sid";
    private static final String TAG_RATE = "rating";
    private static final String TAG_PIC = "profilepic";
    private static final String TAG_CITY = "location";
    private static final String TAG_NAME = "name";
    private static final String TAG_REVIEW = "review";
    private static final String TAG_RID = "rid";
    private static final String TAG_SUCCESS = "success";
    int success;
    Bundle bundle;
    public VReviewFragment() {


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.ureview_fragment,container,false);

        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        id = args.getString("id");


        uData=new ArrayList<>();
        new WelcomeAsyncTask().execute();

        bundle = new Bundle();
        bundle.putString("id",u_id);


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
                    url_ureview , "GET", httpParams);

            return null;
        }

        protected void onPostExecute(final String result) {



            try {
                JSONArray jArray = jsonObject.getJSONArray(TAG_PROFILE);

                for(int i=0;i<2;i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    //Parse the JSON response
                    uData.add(new UReview( json_data.getString(TAG_UID),json_data.getString(TAG_UNAME),json_data.getString(TAG_SID),json_data.getString(TAG_NAME),json_data.getString(TAG_CITY), json_data.getString(TAG_PIC),json_data.getString(TAG_REVIEW),json_data.getString(TAG_RATE),json_data.getString(TAG_RID)));

                }


                myrv=(RecyclerView) view.findViewById(R.id.review_recycler);
                myAdapter=new VReviewRecyclerViewAdapter(getContext(),uData);
                myrv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                myrv.setAdapter(myAdapter);



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }



}







