package com.example.insyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CheckInQuestions extends AppCompatActivity {

    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestion";

    private TextView textViewQuestion;
    private TextView oeqTextView;
    private TextView textViewQuestionCount;
    private RadioGroup rbGroup;
    private RadioButton rbtn1;
    private RadioButton rbtn2;
    private RadioButton rbtn3;
    private RadioButton rbtn4;
    private RadioButton rbtn5;
    private EditText oeqEditText;
    private Button btnConfirmNext;

    private ColorStateList textColorDefaultRb;

    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private static int num = 0;

    private int score;
    private int score1;
    private int score2;
    private int score3;
    private int score4;
    private int score5;
    String oeqText = "";
    String oeqText1 = "";
    String oeqText2 = "";
    String oeqText3 = "";
    String oeqText4 = "";
    String oeqText5 = "";

    private boolean answered;

    private long backPressedTime;

    ArrayList<Answers> userAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_questions);

        textViewQuestion = findViewById(R.id.questionTextView);
        oeqTextView = findViewById(R.id.oeqTextView);
        textViewQuestionCount = findViewById(R.id.questionCountTextView);
        rbGroup = findViewById(R.id.radioGroup);
        rbtn1 = findViewById(R.id.rbtn1);
        rbtn2 = findViewById(R.id.rbtn2);
        rbtn3 = findViewById(R.id.rbtn3);
        rbtn4 = findViewById(R.id.rbtn4);
        rbtn5 = findViewById(R.id.rbtn5);
        oeqEditText = findViewById(R.id.oeqEditText);
        btnConfirmNext = findViewById(R.id.nextButton);

        textColorDefaultRb = rbtn1.getTextColors();

        loadData();

        if(savedInstanceState == null){
            CheckInDbHelper dbHelper = new CheckInDbHelper(this);
            questionList = dbHelper.getAllQuestions();
            questionCountTotal = questionList.size();

            showNextQuestion();
        }else{
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            questionCountTotal = questionList.size();
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questionList.get(questionCounter - 1);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);
        }

        btnConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oeqText = oeqEditText.getText().toString();
                if(!answered){
                    if(rbtn1.isChecked() || rbtn2.isChecked() || rbtn3.isChecked() || rbtn4.isChecked() || rbtn5.isChecked()){
                        checkAnswer(questionCounter);
                        if(!oeqText.matches("")){
                        }else{
                            Toast.makeText(CheckInQuestions.this, "Please fill in the box", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(CheckInQuestions.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    showNextQuestion();
                }
            }
        });

    }

    private void showNextQuestion(){
        rbtn1.setTextColor(textColorDefaultRb);
        rbtn2.setTextColor(textColorDefaultRb);
        rbtn3.setTextColor(textColorDefaultRb);
        rbtn4.setTextColor(textColorDefaultRb);
        rbtn5.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();
        if(questionCounter < questionCountTotal){
            currentQuestion = questionList.get(questionCounter);
            textViewQuestion.setText(currentQuestion.getQuestion());
            oeqTextView.setText(currentQuestion.getOeq());
            rbtn1.setText(currentQuestion.getOption1());
            rbtn2.setText(currentQuestion.getOption2());
            rbtn3.setText(currentQuestion.getOption3());
            rbtn4.setText(currentQuestion.getOption4());
            rbtn5.setText(currentQuestion.getOption5());
            oeqEditText.getText().clear();

            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            btnConfirmNext.setText("Confirm");
        }else{
            insertItem(score1, score2, score3, score4, score5, oeqText1, oeqText2, oeqText3, oeqText4, oeqText5);
            saveData();
            finishCheckIn();
        }

    }

    private void checkAnswer(int num){
        answered = true;
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;
        score = answerNr;
        switch (num){
            case 1:
                if(answerNr == 1){
                    score1 = 2;
                }else if(answerNr == 2){
                    score1 = 4;
                }else if(answerNr == 3){
                    score1 = 6;
                }else if(answerNr == 4){
                    score1 = 8;
                }else{
                    score1 = 9;
                }
                oeqText = oeqText1;
                break;
            case 2:
                score2 = answerNr;
                oeqText = oeqText2;
                break;
            case 3:
                score3 = answerNr;
                oeqText = oeqText3;
                break;
            case 4:
                if(answerNr == 1){
                    score4 = 10;
                }else if(answerNr == 2){
                    score4 = 20;
                }else if(answerNr == 3){
                    score4 = 30;
                }else if(answerNr == 4){
                    score4 = 40;
                }else if(answerNr == 5){
                    score4 = 50;
                }
                oeqText = oeqText4;
                break;
            case 5:
                score5 = answerNr;
                oeqText = oeqText5;
                break;
            default:
                score1 = score2 = score3 = score4 = score5 = 999;
        }
        checkEnd();
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("User Answers", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userAnswers);
        editor.putString("User Answers", json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("User Answers", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("User Answers", null);
        Type type = new TypeToken<ArrayList<Answers>>() {}.getType();
        userAnswers = gson.fromJson(json, type);

        if(userAnswers == null){
            userAnswers = new ArrayList<>();
        }
    }

    private void checkEnd(){
        if(questionCounter < questionCountTotal){
            btnConfirmNext.setText("Next");
        }else{
            btnConfirmNext.setText("Finish");
        }
    }

    private void finishCheckIn(){
        finish();
    }

    public void onBackPressed(){
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            finishCheckIn();
        }else{
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    protected void onSaveInstanceState (Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);
    }

    private void insertItem(int s1, int s2, int s3, int s4, int s5, String ans1, String ans2, String ans3, String ans4, String ans5){
        Calendar currentTime = Calendar.getInstance();
        int month = currentTime.get(Calendar.MONTH) + 1;
        int day = currentTime.get(Calendar.DAY_OF_MONTH);
        String date = month + "/" + day;
        userAnswers.add(new Answers(date, s1, s2, s3, s4, s5, ans1, ans2, ans3, ans4, ans5));
    }

    private void increment(){
        num++;
    }
}