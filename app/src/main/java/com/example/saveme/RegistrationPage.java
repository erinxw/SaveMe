package com.example.saveme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrationPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

//    Database References

    DatabaseReference DB = FirebaseDatabase.getInstance().getReferenceFromUrl("https://saveme-3a2cf-default-rtdb.firebaseio.com/");
    String setQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        final EditText setpassword = findViewById(R.id.editTextTextPassword5);
        final TextInputLayout textinputusername = findViewById(R.id.textInputLayout2);
        final TextInputLayout textinputSecurityAnswer = findViewById(R.id.enter_answer);
        final String SetQustion;

//        Spinner for security quuestion
        Spinner spinnerQuestions=findViewById(R.id.spinner_questions);

        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(this, R.array.questions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinnerQuestions.setAdapter(adapter);
        spinnerQuestions.setOnItemSelectedListener(this);


        Button yourButton = (Button) findViewById(R.id.lets_start_button6);

        yourButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String setUsername = textinputusername.getEditText().getText().toString().trim();
                String setPassword = setpassword.getText().toString();
                String setSecurityAnswer = textinputSecurityAnswer.getEditText().getText().toString().trim();

//              give error
                if(setSecurityAnswer.isEmpty())
                    textinputSecurityAnswer.setError("Please give your answer");

//              check all the fill answered or not
                else if(setPassword.isEmpty() || setUsername.isEmpty() || setQuestion.isEmpty())
                    Toast.makeText(RegistrationPage.this,"Please fill all the field", Toast.LENGTH_SHORT).show();

                else {
//                  check existing username
                    DB.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(setUsername)){
                                textinputusername.setError("Username already Takken");

                            }
                            else {
//                              sending data to the firebase
                                DB.child("user").child(setUsername).child("setPassword").setValue(setPassword);
                                DB.child("user").child(setUsername).child("setSecurityQuestion").setValue(setQuestion);
                                DB.child("user").child(setUsername).child("setSecurityAnswer").setValue(setSecurityAnswer);
                                DB.child("user").child(setUsername).child("totalSaving").setValue(0);
                                DB.child("user").child(setUsername).child("setTargetSaving").setValue(0);
                                DB.child("user").child(setUsername).child("analytic").child("Utility").setValue(0);
                                DB.child("user").child(setUsername).child("analytic").child("House Rent").setValue(0);
                                DB.child("user").child(setUsername).child("analytic").child("Food").setValue(0);
                                DB.child("user").child(setUsername).child("analytic").child("Shopping").setValue(0);
                                DB.child("user").child(setUsername).child("analytic").child("Housing").setValue(0);
                                DB.child("user").child(setUsername).child("analytic").child("Entertainment").setValue(0);
                                DB.child("user").child(setUsername).child("analytic").child("Medical").setValue(0);
                                DB.child("user").child(setUsername).child("analytic").child("Miscellaneous").setValue(0);
                                DB.child("user").child(setUsername).child("Montly Income").setValue(0);
                                DB.child("user").child(setUsername).child("recommendation").setValue(0);
//                              show massage successfull and go to login page
                                Toast.makeText(RegistrationPage.this,"registration successfull",Toast.LENGTH_SHORT).show();


                                startActivity(new Intent(RegistrationPage.this, LoginPage.class));
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
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        setQuestion = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(RegistrationPage.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}