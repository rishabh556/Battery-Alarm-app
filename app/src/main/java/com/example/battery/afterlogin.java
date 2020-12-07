package com.example.battery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class afterlogin extends AppCompatActivity {

    CardView Alarm, Health, Report, Tips, Logout, Feedback ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterlogin);

        Alarm = findViewById(R.id.alarm);
        Health = findViewById(R.id.health);
        Report = findViewById(R.id.report);
        Tips = findViewById(R.id.tips);
        Logout= findViewById(R.id.logOut);
        Feedback= findViewById(R.id.feedback);


        Alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Set Battery Alarm",Toast.LENGTH_LONG).show();
                startActivity(new Intent(afterlogin.this, Alarm.class));
            }
        });
        Health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Your Phone's performance is here",Toast.LENGTH_LONG).show();

                startActivity(new Intent(getApplicationContext(), HealthPeformance.class));
            }
        });

        Report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Overall Report",Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(), OReport.class));
            }
        });

        Tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Tips For Better Battery Life",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Tips.class));

            }
        });







        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                FirebaseAuth.getInstance().signOut();//logout
//                startActivity(new Intent(getApplicationContext(),Login.class));
//                finish();



                final AlertDialog.Builder alert = new AlertDialog.Builder(afterlogin.this);
                View mView = getLayoutInflater().inflate(R.layout.logout_alertdialog, null);
                Button yes= mView.findViewById(R.id.Leftybtn);
                Button no= mView.findViewById(R.id.Rightnbtn);
                alert.setView(mView);

                final AlertDialog alertDialog= alert.create();
                alertDialog.setCanceledOnTouchOutside(false);

                alertDialog.show();
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        GoogleSignIn.getClient(afterlogin.this,new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()).signOut().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startActivity(new Intent(getApplicationContext(),Login.class));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(afterlogin.this, "Signout Failed.", Toast.LENGTH_SHORT).show();
                            }
                        });
// Yaha gadbad ho sakti hai ...ddekh lena neeche ki 3 line ok
                        FirebaseAuth.getInstance().signOut();//logout
                        finish();
                        startActivity(new Intent(getApplicationContext(),Login.class));

                        Toast.makeText(getApplicationContext(),"Logout Successful", Toast.LENGTH_SHORT).show();

                    }
                });

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });




            }

        });



        Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Enter feedback",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Feedback.class));

            }
        });


    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        final AlertDialog.Builder alert = new AlertDialog.Builder(afterlogin.this);
        View mView = getLayoutInflater().inflate(R.layout.exit_alertdialog, null);
        Button yes=mView.findViewById(R.id.Exitybtn);
        Button no=mView.findViewById(R.id.Exitnbtn);
        alert.setView(mView);

        final AlertDialog alertDialog= alert.create();
        alertDialog.setCanceledOnTouchOutside(false);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(Intent.ACTION_MAIN);
                a.addCategory(Intent.CATEGORY_HOME);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(a);  //find some other method so that login state remains logged in
                alertDialog.dismiss();

            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();


    }
}
