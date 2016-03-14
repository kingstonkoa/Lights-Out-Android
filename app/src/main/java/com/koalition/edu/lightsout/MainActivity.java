package com.koalition.edu.lightsout;

import android.app.AlarmManager;
import android.app.PendingIntent;
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
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import com.facebook.FacebookSdk;


public class MainActivity extends AppCompatActivity {
    final static int AR_PENDINGINTENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** For notifications */
        Intent arIntent = new Intent();
        arIntent.setClass(getBaseContext(), FreeCoinReceiver.class);
        PendingIntent arPendingIntent = PendingIntent.getBroadcast(getBaseContext(), AR_PENDINGINTENT,arIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        // Call AlarmManager to broadcast intent
        AlarmManager alarmManager
                = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 2,
                arPendingIntent);


        // Get the shared preferences
        SharedPreferences preferences =  getSharedPreferences("my_preferences", MODE_PRIVATE);
        SharedPreferences preferencesScore = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferencesScore.edit();
        // Check if onboarding_complete is false
        if(!preferences.getBoolean("onboarding_complete",false)) {

            editor.putInt("HighScore",0); // STORE INITIAL SCORE OF 0
            editor.apply();
            // Start the onboarding Activity
            Intent onboarding = new Intent(this, OnboardingActivity.class);
            startActivity(onboarding);

            // Close the main Activity
            finish();
            return;
        }

        Intent i = new Intent(this, Splash.class);
         startActivity(i);



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
