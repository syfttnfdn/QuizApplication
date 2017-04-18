package com.example.seyfettin.quizapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class TestingResult extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_testing_result);

        TextView scoreTxtView = (TextView) findViewById(R.id.score);
        RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBar1);
        ImageView img = (ImageView)findViewById(R.id.img1);
        Button btnExit = (Button) findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMenu = new Intent(TestingResult.this, Menu.class);
                startActivity(intentMenu);
            }
        });
        Bundle b = getIntent().getExtras();
        double score = b.getDouble("score");
        int ratingScore = (int) score;
        ratingBar.setRating(ratingScore);
        scoreTxtView.setText(String.valueOf(score));

        if(ratingScore == 0){
            img.setImageResource(R.drawable.score_0);
        }else if(ratingScore == 1){
            img.setImageResource(R.drawable.score_1);
        }else if(ratingScore == 2){
            img.setImageResource(R.drawable.score_2);
        }else if(ratingScore == 3){
            img.setImageResource(R.drawable.score_3);
        }else if(ratingScore == 4){
            img.setImageResource(R.drawable.score_4);
        }else if(ratingScore == 5){
            img.setImageResource(R.drawable.score_5);
        }
    }
}
