package com.example.vibrantzz3.snipsearch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import java.util.List;

/**
 * Created by Vibrantzz3 on 3/3/2018.
 */

public class SpecRecyclerViewAdapter extends RecyclerView.Adapter<SpecRecyclerViewAdapter.ViewHolder> {


    private Context mContext;
    private List <Spec> hData;

    public SpecRecyclerViewAdapter(Context mContext, List<Spec> hData) {
        this.mContext=mContext;
        this.hData=hData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.spec_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final String id=hData.get(position).getID();




    }

    @Override
    public int getItemCount() {
        return hData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView himage;
        CardView cardview;

        public ViewHolder(View itemview)
        {   super(itemview);


        }
    }

}
