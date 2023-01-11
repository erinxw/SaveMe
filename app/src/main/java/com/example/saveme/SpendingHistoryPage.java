package com.example.saveme;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SpendingHistoryPage extends AppCompatActivity {

    DatabaseReference DB = FirebaseDatabase.getInstance().getReferenceFromUrl("https://saveme-3a2cf-default-rtdb.firebaseio.com/");
    DatabaseReference referSpend = DB.child("user").child(MainActivity.usernmae).child("spending");
    DatabaseReference referTopUp = DB.child("user").child(MainActivity.usernmae).child("TopUp");
    List<Data> firstList = new ArrayList<>();
    List<Data> secondList = new ArrayList<>();
    List<Data> combinedList = new ArrayList<>();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference firstRef =  DB.child("user").child(MainActivity.usernmae).child("spending");
    DatabaseReference secondRef =  DB.child("user").child(MainActivity.usernmae).child("TopUp");

    private HistoryAdapter historyAdapter;
    private List<Data> spendingData;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show Spending History page
        setContentView(R.layout.activity_spending_history_page);

        recyclerView = findViewById(R.id.reycle_view);
        spendingData = new ArrayList<>();
        setRecycleView();
        getInformation();




        // Use BottomNavigationView to create a bottom navigation bar that consists of logout, spending history, home, manage saving and profile buttons
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

//        set the current page on the bottom navigationView
        bottomNavigationView.setSelectedItemId(R.id.history);


//        NAVIGATION VIEW MENU
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
                        return true;
                    // If the Home button is clicked, display Home page
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),HomePage.class));
                        overridePendingTransition(0,0);
                        return true;
                    // If the Manage Saving button is clicked, display Manage Saving page
                    case R.id.saving:
                        startActivity(new Intent(getApplicationContext(),ManageSavingPage.class));
                        overridePendingTransition(0,0);
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
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SpendingHistoryPage.this, HomePage.class);
        startActivity(intent);
        finish();
    }

    private void setRecycleView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        historyAdapter = new HistoryAdapter(SpendingHistoryPage.this,spendingData);
        recyclerView.setAdapter(historyAdapter);
    }

    private List<Data> getData() {
        List<Data> testing = new ArrayList<>();
//        testing.add(new Data("make","hasfsgdhgfhmjd",45,"fsfvbvdnjspdsfnbhbvnjnjv hfjnknfjdbjnf"));


        return testing;
    }

    private void getInformation(){
        referTopUp.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                spendingData.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Data data = dataSnapshot.getValue(Data.class);
                    spendingData.add(data);
                }
                //                sorting
                Collections.sort(spendingData, new Comparator<Data>() {
                    @Override
                    public int compare(Data data, Data t1) {
                        int comparison = t1.getDateFormat(t1.getDate()).compareTo(data.getDateFormat(data.getDate()));
//                        if (comparison!=0)
                            return comparison;
//                        else{
//                            return data.getDate().compareTo();
//                        }
                    }
                });
                historyAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        referSpend.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Data data = dataSnapshot.getValue(Data.class);
                    spendingData.add(data);
                }
                //                sorting
                Collections.sort(spendingData, new Comparator<Data>() {
                    @Override
                    public int compare(Data data, Data t1) {
                        int comparison = t1.getDateFormat(t1.getDate()).compareTo(data.getDateFormat(data.getDate()));
//                        if (comparison!=0)
                        return comparison;
//                        else{
//                            return data.getDate().compareTo();
//                        }
                    }
                });
                historyAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        secondRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for (DataSnapshot dataSnapshot: snapshot.getChildren()){
//                            Data data = dataSnapshot.getValue(Data.class);
//                            secondList.add(data);
//                        }
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        firstRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    Data data = dataSnapshot.getValue(Data.class);
//                    firstList.add(data);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//combinedList.addAll(firstList);
//combinedList.addAll(secondList);


    }

    private Data getsecondList() {
        List<Data> firstList = new ArrayList<>();
        secondRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                            Data data = dataSnapshot.getValue(Data.class);
                            firstList.add(data);
                        }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return (Data) firstList;
    }


}