package com.koalition.edu.lightsout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;


public class GameOverActivity extends AppCompatActivity {
    TextView score;
    ImageView bestScoreText;
    ImageView backToMainButton;
    ImageView backToMainButtonClicked;
    ImageView playAgainButton;
    ImageView playAgainButtonClicked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_game_over);
        score = (TextView) findViewById(R.id.tv_score);
        bestScoreText = (ImageView) findViewById(R.id.thats_your_best_text);
        backToMainButton = (ImageView) findViewById(R.id.back_to_main_menu_button);
        backToMainButtonClicked = (ImageView) findViewById(R.id.back_to_main_menu_button_clicked);
        playAgainButton = (ImageView) findViewById(R.id.play_again_button);
        playAgainButtonClicked = (ImageView) findViewById(R.id.play_again_button_clicked);

        /* hide onclick buttons**/
        backToMainButtonClicked.setVisibility(View.INVISIBLE);
        playAgainButtonClicked.setVisibility(View.INVISIBLE);
        bestScoreText.setVisibility(View.INVISIBLE);

        SharedPreferences preferencesScore = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferencesScore.edit();
        int highScore = preferencesScore.getInt("HighScore", -1);
        //change to store the current score also in preferenceScore
        if(Integer.parseInt(score.getText().toString()) >= highScore)
        {
            editor.putInt("HighScore",Integer.parseInt(score.getText().toString()));
            editor.apply();
            bestScoreText.setVisibility(View.VISIBLE);
        }


        ShareButton fbShareButton = (ShareButton) findViewById(R.id.share_btn);
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentDescription(
                        "Can you beat my score: " + score.getText().toString())
                .setContentUrl(Uri.parse("https://www.facebook.com/LightsOutMobile"))
                .build();
        fbShareButton.setShareContent(content);

        backToMainButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:

                        //=====Write down your Finger Pressed code here
                        backToMainButton.setVisibility(View.INVISIBLE);
                        backToMainButtonClicked.setVisibility(View.VISIBLE);
                        return true;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:

                        //=====Write down you code Finger Released code here
                        backToMainButtonClicked.setVisibility(View.INVISIBLE);
                        backToMainButton.setVisibility(View.VISIBLE);
/*                        Intent i = new Intent(GameOverActivity.this, MainActivity.class);
                        startActivity(i);*/
                        finish();
                        return true;
                }
                return false;

            }
        });

        playAgainButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:

                        //=====Write down your Finger Pressed code here
                        playAgainButton.setVisibility(View.INVISIBLE);
                        playAgainButtonClicked.setVisibility(View.VISIBLE);
                        return true;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:

                        //=====Write down you code Finger Released code here
                        playAgainButtonClicked.setVisibility(View.INVISIBLE);
                        playAgainButton.setVisibility(View.VISIBLE);
                        /*Intent i = new Intent(GameOverActivity.this, MainActivity.class);
                        startActivity(i);*/
                        return true;
                }
                return false;

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        //mediaPlayer.start();
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
