package com.example.saveme;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.database.DatabaseReference;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ProfilePage extends AppCompatActivity {
    private ImageView profilePicture;
    private DatabaseReference DB;
    private final int GALLERY_REQ_CODE = 1000;
    Button im;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show Profile page
        setContentView(R.layout.activity_profile_page);

        // Use BottomNavigationView to create a bottom navigation bar that consists of logout, spending history, home, manage saving and profile buttons
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.profile);
//        viewng the logged usernmae
        TextView username = findViewById(R.id.textView15);

//        View Logged useranme
        username.setText(MainActivity.usernmae);

        profilePicture = findViewById(R.id.profile_Picture);

        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GALLERY_REQ_CODE);
            }
        });



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
        
        Button addDailyBudgetButton = (Button) findViewById(R.id.add_daily_budget_button);

        addDailyBudgetButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(ProfilePage.this, AddDailyBudget.class));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Detects request codes
        if(requestCode==GALLERY_REQ_CODE && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
