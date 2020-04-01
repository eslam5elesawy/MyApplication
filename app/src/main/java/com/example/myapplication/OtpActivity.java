package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class OtpActivity extends AppCompatActivity {
    private String authCreate;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    private EditText codeOtp;
    private Button verifyBtn;
    private TextView otpFeedBack;
    private ProgressBar otpProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        intializing();
        authCreate = getIntent().getStringExtra("AuthCreate");
        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otp = codeOtp.getText().toString();
                if (otp.isEmpty()) {
                    otpFeedBack.setVisibility(View.VISIBLE);
                    otpFeedBack.setText("fill the form and try again");
                } else {
                    otpProgressBar.setVisibility(View.VISIBLE);
                    verifyBtn.setEnabled(false);
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(authCreate, otp);
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });


    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(OtpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information


                            sendtoHome();
                            // ...
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                otpFeedBack.setVisibility(View.VISIBLE);
                                otpFeedBack.setText("Error: " + task.getException().getMessage());
                            }
                        }
                        otpProgressBar.setVisibility(View.INVISIBLE);
                        verifyBtn.setEnabled(true);
                    }
                });
    }

    private void sendtoHome() {
        Intent intent = new Intent(OtpActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void intializing() {
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        codeOtp = findViewById(R.id.otp_text_view);
        verifyBtn = findViewById(R.id.verify_btn);
        otpFeedBack = findViewById(R.id.otp_form_feedback);
        otpProgressBar = findViewById(R.id.otp_progress_bar);
    }
}
