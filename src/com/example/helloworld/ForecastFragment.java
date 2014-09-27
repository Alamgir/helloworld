package com.example.helloworld;

/**
 * Created by Alamgir on 9/25/2014.
 */

import android.app.Fragment;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.helloworld.async_tasks.FetchForecastTask;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastFragment extends Fragment implements AsyncResponse {
    private static final String FORECAST_FRAGMENT_TAG = ForecastFragment.class.getSimpleName();

    private ArrayAdapter<String> adapter;

    Location lastKnown;

    View rootView;
    ListView listView;

    public ForecastFragment(Location lastKnown) {
        this.lastKnown = lastKnown;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1);
        listView = (ListView)rootView.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getActivity(), adapter.getItem(position), Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.forecastfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            //execute the task
            new FetchForecastTask(this).execute(lastKnown);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void taskFinished(JsonNode result) {
        Log.i(FORECAST_FRAGMENT_TAG, "got the result in the fragment");

        JsonNode weathers = result.get("list");

        ArrayList<String> items = new ArrayList<String>();
        for (JsonNode weather : weathers) {
            items.add(weather.get("weather").get(0).get("main").toString());
        }

        adapter.clear();
        adapter.addAll(items);

    }

    @Override
    public void taskFinished(String result) {

    }
}