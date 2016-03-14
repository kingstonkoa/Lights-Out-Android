package com.koalition.edu.lightsout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

import org.w3c.dom.Text;


public class GameOverActivity extends AppCompatActivity {
    private TextView score;
    private TextView bestScoreText;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mediaPlayer = MediaPlayer.create(GameOverActivity.this, R.raw.cinema);

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_game_over);
        score = (TextView) findViewById(R.id.tv_score);
        bestScoreText = (TextView) findViewById(R.id.tv_bestscore);

        SharedPreferences preferencesScore = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferencesScore.edit();
        int highScore = preferencesScore.getInt("HighScore", -1);
        //change to store the current score also in preferenceScore
        if(Integer.parseInt(score.getText().toString()) > highScore)
        {
            editor.putInt("HighScore",Integer.parseInt(score.getText().toString()));
            editor.apply();
            bestScoreText.setText("That's your best score!");
        }


        ShareButton fbShareButton = (ShareButton) findViewById(R.id.share_btn);
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentDescription(
                        "Can you beat my score: " + score.getText().toString())
                .setContentUrl(Uri.parse("https://www.facebook.com/LightsOutMobile"))
                .build();
        fbShareButton.setShareContent(content);


    }

    @Override
    protected void onResume() {
        super.onResume();

        mediaPlayer.start();
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
