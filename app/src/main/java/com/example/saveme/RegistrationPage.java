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
        setContentView(R.layout.activity_registration_page);

        Spinner spinnerQuestions=findViewById(R.id.spinner_questions);

        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.questions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinnerQuestions.setAdapter(adapter);

        Button yourButton = (Button) findViewById(R.id.lets_start_button6);

        yourButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(RegistrationPage.this, ProfilePage.class));
            }
        });
    }
}