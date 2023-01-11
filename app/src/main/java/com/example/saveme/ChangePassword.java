package com.example.saveme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangePassword extends AppCompatActivity {

    DatabaseReference DB = FirebaseDatabase.getInstance().getReferenceFromUrl("https://saveme-3a2cf-default-rtdb.firebaseio.com/").child("user");
    String securityAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show Change Password page
        setContentView(R.layout.activity_change_password);

        final TextInputLayout enterUsername = findViewById(R.id.textInputLayout2);
        TextInputLayout enteranswer = findViewById(R.id.makene);
        final EditText enterOldPassword = findViewById(R.id.editTextTextPassword4);
        final EditText enterNewPassword = findViewById(R.id.editTextTextPassword3);
        final TextView viewSecurityQuestion = findViewById(R.id.textView2);
        final Button changePassword = findViewById(R.id.change_password_button);


        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}