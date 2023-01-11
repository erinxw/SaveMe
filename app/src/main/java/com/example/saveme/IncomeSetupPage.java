package com.example.saveme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class IncomeSetupPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String incomeExpenseType;
    DatabaseReference DB = FirebaseDatabase.getInstance().getReferenceFromUrl("https://saveme-3a2cf-default-rtdb.firebaseio.com/");
    DatabaseReference spendingUserRef = DB.child("user").child(MainActivity.usernmae).child("MontlySetup");

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show Income Setup page
        setContentView(R.layout.activity_income_setup);

        final TextInputLayout incomeAmount = findViewById(R.id.enter_income_name);
        final TextInputLayout incomeName = findViewById(R.id.enter_income_amount);

        Spinner incomeType = findViewById(R.id.spinnerIncomeType);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.IncomeSetupType,R.layout.activity_income_setup);
        incomeType.setAdapter(adapter);
        incomeType.setOnItemSelectedListener(this);
        Button submit = findViewById(R.id.income_setup_button2);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String spendingName = incomeName.getEditText().getText().toString();
                String spendingAmount = incomeAmount.getEditText().getText().toString();

                if (!spendingAmount.isEmpty() && !spendingName.isEmpty() && !incomeExpenseType.isEmpty()) {

                    DB.child("user").child(MainActivity.usernmae).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String entryId = spendingUserRef.push().getKey();

                            Data IncomeData = new Data(Double.parseDouble(spendingAmount),incomeExpenseType,spendingName);

                            spendingUserRef.child(entryId).setValue(IncomeData);

                            Toast.makeText(IncomeSetupPage.this, "successfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(IncomeSetupPage.this, SpendingHistoryPage.class));

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

    };

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        incomeExpenseType = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(IncomeSetupPage.this, ProfilePage.class);
        startActivity(intent);
        finish();
    }
}