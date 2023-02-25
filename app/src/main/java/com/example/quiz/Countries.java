package com.example.quiz;

import static com.example.quiz.R.drawable.quiz33;
import static com.example.quiz.R.drawable.textview_shape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.collection.LLRBNode;

import java.sql.Time;
import java.util.Locale;

public class Countries extends AppCompatActivity {

    TextView timer,Question,a,b,c,d;
    Button Quit,Next,fix;
    String q,aa,bb,cc,dd,Ans;
    int count;
    int QNumber=1;
    String userAnswer;
    int resultCode;
    int currentScore=0;
    String bestScore;
    CountDownTimer Timer;
    private static final long START_TIME_IN_MILLIS = 45000;
    Boolean timer_ruuning;
    long time_left_in_millis = START_TIME_IN_MILLIS;
    MediaPlayer  mediaPlayer;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myref = database.getReference("Countries");
    DatabaseReference ref = database.getReference("Countries");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);
        getSupportActionBar().hide();
        mediaPlayer = MediaPlayer.create(Countries.this,R.raw.sound);

        timer = findViewById(R.id.CTimer);
        Question = findViewById(R.id.CQuestion);
        a = findViewById(R.id.Ca);
        b = findViewById(R.id.Cb);
        c = findViewById(R.id.Cc);
        d = findViewById(R.id.Cd);
        Quit = findViewById(R.id.CQuit);
        Next = findViewById(R.id.CNext);
        fix = findViewById(R.id.fixc);

        game();

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
                game();
            }
        });

        Quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quit();
            }
        });

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.setBackgroundColor(Color.YELLOW);
                b.setBackground(ContextCompat.getDrawable(Countries.this, textview_shape));
                c.setBackground(ContextCompat.getDrawable(Countries.this, textview_shape));
                d.setBackground(ContextCompat.getDrawable(Countries.this, textview_shape));
                userAnswer ="a";
                resultCode = 1;
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.setBackgroundColor(Color.YELLOW);
                userAnswer ="b";
                a.setBackground(ContextCompat.getDrawable(Countries.this, textview_shape));
                c.setBackground(ContextCompat.getDrawable(Countries.this, textview_shape));
                d.setBackground(ContextCompat.getDrawable(Countries.this, textview_shape));
                resultCode = 2;
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.setBackgroundColor(Color.YELLOW);
                userAnswer ="c";
                a.setBackground(ContextCompat.getDrawable(Countries.this, textview_shape));
                b.setBackground(ContextCompat.getDrawable(Countries.this, textview_shape));
                d.setBackground(ContextCompat.getDrawable(Countries.this, textview_shape));
                resultCode = 3;
            }
        });

        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.setBackgroundColor(Color.YELLOW);
                userAnswer ="d";
                a.setBackground(ContextCompat.getDrawable(Countries.this, textview_shape));
                b.setBackground(ContextCompat.getDrawable(Countries.this, textview_shape));
                c.setBackground(ContextCompat.getDrawable(Countries.this, textview_shape));
                resultCode = 4;
            }
        });

        fix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.setClickable(false);
                b.setClickable(false);
                c.setClickable(false);
                d.setClickable(false);
                Next.setClickable(true);
                pauseTimer();



                if(resultCode == 1){

                    if(userAnswer.equals(Ans)){
                        a.setBackgroundColor(Color.GREEN);
                        currentScore++;
                    }
                    else{
                        a.setBackgroundColor(Color.RED);
                        findAnswer();
                        quit();
                    }

                }
                if(resultCode == 2){
                    if(userAnswer.equals(Ans)){
                        currentScore++;
                        b.setBackgroundColor(Color.GREEN);
                    }

                    else{
                        findAnswer();
                        b.setBackgroundColor(Color.RED);
                        quit();
                    }

                }
                if(resultCode == 3){
                    if(userAnswer.equals(Ans)){
                        currentScore++;
                        c.setBackgroundColor(Color.GREEN);
                    }

                    else
                    {
                        findAnswer();
                        c.setBackgroundColor(Color.RED);
                        quit();
                    }

                }
                if(resultCode == 4){
                    if(userAnswer.equals(Ans)){
                        currentScore++;
                        d.setBackgroundColor(Color.GREEN);
                    }

                    else{
                        findAnswer();
                        d.setBackgroundColor(Color.RED);
                        quit();
                    }

                }

            }
        });

    }




    public void startTimer(){

        Timer = new CountDownTimer(time_left_in_millis,1000) {
            @Override
            public void onTick(long l) {
                time_left_in_millis = l;
                updateText();
                mediaPlayer.start();
            }

            @Override
            public void onFinish() {
                timer_ruuning = false;
                Question.setText("Sorry!! Time is Up");
                quit();
                mediaPlayer.stop();
            }
        }.start();
        timer_ruuning = true;
    }

    public void resetTimer(){
        time_left_in_millis = START_TIME_IN_MILLIS;
        updateText();
        mediaPlayer.seekTo(0);
    }

    public void pauseTimer(){
        Timer.cancel();
        timer_ruuning = false;
        mediaPlayer.pause();
    }

    public void updateText(){
        int second = (int) (time_left_in_millis/1000)%45;
        String time_left = String.format(Locale.getDefault(),"%02d",second);
        timer.setText(time_left);
    }

    public void game(){

        a.setClickable(true);
        b.setClickable(true);
        c.setClickable(true);
        d.setClickable(true);
        Next.setClickable(true);
        a.setBackground(ContextCompat.getDrawable(Countries.this, textview_shape));
        b.setBackground(ContextCompat.getDrawable(Countries.this, textview_shape));
        c.setBackground(ContextCompat.getDrawable(Countries.this, textview_shape));
        d.setBackground(ContextCompat.getDrawable(Countries.this, textview_shape));
        startTimer();

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                count = (int) snapshot.child("Questions").getChildrenCount();

                q = snapshot.child("Questions").child(String.valueOf(QNumber)).child("q").getValue().toString();
                aa = snapshot.child("Questions").child(String.valueOf(QNumber)).child("a").getValue().toString();
                bb = snapshot.child("Questions").child(String.valueOf(QNumber)).child("b").getValue().toString();
                cc = snapshot.child("Questions").child(String.valueOf(QNumber)).child("c").getValue().toString();
                dd = snapshot.child("Questions").child(String.valueOf(QNumber)).child("d").getValue().toString();
                Ans = snapshot.child("Questions").child(String.valueOf(QNumber)).child("Ans").getValue().toString();
                bestScore = snapshot.child("Score").child("BestScore").getValue().toString();

                Question.setText(q);
                a.setText(aa);
                b.setText(bb);
                c.setText(cc);
                d.setText(dd);

                if(QNumber < count){
                    QNumber++;
                }
                else{
                    Toast.makeText(Countries.this, "You Answered All Questions", Toast.LENGTH_SHORT).show();
                    quit();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Countries.this, "Error Occured", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void quit(){

        mediaPlayer.stop();
        ref.child("Score").child("CurrentScore").setValue(""+currentScore);
        if(currentScore > Integer.valueOf(bestScore)){
            ref.child("Score").child("BestScore").setValue(""+currentScore);
        }

        Intent intent = new Intent(Countries.this,Result.class);
        startActivity(intent);
        finish();
    }

    public void findAnswer(){
        if(Ans.equals("a"))
            a.setBackgroundColor(Color.GREEN);
        else if(Ans.equals("b"))
            b.setBackgroundColor(Color.GREEN);
        else if(Ans.equals("c"))
            c.setBackgroundColor(Color.GREEN);
        else if(Ans.equals("d"))
            d.setBackgroundColor(Color.GREEN);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
    }

}