package com.example.battery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class OthLogin extends AppCompatActivity {
    public static final int GOOGLE_SIGN_IN_CODE = 10005;

    SignInButton signIn;

    GoogleSignInOptions gso;

    GoogleSignInClient signInClient;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oth_login);

        signIn = findViewById(R.id.gbutton);
        firebaseAuth = FirebaseAuth.getInstance();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("741974207664-c9rs753t2ls7veon4gnoars7sodv8kvf.apps.googleusercontent.com")
                .requestEmail()
                .build();
        signInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);

        if (signInAccount != null || firebaseAuth.getCurrentUser() != null) {

            startActivity(new Intent(this, afterlogin.class));

        }

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sign = signInClient.getSignInIntent();
                startActivityForResult(sign, GOOGLE_SIGN_IN_CODE);

            }

        });
    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){

            super.onActivityResult(requestCode, resultCode, data);


            if (requestCode == GOOGLE_SIGN_IN_CODE) {

                Task<GoogleSignInAccount> signInTask = GoogleSignIn.getSignedInAccountFromIntent(data);


                try {

                    GoogleSignInAccount signInAcc = signInTask.getResult(ApiException.class);

                    AuthCredential authCredential = GoogleAuthProvider.getCredential(signInAcc.getIdToken(), null);



firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        Toast.makeText(getApplicationContext(), "Your Google Account is Connected to Our Application.", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), afterlogin.class));
    }


}).addOnFailureListener(new OnFailureListener() {
    @Override
    public void onFailure(@NonNull Exception e) {

    }
});


                    Toast.makeText(getApplicationContext(), "Your Google Account is Connected to Our Application.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), afterlogin.class));
                } catch (ApiException e) {

                    e.printStackTrace();

                }


            }

        }

    }

