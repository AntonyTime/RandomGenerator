package com.antonytime.randomgenerator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {

    private EditText startNum;
    private EditText endNum;
    private TextView randomNumber;
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
    }

    public void generate(View view){



        int max = Integer.parseInt(endNum.getText().toString());
        int min = Integer.parseInt(startNum.getText().toString());
        int result = 0;

        Random generator = new Random();

        if (min < max){

            if (touches < 1){
                image.setImageResource(R.drawable.dice_red);
                touches++;
            } else {
                touches = 0;
                image.setImageResource(R.drawable.dice);
            }

            result = generator.nextInt(max - min) + min;

        } else {
            Toast.makeText(this,"Invalid range",Toast.LENGTH_SHORT).show();
        }

        randomNumber.setText(String.valueOf(result));
    }
}
