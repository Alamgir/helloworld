package com.example.helloworld;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.*;
import android.view.inputmethod.EditorInfo;
import android.widget.*;
import com.example.helloworld.animation.ImageAnimation;

import java.io.IOException;
import java.util.List;

public class MyActivity extends Activity implements View.OnClickListener, View.OnLongClickListener {
    public LayoutInflater inflater;

    private static final String TAG = MyActivity.class.getSimpleName();

    public TextView tv;

    public Location lastKnown;

    static LinearLayout listViewContainer;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String locationProvider = LocationManager.GPS_PROVIDER;
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        lastKnown = locationManager.getLastKnownLocation(locationProvider);

        Log.i(TAG, "in onCreate()");

        inflater = getLayoutInflater();

        setContentView(R.layout.main);
        tv = (TextView)findViewById(R.id.textView);
        listViewContainer = (LinearLayout)findViewById(R.id.list_view_container);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.list_view_container, new ForecastFragment(lastKnown))
                    .commit();
        }

        //geocode the location
        try {
            List<Address> addressList = new Geocoder(getApplicationContext()).getFromLocation(lastKnown.getLatitude(),lastKnown.getLongitude(),1);

            Address address = addressList.get(0);
            StringBuilder builder = new StringBuilder(address.getAddressLine(0));
            builder.append(address.getAddressLine(1));
            tv.setText(builder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "new intent");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "in onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "in onResume()");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.i(TAG, "in onRestart()");
    }

    @Override
    public void onPause() {
        Log.i(TAG, "in onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, "in onStop()");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "in onDestroy()");
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int callerId = view.getId();

        switch(callerId) {
            default:
                tv.setText("unrecognizable click");
        }
    }

    @Override
    public boolean onLongClick(View view) {
        int callerId = view.getId();

        switch(callerId) {
            default:
                tv.setText("unrecognizable click");
        }

        return true;
    }
}
