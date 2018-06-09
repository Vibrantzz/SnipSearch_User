package com.example.vibrantzz3.snipsearch;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.List;

/**
 * Created by Vibrantzz3 on 2/15/2018.
 */

public class OffersRecyclerViewAdapter extends RecyclerView.Adapter<OffersRecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List <Offers> sData;

    public OffersRecyclerViewAdapter(Context mContext, List<Offers> sData) {
        this.mContext=mContext;
        this.sData=sData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        view=mInflater.inflate(R.layout.card_offers,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // final String id=sData.get(position).getID();
        // final String uid=sData.get(position).getUID();
        //holder.sname.setText(sData.get(position).getName());
        //final String rating=sData.get(position).getRating();

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,ViewOffers.class);

                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return sData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView sname,slocation,srating;
        ImageView simage;
        CardView cardview;
        public MyViewHolder(View itemview)
        {   super(itemview);

            cardview=(CardView)itemview.findViewById(R.id.maincard);
        }
    }
}
