package com.example.saveme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Pop extends Activity {
    DatabaseReference DB = FirebaseDatabase.getInstance().getReferenceFromUrl("https://saveme-3a2cf-default-rtdb.firebaseio.com/")
            .child("user");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show pop-up window to ask for confirmation before deleting account
        setContentView(R.layout.pop_window);

        // Use DisplayMetrics to set the width and height of the pop-up window
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.2));

        // When the "Yes" button is clicked, the details of the user are removed from the database and another
        // window pops up to confirm that their account has been deleted
        Button deleteAccountButton = (Button) findViewById(R.id.yes_button);

        deleteAccountButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                DB.child(MainActivity.usernmae).removeValue();
                startActivity(new Intent(Pop.this, Pop2.class));
            }
        });

        // When the "No" button is clicked, it indicates that the user does not want to delete their account, thus
        // they are directed back to Profile page
        Button dontDeleteAccountButton = (Button) findViewById(R.id.okay_button);

        dontDeleteAccountButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Pop.this, ProfilePage.class));
            }
        });
    }
}