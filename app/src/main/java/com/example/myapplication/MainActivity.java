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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private DatabaseReference rootRef;
    private DatabaseReference childRef;

    private RecycleViewAdaptor adaptor;

    private ImageButton btn ;
    private EditText ed;

    private ArrayList<Massage> massage = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        rootRef = FirebaseDatabase.getInstance().getReference();
<<<<<<< HEAD
        childRef = rootRef.child("NonUser");
        mAuth.signInWithEmailAndPassword("eslam5@gmail.com","12341234")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mCurrentUser = mAuth.getCurrentUser();
                            childRef = rootRef.child("U"+mAuth.getUid());
                            Toast.makeText(MainActivity.this, "user"+mAuth.getUid(), Toast.LENGTH_SHORT).show();
                        }else{
                            childRef = rootRef.child("NonUser");
                            Toast.makeText(MainActivity.this, "user"+mAuth.getUid(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

=======
//        childRef  = rootRef.child(mCurrentUser.getUid());
>>>>>>> 795bca391b9b0a6312c77a94de3a1dc324d777de

        btn=findViewById(R.id.button);
        ed= findViewById(R.id.editText);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                massage.add(new Massage(massage.size(),ed.getText().toString(),mCurrentUser.getUid()));
                ed.setText("");
                childRef.setValue(massage);
            }
        });

        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.getValue() != "") {
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
        if(mCurrentUser == null){
           // sendToLogin();
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
