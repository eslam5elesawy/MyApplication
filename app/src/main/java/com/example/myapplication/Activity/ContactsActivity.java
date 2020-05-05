package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication.Model.Contacts;
import com.example.myapplication.Adaptor.ContactsAdaptor;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {

    ImageButton backButton;
    ArrayList<Contacts> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        backButton = findViewById(R.id.Toolbar_back_button);
        contacts = new ArrayList<>();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContactsActivity.this, UsageActivity.class));
            }
        });

        String name = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
        String phone = ContactsContract.CommonDataKinds.Phone.NUMBER;

        //get contacts names and mobile phones from phone to display



        InContactsRecycleView();
    }

    public void InContactsRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.RV_Contact);
        ContactsAdaptor adaptor = new ContactsAdaptor(contacts);
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
