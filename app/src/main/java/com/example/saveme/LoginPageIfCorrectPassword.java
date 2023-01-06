package com.example.saveme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginPageIfCorrectPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show Login page if the password is entered correctly
        setContentView(R.layout.activity_login_page_if_correct_password);

        // When the "Let's Start" button is clicked, the user is signed in and they are directed to the Home page
        Button startButton = (Button) findViewById(R.id.lets_start_button);

        startButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(LoginPageIfCorrectPassword.this, HomePage.class));
            }
        });
    }
}