package com.example.QuizApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ReviewTestActivity extends AppCompatActivity {
    TextView txtQuestions;
    TextView txtQuestion;
    int currentQuestion;
    SQLiteDatabase db;
    Button option1, option2, option3,option4;
    ImageView backBtn;
    String answerCorrect;
    List<Question> qt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_test);
         qt = QuizActivity.questionList;
        txtQuestions = findViewById(R.id.questions);
        txtQuestion = findViewById(R.id.question);

        option1 =(Button) findViewById(R.id.option1);
        option2 = (Button)findViewById(R.id.option2);
        option3 = (Button)findViewById(R.id.option3);
        option4 =(Button) findViewById(R.id.option4);
        backBtn = findViewById(R.id.backBtn);

        Intent intent = getIntent();
        currentQuestion = intent.getIntExtra("numberQuestion",0) -1;
        String userSelected = getAnswerUserSelected();

        txtQuestions.setText((currentQuestion + 1) + ""+"/"+(qt.size()));
        txtQuestion.setText(qt.get(currentQuestion).getQuestion());
        option1.setText(qt.get(currentQuestion).getOption1());
        option2.setText(qt.get(currentQuestion).getOption2());
        option3.setText(qt.get(currentQuestion).getOption3());
        option4.setText(qt.get(currentQuestion).getOption4());

         answerCorrect = qt.get(currentQuestion).getAnswer_cr();

        if(userSelected.equals(option1.getText()) && userSelected.equals(qt.get(currentQuestion).getAnswer_cr())) {
            option1.setBackgroundResource(R.drawable.round_back_green10);
            option1.setTextColor(Color.WHITE);
        }
        else if(userSelected.equals(option2.getText()) && userSelected.equals(qt.get(currentQuestion).getAnswer_cr())) {
            option2.setBackgroundResource(R.drawable.round_back_green10);
            option2.setTextColor(Color.WHITE);
        }
        else if(userSelected.equals(option3.getText()) && userSelected.equals(qt.get(currentQuestion).getAnswer_cr())) {
            option3.setBackgroundResource(R.drawable.round_back_green10);
            option3.setTextColor(Color.WHITE);
        }
        else if(userSelected.equals(option4.getText()) && userSelected.equals(qt.get(currentQuestion).getAnswer_cr())) {
            option4.setBackgroundResource(R.drawable.round_back_green10);
            option4.setTextColor(Color.WHITE);
        }


        if(userSelected.equals(option1.getText()) && !userSelected.equals(qt.get(currentQuestion).getAnswer_cr())) {
            option1.setBackgroundResource(R.drawable.round_back_red10);
            option1.setTextColor(Color.WHITE);
            revealAnswer();
        }
        else if(userSelected.equals(option2.getText()) && !userSelected.equals(qt.get(currentQuestion).getAnswer_cr())) {
            option2.setBackgroundResource(R.drawable.round_back_red10);
            option2.setTextColor(Color.WHITE);
            revealAnswer();
        }
        else if(userSelected.equals(option3.getText()) && !userSelected.equals(qt.get(currentQuestion).getAnswer_cr())) {
            option3.setBackgroundResource(R.drawable.round_back_red10);
            option3.setTextColor(Color.WHITE);
            revealAnswer();
        }
        else if(userSelected.equals(option4.getText()) && !userSelected.equals(qt.get(currentQuestion).getAnswer_cr())) {
            option4.setBackgroundResource(R.drawable.round_back_red10);
            option4.setTextColor(Color.WHITE);
            revealAnswer();
        }

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



    }

    public String getAnswerUserSelected() {
       String userGetSelected = "";
        // Select All Query

        db = openOrCreateDatabase(LoginActivity.DATABASE_NAME, MODE_PRIVATE,null);
        Cursor c = db.rawQuery("SELECT  userSelectedAnswer FROM " + LoginActivity.TABLE_QUESTION + "  where " + LoginActivity.COLUMN_QUESTION + " = ? "  ,new String[]{qt.get(currentQuestion).getQuestion()});
        c.moveToFirst();
        userGetSelected = c.getString(0);

        return userGetSelected;

    }

    private void revealAnswer() {
        if(option1.getText().toString().equals(answerCorrect)) {
            option1.setBackgroundResource(R.drawable.round_back_green10);
            option1.setTextColor(Color.WHITE);
        }
        else if(option2.getText().toString().equals(answerCorrect)) {
            option2.setBackgroundResource(R.drawable.round_back_green10);
            option2.setTextColor(Color.WHITE);
        }
        else if(option3.getText().toString().equals(answerCorrect)) {
            option3.setBackgroundResource(R.drawable.round_back_green10);
            option3.setTextColor(Color.WHITE);
        }
        else if(option4.getText().toString().equals(answerCorrect)){
            option4.setBackgroundResource(R.drawable.round_back_green10);
            option4.setTextColor(Color.WHITE);
        }

    }
}