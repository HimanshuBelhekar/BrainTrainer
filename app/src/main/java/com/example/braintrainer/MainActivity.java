package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.max;

public class MainActivity extends AppCompatActivity {

    Button playButton;
    Button option1;
    Button option2;
    Button option3;
    Button option4;
    Button playagainButton;
    TextView timerTextView;
    TextView questionTextView;
    TextView scoreTextView;
    TextView resultTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();

    int answerLocation;
    int score;
    int totalQue;
    int ans;

    public void playFunction(View view){
        playButton.setVisibility(View.INVISIBLE);
        option1.setVisibility(View.VISIBLE);
        option2.setVisibility(View.VISIBLE);
        option3.setVisibility(View.VISIBLE);
        option4.setVisibility(View.VISIBLE);
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);
        playagainButton.setVisibility(View.INVISIBLE);

        timerTextView.setVisibility(View.VISIBLE);
        questionTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);

        score = 0;
        totalQue = 0;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(totalQue));

        int time = 30;

        CountDownTimer countDownTimer = new CountDownTimer(time*1000+100,1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                option1.setEnabled(false);
                option2.setEnabled(false);
                option3.setEnabled(false);
                option4.setEnabled(false);
                playagainButton.setVisibility(View.VISIBLE);
                resultTextView.setVisibility(View.INVISIBLE);
            }
        };
        countDownTimer.start();
    }

    public void createQuestion(){
        int a = new Random().nextInt(21);
        int b = new Random().nextInt(21);

        ans = a+b;
        questionTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        answerLocation = new Random().nextInt(4);
        answers.clear();

        for(int i=0;i<4;i++){
            if(i==answerLocation){
                answers.add(ans);
            }else{
                int min = max(0,ans-10);
                int max = ans+10;
                int wrongAnswer = new Random().nextInt((max-min)+1) + min;
                while(wrongAnswer == ans){
                    wrongAnswer = new Random().nextInt((max-min)+1) + min;
                }
                answers.add(wrongAnswer);
            }
        }

        option1.setText(Integer.toString(answers.get(0)));
        option2.setText(Integer.toString(answers.get(1)));
        option3.setText(Integer.toString(answers.get(2)));
        option4.setText(Integer.toString(answers.get(3)));
    }

    public void checkAnswer(View view){
        String message;
        Button b = (Button) view;
        if(Integer.toString(ans).equals(b.getText().toString())){
           message = "Correct!";
            score++;
        }else{
            message = "Wrong!";
        }
        totalQue++;
        resultTextView.setVisibility(View.VISIBLE);
        resultTextView.setText(message);
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(totalQue));
        createQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = (Button) findViewById(R.id.playButton);
        option1 = (Button) findViewById(R.id.buttonoption1);
        option2 = (Button) findViewById(R.id.buttonoption2);
        option3 = (Button) findViewById(R.id.buttonoption3);
        option4 = (Button) findViewById(R.id.buttonoption4);
        playagainButton = (Button) findViewById(R.id.playagainButton);

        timerTextView = (TextView) findViewById(R.id.timerTextView);
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);

        createQuestion();

    }
}