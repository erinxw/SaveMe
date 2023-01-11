package com.example.saveme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.saveme.databinding.ActivityMainBinding;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    DatabaseReference DBcategory = FirebaseDatabase.getInstance().getReferenceFromUrl("https://saveme-3a2cf-default-rtdb.firebaseio.com/").child("user").child(MainActivity.usernmae).child("analytic");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show Home page
        setContentView(R.layout.activity_home_page);

        // Use BottomNavigationView to create a bottom navigation bar that consists of logout, spending history, home, manage saving and profile buttons
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.profile);

        PieChart pieChart = findViewById(R.id.piechart);
        Description description = new Description();
        description.setText("");
        pieChart.setDescription(description);





        DBcategory.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<PieEntry> pieEntry = new ArrayList<>();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    // for example, assuming that your Firebase data is in the following format:
                    // {"label1": 2, "label2": 3, "label3": 4}

                    String label = ds.getKey();
                    float value = ds.getValue(Float.class);

                    pieEntry.add(new PieEntry(value, label));
                    pieChart.setDrawEntryLabels(false);
                }
                PieDataSet dataSet = new PieDataSet(pieEntry, "My pie chart");
                dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                PieData data = new PieData(dataSet);
                pieChart.setData(data); pieChart.animateY(1000);
                pieChart.invalidate(); // refresh
                }
                @Override
                public void onCancelled(DatabaseError error) {
                // Failed to read value
                    Log.w("Failed to read value.", error.toException()); } });



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
                    // If the Add button is clicked, display Add page
                    case R.id.addSpend:
                        startActivity(new Intent(getApplicationContext(),AddSpendingPage.class));
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
        // When the Add Spending image button on Home page is clicked, display Add Spending page
//        ImageButton addSpendingButton = (ImageButton) findViewById(R.id.add_spending_button);

//        addSpendingButton.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                startActivity(new Intent(HomePage.this, AddSpendingPage.class));
//            }
//        });
    }
    @Override
    public void onBackPressed() {

        super.onBackPressed();
        finishAffinity();
    }
}