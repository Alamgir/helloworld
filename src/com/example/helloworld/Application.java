package com.example.helloworld;

import android.content.Context;
import android.content.SharedPreferences;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Alamgir on 9/29/2014.
 */
public class Application extends android.app.Application {
    // Debugging switch
    public static final boolean APPDEBUG = false;

    // Debugging tag for the application
    public static final String APPTAG = "HelloWorld";

    private static SharedPreferences preferences;

    private static ConfigHelper configHelper;

    public Application() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //ParseObject.registerSubclass(AnywallPost.class);
        Parse.initialize(this, "hZa162wrlni6ZvMXamXgZgiSd7ulna1p1zcDZ80E",
                         "hjoLJNab61uJ0HPqWAYFGenCQcNioNEgDzLQ6nMK");

        preferences = getSharedPreferences("com.parse.helloworld", Context.MODE_PRIVATE);

        configHelper = new ConfigHelper();
        configHelper.fetchConfigIfNeeded();
    }

}