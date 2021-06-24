package com.restaurant.fooddeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class OtpVarification extends AppCompatActivity {
    EditText otpCheck;
    Button verfy;
    String otp;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_varification);
        verfy=findViewById(R.id.button);
        otpCheck=findViewById(R.id.editTextNumber);
        firestore=FirebaseFirestore.getInstance();

        firebaseAuth=FirebaseAuth.getInstance();
        otp=getIntent().getStringExtra("auth");
        verfy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String verification_code=otpCheck.getText().toString();
                if(!verification_code.isEmpty())
                {
                    PhoneAuthCredential credential= PhoneAuthProvider.getCredential(otp,verification_code);
                    signIn(credential);
                }
                else
                {
                    Toast.makeText(OtpVarification.this, "please enter OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signIn(PhoneAuthCredential credential)
    {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    DocumentReference documentReference= firestore.collection("user")
                            .document(firebaseAuth.getCurrentUser().getUid());
                    Map<String, Object> user = new HashMap<>();
                    user.put("CustomerId",firebaseAuth.getCurrentUser().getUid());
                    user.put("phoneNo",getIntent().getStringExtra("phone"));
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            //Toast.makeText(Signup.this, " number inserted", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("hemubhaiya","fail due to"+e);
                        }
                    });
                    Intent i=new Intent(OtpVarification.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(OtpVarification.this, "verification failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}