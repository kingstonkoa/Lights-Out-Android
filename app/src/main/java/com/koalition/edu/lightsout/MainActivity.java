package com.koalition.edu.lightsout;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.FacebookSdk;


public class MainActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    ImageView playGameButton;
    ImageView shopButton;
    ImageView settingsButton;

    ImageView playGameButtonOnClick;
    ImageView shopButtonOnClick;
    ImageView settingsButtonOnClick;

    final static int BC_PENDINGINTENT = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the shared preferences
        SharedPreferences preferences =  getSharedPreferences("my_preferences", MODE_PRIVATE);
        SharedPreferences preferencesScore = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferencesScore.edit();
        // Check if onboarding_complete is false
        if(!preferences.getBoolean("onboarding_complete",false)) {
            dbHelper = new DatabaseHelper(getBaseContext());
            dbHelper.insertPowerUp(new PowerUp(1, "Freeze", 500));
            dbHelper.insertPowerUp(new PowerUp(2, "Slow", 300));
            dbHelper.insertPowerUp(new PowerUp(3, "Distract", 400));
            editor.putInt("HighScore",0); // STORE INITIAL SCORE OF 0
            editor.apply();
            // Start the onboarding Activity
            Intent onboarding = new Intent(this, OnboardingActivity.class);
            startActivity(onboarding);




            // Close the main Activity
            finish();
            return;
        }

        playGameButton = (ImageView) findViewById(R.id.iv_playgamewht);
        shopButton = (ImageView) findViewById(R.id.iv_shopwht);
        settingsButton = (ImageView) findViewById(R.id.iv_settingswht);

        playGameButtonOnClick = (ImageView) findViewById(R.id.iv_playgameblk);
        shopButtonOnClick = (ImageView) findViewById(R.id.iv_shopblk);
        settingsButtonOnClick = (ImageView) findViewById(R.id.iv_settingsblk);

        /* hide onclick buttons**/
        playGameButtonOnClick.setVisibility(View.INVISIBLE);
        shopButtonOnClick.setVisibility(View.INVISIBLE);
        settingsButtonOnClick.setVisibility(View.INVISIBLE);

        playGameButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:

                        //=====Write down your Finger Pressed code here
                        playGameButton.setVisibility(View.INVISIBLE);
                        playGameButtonOnClick.setVisibility(View.VISIBLE);
                        return true;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:

                        //=====Write down you code Finger Released code here
                        playGameButtonOnClick.setVisibility(View.INVISIBLE);
                        playGameButton.setVisibility(View.VISIBLE);
                        Intent i = new Intent(MainActivity.this, GameOverActivity.class);
                        startActivity(i);
                        return true;
                }
                return false;

            }
        });

        shopButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:

                        //=====Write down your Finger Pressed code here
                        shopButton.setVisibility(View.INVISIBLE);
                        shopButtonOnClick.setVisibility(View.VISIBLE);
                        return true;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:

                        //=====Write down you code Finger Released code here
                        shopButtonOnClick.setVisibility(View.INVISIBLE);
                        shopButton.setVisibility(View.VISIBLE);
                        return true;
                }
                return false;

            }
        });

        settingsButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:

                        //=====Write down your Finger Pressed code here
                        settingsButton.setVisibility(View.INVISIBLE);
                        settingsButtonOnClick.setVisibility(View.VISIBLE);
                        return true;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:

                        //=====Write down you code Finger Released code here
                        settingsButtonOnClick.setVisibility(View.INVISIBLE);
                        settingsButton.setVisibility(View.VISIBLE);
                        return true;
                }
                return false;

            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();


        // Get the shared preferences
        SharedPreferences preferences =  getSharedPreferences("my_preferences", MODE_PRIVATE);
        SharedPreferences preferencesScore = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferencesScore.edit();

// check if new coins
        if(preferences.getBoolean("getsFreeCoins", true)){
            int seconds = 3;
            Intent broadcastIntent = new Intent(getBaseContext(), FreeCoinReceiver.class);
            PendingIntent pendingIntent
                    = PendingIntent.getBroadcast(getBaseContext(),
                    BC_PENDINGINTENT,
                    broadcastIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT);

            ((AlarmManager) getSystemService(Service.ALARM_SERVICE))
                    .set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                            SystemClock.elapsedRealtime() + (seconds * 1000),
                            pendingIntent);

            editor.putBoolean("getsFreeCoins", false);
        }
        /** checks if opened from notification */
//        Intent intent = this.getIntent();
//        if (intent != null && intent.getExtras() != null && intent.getExtras().containsKey("getsFreeCoins")) {
//            if(intent.getExtras().getBoolean("getsFreeCoins")==true) {
//                int seconds = 3;
//                Intent broadcastIntent = new Intent(getBaseContext(), FreeCoinReceiver.class);
//                PendingIntent pendingIntent
//                        = PendingIntent.getBroadcast(getBaseContext(),
//                        BC_PENDINGINTENT,
//                        broadcastIntent,
//                        PendingIntent.FLAG_CANCEL_CURRENT);
//
//                ((AlarmManager) getSystemService(Service.ALARM_SERVICE))
//                        .set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                                SystemClock.elapsedRealtime() + (seconds * 1000),
//                                pendingIntent);
//
//                intent.removeExtra("getsFreeCoins");
//            }
//
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
