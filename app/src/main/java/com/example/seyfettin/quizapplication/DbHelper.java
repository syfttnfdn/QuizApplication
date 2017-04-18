package com.example.seyfettin.quizapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "quizdb";
    private static final String DB_TABLE = "quiztable";
    private static final String DB_TABLE1 = "quiztable1";

    private static final String KEY_ID = "id";
    private static final String KEY_WORDS = "words";
    private static final String KEY_MEANING = "meaning";

    private SQLiteDatabase dbase;

    public DbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String sqlQuery = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)", DB_TABLE, KEY_ID, KEY_WORDS, KEY_MEANING);
        Log.d("TaskDBHelper", "Query to form table" + sqlQuery);
        db.execSQL(sqlQuery);
        addWords();
    }

    private void addWords(){
        Words w1 = new Words("Size", "Boyut");
        this.addWordsToDB(w1);
        Words w2 = new Words("New", "Yeni");
        this.addWordsToDB(w2);
        Words w3 = new Words("Old", "Eski");
        this.addWordsToDB(w3);
    }

    public void addWordsToDB(Words w){
        ContentValues values = new ContentValues();
        values.put(KEY_WORDS, w.getWord());
        values.put(KEY_MEANING,w.getMeaning());
        dbase.insert(DB_TABLE, null, values);
    }

    public List <Words> getAllWords(){
        List <Words> wordsList = new ArrayList<>();

        dbase = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+DB_TABLE;
        Cursor cursor = dbase.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                Words w = new Words();
                w.setId(cursor.getInt(0));
                w.setWord(cursor.getString(1));
                w.setMeaning(cursor.getString(2));
                wordsList.add(w);

            }while (cursor.moveToNext());
        }
        return wordsList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
        onCreate(db);
    }
}