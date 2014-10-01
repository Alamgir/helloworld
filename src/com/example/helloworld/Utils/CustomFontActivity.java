package com.example.helloworld.Utils;

import android.app.Activity;
import android.content.Context;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Alamgir on 9/30/14.
 */
public class CustomFontActivity extends Activity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(new CalligraphyContextWrapper(newBase));
    }


}
