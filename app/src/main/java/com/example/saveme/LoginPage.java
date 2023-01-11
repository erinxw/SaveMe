package com.example.saveme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {

    public static boolean loggedIn;
    DatabaseReference DB = FirebaseDatabase.getInstance().getReferenceFromUrl("https://saveme-3a2cf-default-rtdb.firebaseio.com/");
    public static int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        Button yourButton = (Button) findViewById(R.id.submit_button);
        final TextInputLayout username = findViewById(R.id.textInputLayout2);
        final EditText passwordET = findViewById(R.id.editTextTextPassword3);
        counter=0; ///counter for the error password

        yourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String userlogin = username.getEditText().getText().toString().trim();
                final String password = passwordET.getText().toString();
                MainActivity.usernmae = userlogin;

                if (userlogin.isEmpty())
                    username.setError("Plaese fill your username");

                else if (password.isEmpty()){
                    Toast.makeText(LoginPage.this,"No password",Toast.LENGTH_SHORT).show();
                }

                else{
                    DB.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

//                          check the existance of username
                            if (snapshot.hasChild(userlogin)) {
                                username.setError(null);

//                              check correct password
                                final String correctPassword = snapshot.child(userlogin).child("setPassword").getValue(String.class);

                                if (correctPassword.equals(password)){
                                    Toast.makeText(LoginPage.this,"Login success",Toast.LENGTH_SHORT).show();

                                    loggedIn=true;

//                              Go to main page
                                startActivity(new Intent(LoginPage.this, HomePage.class));}


                                else{
                                    Toast.makeText(LoginPage.this,"Incorrect password",Toast.LENGTH_SHORT).show();
                                    counter++;
                                    if (counter>=3){
//                                        go to answer the question section
                                        Intent i = new Intent(LoginPage.this,LoginPageIfCorrectPassword.class);
                                        startActivity(i);
                                    }

                                }

                            }

                            else {
                                username.setError("Invalid Username");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LoginPage.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}