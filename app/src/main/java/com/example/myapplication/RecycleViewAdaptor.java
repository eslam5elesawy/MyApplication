package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecycleViewAdaptor extends RecyclerView.Adapter<RecycleViewAdaptor.ViewHolder> {

    public ArrayList<Massage> massages;

    RecycleViewAdaptor(ArrayList<Massage> massages) {
        this.massages = massages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_activity,parent,false);
        ViewHolder holder= new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.massage.setText(massages.get(position).getMassage());
        holder.Time.setText(massages.get(position).formatTime());

    }

    @Override
    public int getItemCount() {
        return massages.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView User;
        TextView massage;
        TextView Time;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            massage = itemView.findViewById(R.id.RV_Massage);
            Time = itemView.findViewById(R.id.RV_Time);

        }
    }

}
