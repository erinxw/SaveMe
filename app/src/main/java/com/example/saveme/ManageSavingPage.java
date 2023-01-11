package com.example.saveme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saveme.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ManageSavingPage extends AppCompatActivity {

    DatabaseReference DBTotalSaving = FirebaseDatabase.getInstance().getReferenceFromUrl("https://saveme-3a2cf-default-rtdb.firebaseio.com/")
            .child("user").child(MainActivity.usernmae).child("totalSaving");
    DatabaseReference DBTargetSaving = FirebaseDatabase.getInstance().getReferenceFromUrl("https://saveme-3a2cf-default-rtdb.firebaseio.com/")
            .child("user").child(MainActivity.usernmae).child("setTargetSaving");

    RecyclerView recyclerView;
    Context mContext;
    manageSavingAdapter adapter;
    List<Data> list = new ArrayList<>();


    public double totalSaving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show Manage Saving page
        setContentView(R.layout.activity_manage_saving_page);

        TextView totaSpendingView = findViewById(R.id.textView19);
        // When the Add Income button is clicked, display Add Income page
        Button addIncomeButton = (Button) findViewById(R.id.add_income_button);
        // When the Add Income button is clicked, display floating page
        Button addTargetSaving = findViewById(R.id.income_setup_button4);

        // Use BottomNavigationView to create a bottom navigation bar that consists of logout, spending history, home, manage saving and profile buttons
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.saving);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    // If the logout button is clicked, display Launch Screen
                    case R.id.logout:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    // If the Spending History button is clicked, display Spending History page
                    case R.id.history:
                        startActivity(new Intent(getApplicationContext(),SpendingHistoryPage.class));
                        overridePendingTransition(0,0);
                        return true;
                    // If the Home button is clicked, display Home page
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),HomePage.class));
                        overridePendingTransition(0,0);
                        return true;
                    // If the Manage Saving button is clicked, display Manage Saving page
                    case R.id.saving:
                        return true;
                    // If the Profile button is clicked, display Profile page
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),ProfilePage.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        DBTotalSaving.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                totalSaving = snapshot.getValue(double.class);
                totaSpendingView.setText("RM "+ totalSaving);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        addTargetSaving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                call the pop window
                addItemTarget();
            }
        });

        setRecycleView();
        addIncomeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(ManageSavingPage.this, AddIncomePage.class));
            }
        });
    }



    private void setRecycleView() {
        recyclerView = findViewById(R.id.viewTarget);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true));
        adapter = new manageSavingAdapter(ManageSavingPage.this,getData());
        recyclerView.setAdapter(adapter);
    }
    private List<Data> getData() {
        List<Data> testing = new ArrayList<>();
        testing.add(new Data("ayam",56));
        testing.add(new Data("ayam",56));
        testing.add(new Data("ayam",56));
        testing.add(new Data("ayam",56));



        return testing;
    }

    private void addItemTarget() {
        AlertDialog.Builder theDialog = new AlertDialog.Builder(this);


        LayoutInflater inflater = getLayoutInflater();
        View addView = inflater.inflate(R.layout.add_new_target,null);
        theDialog.setView(addView);
        AlertDialog dialog = theDialog.create();


        EditText targetAmount = findViewById(R.id.targetAmount);
        EditText targetpercentage = findViewById(R.id.percentage);
        Button saveIt = findViewById(R.id.saveChange);
        TextView viewCurrent = findViewById(R.id.currentPercentage);
//        saveIt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (targetAmount.getText().toString().isEmpty())
//                    targetAmount.setError("Please input the amount");
//                else if (Double.parseDouble(targetpercentage.getText().toString()) > 100){
//                    targetpercentage.setError("The percentage is exceeding");
////
//                }
//                startActivity(new Intent(ManageSavingPage.this, AddIncomePage.class));
//                else {
//
//                    DBTargetSaving.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            double currentPercent = (double) snapshot.getValue();
//                            viewCurrent.setText(Double.toString(currentPercent));
//                            String entryId = DBTargetSaving.push().getKey();
//                            DBTargetSaving.child(entryId).child("targetAmount").setValue(targetAmount.getText().toString().trim());
//                            DBTargetSaving.child(entryId).child("targetpercentage").setValue(targetpercentage.getText().toString().trim());
//
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//                }
//            }
//        });
//        dialog.show();
    }
}