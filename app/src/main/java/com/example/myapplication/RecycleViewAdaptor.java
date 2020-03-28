package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecycleViewAdaptor extends RecyclerView.Adapter<RecycleViewAdaptor.ViewHolder> {

    public ArrayList<Massage> massages;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();;

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

        if (!mAuth.getCurrentUser().getUid().equals(massages.get(position).getUserID())){

            holder.layout.setVisibility(View.VISIBLE);
            holder.massage.setText(massages.get(position).getMassage());
            holder.Time.setText(massages.get(position).formatTime());

        }else{

            holder.User_layout.setVisibility(View.VISIBLE);
            holder.User_massage.setText(massages.get(position).getMassage());
            holder.User_Time.setText(massages.get(position).formatTime());

        }
    }

    @Override
    public int getItemCount() {
        return massages.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView massage;
        TextView Time;
        LinearLayout layout;

        TextView User_massage;
        TextView User_Time;
        LinearLayout User_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            massage = itemView.findViewById(R.id.RV_Massage);
            Time = itemView.findViewById(R.id.RV_Time);
            layout = itemView.findViewById(R.id.RV_Layout);

            User_massage = itemView.findViewById(R.id.RV_Massage_User);
            User_Time = itemView.findViewById(R.id.RV_Time_User);
            User_layout = itemView.findViewById(R.id.RV_Layout_User);

        }
    }

}
