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
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

/**
 * Created by Vibrantzz3 on 2/15/2018.
 */

public class AcademyRecyclerViewAdapter extends RecyclerView.Adapter<AcademyRecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List <Academy> sData;

    public AcademyRecyclerViewAdapter(Context mContext, List<Academy> sData) {
        this.mContext=mContext;
        this.sData=sData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        view=mInflater.inflate(R.layout.card_academy,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,SalonProfile.class);

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

            sname=(TextView) itemview.findViewById(R.id.sname);
            slocation=(TextView)itemview.findViewById(R.id.saddress);
            srating=(TextView) itemview.findViewById(R.id.txtrating);
            cardview=(CardView) itemview.findViewById(R.id.cardview_id);
        }
    }
}
