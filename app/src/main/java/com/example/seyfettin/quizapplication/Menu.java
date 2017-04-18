package com.example.seyfettin.quizapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Menu extends Activity {
    public Button trainBtn, testBtn, addWordBtn, exitBtn;

    public void moveTrainPart(){
        trainBtn = (Button) findViewById(R.id.btnTrain);
        trainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTrain = new Intent(Menu.this, Training.class);
                startActivity(intentTrain);
            }
        });
    }
    public void moveTestPart(){
        testBtn = (Button) findViewById(R.id.btnTest);
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTest = new Intent(Menu.this, Testing.class);
                startActivity(intentTest);
            }
        });
    }
    public void moveAddWordPart(){
        addWordBtn = (Button) findViewById(R.id.btnAddWord);
        addWordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddWord = new Intent(Menu.this, AddingWord.class);
                startActivity(intentAddWord);
            }
        });
    }
    public  void exitPart(){
        exitBtn = (Button) findViewById(R.id.btnExit);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu);

        moveTrainPart();
        moveTestPart();
        moveAddWordPart();
        exitPart();
    }
    public void exit() {
        this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
