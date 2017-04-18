package com.example.seyfettin.quizapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddingWord extends Activity {

    EditText editWord, editMean;
    Button btnAdd;

    private DbHelper words;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_adding_word);

        editWord = (EditText) findViewById(R.id.editWord);
        editMean = (EditText) findViewById(R.id.editMean);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        words = new DbHelper(this);
        db = words.getWritableDatabase();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Toast.makeText(getApplicationContext(), "Words are added !", Toast.LENGTH_SHORT).show();
                    addingInf(editWord.getText().toString(),editMean.getText().toString());
                    editMean.setText("");
                    editWord.setText("");
                }finally {
                    words.close();
                }
            }
        });
    }
    private void addingInf(String words, String meaning){
        ContentValues info = new ContentValues();
        info.put("words", words);
        info.put("meaning",meaning);
        db.insertOrThrow("quiztable", null, info);
    }
}
