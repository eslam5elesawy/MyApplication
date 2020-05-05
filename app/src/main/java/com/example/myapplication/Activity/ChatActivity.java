package com.example.myapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Model.Massage;
import com.example.myapplication.Adaptor.MassageAdaptor;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private DatabaseReference rootRef;
    private DatabaseReference childRef;
    private MassageAdaptor adaptor;

    private ImageButton BTN_Send;
    private EditText ED_Massage;
    private ImageView User_photo;
    private TextView User_name;

    private ArrayList<Massage> Massages_Data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        rootRef = FirebaseDatabase.getInstance().getReference();
        childRef = rootRef.child(mCurrentUser.getUid()).child("dIVGZUyhFvgJ1O1Nd0utFdOuA592");

        BTN_Send =findViewById(R.id.button);
        ED_Massage = findViewById(R.id.editText);
        User_name = findViewById(R.id.TV_Toolbar);
        User_photo = findViewById(R.id.IV_Toolbar);

        User_name.setText(mCurrentUser.getDisplayName());


        BTN_Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ED_Massage.getText().toString().equals("")) {

                    Massages_Data.add(new Massage(new Date().toString(), ED_Massage.getText().toString(), mAuth.getUid()));
                    ED_Massage.setText("");
                    childRef.setValue(Massages_Data);
                }
            }
        });

        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue() != "") {
                    Massages_Data.clear();
                    for (DataSnapshot dataSnap : dataSnapshot.getChildren()) {
                        Massages_Data.add(dataSnap.getValue(Massage.class));
                    }
                    adaptor.notifyItemInserted(adaptor.massages.size());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        InChatRecycleView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mCurrentUser == null) {
            sendToLogin();
        }
    }

    private void sendToLogin() {

        Intent intent = new Intent(ChatActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void InChatRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.Chat_RecycleView);
        adaptor = new MassageAdaptor(Massages_Data);
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
