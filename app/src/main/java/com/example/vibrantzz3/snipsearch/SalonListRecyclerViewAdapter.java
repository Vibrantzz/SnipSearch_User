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


import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Vibrantzz3 on 3/3/2018.
 */

public class SalonListRecyclerViewAdapter extends RecyclerView.Adapter<SalonListRecyclerViewAdapter.ViewHolder> {


    private Context mContext;
    private List <Salon> hData;

    public SalonListRecyclerViewAdapter(Context mContext, List<Salon> hData) {
        this.mContext=mContext;
        this.hData=hData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.salon_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.sname.setText(hData.get(position).getName());
        holder.saddress.setText(hData.get(position).getLocation());
        holder.txtrating.setText(hData.get(position).getRating());
        final String rating=hData.get(position).getRating();

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
            holder.txtrating.setText("N/A");
            holder.ratebg.setImageResource((R.drawable.bgred));
        }
        Picasso.get()
                .load(hData.get(position).getThumbnail())
                .into(holder.sthumbnail);
        final String id=hData.get(position).getID();
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,SalonProfile.class);
                intent.putExtra("id",id);
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return hData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sname, saddress,txtrating;
        ImageView sthumbnail, ratebg;
        CardView cardview;

        public ViewHolder(View itemview)
        {   super(itemview);
            sname=(TextView)itemview.findViewById(R.id.sname);
            saddress=(TextView)itemview.findViewById(R.id.saddress);
            txtrating=(TextView)itemview.findViewById(R.id.txtrating);
            sthumbnail=(ImageView)itemview.findViewById(R.id.sicon);
            ratebg=(ImageView)itemview.findViewById(R.id.ratebg);
            cardview=(CardView)itemview.findViewById(R.id.hairlist_card);
        }
    }

}
