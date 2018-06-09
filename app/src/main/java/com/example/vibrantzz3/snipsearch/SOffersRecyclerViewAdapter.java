package com.example.vibrantzz3.snipsearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;


/**
 * Created by Vibrantzz3 on 2/28/2018.
 */

public class SOffersRecyclerViewAdapter extends RecyclerView.Adapter<SOffersRecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<SOffers> mData;

    public SOffersRecyclerViewAdapter(Context mContext, List<SOffers> mData) {
        this.mContext=mContext;
        this.mData=mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        view=mInflater.inflate(R.layout.offerlist,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.offer.setText(mData.get(position).getOffer());
        final String offerperiod=mData.get(position).getPeriod();
        if (offerperiod==null)
        {
            holder.period.setText(" period specified");
        }
        else
        {
            holder.period.setText(offerperiod);
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView offer, period;
        public MyViewHolder(View itemview)
        {   super(itemview);

            offer=(TextView)itemview.findViewById(R.id.txtoffer);
            period=(TextView)itemview.findViewById(R.id.txtperiod);
        }
    }
}

