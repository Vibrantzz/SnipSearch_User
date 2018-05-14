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
 * Created by Vibrantzz3 on 3/3/2018.
 */

public class FranchiseListRecyclerViewAdapter extends RecyclerView.Adapter<FranchiseListRecyclerViewAdapter.ViewHolder> {


    private Context mContext;
    private List <FranList> hData;

    public FranchiseListRecyclerViewAdapter(Context mContext, List<FranList> hData) {
        this.mContext=mContext;
        this.hData=hData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.fran_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final String id= hData.get(position).getID();
        final String uid= hData.get(position).getUID();
        holder.hname.setText(hData.get(position).getName());
        holder.hlocation.setText(hData.get(position).getLocation());


        final String rating=hData.get(position).getRating();

        if(Float.parseFloat(rating)<2.0 && Float.parseFloat(rating)>=0.0)
        {
            holder.hrating.setBackgroundResource(R.color.coloronetwo);
            holder.hrating.setTextColor(Color.parseColor("#eeeeee"));
        }
        else if(Float.parseFloat(rating)<3.0 && Float.parseFloat(rating)>=2.0)
        {
            holder.hrating.setBackgroundResource(R.color.colortwothree);
            holder.hrating.setTextColor(Color.parseColor("#eeeeee"));
        }
        else if(Float.parseFloat(rating)<4.0 && Float.parseFloat(rating)>=3.0)
        {
            holder.hrating.setBackgroundResource(R.color.colorthreefour);
            holder.hrating.setTextColor(Color.parseColor("#eeeeee"));
        }
        else if(Float.parseFloat(rating)<=5.0 && Float.parseFloat(rating)>=4.0)
        {
            holder.hrating.setBackgroundResource(R.color.colorfourfive);
            holder.hrating.setTextColor(Color.parseColor("#eeeeee"));
        }
        else
        {
            holder.hrating.setText("0.0");
            holder.hrating.setBackgroundResource(R.color.colorna);
            holder.hrating.setTextColor(Color.parseColor("#eeeeee"));
        }

        holder.hrating.setText(hData.get(position).getRating());


        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });



    }

    @Override
    public int getItemCount() {
        return hData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hname,hlocation,hrating;
        ImageView himage;
        CardView cardview;

        public ViewHolder(View itemview)
        {   super(itemview);

            hname=(TextView) itemview.findViewById(R.id.franname);
            hlocation=(TextView)itemview.findViewById(R.id.franloc);
            hrating=(TextView) itemview.findViewById(R.id.franrate);
            himage=(ImageView)itemview.findViewById(R.id.franimg);
            cardview=(CardView) itemview.findViewById(R.id.franlist_card);
        }
    }

}
