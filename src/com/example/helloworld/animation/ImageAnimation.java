package com.example.helloworld.animation;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

/**
 * Created by Alamgir on 9/17/2014.
 */
public class ImageAnimation {

    public static final int fadeDuration = 500;

    public static void fadeIn(ImageView img) {
        Animation fadeIn = new AlphaAnimation(0,1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(fadeDuration);

        img.setAnimation(fadeIn);
        img.setVisibility(View.VISIBLE);
    }

    public static void fadeOut(ImageView img) {
        Animation fadeOut = new AlphaAnimation(1,0);
        fadeOut.setInterpolator(new DecelerateInterpolator());
        fadeOut.setDuration(fadeDuration);

        img.setAnimation(fadeOut);
        img.setVisibility(View.INVISIBLE);
    }

}
