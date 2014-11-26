package com.antonytime.randomgenerator;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

import java.util.Random;

public class MainActivity extends Activity {

    private EditText startNum;
    private EditText endNum;
    private TextView randomNumber;
    private Switch startSwitch;
    private Switch endSwitch;
    private ImageView image;
    private int touches = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        startNum  = (EditText) findViewById(R.id.etStartNum);
        endNum = (EditText) findViewById(R.id.etEndNum);
        randomNumber = (TextView) findViewById(R.id.txtResult);
        image = (ImageView) findViewById(R.id.imageView);
        startSwitch = (Switch) findViewById(R.id.switchStartRange);
        endSwitch = (Switch) findViewById(R.id.switchEndRange);
    }

    public void playSoundOnClick(){
        MediaPlayer mp = MediaPlayer.create(this,R.raw.dice);
        mp.start();
    }

    public void shakeImageOnClick(){
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        findViewById(R.id.imageView).startAnimation(shake);
    }

    public void vibrateOnClick(){
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(100);
    }

    public void changeImageOnClick(){
        if (touches < 1){
            image.setImageResource(R.drawable.dice_red);
            touches++;
        } else {
            touches = 0;
            image.setImageResource(R.drawable.dice);
        }
    }

    public void onClickGenerate(View view){

        if(startNum.getText().length() != 0 && endNum.getText().length() != 0){

            int max = Integer.parseInt(endNum.getText().toString());
            int min = Integer.parseInt(startNum.getText().toString());
            int result = 0;

            Random random = new Random();

            if (min < max){
                if (max - min != 1){

                    playSoundOnClick();

                    shakeImageOnClick();

                    vibrateOnClick();

                    changeImageOnClick();

                    if((startSwitch.isChecked() && !endSwitch.isChecked())){
                        result = random.nextInt(max - min) + min;
                    }
                    if((!startSwitch.isChecked()) && endSwitch.isChecked()){
                        min = min + 1;
                        result = random.nextInt(max - min + 1) + min;
                    }
                    if ((!startSwitch.isChecked() && !endSwitch.isChecked())) {
                        min = min + 1;
                        result = random.nextInt(max - min) + min;
                    }
                    if((startSwitch.isChecked() && endSwitch.isChecked())){
                        result = random.nextInt(max - min + 1) + min;
                    }
                } else {

                    Toast.makeText(this,"Invalid range",Toast.LENGTH_SHORT).show();

                }

            } else {

                Toast.makeText(this,"Invalid range",Toast.LENGTH_SHORT).show();

            }

            randomNumber.setText(String.valueOf(result));

        } else {

            Toast.makeText(this,"Invalid range",Toast.LENGTH_SHORT).show();

        }
    }
}
