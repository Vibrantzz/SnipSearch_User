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


public class MenuFragment extends Fragment {
    View view;
    List<Gallery> GalleryData;
    String id;
    GalleryRecyclerViewAdapter myAdapter;
    RecyclerView myrv;
    private static final String url_menu = "http://test.epoqueapparels.com/Salon_App/salongallery.php";
    JSONObject jsonObject;
    private static final String TAG_PROFILE = "data";
    private static final String TAG_ID = "id";
    private static final String TAG_PHOTO = "photo";

    public MenuFragment() {


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_gallery,container,false);
        super.onCreate(savedInstanceState);



        GalleryData=new ArrayList<>();
        for(int i=0;i<5;i++){

            //Parse the JSON response
            GalleryData.add(new Gallery( "",""));
        }


        myrv=(RecyclerView) view.findViewById(R.id.gallery_recycler);
        myAdapter=new GalleryRecyclerViewAdapter(getContext(),GalleryData);
        myrv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        myrv.setAdapter(myAdapter);
        return view;

    }





}






