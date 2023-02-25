package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen2 extends AppCompatActivity {


    ImageView alphabetA,alphabetS;
    TextView sQuiz;
    Animation a,s,q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen2);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        alphabetA = findViewById(R.id.alphaA);
        alphabetS = findViewById(R.id.alphaS);
        sQuiz = findViewById(R.id.squiz);

        a = AnimationUtils.loadAnimation(this,R.anim.alphabeta);
        s = AnimationUtils.loadAnimation(this,R.anim.alphabets);
        q = AnimationUtils.loadAnimation(this,R.anim.quizanim);

        alphabetA.setAnimation(a);
        alphabetS.setAnimation(s);
        sQuiz.setAnimation(q);

        CountDownTimer timer =  new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {

                Intent i = new Intent(SplashScreen2.this, LoginActivity.class);
                startActivity(i);
                finish();

            }
        };
        timer.start();

    }
}