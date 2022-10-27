package com.barisdundar.catchthethief;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
TextView timetext1;
TextView scoretext;

ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView [] image1Array;
    Handler handler;
    Runnable runnable;
int scor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timetext1=findViewById(R.id.timeTextView);
        scoretext=findViewById(R.id.scoreTextView);
        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);


        image1Array=new ImageView[] {imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        hideImages();
        timetext1.setText("Time: 0");
     scor=0;
        CountDownTimer countDownTimer=new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timetext1.setText("Time: " + (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                timetext1.setText("Time off");
                handler.removeCallbacks(runnable);
                {
                    for (ImageView image : image1Array) {
                        image.setVisibility(View.INVISIBLE);
                    }
                }


            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart The Game?");
                alert.setMessage("Are you sure restart the game");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //restart
                        Intent intent=getIntent()
                                ;
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Game is finished",Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();
        }
        }.start();
    }
    public void score(View view){
       scor++;
        scoretext.setText("Score: "+ scor);
    }

    public void hideImages(){
        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {

                for (ImageView image : image1Array) {
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(9);
                image1Array[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,500);

            }
        };
        handler.post(runnable);

    }
}