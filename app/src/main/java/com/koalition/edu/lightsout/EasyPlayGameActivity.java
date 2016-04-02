package com.koalition.edu.lightsout;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EasyPlayGameActivity extends Activity {

    ImageView room1Box;
    ImageView room2Box;
    ImageView room3Box;
    ImageView room4Box;

    ImageView switchButton1;
    ImageView switchButton2;
    ImageView switchButton3;
    ImageView switchButton4;

    TextView moneyTextView;
    TextView scoreTextView;
    TextView deductionTextView;

    // timer for randomizing every randomizeSpeed
    static int RANDOMIZE_SPEED = 2000;
    static int POINTS_LOST = 1;
    static int POINTS_GAINED = 10;
    static int POSSIBLE_LIGHTS_ON = 2;
    int time;
    int moneyValue;
    int scoreValue;
    // kung nakailan na siyang sunod sunod
    int streakValue;

    ArrayList<Switch> switches;
    private Random statusRandom;

    Animation slideDownAnim;
    Animation fadeOutAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_play_game);
        room1Box = (ImageView) findViewById(R.id.easy_room1);
        room2Box = (ImageView) findViewById(R.id.easy_room2);
        room3Box = (ImageView) findViewById(R.id.easy_room3);
        room4Box = (ImageView) findViewById(R.id.easy_room4);
        switchButton1 = (ImageView) findViewById(R.id.easy_button1);
        switchButton2 = (ImageView) findViewById(R.id.easy_button2);
        switchButton3 = (ImageView) findViewById(R.id.easy_button3);
        switchButton4 = (ImageView) findViewById(R.id.easy_button4);
        moneyTextView = (TextView) findViewById(R.id.tv_money);
        scoreTextView = (TextView) findViewById(R.id.tv_easy_score);
        deductionTextView = (TextView) findViewById(R.id.tv_deduction);
        slideDownAnim = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_down_deduction);
        fadeOutAnim = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fadeout);


        moneyValue = 100;
        scoreValue = 0;
        updateHUD(moneyValue, scoreValue);

        statusRandom = new Random();
        // timer for randomizing every randomizeSpeed
        time = 0;
        timerHandler.postDelayed(timerRunnable, 0);
        refreshSwitches();


        switchButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switches.get(0).isSwitchState() == true) {
                    switches.get(0).setSwitchState(false);
                    switchButton1.setImageResource(R.drawable.switchoff);
                } else if (switches.get(0).isSwitchState() == false) {
                    switches.get(0).setSwitchState(true);
                    switchButton1.setImageResource(R.drawable.switchon);
                }

                if(switches.get(0).isRoomState() == true) {
                    // TODO add score
                    if(switches.get(0).getIsSwitchedByAI() == true) {
                        scoreValue += POINTS_GAINED;
                        updateHUD(moneyValue, scoreValue);
                        streakValue++;
                        checkIfStreakBonus(streakValue);
                        switches.get(0).setIsSwitchedByAI(false);
                    }
                    switches.get(0).setRoomState(false);
                    turnOffRoom(switches.get(0).getRoomNumber());

                } else if (switches.get(0).isRoomState() == false) {
                    streakValue=0;

                    switches.get(0).setRoomState(true);
                    turnOnRoom(switches.get(0).getRoomNumber());
                }
            }
        });

        switchButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switches.get(1).isSwitchState() == true) {
                    switches.get(1).setSwitchState(false);
                    switchButton2.setImageResource(R.drawable.switchoff);
                } else if (switches.get(1).isSwitchState() == false) {
                    switches.get(1).setSwitchState(true);
                    switchButton2.setImageResource(R.drawable.switchon);
                }

                if(switches.get(1).isRoomState() == true) {
                    // TODO add score
                    if(switches.get(1).getIsSwitchedByAI() == true) {
                        scoreValue += POINTS_GAINED;
                        updateHUD(moneyValue, scoreValue);
                        streakValue++;
                        checkIfStreakBonus(streakValue);
                        switches.get(1).setIsSwitchedByAI(false);
                    }

                    switches.get(1).setRoomState(false);
                    turnOffRoom(switches.get(1).getRoomNumber());
                } else if (switches.get(1).isRoomState() == false) {
                    streakValue=0;

                    switches.get(1).setRoomState(true);
                    turnOnRoom(switches.get(1).getRoomNumber());
                }
            }
        });

        switchButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switches.get(2).isSwitchState() == true) {
                    switches.get(2).setSwitchState(false);
                    switchButton3.setImageResource(R.drawable.switchoff);
                } else if (switches.get(2).isSwitchState() == false) {
                    switches.get(2).setSwitchState(true);
                    switchButton3.setImageResource(R.drawable.switchon);
                }

                if(switches.get(2).isRoomState() == true) {
                    // TODO add score
                    if(switches.get(2).getIsSwitchedByAI() == true) {
                        scoreValue += POINTS_GAINED;
                        updateHUD(moneyValue, scoreValue);
                        streakValue++;
                        checkIfStreakBonus(streakValue);
                        switches.get(2).setIsSwitchedByAI(false);
                    }

                    switches.get(2).setRoomState(false);
                    turnOffRoom(switches.get(2).getRoomNumber());
                } else if (switches.get(2).isRoomState() == false) {
                    streakValue=0;

                    switches.get(2).setRoomState(true);
                    turnOnRoom(switches.get(2).getRoomNumber());
                }
            }
        });

        switchButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switches.get(3).isSwitchState() == true) {
                    switches.get(3).setSwitchState(false);
                    switchButton4.setImageResource(R.drawable.switchoff);
                } else if (switches.get(3).isSwitchState() == false) {
                    switches.get(3).setSwitchState(true);
                    switchButton4.setImageResource(R.drawable.switchon);
                }

                if(switches.get(3).isRoomState() == true) {
                    // TODO add score
                    if(switches.get(3).getIsSwitchedByAI() == true) {
                        scoreValue += POINTS_GAINED;
                        updateHUD(moneyValue, scoreValue);
                        streakValue++;
                        checkIfStreakBonus(streakValue);
                        switches.get(3).setIsSwitchedByAI(false);
                    }

                    switches.get(3).setRoomState(false);
                    turnOffRoom(switches.get(3).getRoomNumber());
                } else if (switches.get(3).isRoomState() == false) {
                    streakValue=0;

                    switches.get(3).setRoomState(true);
                    turnOnRoom(switches.get(3).getRoomNumber());
                }
            }
        });
    }

    private void checkIfStreakBonus(int streakValue) {
        if(streakValue == 5){
            scoreValue += 20;
            updateHUD(moneyValue, scoreValue);
            Toast.makeText(getBaseContext(), "STREAK 5 BIATCH!!!",
                    Toast.LENGTH_SHORT).show();
        } else
        if(streakValue == 10){
            scoreValue += 50;
            Toast.makeText(getBaseContext(), "STREAK 10 BIATCH!!!",
                    Toast.LENGTH_SHORT).show();
            updateHUD(moneyValue, scoreValue);
        }
    }

    public void turnOffRoom(int roomNumber) {
        switch (roomNumber)
        {
            case 1: room1Box.setImageResource(R.drawable.roomplaceholder4); break;
            case 2: room2Box.setImageResource(R.drawable.roomplaceholder4); break;
            case 3: room3Box.setImageResource(R.drawable.roomplaceholder4); break;
            case 4: room4Box.setImageResource(R.drawable.roomplaceholder4); break;
        }
    }

    public void turnOnRoom(int roomNumber) {
        switch (roomNumber)
        {
            case 1: room1Box.setImageResource(R.drawable.litroomplaceholder); break;
            case 2: room2Box.setImageResource(R.drawable.litroomplaceholder); break;
            case 3: room3Box.setImageResource(R.drawable.litroomplaceholder); break;
            case 4: room4Box.setImageResource(R.drawable.litroomplaceholder); break;
        }
    }


    public void refreshSwitches() {
        switches = new ArrayList<>();
        Switch s;
        int[] choicesArray = { 1, 2, 3, 4 };
        int[] switchChoiceArray = {0,1,2,3};
        choicesArray = shuffleArray(choicesArray);
        switchChoiceArray = shuffleArray(switchChoiceArray);
        System.out.println("HELLO "+switchChoiceArray[0]);
        System.out.println("HELLO "+switchChoiceArray[0]);
        boolean startingRoomState = false;
        boolean startingSwitchState = false;

        for (int i = 0; i < choicesArray.length; i++)
        {
            s = new Switch(switchChoiceArray[i],choicesArray[i],startingSwitchState, startingRoomState);
            switches.add(s);
        }

        for(int j = 0; j < switches.size(); j++)
        {
            updateComponents(switches.get(j).getSwitchNumber(), switches.get(j).getRoomNumber(), switches.get(j).isSwitchState(), switches.get(j).isRoomState());

        }
        for(int i = 0; i < switches.size(); i++)
        {
            switches.get(i).setSwitchNumber(i);

        }
        for (int j = 0; j < switches.size(); j++ )
        System.out.println("switchNumber: " + switches.get(j).getSwitchNumber() + " room "+ switches.get(j).getRoomNumber() );
    }

    public void updateComponents(int switchNumber, int roomNumber, boolean switchState, boolean roomState)
    {
        switch (switchNumber)
        {
            case 0:
                if(switchState == true)
                {
                    switchButton1.setImageResource(R.drawable.switchon);
                    //switches.get(switchNumber).setSwitchState(true);
                }
                else
                {
                    switchButton1.setImageResource(R.drawable.switchoff);
                    //switches.get(switchNumber).setSwitchState(false);
                }
                break;
            case 1:
                if(switchState == true)
                {
                    switchButton2.setImageResource(R.drawable.switchon);
                //switches.get(switchNumber).setSwitchState(true);
                }
                else
                {
                    switchButton2.setImageResource(R.drawable.switchoff);
                //switches.get(switchNumber).setSwitchState(false);
                }
                break;
            case 2:
                if(switchState == true)
                {
                    switchButton3.setImageResource(R.drawable.switchon);
                    //switches.get(switchNumber).setSwitchState(true);
                }
                else
                {
                    switchButton3.setImageResource(R.drawable.switchoff);
                    //switches.get(switchNumber).setSwitchState(false);
                }
                break;
            case 3:
                if(switchState == true)
                {
                    switchButton4.setImageResource(R.drawable.switchon);
                    //switches.get(switchNumber).setSwitchState(true);
                }
                else
                {
                    switchButton4.setImageResource(R.drawable.switchoff);
                    //switches.get(switchNumber).setSwitchState(false);
                }
                break;
        }

        switch (roomNumber)
        {
            case 1:
                if(roomState == true)
                {
                    room1Box.setImageResource(R.drawable.litroomplaceholder);
                    //switches.get(switchNumber).setRoomState(true);
                }
                else
                {
                    room1Box.setImageResource(R.drawable.roomplaceholder4);
                    //switches.get(switchNumber).setRoomState(false);
                }
                break;
            case 2:
                if(roomState == true)
                {
                    room2Box.setImageResource(R.drawable.litroomplaceholder);
                    //switches.get(switchNumber).setRoomState(true);
                }
                else
                {
                    room2Box.setImageResource(R.drawable.roomplaceholder4);
                    //switches.get(switchNumber).setRoomState(false);
                }
                break;
            case 3:
                if(roomState == true)
                {
                    room3Box.setImageResource(R.drawable.litroomplaceholder);
                    //switches.get(switchNumber).setRoomState(true);
                }
                else
                {
                    room3Box.setImageResource(R.drawable.roomplaceholder4);
                    //switches.get(switchNumber).setRoomState(false);
                }
                break;
            case 4:
                if(roomState == true)
                {
                    room4Box.setImageResource(R.drawable.litroomplaceholder);
                    //switches.get(switchNumber).setRoomState(true);
                }
                else
                {
                    room4Box.setImageResource(R.drawable.roomplaceholder4);
                    //switches.get(switchNumber).setRoomState(false);
                }
                break;
        }
    }




    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public int[] shuffleArray(int[] choicesArray) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = choicesArray.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = choicesArray[index];
            choicesArray[index] = choicesArray[i];
            choicesArray[i] = a;
        }
        return choicesArray;
    }

    public boolean getRandomBoolean() {
        return statusRandom.nextBoolean();
    }

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            int seconds = time;
            // for testing only
            TextView timeTextView = (TextView) findViewById(R.id.tv_time);
            timeTextView.setText(String.format("%d",seconds));
            time++;

            // TODO randomize room status
            randomizeAllRoomStatus();
            updateMoneyValue();

            updateHUD(moneyValue, scoreValue);

            timerHandler.postDelayed(this, RANDOMIZE_SPEED);
        }
    };

    public void updateHUD(int updatedMoney, int updatedScore){
        moneyTextView.setText(String.format("%d", updatedMoney));
        scoreTextView.setText(String.format("%d", updatedScore));
    }

    public void updateMoneyValue(){
        int totalPointsLost=0;
        for(Switch switchObject : switches){
            if(switchObject.getRoomState()==true)
                totalPointsLost += POINTS_LOST;
        }
        playDeductionAnimation(totalPointsLost);
        moneyValue -= totalPointsLost;
    }

    public void playDeductionAnimation(int totalPointsLost) {
        if(totalPointsLost == 0)
            deductionTextView.setVisibility(View.GONE);
        else {
            deductionTextView.setText("-"+String.format("%d", totalPointsLost));
            deductionTextView.startAnimation(slideDownAnim);
            slideDownAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    deductionTextView.startAnimation(fadeOutAnim);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }

    public void randomizeAllRoomStatus(){
        // get number of lit rooms
        int numOfLitRooms=0;
        for(int i=0; i<switches.size(); i++){
            Switch switchObject = switches.get(i);
            if(switchObject.isRoomState() == true) {
                numOfLitRooms++;
            }
        }
        //randomize room states
        for(int i=0; i<switches.size(); i++){
            Switch switchObject = switches.get(i);
            if(numOfLitRooms<POSSIBLE_LIGHTS_ON) {
                if (switchObject.isRoomState() == false) {
                    Boolean roomState = getRandomBoolean();
                    if(roomState == true) {
                        numOfLitRooms++;
                        switchObject.setRoomState(roomState);
                        switchObject.setIsSwitchedByAI(true);
                        updateComponents(i, switchObject.getRoomNumber(), switchObject.getSwitchState(), switchObject.getRoomState());
                    }
                }
            } else break;
        }
    }

}
