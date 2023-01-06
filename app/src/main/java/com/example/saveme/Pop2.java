package com.example.saveme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class Pop2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show pop-up window to confirm that account has been deleted
        setContentView(R.layout.pop_window2);

        // Use DisplayMetrics to set the width and height of the pop-up window
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.2));

        // When "Okay" button is clicked, the user is ware that the account has been deleted thus
        // Launch Screen is displayed
        Button okayButton = (Button) findViewById(R.id.okay_button);

        okayButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Pop2.this, MainActivity.class));
            }
        });
    }
}