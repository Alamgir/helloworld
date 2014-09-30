package com.example.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.Toast;
import com.parse.ParseUser;

/**
 * Created by Alamgir on 9/29/2014.
 */
public class DispatchActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
//        getActionBar().hide();

        setContentView(R.layout.dispatch);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // Check if there is current user info
                if (ParseUser.getCurrentUser() != null) {
                    // Start an intent for the logged in activity
                    startActivity(new Intent(DispatchActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "on the splash screen", Toast.LENGTH_SHORT).show();

                    // Start and intent for the logged out activity
                    startActivity(new Intent(DispatchActivity.this, WelcomeActivity.class));
                    finish();
                }
            }
        }, 3000);

    }
}