package com.example.saveme;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.saveme.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfilePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show Profile page
        setContentView(R.layout.activity_profile_page);

        // Use BottomNavigationView to create a bottom navigation bar that consists of logout, spending history, home, manage saving and profile buttons
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.profile);

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
                        startActivity(new Intent(getApplicationContext(),ManageSavingPage.class));
                        overridePendingTransition(0,0);
                        return true;
                    // If the Profile button is clicked, display Profile page
                    case R.id.profile:
                        return true;
                }
                return false;
            }
        });

        // When "Change Password" button is clicked, display Change Password page
        Button changePasswordButton = (Button) findViewById(R.id.change_password_button);

        changePasswordButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(ProfilePage.this, ChangePassword.class));
            }
        });

        // When "Set Up Income" button is clicked, display Income Setup page
        Button incomeSetupButton = (Button) findViewById(R.id.income_setup_button);

        incomeSetupButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(ProfilePage.this, IncomeSetupPage.class));
            }
        });

        // When "Delete Account" button is clicked, display a pop-up window that asks for confirmation
        // whether to delete the account or not
        Button deleteAccountButton = (Button) findViewById(R.id.submit_button);

        deleteAccountButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(ProfilePage.this, Pop.class));
            }
        });
    }
}