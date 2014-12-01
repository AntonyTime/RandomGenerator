package com.antonytime.randomgenerator.activitys;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.antonytime.randomgenerator.R;
import com.antonytime.randomgenerator.interfaces.ImageEffects;

import java.util.Random;

public class MainActivity extends Activity implements ImageEffects{

    private EditText startNum;
    private EditText endNum;
    private TextView randomNumber;
    private Switch startSwitch;
    private Switch endSwitch;
    private ImageView image;
    private Integer touches = 0;

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

        randomNumber.setText("");
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

    public void playSoundOnClick(){
        MediaPlayer mp = MediaPlayer.create(this, R.raw.dice);
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

    public void onClickGenerate(View view){

        if (startNum.getText().length() == 0 || endNum.getText().length() == 0) {
            Toast.makeText(this,"Invalid range",Toast.LENGTH_SHORT).show();
            return;
        }

        int max = Integer.parseInt(endNum.getText().toString());
        int min = Integer.parseInt(startNum.getText().toString());
        int result = 0;

        Random random = new Random();

        if (min > max || max - min <= 1){
            Toast.makeText(this,"Invalid range",Toast.LENGTH_SHORT).show();
            return;
        }

        playSoundOnClick();

        shakeImageOnClick();

        vibrateOnClick();

        changeImageOnClick();

        if((startSwitch.isChecked() && !endSwitch.isChecked())){
            result = random.nextInt(max - min) + min;
        }
        else if((!startSwitch.isChecked()) && endSwitch.isChecked()){
            result = random.nextInt(max - min) + min + 1;
        }
        else if ((!startSwitch.isChecked())) {
            result = random.nextInt(max - 1 - min) + min + 1;
        }
        else {
            result = random.nextInt(max + 1 - min) + min;
        }

        randomNumber.setText(String.valueOf(result));
    }
}
