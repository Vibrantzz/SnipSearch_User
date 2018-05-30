package com.example.vibrantzz3.snipsearch;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Vibrantzz3 on 2/28/2018.
 */

public class UReviewRecyclerViewAdapter extends RecyclerView.Adapter<UReviewRecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<UReview> uData;

    public UReviewRecyclerViewAdapter(Context mContext, List<UReview> uData) {
        this.mContext=mContext;
        this.uData=uData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        view=mInflater.inflate(R.layout.ureview,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final String id=uData.get(position).getID();
        final String rid=uData.get(position).getRID();
        final String rating=uData.get(position).getRating();
        holder.uname.setText(uData.get(position).getUName());
        holder.sname.setText(uData.get(position).getName());
        holder.ulocation.setText(uData.get(position).getCity());
        holder.ureview.setText(uData.get(position).getReview());
        holder.urate.setText(uData.get(position).getRating());
        Picasso.get()
                .load(uData.get(position).getThumbnail())
                .placeholder(R.drawable.logo2) // optional
                .into(holder.uimage);
        holder.uname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,SalonProfile.class);
                intent.putExtra("id",id);
                mContext.startActivity(intent);

            }
        });

        if(Float.parseFloat(rating)<=1.0 && Float.parseFloat(rating)>0.0)
        {
            holder.ratebg.setImageResource(R.drawable.bgred);        }
        if(Float.parseFloat(rating)<=2.0 && Float.parseFloat(rating)>1.0)
        {
            holder.ratebg.setImageResource((R.drawable.bgorange));
        }
        else if(Float.parseFloat(rating)<=3.0 && Float.parseFloat(rating)>2.0)
        {
            holder.ratebg.setImageResource((R.drawable.bgyellow));
        }
        else if(Float.parseFloat(rating)<=4.0 && Float.parseFloat(rating)>3.0)
        {
            holder.ratebg.setImageResource((R.drawable.ratingellipse));
        }
        else if(Float.parseFloat(rating)<=5.0 && Float.parseFloat(rating)>4.0)
        {
            holder.ratebg.setImageResource((R.drawable.bgdgreen));
        }
        else
        {
            holder.urate.setText("N/A");
            holder.ratebg.setImageResource((R.drawable.bgred));
        }


    }

    @Override
    public int getItemCount() {
        return uData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView uname,ulocation,ureview,urate,sname;
        ImageView uimage,ratebg;

        CardView cardview;
        public MyViewHolder(View itemview)
        {   super(itemview);
        ratebg=(ImageView)itemview.findViewById(R.id.ratebg);
            sname=(TextView)itemview.findViewById(R.id.sname);
            uname=(TextView) itemview.findViewById(R.id.uname);
            ulocation=(TextView)itemview.findViewById(R.id.sloc);
            uimage=(ImageView)itemview.findViewById(R.id.uimg);
            ureview=(TextView)itemview.findViewById(R.id.txtreview);
            urate=(TextView)itemview.findViewById(R.id.txtrate);
            cardview=(CardView) itemview.findViewById(R.id.maincard);
        }
    }
}

