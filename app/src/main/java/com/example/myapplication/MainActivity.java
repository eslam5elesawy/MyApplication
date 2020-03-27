package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ArrayList<Massage> massage = new ArrayList<>();
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference childRef = rootRef.child("chat");

    RecycleViewAdaptor adaptor;

    ImageButton btn ;
    EditText ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser=mAuth.getCurrentUser();

        btn=findViewById(R.id.button);
        ed= findViewById(R.id.editText);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                massage.add(new Massage(massage.size(),ed.getText().toString()));
                ed.setText("");
                childRef.setValue(massage);
            }
        });



        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.getValue() !=null) {
                    massage.clear();
                    for (DataSnapshot dataSnap:dataSnapshot.getChildren()){
                        massage.add(dataSnap.getValue(Massage.class));
                    }
                    adaptor.notifyItemInserted(adaptor.massages.size());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        InRecycleView();
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(mCurrentUser ==null){
            sendToLogin();
        }
    }
    private void sendToLogin(){
        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    public void InRecycleView(){
        RecyclerView recyclerView = findViewById(R.id.RecycleView);
        adaptor = new RecycleViewAdaptor(massage);
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
