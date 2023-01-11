package com.example.saveme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPageIfCorrectPassword extends AppCompatActivity {

    DatabaseReference DB = FirebaseDatabase.getInstance().getReferenceFromUrl("https://saveme-3a2cf-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show Login page if the password is entered correctly
        setContentView(R.layout.activity_login_page_if_correct_password);

        TextView QuestionView = findViewById(R.id.textView8);
        LoginPage.counter=0;


        // When the "Let's Start" button is clicked, the user is signed in and they are directed to the Home page
        Button startButton = (Button) findViewById(R.id.lets_start_button);
        DatabaseReference dataRef = DB.child("user");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String userQuestion = snapshot.child(MainActivity.usernmae).child("setSecurityQuestion").getValue(String.class);
                QuestionView.setText(userQuestion);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        dataRef.addValueEventListener(valueEventListener);

        startButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(LoginPageIfCorrectPassword.this, HomePage.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LoginPageIfCorrectPassword.this, LoginPage.class);
        startActivity(intent);
        finish();
    }
}