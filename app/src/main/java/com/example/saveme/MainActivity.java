package com.example.saveme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public static String usernmae;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show Launch Screen
        setContentView(R.layout.activity_main);

//        if (LoginPage.loggedIn){
//        startActivity(new Intent(MainActivity.this,HomePage.class));}
//        else {

            // When "Login" button is clicked, display Login page
            Button loginButton = (Button) findViewById(R.id.login_button);

            loginButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, LoginPage.class));
                }
            });

            // When "Register" button is clicked, display Register page
            Button registerButton = (Button) findViewById(R.id.register_button);

            registerButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, RegistrationPage.class));
                }
            });
        }

//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}