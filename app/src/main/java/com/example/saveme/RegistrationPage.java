package com.example.saveme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class RegistrationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show Registration page
        setContentView(R.layout.activity_registration_page);

        // Create spinner to display array of security question choices in the form of dropdown list for the user to select
        Spinner spinnerQuestions=findViewById(R.id.spinner_questions);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this,
                R.array.questions,
                R.layout.spinner_list);

        spinnerQuestions.setAdapter(adapter);

        // When the "Let's Start" button is clicked, the user information is stored in the database and the user can now access the application
        // User is directed to Profile page to set up their income and add their profile picture
        Button yourButton = (Button) findViewById(R.id.lets_start_button6);

        yourButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(RegistrationPage.this, ProfilePage.class));
            }
        });
    }
}