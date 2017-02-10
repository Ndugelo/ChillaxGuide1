package com.example.admin.chillaxguide;

/**
 * Created by admin on 2017/02/03.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class GoogleSignInActivity extends AppCompatActivity {

    private FirebaseAuth mfirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    public static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //<---Initialize Firebase Components--->
        mfirebaseAuth = FirebaseAuth.getInstance();


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    //<---Signed in--->
                    startActivity(new Intent(GoogleSignInActivity.this, RegisterActivity.class));
//                    Toast.makeText(MainActivity.this,"You're now signed in.Welcome to Pharmarcee!",Toast.LENGTH_SHORT).show();


                }else {
                    //<--Signed Out--->

                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
//                                    .setLogo(R.drawable.pharmacyt)
                                    .setTheme(R.style.AppTheme)
                                    .setIsSmartLockEnabled(false)
                                    .setProviders(Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                            new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                                            new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build()))
                                    .build(),
                            RC_SIGN_IN);
                }
            }

        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        mfirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mfirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent (this,MainActivity.class);
        startActivity(i);
        return;
    }
}