package com.example.saveme;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.saveme.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddSpendingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spending_page);

        Spinner spinnerQuestions=findViewById(R.id.spending_category_spinner);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.spending_category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinnerQuestions.setAdapter(adapter);

//        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
//
//        bottomNavigationView.setSelectedItemId(R.id.history);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                switch(item.getItemId())
//                {
//                    case R.id.logout:
//                        startActivity(new Intent(getApplicationContext(),Logout.class));
//                        overridePendingTransition(0,0);
//                        return true;
//                    case R.id.history:
//                        startActivity(new Intent(getApplicationContext(),SpendingHistoryPage.class));
//                        overridePendingTransition(0,0);
//                        return true;
//                    case R.id.home:
//                        startActivity(new Intent(getApplicationContext(),HomePage.class));
//                        overridePendingTransition(0,0);
//                        return true;
//                    case R.id.add_spending:
//                        return true;
//                    case R.id.saving:
//                        startActivity(new Intent(getApplicationContext(),ManageSavingPage.class));
//                        overridePendingTransition(0,0);
//                        return true;
//                    case R.id.profile:
//                        startActivity(new Intent(getApplicationContext(),ProfilePage.class));
//                        overridePendingTransition(0,0);
//                        return true;
//                }
//                return false;
//            }
//        });
    }
}