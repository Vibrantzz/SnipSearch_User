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

public class SpecRecyclerViewAdapter extends RecyclerView.Adapter<SpecRecyclerViewAdapter.ViewHolder> {


    private Context mContext;
    private List <Spec> hData;

    public SpecRecyclerViewAdapter(Context mContext, List<Spec> hData) {
        this.mContext=mContext;
        this.hData=hData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.spec_list,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final String id=hData.get(position).getID();
        holder.hname.setText(hData.get(position).getName());
        Picasso.get()
                .load(hData.get(position).getThumbnail())
                .into(holder.himage);
        holder.himage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,SpecList.class);

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

        ImageView himage;
        TextView hname;
        CardView cardview;

        public ViewHolder(View itemview)
        {   super(itemview);
           himage=(ImageView)itemview.findViewById(R.id.specimg);
            hname=(TextView)itemview.findViewById(R.id.specname);

        }
    }

}
