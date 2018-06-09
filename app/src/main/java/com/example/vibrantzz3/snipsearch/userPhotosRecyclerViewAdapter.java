package com.example.vibrantzz3.snipsearch;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Vibrantzz3 on 2/28/2018.
 */

public class userPhotosRecyclerViewAdapter extends RecyclerView.Adapter<userPhotosRecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<userPhotos> uData;


    public userPhotosRecyclerViewAdapter(Context mContext, List<userPhotos> uData) {
        this.mContext=mContext;
        this.uData=uData;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        view=mInflater.inflate(R.layout.uphotos_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Picasso.get()
                .load(uData.get(position).getImage())
                .into(holder.img);

        final String uname=uData.get(position).getUName();
        final String sname=uData.get(position).getSName();
        final String uid=uData.get(position).getUID();
        final String sid=uData.get(position).getSID();
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,ViewImageActivity.class);
                intent.putExtra("uname",uname);
                intent.putExtra("sname",sname);
                intent.putExtra("uid",uid);
                intent.putExtra("sid",sid);
                intent.putExtra("Image",uData.get(position).getImage());
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return uData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        ImageView img;

        public MyViewHolder(View itemview)
        {   super(itemview);

            img=(ImageView)itemview.findViewById(R.id.img);


        }
    }
}

