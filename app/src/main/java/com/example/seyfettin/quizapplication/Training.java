package com.example.seyfettin.quizapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class Training extends Activity {

    List<Words> wordList;
    int quid = 0;
    Words currentWords;

    Button btnNext;
    TextView txtWord, txtMeaning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_training);

        DbHelper dbHelper = new DbHelper(this);
        wordList = dbHelper.getAllWords();
        Collections.shuffle(wordList);
        currentWords = wordList.get(quid);

        txtWord = (TextView) findViewById(R.id.txtWord);
        txtMeaning = (TextView) findViewById(R.id.txtMeaning);
        btnNext = (Button) findViewById(R.id.btnNext);
        btClick();
    }

    private void setWordView(){
        txtWord.setText(currentWords.getWord());
        txtMeaning.setText(currentWords.getMeaning());
        btClick();
    }

    public void btClick(){
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            btnNext.setText("Next");
                if(quid < wordList.size()) {
                    currentWords = wordList.get(quid);
                    quid++;
                    setWordView();
                }
                else{
                    Intent intent = new Intent(Training.this, FinishTrain.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Finish the Training!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
