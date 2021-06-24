package com.restaurant.fooddeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class authentication extends AppCompatActivity {
    CountryCodePicker codePicker;
    EditText phone;
    TextView processText;
    Button getotp;
    FirebaseAuth auth;
    ProgressBar progressBar;
    String profilePhn;
    // FirebaseAuth auth;
    FirebaseFirestore firestore;
    LinearLayout layout;
    // Integer check;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;

//    View decorview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        phone = findViewById(R.id.editTextPhone);
        codePicker = findViewById(R.id.ccp);
        processText = findViewById(R.id.textprocess);
        getotp = findViewById(R.id.phone_get_otp);
        progressBar = findViewById(R.id.progressBar2);

        auth = FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        layout=findViewById(R.id.hor1);
//        phone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(phone.getText().toString().length() == 10)
//                {
//                    InputMethodManager imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(v.getWindowToken(),0);
//                }
//            }
//        });

//<---------------------------------------------------hide the toolbar -------------------------------------------------------------------->
//        toolbar=findViewById(R.id.toolbarCustom);
//        setSupportActionBar(toolbar);
//        StatusBarUtil.setTransparent(this);
//        View decorView = getWindow().getDecorView();
// Hide the status bar.
//        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.
//        ActionBar actionBar = getActionBar();
//        actionBar.hide();

//        decorview=getWindow().getDecorView();
//        decorview.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
//            @Override
//            public void onSystemUiVisibilityChange(int visibility) {
//                if(visibility==0)
//                {
//                    decorview.setSystemUiVisibility(hidesystembars());
//                }
//            }
//        });
//<------------------------------------------------------end hiding--------------------------------------------------------------------------->


        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processText.setVisibility(View.VISIBLE);
                processText.setText("sending otp");
                progressBar.setVisibility(View.VISIBLE);
                layout.setVisibility(View.VISIBLE);
                //  processText.setTextColor(Color.DKGRAY);
                String country_code = codePicker.getSelectedCountryCode();//.toString();
                String phone_num = phone.getText().toString();
                String phone_number = "+" + country_code + "" + phone_num;
                if (!country_code.isEmpty() && phone_num.length() == 10) {

                    PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth).setPhoneNumber(phone_number).setTimeout(60L, TimeUnit.SECONDS)
                            .setActivity(authentication.this)
                            .setCallbacks(mCallBacks).build();
                    PhoneAuthProvider.verifyPhoneNumber(options);
//                    InputMethodManager imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(v.getWindowToken(),0);
                } else {
                    phone.setError("invalid phone number");
                }

            }
        });
        mCallBacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                signIn(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                processText.setText(e.getMessage());
                processText.setTextColor(Color.RED);
                processText.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                processText.setVisibility(View.VISIBLE);
                processText.setText("code has been sent");
                new Handler().postDelayed(new Runnable() {
                    @Override

                    public void run() {
                        Intent otpIntent = new Intent(authentication.this, OtpVarification.class);
                        otpIntent.putExtra("auth", s);
                        otpIntent.putExtra("phone",phone.getText().toString());
                        startActivity(otpIntent);
                    }
                }, 5000);

            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            Intent intent =new Intent(authentication.this,MainActivity.class);
            finish();
            startActivity(intent);
            finish();
           /*
            userId = auth.getCurrentUser().getUid();
            FirebaseFirestore fsotre;
            fsotre = FirebaseFirestore.getInstance();
            DocumentReference documentReference = fsotre.collection("user").document(userId);
            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    if (value.exists()) {
                        Intent i = new Intent(Signup.this, show_activity.class);
                        startActivity(i);
                    }
                    else
                    {
                        Intent i=new Intent(Signup.this,Profile.class);
                        startActivity(i);
                    }
                }
            });

            */
        }
    }

    private void signIn (PhoneAuthCredential credential)
    {
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
//                        DocumentReference documentReference= firestore.collection("user")
//                                .document(auth.getCurrentUser().getUid());
//                        Map<String, Object> user = new HashMap<>();
//                        user.put("CustomerId",auth.getCurrentUser().getUid());
//                        user.put("phoneNo",phone.getText().toString());
//                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                //Toast.makeText(Signup.this, " number inserted", Toast.LENGTH_SHORT).show();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Log.d("hemubhaiya","fail due to"+e);
//                            }
//                        });
                    Intent i = new Intent(authentication.this, MainActivity.class);
                    // profilePhn=phone.getText().toString();
                    //i.putExtra("phoneNo",profilePhn);
                    startActivity(i);
                    finish();
                } else {
                    Log.d("himanshu",task.getException().getMessage());
                    //processText.setText(task.getException().getMessage());
                    //processText.setVisibility(View.VISIBLE);
                }
            }
        });
    }




//    <---------------------------------------------------------------hiding toolbars------------------------------------------------------------->
//public void onWindowFocusChanged(boolean hasfocus)
//{
//    super.onWindowFocusChanged(hasfocus);
//    if(hasfocus)
//    {
//        decorview.setSystemUiVisibility(hidesystembars());
//    }
//}
//    private int hidesystembars()
//    {
//        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                |View.SYSTEM_UI_FLAG_FULLSCREEN
//                |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
//    }
//    <------------------------------------------------------------------ends here------------------------------------------------------------------->


}