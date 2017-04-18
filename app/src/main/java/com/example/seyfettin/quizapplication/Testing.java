package com.example.seyfettin.quizapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class Testing extends Activity {

    List<Words> questionList;
    double score = 0.0;
    int quid = 0;
    Words currentWords;

    Button btnNextQ, btnAnswer;
    TextView txtQ, txtAnswer;
    RadioGroup grp;
    RadioButton rbOptA, rbOptB, rbOptC, answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_testing);

        DbHelperQ dbHelperQ = new DbHelperQ(this);
        questionList = dbHelperQ.getAllQuestions();
        Collections.shuffle(questionList);
        currentWords = questionList.get(quid);

        btnNextQ = (Button) findViewById(R.id.btnNextQ);
        btnAnswer = (Button) findViewById(R.id.btnAnswer);
        txtQ = (TextView) findViewById(R.id.txtQ);
        rbOptA = (RadioButton) findViewById(R.id.rbOptA);
        rbOptB = (RadioButton) findViewById(R.id.rbOptB);
        rbOptC = (RadioButton) findViewById(R.id.rbOptC);
        txtAnswer = (TextView) findViewById(R.id.txtAnswer);
        btClick();
    }

    private void setQuestionView(){
        txtQ.setText(txtQ.getText() + currentWords.getWord());
        rbOptA.setText(currentWords.getOptA());
        rbOptB.setText(currentWords.getOptB());
        rbOptC.setText(currentWords.getOptC());
        btnAnswerClick();
    }

    public void btClick(){
        btnNextQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNextQ.setText("Next");
                if(txtAnswer.getVisibility() == View.VISIBLE) {
                    btnAnswer.setVisibility(View.VISIBLE);
                    if (quid < questionList.size()) {
                        currentWords = questionList.get(quid);
                        quid++;
                        textClean();
                        setQuestionView();
                    } else {
                        Intent intent = new Intent(Testing.this, TestingResult.class);
                        Bundle b = new Bundle();
                        b.putDouble("score", score);
                        intent.putExtras(b);
                        startActivity(intent);
                        finish();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Please First Click Answer !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void btnAnswerClick(){
        grp = (RadioGroup)findViewById(R.id.radioGroup1);

        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtAnswer.getVisibility() == View.INVISIBLE) {
                    answer = (RadioButton) findViewById(grp.getCheckedRadioButtonId());
                    if (currentWords.getAnswer().toString().equals(answer.getText().toString())) {
                        txtAnswer.setVisibility(View.VISIBLE);
                        txtAnswer.setText(txtAnswer.getText() + currentWords.getAnswer());
                        Toast.makeText(getApplicationContext(), "True", Toast.LENGTH_SHORT).show();
                        score++;
                        Log.d("Score", "Your score: " + score);
                    } else {
                        txtAnswer.setVisibility(View.VISIBLE);
                        txtAnswer.setText(txtAnswer.getText() + currentWords.getAnswer());
                        Toast.makeText(getApplicationContext(), "False", Toast.LENGTH_SHORT).show();
                        score -= 0.5;
                        Log.d("Score", "Your score: " + score);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please First Click Next !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void textClean(){
        txtQ.setText("What is the meaning of ");
        txtAnswer.setText("                      Answer is : ");
        txtAnswer.setVisibility(View.INVISIBLE);
    }
}
