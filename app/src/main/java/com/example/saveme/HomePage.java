package com.example.saveme;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show Home page
        setContentView(R.layout.activity_home_page);

        // Use BottomNavigationView to create a bottom navigation bar that consists of logout, spending history, home, manage saving and profile buttons
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.history);

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
        ImageButton addSpendingButton = (ImageButton) findViewById(R.id.add_spending_button);

        addSpendingButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(HomePage.this, AddSpendingPage.class));
            }
        });
    }
}