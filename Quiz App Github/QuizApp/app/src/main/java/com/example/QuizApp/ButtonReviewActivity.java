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

    public void onClick(View v) {
        int viewId = v.getId();

        if (viewId == R.id.cau1) {
            startReviewActivity(1);
        } else if (viewId == R.id.cau2) {
            startReviewActivity(2);
        } else if (viewId == R.id.cau3) {
            startReviewActivity(3);
        } else if (viewId == R.id.cau4) {
            startReviewActivity(4);
        } else if (viewId == R.id.cau5) {
            startReviewActivity(5);
        } else if (viewId == R.id.cau6) {
            startReviewActivity(6);
        } else if (viewId == R.id.cau7) {
            startReviewActivity(7);
        } else if (viewId == R.id.cau8) {
            startReviewActivity(8);
        } else if (viewId == R.id.cau9) {
            startReviewActivity(9);
        } else if (viewId == R.id.cau10) {
            startReviewActivity(10);
        } else if (viewId == R.id.cau11) {
            startReviewActivity(11);
        } else if (viewId == R.id.cau12) {
            startReviewActivity(12);
        } else if (viewId == R.id.cau13) {
            startReviewActivity(13);
        } else if (viewId == R.id.cau14) {
            startReviewActivity(14);
        } else if (viewId == R.id.cau15) {
            startReviewActivity(15);
        } else if (viewId == R.id.cau16) {
            startReviewActivity(16);
        } else if (viewId == R.id.cau17) {
            startReviewActivity(17);
        } else if (viewId == R.id.cau18) {
            startReviewActivity(18);
        } else if (viewId == R.id.cau19) {
            startReviewActivity(19);
        } else if (viewId == R.id.cau20) {
            startReviewActivity(20);
        } else {
            Toast.makeText(ButtonReviewActivity.this, "কোন ধারণা নেই", Toast.LENGTH_SHORT).show();
        }
    }

    private void startReviewActivity(int questionNumber) {
        Intent intent = new Intent(ButtonReviewActivity.this, ReviewTestActivity.class);
        intent.putExtra("numberQuestion", questionNumber);
        startActivity(intent);
    }

}