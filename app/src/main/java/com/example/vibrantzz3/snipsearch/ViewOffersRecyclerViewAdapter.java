package com.example.vibrantzz3.snipsearch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.List;

/**
 * Created by Vibrantzz3 on 3/3/2018.
 */

public class ViewOffersRecyclerViewAdapter extends RecyclerView.Adapter<ViewOffersRecyclerViewAdapter.ViewHolder> {


    private Context mContext;
    private List <VOffers> hData;

    public ViewOffersRecyclerViewAdapter(Context mContext, List<VOffers> hData) {
        this.mContext=mContext;
        this.hData=hData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            holder.contentframe.setVisibility(View.VISIBLE);
            holder.expand.setVisibility(View.GONE);
            holder.close.setVisibility(View.VISIBLE);
            }
        });


        holder.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.contentframe.setVisibility(View.GONE);
                holder.expand.setVisibility(View.VISIBLE);
                holder.close.setVisibility(View.GONE);
            }
        });



    }

    @Override
    public int getItemCount() {
        return hData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hname,hlocation,hrating,hrcount;
        ImageView expand,close;
        FrameLayout contentframe;
        CardView cardview;

        public ViewHolder(View itemview)
        {   super(itemview);

            expand=(ImageView)itemview.findViewById(R.id.expandbtn);
            close=(ImageView)itemview.findViewById(R.id.closedbtn);
            contentframe=(FrameLayout)itemview.findViewById(R.id.infoframe);

        }
    }

}
