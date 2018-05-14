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
    private static final String url_fran = "http://test.epoqueapparels.com/Salon_App/franchisefragment.php";
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
       // final Bundle args = getArguments();
        //id = args.getString("id");

        franData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {

            //Parse the JSON response
            franData.add(new Franchise( TAG_ID,TAG_PIC,TAG_COUNT + " Branches"));
        }


        myrv = (RecyclerView) view.findViewById(R.id.franchise_recycler);
        FranchiseRecyclerViewAdapter myAdapter = new FranchiseRecyclerViewAdapter(getContext(), franData);
        myrv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        myrv.setAdapter(myAdapter);
        return view;

    }
}

