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
        // Show Add Spending page
        setContentView(R.layout.activity_add_spending_page);

        // Create spinner to display array of spending category choices in the form of dropdown list for the user to select
        Spinner spinnerQuestions=findViewById(R.id.spending_category_spinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this,
                R.array.spending_category,
                R.layout.spinner_list);

        spinnerQuestions.setAdapter(adapter);
    }
}