package com.example.vibrantzz3.snipsearch;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import java.util.List;

/**
 * Created by Vibrantzz3 on 2/28/2018.
 */

public class GalleryRecyclerViewAdapter extends RecyclerView.Adapter<GalleryRecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<Gallery> gData;

    public GalleryRecyclerViewAdapter(Context mContext, List<Gallery> gData) {
        this.mContext=mContext;
        this.gData=gData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        view=mInflater.inflate(R.layout.gallery_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return gData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView gimg;
        CardView cardview;
        public MyViewHolder(View itemview)
        {   super(itemview);


        }
    }
}

