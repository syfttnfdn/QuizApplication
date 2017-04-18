package com.example.seyfettin.quizapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DbHelperQ  extends SQLiteOpenHelper {

    private static final int DB_VERSION = 2;
    private static final String DB_NAME1 = "quizdb1";
    private static final String DB_TABLE1 = "quiztable1";

    private static final String KEY_ID = "id";
    private static final String KEY_WORDS = "words";
    private static final String KEY_OPTA = "optA";
    private static final String KEY_OPTB = "optB";
    private static final String KEY_OPTC = "optC";
    private static final String KEY_ANSWER = "answer";

    private SQLiteDatabase dbaseQ;

    public DbHelperQ(Context context){
        super(context,DB_NAME1,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db1) {
        dbaseQ = db1;
        String sqlQuery1 = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT )", DB_TABLE1, KEY_ID, KEY_WORDS, KEY_OPTA, KEY_OPTB, KEY_OPTC, KEY_ANSWER);
        Log.d("TaskDBHelper1", "Query to form table1" + sqlQuery1);
        db1.execSQL(sqlQuery1);
        addQuestions();
    }

    private void addQuestions() {
        Words w1 = new Words("Size", "Boyut", "Genişlik", "Uzunluk", "Boyut");
        this.addQuestionToDB(w1);
        Words w2 = new Words("New", "Yıl", "Yeni", "Uzun", "Yeni");
        this.addQuestionToDB(w2);
        Words w3 = new Words("Old", "Bütün", "Kısa", "Eski", "Eski");
        this.addQuestionToDB(w3);
    }

    public void addQuestionToDB(Words w){
        ContentValues values = new ContentValues();
        values.put(KEY_WORDS, w.getWord());
        values.put(KEY_OPTA,w.getOptA());
        values.put(KEY_OPTB,w.getOptB());
        values.put(KEY_OPTC,w.getOptC());
        values.put(KEY_ANSWER,w.getAnswer());
        dbaseQ.insert(DB_TABLE1, null, values);
    }

    public List<Words> getAllQuestions(){
        List <Words> questionList = new ArrayList<>();

        dbaseQ = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+DB_TABLE1;
        Cursor cursor = dbaseQ.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                Words q = new Words();
                q.setId(cursor.getInt(0));
                q.setWord(cursor.getString(1));
                q.setOptA(cursor.getString(2));
                q.setOptB(cursor.getString(3));
                q.setOptC(cursor.getString(4));
                q.setAnswer(cursor.getString(5));
                questionList.add(q);

            }while (cursor.moveToNext());
        }
        return questionList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE1);
        onCreate(db);
    }
}
