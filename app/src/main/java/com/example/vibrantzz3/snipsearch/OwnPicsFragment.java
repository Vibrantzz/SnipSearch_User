package com.example.vibrantzz3.snipsearch;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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


public class OwnPicsFragment extends Fragment {
    View view;
    List<userPhotos> MenuData;
    String id;
    userPhotosRecyclerViewAdapter myAdapter;
    RecyclerView myrv;
    private static final String url_menu = "http://test.epoqueapparels.com/Salon/Salon_App/salongallery.php";
    JSONObject jsonObject;
    private static final String TAG_PROFILE = "data";
    private static final String TAG_ID = "id";
    private static final String TAG_PHOTO = "photo";

    private static final String TAG_UNAME = "uname";
    private static final String TAG_SNAME = "sname";
    private static final String TAG_USERID = "userid";
    private static final String TAG_SID = "salonid";
    public OwnPicsFragment() {


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ownpics, container, false);

        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        id = args.getString("id");


        MenuData = new ArrayList<>();

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
                    url_menu , "GET", httpParams);

            return null;
        }

        protected void onPostExecute(final String result) {



            try {
                JSONArray jArray = jsonObject.getJSONArray(TAG_PROFILE);

                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    //Parse the JSON response
                    MenuData.add(new userPhotos( json_data.getString(TAG_ID),json_data.getString(TAG_PHOTO),json_data.getString(TAG_UNAME),json_data.getString(TAG_SNAME),json_data.getString(TAG_USERID),json_data.getString(TAG_SID)));
                }


                myrv=(RecyclerView) view.findViewById(R.id.ownpics_recycler);
                myAdapter=new userPhotosRecyclerViewAdapter(getContext(),MenuData);
                myrv.setLayoutManager(new GridLayoutManager(getActivity(), 4,GridLayoutManager.VERTICAL, false));
                myrv.setAdapter(myAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


}







