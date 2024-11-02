package com.example.QuizApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ButtonReviewActivity extends AppCompatActivity {
    List<Question> lstQuestion = QuizActivity.questionList;
    List<Button> buttonList = new ArrayList<>();
    SQLiteDatabase db;
    TextView correctAnswer, inCorrectAnswer, txtScrore;
    Button btnExit,btnRework;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_review);
        ViewGroup rootView = (ViewGroup) findViewById(android.R.id.content);
        findButtons(rootView, buttonList);
        btnExit = findViewById(R.id.btnExit);
        btnRework = findViewById(R.id.btnRework);
        txtScrore = findViewById(R.id.score);
        correctAnswer = findViewById(R.id.correctAnswer);
        inCorrectAnswer = findViewById(R.id.inCorrectAnswer);
        int correctAnswers = getIntent().getIntExtra("correct",0);
        double score = ((double)(correctAnswers * 10) / 20);
        int inCorrectAnswers = getIntent().getIntExtra("incorrect",0);


        for(int i = 0 ;i < lstQuestion.size() ; i++) {
            if(lstQuestion.get(i).getAnswer_cr().equals(lstQuestion.get(i).getUserSelectedAnswer()) ) {
                buttonList.get(i).setBackgroundTintList(this.getResources().getColorStateList(R.color.green));
            }else  {
                buttonList.get(i).setBackgroundTintList(this.getResources().getColorStateList(R.color.error));
            }
        }

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ButtonReviewActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnRework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ButtonReviewActivity.this, StartActivity.class);
                intent.putExtra("categoryId",getIntent().getIntExtra("categoryId",0));
                intent.putExtra("categoryName",getIntent().getStringExtra("categoryName"));
                startActivity(intent);
            }
        });

        correctAnswer.setText(correctAnswers + " সঠিক বাক্য");
        inCorrectAnswer.setText(inCorrectAnswers + " ভুল বাক্য");
        txtScrore.setText(score + " বিন্দু");
    }

    private void findButtons(ViewGroup viewGroup, List<Button> buttonList) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            if (child instanceof ViewGroup) {
                findButtons((ViewGroup) child, buttonList);
            } else if (child instanceof Button) {
                buttonList.add((Button) child);
            }
        }
    }


