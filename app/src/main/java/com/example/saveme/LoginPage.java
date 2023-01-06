package com.example.saveme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show Login page
        setContentView(R.layout.activity_login_page);

        // When "Submit" button is clicked, display Login page that allows user to answer their security question
        // after entering the correct username and password
        Button yourButton = (Button) findViewById(R.id.submit_button);

        yourButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(LoginPage.this, LoginPageIfCorrectPassword.class));
            }
        });
    }
}