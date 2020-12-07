package com.example.battery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Feedback extends AppCompatActivity {

    EditText e1;
    Button fbutton;
    ProgressBar progressBar;
   // FirebaseAuth fAuth2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        e1= findViewById(R.id.editText2);
        fbutton= findViewById(R.id.fbtn);

      //  fAuth2 = FirebaseAuth.getInstance();
        fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fback = e1.getText().toString().trim();

                if(TextUtils.isEmpty(fback)){
                    e1.setError("Email is Required.");
                    return;
                }



sendMail(fback);
     e1.setText("");

     Toast.makeText(getApplicationContext(),"Response Mail Sent",Toast.LENGTH_SHORT).show();
            }
        });




    }

    private void sendMail(String message) {
        Intent mEmailIntent = new Intent(Intent.ACTION_SEND);
        mEmailIntent.setData(Uri.parse("mailto:"));
        mEmailIntent.setType("text/plane");
        String aEmailList[] = {"r.tiwari6396@gmail.com"};
        mEmailIntent.putExtra(Intent.EXTRA_EMAIL,aEmailList);
        mEmailIntent.putExtra(Intent.EXTRA_SUBJECT,"Battery App Feedback");
        mEmailIntent.putExtra(Intent.EXTRA_TEXT,message);


        try {
            //send mail
            startActivity(Intent.createChooser(mEmailIntent,"Choose Email"));
        }
        catch (Exception e){
            Toast.makeText(Feedback.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
}
