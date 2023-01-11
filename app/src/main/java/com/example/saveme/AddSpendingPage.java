package com.example.saveme;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class AddSpendingPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    DatabaseReference DB = FirebaseDatabase.getInstance().getReferenceFromUrl("https://saveme-3a2cf-default-rtdb.firebaseio.com/");
    DatabaseReference spendingUserRef = DB.child("user").child(MainActivity.usernmae).child("spending");
    String spendingCategory;
    DatabaseReference DBTotalSaving = FirebaseDatabase.getInstance().getReferenceFromUrl("https://saveme-3a2cf-default-rtdb.firebaseio.com/")
            .child("user").child(MainActivity.usernmae).child("totalSaving");

    public double totalSaving;

    EditText date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Show Add Spending page
        setContentView(R.layout.activity_add_spending_page);
        final TextInputLayout enterSpendingName = findViewById(R.id.enter_income_name);
        final TextInputLayout enterSpendingAmount = findViewById(R.id.enter_amount);


        // Create spinner to display array of spending category choices in the form of dropdown list for the user to select
        Spinner spinnerSpendingCategory=findViewById(R.id.spending_category_spinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this,
                R.array.spending_category,
                R.layout.spinner_list);

        spinnerSpendingCategory.setAdapter(adapter);
        spinnerSpendingCategory.setOnItemSelectedListener(this);

        date = (EditText)findViewById(R.id.enter_date);

        date.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);

                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    date.setText(current);
                    date.setSelection(sel < current.length() ? sel : current.length());



                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });

        final Button submitSpending = findViewById(R.id.add_button);

        submitSpending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String dateSpending = date.getText().toString();
                String spendingName = enterSpendingName.getEditText().getText().toString();
                String spendingAmount = enterSpendingAmount.getEditText().getText().toString();
                EditText make = enterSpendingAmount.getEditText();



                if (!dateSpending.isEmpty() && !spendingAmount.isEmpty() && !spendingName.isEmpty() && !spendingCategory.isEmpty()){


                    DB.child("user").child(MainActivity.usernmae).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String entryId = spendingUserRef.push().getKey();


                            Data spendingData = new Data(Double.parseDouble(make.getText().toString()),spendingCategory,dateSpending,spendingName);

                            spendingUserRef.child(entryId).setValue(spendingData);


//                            Read the current total saving and
//                            key in the topUp amount into the total saving part
                            DBTotalSaving.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    totalSaving = snapshot.getValue(double.class);
                                    DBTotalSaving.setValue(totalSaving-Double.parseDouble(make.getText().toString()));


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            DB.child("user").child(MainActivity.usernmae).child("analytic").child(spendingCategory).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                    get total spend on this category
                                    double totalspend = snapshot.getValue(double.class);
                                    DB.child("user").child(MainActivity.usernmae).child("analytic").child(spendingCategory).setValue(totalspend+Double.parseDouble(make.getText().toString()));


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });



                            Toast.makeText(AddSpendingPage.this,"successfull",Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    startActivity(new Intent(AddSpendingPage.this,HomePage.class));

                }

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spendingCategory  = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView){}

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddSpendingPage.this, HomePage.class);
        startActivity(intent);
        finish();
    }


}