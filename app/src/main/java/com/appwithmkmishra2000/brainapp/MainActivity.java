package com.appwithmkmishra2000.brainapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView questionView;
    TextView resultView;
    TextView scoreView;
    TextView timeView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int correctAnsLocation;
    int inCorrectAns;
    Button startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgain;
    CountDownTimer countSecond;
    int score =0;
    int noOfQuestions =0;



    public void updateButtons(ArrayList<Integer> answers){
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }

    public void generateQuestion(){
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        int ans = a+b;

        questionView.setText(Integer.toString(a)+"+"+Integer.toString(b)+"=");

        correctAnsLocation = rand.nextInt(4);

        answers.clear();

        for (int i =0; i<4; i++){
            if(i == correctAnsLocation){
                answers.add(ans);
            }else{
                inCorrectAns = rand.nextInt(41);
                while(inCorrectAns==ans){
                    inCorrectAns = rand.nextInt(41);
                }
                answers.add(inCorrectAns);
            }
        }
        updateButtons(answers);
    }

    public void declareResult(){

        button0.setVisibility(View.INVISIBLE);
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);

        playAgain.setVisibility(View.VISIBLE);

    }


    public void playAgain(View view){
        score =0;
        noOfQuestions=0;
        timeView.setText("30s");
        playAgain.setVisibility(View.INVISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);

        generateQuestion();


        countSecond = new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeView.setText(Integer.toString((int) millisUntilFinished/1000)+"s");

            }

            @Override
            public void onFinish() {
                declareResult();
                resultView.setText("DONE...");
                MediaPlayer myplayer = MediaPlayer.create(getApplicationContext(),R.raw.funnyvoice);
                myplayer.start();
            }
        }.start();

    }



    public void chooseAnswer (View view){

        noOfQuestions++;
        Log.i("Tag", (String) view.getTag());

        if(view.getTag().toString().equals(Integer.toString(correctAnsLocation))){
            score++;
            resultView.setText("Correct");
        }else{
            resultView.setText("Incorrect");
        }

        scoreView.setText(Integer.toString(score)+"/"+Integer.toString(noOfQuestions));

        generateQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionView = (TextView)findViewById(R.id.QuestionView);
        resultView = (TextView)findViewById(R.id.resultView);
        scoreView = (TextView)findViewById(R.id.scoreView);
        timeView = (TextView)findViewById(R.id.timeView);
        startButton = (Button)findViewById(R.id.startButton);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        playAgain = (Button)findViewById(R.id.playAgainButton);


        playAgain.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }
}
