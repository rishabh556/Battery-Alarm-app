package com.example.battery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgetpass extends AppCompatActivity {
    private Button Sendemail;
    private EditText Useremail;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass);

        Sendemail= findViewById(R.id.Forgbtn);
        Useremail= findViewById(R.id.fEmail);
        progressBar = findViewById(R.id.progressBar);

        mAuth=FirebaseAuth.getInstance();
         Sendemail.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String email =Useremail.getText().toString();
                 if(TextUtils.isEmpty(email)){

                     Toast.makeText(getApplicationContext(),"Please enter your correct email",Toast.LENGTH_LONG).show();
                 }
                 else{

                     mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                         @Override
                         public void onComplete(@NonNull Task<Void> task) {
                             if(task.isSuccessful()){
                                 progressBar.setVisibility(View.VISIBLE);


                                 Toast.makeText(getApplicationContext(),"Please visit your email to reset password ",Toast.LENGTH_SHORT).show();
                                  startActivity(new Intent(Forgetpass.this, Login.class));
                             }
                             else{

                                 String message = task.getException().getMessage();
                                 Toast.makeText(getApplicationContext(),"Error:"+message,Toast.LENGTH_SHORT).show();
                                 progressBar.setVisibility(View.GONE);

                             }
                         }
                     });
                 }
             }
         });

    }
}
