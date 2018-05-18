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

public class UReviewFragment extends Fragment {
    View view;
    List<UReview> rData;
    UReviewRecyclerViewAdapter myAdapter;
    RecyclerView myrv;
    private static final String url_spa = "http://test.epoqueapparels.com/Salon_App/hairFragment.php";
    JSONObject jsonObject;
    private static final String TAG_PROFILE = "data";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_LOCATION = "location";
    private static final String TAG_RATING = "rating";
    private static final String TAG_PIC = "profilepic";
    private static final String TAG_RCOUNT = "rcount";
    String id;

    public UReviewFragment() {


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ureview_fragment, container, false);

        super.onCreate(savedInstanceState);
        //final Bundle args = getArguments();
        //id = args.getString("id");

        rData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {

            //Parse the JSON response
            rData.add(new UReview( TAG_NAME,TAG_LOCATION,TAG_RATING,TAG_PIC,TAG_ID,id,TAG_RCOUNT));
        }


        myrv = (RecyclerView) view.findViewById(R.id.review_recycler);
        UReviewRecyclerViewAdapter myAdapter = new UReviewRecyclerViewAdapter(getContext(), rData);
        myrv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        myrv.setAdapter(myAdapter);
        return view;

    }
}


