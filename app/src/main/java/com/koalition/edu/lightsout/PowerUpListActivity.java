package com.koalition.edu.lightsout;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class PowerUpListActivity extends Activity {

    ImageView powerUpHead;
    ImageView backButton;
    ImageView backButtonOnClick;
    ImageView freezePowerUpButton;
    ImageView freezePowerUpButtonOnClick;
    ImageView brownoutPowerUpButton;
    ImageView brownoutPowerUpButtonOnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_up_list);

        powerUpHead = (ImageView) findViewById(R.id.powerup_head);
        backButton = (ImageView) findViewById(R.id.back_button);;
        backButtonOnClick = (ImageView) findViewById(R.id.back_button_clicked);;
        freezePowerUpButton = (ImageView) findViewById(R.id.freeze_powerup);;
        freezePowerUpButtonOnClick = (ImageView) findViewById(R.id.freeze_powerup_cliicked);;
        brownoutPowerUpButton = (ImageView) findViewById(R.id.brownout_powerup);;
        brownoutPowerUpButtonOnClick = (ImageView) findViewById(R.id.brownout_powerup_clicked);

        /* hide onclick buttons**/
        freezePowerUpButtonOnClick.setVisibility(View.INVISIBLE);
        brownoutPowerUpButtonOnClick.setVisibility(View.INVISIBLE);
        backButtonOnClick.setVisibility(View.INVISIBLE);

        freezePowerUpButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:

                        //=====Write down your Finger Pressed code here
                        freezePowerUpButton.setVisibility(View.INVISIBLE);
                        freezePowerUpButtonOnClick.setVisibility(View.VISIBLE);
                        return true;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:

                        //=====Write down you code Finger Released code here
                        freezePowerUpButtonOnClick.setVisibility(View.INVISIBLE);
                        freezePowerUpButton.setVisibility(View.VISIBLE);

                        return true;
                }
                return false;

            }
        });

        brownoutPowerUpButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:

                        //=====Write down your Finger Pressed code here
                        brownoutPowerUpButton.setVisibility(View.INVISIBLE);
                        brownoutPowerUpButtonOnClick.setVisibility(View.VISIBLE);
                        return true;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:

                        //=====Write down you code Finger Released code here
                        brownoutPowerUpButtonOnClick.setVisibility(View.INVISIBLE);
                        brownoutPowerUpButton.setVisibility(View.VISIBLE);

                        return true;
                }
                return false;

            }
        });

        backButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:

                        //=====Write down your Finger Pressed code here
                        backButton.setVisibility(View.INVISIBLE);
                        backButtonOnClick.setVisibility(View.VISIBLE);
                        return true;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:

                        //=====Write down you code Finger Released code here
                        backButtonOnClick.setVisibility(View.INVISIBLE);
                        backButton.setVisibility(View.VISIBLE);

                        finish();
                        return true;
                }
                return false;

            }
        });
    }

}
