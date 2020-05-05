package com.example.myapplication.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Contacts;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ContactsAdaptor  extends RecyclerView.Adapter<ContactsAdaptor.ViewHolder>{

    ArrayList<Contacts> contacts;

    public ContactsAdaptor(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleveiw_contacts,parent,false);
        ContactsAdaptor.ViewHolder holder = new ContactsAdaptor.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //data set
        holder.contact_name.setText(contacts.get(position).getName());
        holder.contact_phone.setText(contacts.get(position).getPhone());

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView contact_name;
        TextView contact_phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            contact_name = itemView.findViewById(R.id.RV_TV_Contacts_Name);
            contact_phone = itemView.findViewById(R.id.RV_TV_Contacts_Phone);

        }
    }
}
