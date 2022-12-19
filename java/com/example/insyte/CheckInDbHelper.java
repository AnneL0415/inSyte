package com.example.insyte;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.insyte.CheckInContract.*;

import java.util.ArrayList;
import java.util.List;

public class CheckInDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CheckInQuestions.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db1;

    public CheckInDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db1 = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OEQ + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 +  " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 +  " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 +  " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 +  " TEXT, " +
                QuestionsTable.COLUMN_OPTION5 +  " TEXT" + ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);

        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db1.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db1);

    }

    private void fillQuestionsTable(){
        Question q1 = new Question("How many hours have you slept?", "What do you do before bed?", "0-2 hours", "3-4 hours", "5-6 hours", "7-8 hours", "9+ hours");
        addQuestion(q1);
        Question q2 = new Question("How often did you feel stressed?", "What may have occurred to make you feel that way?", "1 - Very Often", "2 - Somewhat Often", "3 - Sometimes", "4 - Not Often", "5 - Rarely");
        addQuestion(q2);
        Question q3 = new Question("How often have you talked to your friends and family?", "What do you talk about with your friends and family?", "1 - Rarely", "2 - Not Often", "3 - Sometimes", "4 - Somewhat Often", "5 - Very Often");
        addQuestion(q3);
        Question q4 = new Question("How much physical activity have you done?", "What exercises do you do?", "0-10 minutes", "10-20 minutes", "20-30 minutes", "30-40 minutes", "40-60+ minutes");
        addQuestion(q4);
        Question q5 = new Question("How is your diet?", "What are you eating? How much?", "1 - Unhealthy", "2 - Mediocre", "3 - Okay", "4 - Decent", "5 - Healthy");
        addQuestion(q5);
    }

    private void addQuestion(Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OEQ, question.getOeq());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_OPTION5, question.getOption5());
        db1.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    @SuppressLint("Range")
    public ArrayList<Question> getAllQuestions(){
        ArrayList<Question> questionList = new ArrayList<>();
        db1 = getReadableDatabase();
        Cursor c = db1.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOeq(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OEQ)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setOption5(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION5)));
                questionList.add(question);
            }while(c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
