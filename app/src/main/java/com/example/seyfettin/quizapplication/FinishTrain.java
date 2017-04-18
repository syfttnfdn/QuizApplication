package com.example.seyfettin.quizapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class FinishTrain extends Activity {
    Button btnQuiz, btnBack;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_finish_train);

        btnQuiz = (Button) findViewById(R.id.btnQuiz);
        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Starting Quiz...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(FinishTrain.this, Testing.class);
                startActivity(intent);
            }
        });

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Back to Menu...", Toast.LENGTH_SHORT).show();
                Intent intentBack = new Intent(FinishTrain.this, Menu.class);
                startActivity(intentBack);
            }
        });
    }
}
