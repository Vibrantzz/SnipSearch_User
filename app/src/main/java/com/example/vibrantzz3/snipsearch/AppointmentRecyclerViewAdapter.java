package com.example.vibrantzz3.snipsearch;

import android.content.Context;
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

public class AppointmentRecyclerViewAdapter extends RecyclerView.Adapter<AppointmentRecyclerViewAdapter.ViewHolder> {


    private Context mContext;
    private List <Appointments> hData;

    public AppointmentRecyclerViewAdapter(Context mContext, List<Appointments> hData) {
        this.mContext=mContext;
        this.hData=hData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
    holder.name.setText(hData.get(position).getName());
    holder.location.setText(hData.get(position).getLocation());
        Picasso.get()
                .load(hData.get(position).getThumbnail())
                .placeholder(R.drawable.logo2) // optional
                .into(holder.himage);
        holder.txt.setText(hData.get(position).getRequest()+hData.get(position).getUDate()+hData.get(position).getUTime());

    }

    @Override
    public int getItemCount() {
        return hData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,location,txt;
        ImageView himage;
        CardView cardview;

        public ViewHolder(View itemview)
        {   super(itemview);
            name=(TextView)itemview.findViewById(R.id.txtname);
            location=(TextView)itemview.findViewById(R.id.txtloc);
            txt=(TextView)itemview.findViewById(R.id.txtapp);
            himage=(ImageView)itemview.findViewById(R.id.simg);
            cardview=(CardView) itemview.findViewById(R.id.maincard);



        }
    }

}
