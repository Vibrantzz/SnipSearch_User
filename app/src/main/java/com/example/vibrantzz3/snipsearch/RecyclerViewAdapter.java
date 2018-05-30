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


import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Vibrantzz3 on 2/15/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List <Salon> sData;

    public RecyclerViewAdapter(Context mContext, List<Salon> sData) {
        this.mContext=mContext;
        this.sData=sData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        view=mInflater.inflate(R.layout.card_hair,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.sname.setText(sData.get(position).getName());
        holder.slocation.setText(sData.get(position).getLocation());
        holder.srating.setText(sData.get(position).getRating());
        final String rating=sData.get(position).getRating();
        final String id=sData.get(position).getID();
        if(Float.parseFloat(rating)<=1.0 && Float.parseFloat(rating)>0.0)
        {
            holder.ratebgrnd.setImageResource(R.drawable.bgred);        }
        if(Float.parseFloat(rating)<=2.0 && Float.parseFloat(rating)>1.0)
        {
            holder.ratebgrnd.setImageResource((R.drawable.bgorange));
        }
        else if(Float.parseFloat(rating)<=3.0 && Float.parseFloat(rating)>2.0)
        {
            holder.ratebgrnd.setImageResource((R.drawable.bgyellow));
        }
        else if(Float.parseFloat(rating)<=4.0 && Float.parseFloat(rating)>3.0)
        {
            holder.ratebgrnd.setImageResource((R.drawable.ratingellipse));
        }
        else if(Float.parseFloat(rating)<=5.0 && Float.parseFloat(rating)>4.0)
        {
            holder.ratebgrnd.setImageResource((R.drawable.bgdgreen));
        }
        else
        {
            holder.srating.setText("N/A");
            holder.ratebgrnd.setImageResource((R.drawable.bgred));
        }
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,SalonProfile.class);
                intent.putExtra("id",id);
                mContext.startActivity(intent);

            }
        });

        Picasso.get()
                .load(sData.get(position).getThumbnail())
                .into(holder.thumbnail);




    }

    @Override
    public int getItemCount() {
        return sData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView sname,slocation,srating;
        ImageView thumbnail, ratebgrnd;
        CardView cardview;
        public MyViewHolder(View itemview)
        {   super(itemview);

            sname=(TextView)itemview.findViewById(R.id.sname);
            slocation=(TextView)itemview.findViewById(R.id.saddress);
            srating=(TextView)itemview.findViewById(R.id.txtrating);
            thumbnail=(ImageView)itemview.findViewById(R.id.s_icon);
            ratebgrnd=(ImageView)itemview.findViewById(R.id.ratebg);
            cardview=(CardView) itemview.findViewById(R.id.cardview_id);
        }
    }
}
