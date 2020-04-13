package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    Button playAgainButton;
    Button button0;Button button1;Button button2;Button button3;
    TextView sumTextView;
    TextView answerAccuracy;
    TextView points;
    TextView timer;
    ConstraintLayout gameRelativeLayout;

    ArrayList<Integer> answers= new ArrayList<Integer>(); //list of options created
    int locationOfCorrectAnswer;
    int score=0;
    int totalQ=0;
    /*-----------------------------------------------------------------------------*/
    public void playAgain(View view){

        score=0;
        totalQ=0;
        timer.setText("30s");
        points.setText("0/0");
        answerAccuracy.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        questionGenerator();

        //enabling option buttons
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);


        new CountDownTimer(30500,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                //countdown is counting down(every second)
                timer.setText(Long.toString(millisUntilFinished/1000)+ "s");
            }

            @Override
            public void onFinish() {
                //countdown is finished(after 10 seconds)
                answerAccuracy.setTextColor(Color.parseColor("#000000"));
                answerAccuracy.setText("DONE! Your Score: "+Integer.toString(score)+"/"+Integer.toString(totalQ));
                timer.setText("0s");
                playAgainButton.setVisibility(View.VISIBLE);

                //disabling option buttons
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);

            }
        }.start();
    }

    /*----------------------------------------------------------------------------*/
    public void questionGenerator(){

        Random rand=new Random();
        //generating random values for question
        int a = rand.nextInt(49); //no b/w 0 and 20
        int b = rand.nextInt(49); //no b/w 0 and 20

        sumTextView.setText( Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer=rand.nextInt(4); //b/w 0 to3

        answers.clear(); //clear the array list every time new question is generated
        int incorrectAnswer;
        for(int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);
            }else{
                incorrectAnswer=rand.nextInt(100);
                while(incorrectAnswer==a+b){
                    incorrectAnswer=rand.nextInt(100);
                }
                answers.add(incorrectAnswer);
            }
        }
        //setting the options
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    /*----------------------------------------------------------------------------*/
    public void chooseAnswer(View view){
        Log.i("Tag",view.getTag().toString()); //tag is an integer
        totalQ++;
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            Log.i("accuracy","Correct");
            score++;
            answerAccuracy.setTextColor(Color.parseColor("#3DBA42"));
           answerAccuracy.setText("CORRECT :D");
        }else{
            answerAccuracy.setTextColor(Color.parseColor("#E3382C"));
            answerAccuracy.setText("INCORRECT :(");
        }
        points.setText(Integer.toString(score)+"/"+Integer.toString(totalQ));
        questionGenerator();
    }

    /*----------------------------------------------------------------------------*/
    public void startGame(View view){
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }

    /*----------------------------------------------------------------------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton= (Button)findViewById(R.id.startButton);
        playAgainButton=(Button)findViewById(R.id.playAgainButton);
        sumTextView=(TextView)findViewById(R.id.queryTextView);
        answerAccuracy=(TextView)findViewById(R.id.resultTextView);
        timer=(TextView)findViewById(R.id.timerTextView);
        points=(TextView)findViewById(R.id.pointsTextView);
        button0=(Button)findViewById(R.id.button0);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);

        gameRelativeLayout=(ConstraintLayout)findViewById(R.id.gameRelativeLayout);

        gameRelativeLayout.setVisibility(View.INVISIBLE);





    }
    /*----------------------------------------------------------------------------*/
}
