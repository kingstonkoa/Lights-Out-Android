package com.koalition.edu.lightsout;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ShopMainActivity extends Activity {

    ImageView shopHead;
    ImageView backButton;
    ImageView backButtonOnClick;
    ImageView powerUpButton;
    ImageView powerUpButtonOnClick;
    ImageView designButton;
    ImageView designButtonOnClick;
    TextView playerBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_main);

        shopHead = (ImageView) findViewById(R.id.shop_head);
        backButton = (ImageView) findViewById(R.id.back_button);
        backButtonOnClick = (ImageView) findViewById(R.id.back_button_clicked);
        powerUpButton = (ImageView) findViewById(R.id.power_button);
        powerUpButtonOnClick = (ImageView) findViewById(R.id.power_button_clicked);
        designButton = (ImageView) findViewById(R.id.design_button);
        designButtonOnClick = (ImageView) findViewById(R.id.design_button_clicked);
        playerBalance = (TextView) findViewById(R.id.player_balance);

        /* hide onclick buttons**/
        powerUpButtonOnClick.setVisibility(View.INVISIBLE);
        designButtonOnClick.setVisibility(View.INVISIBLE);
        backButtonOnClick.setVisibility(View.INVISIBLE);

        powerUpButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:

                        //=====Write down your Finger Pressed code here
                        powerUpButton.setVisibility(View.INVISIBLE);
                        powerUpButtonOnClick.setVisibility(View.VISIBLE);
                        return true;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:

                        //=====Write down you code Finger Released code here
                        powerUpButtonOnClick.setVisibility(View.INVISIBLE);
                        powerUpButton.setVisibility(View.VISIBLE);

                        Intent intent = new Intent(getApplicationContext(), PowerUpListActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;

            }
        });

        designButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:

                        //=====Write down your Finger Pressed code here
                        designButton.setVisibility(View.INVISIBLE);
                        designButtonOnClick.setVisibility(View.VISIBLE);
                        return true;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:

                        //=====Write down you code Finger Released code here
                        designButtonOnClick.setVisibility(View.INVISIBLE);
                        designButton.setVisibility(View.VISIBLE);

/*                        Intent intent=new Intent(getApplicationContext(), ShopMainActivity.class);
                        startActivity(intent);*/
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
