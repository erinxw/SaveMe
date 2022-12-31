package com.example.saveme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private Button lbutton, rbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lbutton = (Button) findViewById(R.id.login_button);
        lbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });
        rbutton = (Button) findViewById(R.id.register_button);
        rbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openRegisterActivity(); }
        });
    }

    private void openLoginActivity() {
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }

    private void openRegisterActivity() {
        Intent intent = new Intent(this, RegistrationPage.class);
        startActivity(intent);
    }


}