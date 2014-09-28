package com.example.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by Alamgir on 9/17/2014.
 */
public class DetailActivity extends Activity {

    TextView detailText;

    String detailString;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail);

        detailString = getIntent().getStringExtra("weather object");

        detailText = (TextView)findViewById(R.id.detail_weather_text);
        detailText.setText(detailString);

//        if(savedInstanceState == null) {
//            getFragmentManager().beginTransaction().add();
//        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}