package com.example.myapplication.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.UserData;
import com.example.myapplication.R;

import java.util.ArrayList;

public class UsageAdaptor extends RecyclerView.Adapter<UsageAdaptor.ViewHolder> {

    public ArrayList<UserData> Usage;

    public UsageAdaptor(ArrayList<UserData> Usage) {
        this.Usage = Usage;
    }

    @NonNull
    @Override
    public UsageAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleveiw_usage,parent,false);
        UsageAdaptor.ViewHolder holder= new UsageAdaptor.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UsageAdaptor.ViewHolder holder, int position) {

        holder.UserName.setText(Usage.get(position).getUserID());
       // holder.UserPhoto.setImageURI(Usage.get(position).getPhoto());

    }

    @Override
    public int getItemCount() {
        return Usage.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView UserName;
        ImageView UserPhoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            UserName = itemView.findViewById(R.id.RV_TV_Usage);
            UserPhoto = itemView.findViewById(R.id.RV_IV_Usage);
        }
    }

    public interface onUserClickListener {
        void onUserClick(int position);
    }
}
