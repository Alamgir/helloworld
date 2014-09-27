package com.example.helloworld.async_tasks;

import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import com.example.helloworld.AsyncResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchForecastTask extends AsyncTask<Location, Void, String> {

    private static final String ASYNC_TASK_TAG = FetchForecastTask.class.getSimpleName();

    public AsyncResponse listener;

    private ObjectMapper _mapper;

    public FetchForecastTask(AsyncResponse listener) {
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        Log.i(ASYNC_TASK_TAG, "before...");

        //set up the mapper
        _mapper = new ObjectMapper();
        _mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    }

    @Override
    protected String doInBackground(Location... locations) {
        Location lastKnown = locations[0];
        Log.i(ASYNC_TASK_TAG, "in background...");

        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;

        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are available at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
            Uri.Builder uriBuilder = new Uri.Builder();
            uriBuilder.scheme("http")
                      .authority("api.openweathermap.org")
                      .appendPath("data")
                      .appendPath("2.5")
                      .appendPath("forecast")
                      .appendPath("daily")
                      .appendQueryParameter("lat", Double.toString(lastKnown.getLatitude()))
                      .appendQueryParameter("lon", Double.toString(lastKnown.getLongitude()))
                      .appendQueryParameter("cnt", "10")
                      .appendQueryParameter("mode", "json").build();
            URL url = new URL(uriBuilder.toString());

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            forecastJsonStr = buffer.toString();
        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attempting
            // to parse it.
            return null;
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }


        return forecastJsonStr;
    }

    @Override
    protected void onPostExecute(String result) {
        Log.i(ASYNC_TASK_TAG, result);

        try {
            @SuppressWarnings("unchecked")
            JsonNode weatherData = _mapper.readTree(result);

            //Log.i(ASYNC_TASK_TAG, weatherData.toString());

            listener.taskFinished(weatherData);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
