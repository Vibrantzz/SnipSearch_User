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
import android.widget.TextView;



import java.util.List;

/**
 * Created by Vibrantzz3 on 3/3/2018.
 */

public class FranchiseRecyclerViewAdapter extends RecyclerView.Adapter<FranchiseRecyclerViewAdapter.ViewHolder> {


    private Context mContext;
    private List <Franchise> hData;

    public FranchiseRecyclerViewAdapter(Context mContext, List<Franchise> hData) {
        this.mContext=mContext;
        this.hData=hData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.franchise_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {




    }

    @Override
    public int getItemCount() {
        return hData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView himage;
        TextView hcount;
        CardView cardview;

        public ViewHolder(View itemview)
        {   super(itemview);

            himage=(ImageView)itemview.findViewById(R.id.brandimg);

        }
    }

}
