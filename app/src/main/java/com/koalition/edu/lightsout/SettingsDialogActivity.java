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
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SettingsDialogActivity extends Activity {

    ImageView musicChecked;
    ImageView musicNotChecked;
    ImageView soundFxChecked;
    ImageView soundFxNotChecked;
    ImageView credits;
    ImageView resetProgress;
    ImageView close;

    ImageView resetQuestion;
    ImageView cancelButton;
    ImageView resetButton;

    ImageView resetStatus;

    SharedPreferences preferences;

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

        resetQuestion = (ImageView) findViewById(R.id.iv_reset_question);
        cancelButton = (ImageView) findViewById(R.id.iv_cancel_button);
        resetButton = (ImageView) findViewById(R.id.iv_reset_button);

        resetStatus = (ImageView) findViewById(R.id.iv_reset_status);



        preferences = PreferenceManager.getDefaultSharedPreferences(this);
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
                        editor.commit();
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
                        editor.commit();
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
                        editor.commit();
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
                        editor.commit();
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

        hideResetDialog();
        hideResetStatusDialog();

        resetProgress.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               RelativeLayout ly = (RelativeLayout) findViewById(R.id.settingsLayout);
                ly.setBackgroundResource(R.drawable.dialog_box_blank);
                hideSettingsDialog();
                showResetDialog();

/*                SharedPreferences preferences =  getSharedPreferences("my_preferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.putBoolean("onboarding_complete", false);
                editor.commit();
                Toast.makeText(getBaseContext(), "Progress has been reset",
                        Toast.LENGTH_LONG).show();

                Intent i = new Intent(SettingsDialogActivity.this, MainActivity.class);
                startActivity(i);*/

            }

        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                SharedPreferences preferences =  getSharedPreferences("my_preferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.putBoolean("onboarding_complete", false);
                editor.commit();

                hideResetDialog();
                showResetStatusDialog();
                //finish();
                /*Intent i = new Intent(SettingsDialogActivity.this, MainActivity.class);
                startActivity(i);*/
            }

        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            finish();
                Intent intent=new Intent(getApplicationContext(), SettingsDialogActivity.class);
                startActivity(intent);

            }

        });

    }

    private void hideSettingsDialog() {
        musicChecked.setVisibility(View.INVISIBLE);
        musicNotChecked.setVisibility(View.INVISIBLE);
        soundFxChecked.setVisibility(View.INVISIBLE);
        soundFxNotChecked.setVisibility(View.INVISIBLE);
        credits.setVisibility(View.INVISIBLE);
        resetProgress.setVisibility(View.INVISIBLE);
    }
    private void hideResetDialog() {
        resetQuestion.setVisibility(View.INVISIBLE);
        cancelButton.setVisibility(View.INVISIBLE);
        resetButton.setVisibility(View.INVISIBLE);

    }

    private void hideResetStatusDialog() {
        resetStatus.setVisibility(View.INVISIBLE);
    }
    private void showResetDialog() {
        resetQuestion.setVisibility(View.VISIBLE);
        cancelButton.setVisibility(View.VISIBLE);
        resetButton.setVisibility(View.VISIBLE);
    }
    private void showResetStatusDialog() {
        resetStatus.setVisibility(View.VISIBLE);
    }
}