//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.cau1: {
//                Intent intent = new Intent(ButtonReviewActivity.this, ReviewTestActivity.class);
//
//                intent.putExtra("numberQuestion",1);
//                startActivity(intent);
//                break;
//            }
//
//            case R.id.cau2: {
//                Intent intent = new Intent(ButtonReviewActivity.this, ReviewTestActivity.class);
//                intent.putExtra("numberQuestion",2);
//                startActivity(intent);
//                break;
//            }
//
//            case R.id.cau3: {
//                Intent intent = new Intent(ButtonReviewActivity.this, ReviewTestActivity.class);
//                intent.putExtra("numberQuestion",3);
//                startActivity(intent);
//                break;
//            }
//
//            case R.id.cau4: {
//                Intent intent = new Intent(ButtonReviewActivity.this, ReviewTestActivity.class);
//                intent.putExtra("numberQuestion",4);
//                startActivity(intent);
//                break;
//            }
//
//            case R.id.cau5: {
//                Intent intent = new Intent(ButtonReviewActivity.this, ReviewTestActivity.class);
//                intent.putExtra("numberQuestion",5);
//                startActivity(intent);
//                break;
//            }
//
//            case R.id.cau6: {
//                Intent intent = new Intent(ButtonReviewActivity.this, ReviewTestActivity.class);
//                intent.putExtra("numberQuestion",6);
//                startActivity(intent);
//                break;
//            }
//
//            case R.id.cau7: {
//                Intent intent = new Intent(ButtonReviewActivity.this, ReviewTestActivity.class);
//                intent.putExtra("numberQuestion",7);
//                startActivity(intent);
//                break;
//            }
//
//            case R.id.cau8: {
//                Intent intent = new Intent(ButtonReviewActivity.this, ReviewTestActivity.class);
//                intent.putExtra("numberQuestion",8);
//                startActivity(intent);
//                break;
//            }
//
//            case R.id.cau9: {
//                Intent intent = new Intent(ButtonReviewActivity.this, ReviewTestActivity.class);
//                intent.putExtra("numberQuestion",9);
//                startActivity(intent);
//                break;
//            }
//
//            case R.id.cau10: {
//                Intent intent = new Intent(ButtonReviewActivity.this, ReviewTestActivity.class);
//                intent.putExtra("numberQuestion",10);
//                startActivity(intent);
//                break;
//            }
//
//            case R.id.cau11: {
//                Intent intent = new Intent(ButtonReviewActivity.this, ReviewTestActivity.class);
//                intent.putExtra("numberQuestion",11);
//                startActivity(intent);
//                break;
//            }
//
//            case R.id.cau12: {
//                Intent intent = new Intent(ButtonReviewActivity.this, ReviewTestActivity.class);
//                intent.putExtra("numberQuestion",12);
//                startActivity(intent);
//                break;
//            }
//
//            case R.id.cau13: {
//                Intent intent = new Intent(ButtonReviewActivity.this, ReviewTestActivity.class);
//                intent.putExtra("numberQuestion",13);
//                startActivity(intent);
//                break;
//            }
//
//            case R.id.cau14: {
//                Intent intent = new Intent(ButtonReviewActivity.this, ReviewTestActivity.class);
//                intent.putExtra("numberQuestion",14);
//                startActivity(intent);
//                break;
//            }
//
//            case R.id.cau15: {
//                Intent intent = new Intent(ButtonReviewActivity.this, ReviewTestActivity.class);
//                intent.putExtra("numberQuestion",15);
//                startActivity(intent);
//                break;
//            }
//
//            case R.id.cau16: {
//                Intent intent = new Intent(ButtonReviewActivity.this, ReviewTestActivity.class);
//                intent.putExtra("numberQuestion",16);
//                startActivity(intent);
//                break;
//            }
//
//            case R.id.cau17: {
//                Intent intent = new Intent(ButtonReviewActivity.this, ReviewTestActivity.class);
//                intent.putExtra("numberQuestion",17);
//                startActivity(intent);
//                break;
//            }
//
//            case R.id.cau18: {
//                Intent intent = new Intent(ButtonReviewActivity.this, ReviewTestActivity.class);
//                intent.putExtra("numberQuestion",18);
//                startActivity(intent);
//                break;
//            }
//            case R.id.cau19: {
//                Intent intent = new Intent(ButtonReviewActivity.this, ReviewTestActivity.class);
//                intent.putExtra("numberQuestion",19);
//                startActivity(intent);
//                break;
//            }
//            case R.id.cau20: {
//                Intent intent = new Intent(ButtonReviewActivity.this, ReviewTestActivity.class);
//                intent.putExtra("numberQuestion",20);
//                startActivity(intent);
//                break;
//            }
//
//            default: {
//                Toast.makeText(ButtonReviewActivity.this, "Chua biet", Toast.LENGTH_SHORT).show();
//                break;
//            }
//        }
//    }


    public void onClick(View v) {
        int questionNumber = -1; // Initialize with an invalid number

        if (v.getId() == R.id.cau1) {
            questionNumber = 1;
        } else if (v.getId() == R.id.cau2) {
            questionNumber = 2;
        } else if (v.getId() == R.id.cau3) {
            questionNumber = 3;
        } else if (v.getId() == R.id.cau4) {
            questionNumber = 4;
        } else if (v.getId() == R.id.cau5) {
            questionNumber = 5;
        } else if (v.getId() == R.id.cau6) {
            questionNumber = 6;
        } else if (v.getId() == R.id.cau7) {
            questionNumber = 7;
        } else if (v.getId() == R.id.cau8) {
            questionNumber = 8;
        } else if (v.getId() == R.id.cau9) {
            questionNumber = 9;
        } else if (v.getId() == R.id.cau10) {
            questionNumber = 10;
        } else if (v.getId() == R.id.cau11) {
            questionNumber = 11;
        } else if (v.getId() == R.id.cau12) {
            questionNumber = 12;
        } else if (v.getId() == R.id.cau13) {
            questionNumber = 13;
        } else if (v.getId() == R.id.cau14) {
            questionNumber = 14;
        } else if (v.getId() == R.id.cau15) {
            questionNumber = 15;
        } else if (v.getId() == R.id.cau16) {
            questionNumber = 16;
        } else if (v.getId() == R.id.cau17) {
            questionNumber = 17;
        } else if (v.getId() == R.id.cau18) {
            questionNumber = 18;
        } else if (v.getId() == R.id.cau19) {
            questionNumber = 19;
        } else if (v.getId() == R.id.cau20) {
            questionNumber = 20;
        } else {
            Toast.makeText(ButtonReviewActivity.this, "Chưa biết", Toast.LENGTH_SHORT).show();
            return; // Exit if no valid button was clicked
        }

        // Start the ReviewTestActivity with the question number
        Intent intent = new Intent(ButtonReviewActivity.this, ReviewTestActivity.class);
        intent.putExtra("numberQuestion", questionNumber);
        startActivity(intent);
    }




}