package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
    private TextView mCountryNumber;
    private TextView mPhoneNumber;
    private Button mGenerateBtn;
    private ProgressBar mLoginProgressBar;
    private TextView LoginFeedBack;

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intialize();
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mGenerateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String country = mCountryNumber.getText().toString();
                String phone = mPhoneNumber.getText().toString();
                String phoneNumber="+"+country+""+phone;
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(country)) {
                    LoginFeedBack.setVisibility(View.VISIBLE);
                    mLoginProgressBar.setVisibility(View.VISIBLE);
                    mGenerateBtn.setEnabled(false);

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            phoneNumber,        // Phone number to verify
                            60,                 // Timeout duration
                            TimeUnit.SECONDS,   // Unit of timeout
                            LoginActivity.this,               // Activity (for callback binding)
                            mCallbacks);
                } else {
                    LoginFeedBack.setVisibility(View.VISIBLE);
                    LoginFeedBack.setText("Please fill all inputs");
                    mLoginProgressBar.setVisibility(View.VISIBLE);

                }

            }
        });
        mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                sendToOtp();
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mCurrentUser != null) {
            sendToHome();
        }
    }

    private void sendToHome() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    private void sendToOtp(){
        Intent intent = new Intent(LoginActivity.this, OtpActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void intialize() {
        mCountryNumber = findViewById(R.id.country_code_text);
        mPhoneNumber = findViewById(R.id.phone_number_text);
        mGenerateBtn = findViewById(R.id.generate_btn);
        mLoginProgressBar = findViewById(R.id.login_progress_bar);
        LoginFeedBack = findViewById(R.id.login_form_feedback);
    }
}
