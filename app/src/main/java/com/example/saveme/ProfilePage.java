package com.example.saveme;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.saveme.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfilePage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.history:
                        startActivity(new Intent(getApplicationContext(),SpendingHistoryPage.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        return true;
                    case R.id.saving:
                        startActivity(new Intent(getApplicationContext(),ManageSavingPage.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        Button changePasswordButton = (Button) findViewById(R.id.change_password_button);

        changePasswordButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(ProfilePage.this, ChangePassword.class));
            }
        });

        Button incomeSetupButton = (Button) findViewById(R.id.income_setup_button);

        incomeSetupButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(ProfilePage.this, IncomeSetupPage.class));
            }
        });

        Button deleteAccountButton = (Button) findViewById(R.id.delete_account_button);

        deleteAccountButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(ProfilePage.this, Pop.class));
            }
        });

    }




}



