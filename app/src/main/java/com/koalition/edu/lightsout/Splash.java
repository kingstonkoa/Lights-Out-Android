package com.koalition.edu.lightsout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by Kingston on 3/5/2016.
 */
public class Splash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashh);

        final ImageView iv = (ImageView) findViewById(R.id.imageView);
        final Animation rotateAnim = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
        final Animation slideDownAnim = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_down);
        final Animation fadeOutAnim = AnimationUtils.loadAnimation(getBaseContext(), R.anim.abc_fade_out);
        final Animation fadeInAnim = AnimationUtils.loadAnimation(getBaseContext(), R.anim.abc_fade_in);

        iv.startAnimation(fadeOutAnim);
        fadeOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv.startAnimation(fadeInAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fadeInAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv.startAnimation(fadeOutAnim);
                //finish();
                //Intent i = new Intent(Splash.this, MainActivity.class);
               // startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        iv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                finish();
                Intent i = new Intent(Splash.this, MainActivity.class);
                 startActivity(i);
            }

        });
    }
}
