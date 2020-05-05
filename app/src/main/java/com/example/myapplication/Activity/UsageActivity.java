package com.example.myapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.Adaptor.UsageAdaptor;
import com.example.myapplication.Model.UserData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UsageActivity extends AppCompatActivity implements UsageAdaptor.onUserClickListener {

    TextView UserName;
    FloatingActionButton FAB;

    private ArrayList<UserData> Usage_Data;

    private FirebaseAuth mAuth;
    private DatabaseReference RootRef;
    private DatabaseReference ChildRef;
    private UsageAdaptor adaptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage);

        UserName = findViewById(R.id.TV_Toolbar_User);
        FAB = findViewById(R.id.Usage_Contacts_Button);

        Usage_Data = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword("eslam5@gmail.com","12341234");

        RootRef = FirebaseDatabase.getInstance().getReference();
        ChildRef = RootRef.child(mAuth.getCurrentUser().getUid());
//dIVGZUyhFvgJ1O1Nd0utFdOuA592

        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UsageActivity.this, ContactsActivity.class));
            }
        });

        ChildRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue() != "") {
                    Usage_Data.clear();
                    for (DataSnapshot dataSnap : dataSnapshot.getChildren()) {
                        //get all users chat history ID
                        Usage_Data.add(new UserData(dataSnap.getKey()));

                    }
                    adaptor.notifyItemInserted(0);
                    //the fuckin user name doesn't display what the fuck is that
                    //add click on item to get user id that will take with


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });


     InUsageRecycleView();
    }

    public void InUsageRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.Usage_RecycleView);
        adaptor = new UsageAdaptor(Usage_Data);
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onUserClick(int position) {
        Intent intent = new Intent(this, ChatActivity.class);
        //intent.putExtra("UserId",Usage_Data.get(position).getUserID());
        //intent.putExtra("UserName",Usage_Data.get(position).getName());
        //intent.putExtra("UserPhoto",Usage_Data.get(position).getPhoto());
         startActivity(intent);
        finish();
    }
}
