package com.koalition.edu.lightsout;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.app.Activity;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class SettingsDialogActivity extends Activity {

    ImageView musicChecked;
    ImageView musicNotChecked;
    ImageView soundFxChecked;
    ImageView soundFxNotChecked;
    ImageView credits;
    ImageView resetProgress;
    ImageView close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.activity_settings_dialog);

        musicChecked = (ImageView) findViewById(R.id.iv_music_check);
        musicNotChecked = (ImageView) findViewById(R.id.iv_music_not);;
        soundFxChecked = (ImageView) findViewById(R.id.iv_sound_fx_check);;
        soundFxNotChecked = (ImageView) findViewById(R.id.iv_sound_fx_not);;
        credits = (ImageView) findViewById(R.id.iv_credits);;
        resetProgress = (ImageView) findViewById(R.id.iv_reset);
        close = (ImageView) findViewById(R.id.iv_close);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = preferences.edit();
        int isMusic = preferences.getInt("Music", -1);
        int isSoundFX = preferences.getInt("SoundFX", -1);

        /*initial dialog**/
        if(isMusic == 1)
        {
            musicNotChecked.setVisibility(View.INVISIBLE);
            musicChecked.setVisibility(View.VISIBLE);
        }
        else if(isMusic == 0)
        {
            musicChecked.setVisibility(View.INVISIBLE);
            musicNotChecked.setVisibility(View.VISIBLE);
        }

        if(isSoundFX == 1)
        {
            soundFxNotChecked.setVisibility(View.INVISIBLE);
            soundFxChecked.setVisibility(View.VISIBLE);
        }
        else if(isMusic == 0)
        {
            soundFxChecked.setVisibility(View.INVISIBLE);
            soundFxNotChecked.setVisibility(View.VISIBLE);
        }

        musicChecked.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:

                        //=====Write down your Finger Pressed code here
                        musicChecked.setVisibility(View.INVISIBLE);
                        musicNotChecked.setVisibility(View.VISIBLE);
                        return true;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:

                        //=====Write down you code Finger Released code here
                        editor.putInt("Music",0);
                        return true;
                }
                return false;

            }
        });

        musicNotChecked.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:

                        //=====Write down your Finger Pressed code here
                        musicNotChecked.setVisibility(View.INVISIBLE);
                        musicChecked.setVisibility(View.VISIBLE);
                        return true;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:

                        //=====Write down you code Finger Released code here
                        editor.putInt("Music",1);
                        return true;
                }
                return false;

            }
        });

        soundFxChecked.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:

                        //=====Write down your Finger Pressed code here
                        soundFxChecked.setVisibility(View.INVISIBLE);
                        soundFxNotChecked.setVisibility(View.VISIBLE);
                        return true;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:

                        //=====Write down you code Finger Released code here
                        editor.putInt("SoundFX",0);
                        return true;
                }
                return false;

            }
        });

        soundFxNotChecked.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:

                        //=====Write down your Finger Pressed code here
                        soundFxNotChecked.setVisibility(View.INVISIBLE);
                        soundFxChecked.setVisibility(View.VISIBLE);
                        return true;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:

                        //=====Write down you code Finger Released code here
                        editor.putInt("SoundFX",1);
                        return true;
                }
                return false;

            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                finish();

            }

        });
    }
    }


