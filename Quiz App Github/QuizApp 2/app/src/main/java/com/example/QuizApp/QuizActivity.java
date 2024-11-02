package com.example.QuizApp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity {
    TextView txtQuestions;
    TextView txtQuestion;

    Button option1, option2, option3,option4;
    Button nextBtn;
    Button prevBtn;
    String sql;

    int questionPosition;
    static String selectedOptionByUser = "";
    Timer quiztimer;
    int totalTimeInMins = 20;
    int seconds = 0;
    SQLiteDatabase db;
    static List<Question> questionList;
    String getAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionPosition = 0;
        questionList = getAllQuestions();
        ImageView backBtn = findViewById(R.id.backBtn);
        TextView timer = findViewById(R.id.timer);
        TextView selectTopicname = findViewById(R.id.topicName);
        txtQuestions = findViewById(R.id.questions);
        txtQuestion = findViewById(R.id.question);

        ContentValues values = new ContentValues();
        values.put(LoginActivity.COLUMN_USER_SELECTED, selectedOptionByUser);
        db.update(LoginActivity.TABLE_QUESTION, values, null, null);

        option1 =(Button) findViewById(R.id.option1);
        option2 = (Button)findViewById(R.id.option2);
        option3 = (Button)findViewById(R.id.option3);
        option4 =(Button) findViewById(R.id.option4);

        nextBtn = (Button)findViewById(R.id.nextBtn);
        prevBtn = (Button)findViewById(R.id.prevBtn);
        selectedOptionByUser = "";

        startTimer(timer);

        selectTopicname.setText(getIntent().getStringExtra("categoryName"));
        txtQuestions.setText((questionPosition + 1) + ""+"/"+(questionList.size()));
        txtQuestion.setText(questionList.get(0).getQuestion());
        option1.setText(questionList.get(0).getOption1());
        option2.setText(questionList.get(0).getOption2());
        option3.setText(questionList.get(0).getOption3());
        option4.setText(questionList.get(0).getOption4());

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                option1.setBackgroundResource(R.drawable.round_back_selected);
                option1.setTextColor(Color.WHITE);

                option2.setTextColor(Color.parseColor("#1F6BB8"));
                option2.setBackgroundResource(R.drawable.round_back_white_stroke_10);

                option3.setTextColor(Color.parseColor("#1F6BB8"));
                option3.setBackgroundResource(R.drawable.round_back_white_stroke_10);

                option4.setTextColor(Color.parseColor("#1F6BB8"));
                option4.setBackgroundResource(R.drawable.round_back_white_stroke_10);

                selectedOptionByUser = option1.getText().toString();
                questionList.get(questionPosition).setUserSelectedAnswer(selectedOptionByUser);

                updateUserSelected();

            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                option2.setBackgroundResource(R.drawable.round_back_selected);
                option2.setTextColor(Color.WHITE);

                option1.setTextColor(Color.parseColor("#1F6BB8"));
                option1.setBackgroundResource(R.drawable.round_back_white_stroke_10);

                option3.setTextColor(Color.parseColor("#1F6BB8"));
                option3.setBackgroundResource(R.drawable.round_back_white_stroke_10);

                option4.setTextColor(Color.parseColor("#1F6BB8"));
                option4.setBackgroundResource(R.drawable.round_back_white_stroke_10);

                selectedOptionByUser = option2.getText().toString();
                questionList.get(questionPosition).setUserSelectedAnswer(selectedOptionByUser);
                updateUserSelected();


            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option3.setBackgroundResource(R.drawable.round_back_selected);
                option3.setTextColor(Color.WHITE);

                option1.setTextColor(Color.parseColor("#1F6BB8"));
                option1.setBackgroundResource(R.drawable.round_back_white_stroke_10);

                option2.setTextColor(Color.parseColor("#1F6BB8"));
                option2.setBackgroundResource(R.drawable.round_back_white_stroke_10);

                option4.setTextColor(Color.parseColor("#1F6BB8"));
                option4.setBackgroundResource(R.drawable.round_back_white_stroke_10);

                selectedOptionByUser = option3.getText().toString();
                questionList.get(questionPosition).setUserSelectedAnswer(selectedOptionByUser);
                updateUserSelected();


            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option4.setBackgroundResource(R.drawable.round_back_selected);
                option4.setTextColor(Color.WHITE);

                option1.setTextColor(Color.parseColor("#1F6BB8"));
                option1.setBackgroundResource(R.drawable.round_back_white_stroke_10);

                option2.setTextColor(Color.parseColor("#1F6BB8"));
                option2.setBackgroundResource(R.drawable.round_back_white_stroke_10);

                option3.setTextColor(Color.parseColor("#1F6BB8"));
                option3.setBackgroundResource(R.drawable.round_back_white_stroke_10);

                selectedOptionByUser = option4.getText().toString();
                questionList.get(questionPosition).setUserSelectedAnswer(selectedOptionByUser);
                updateUserSelected();


            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(questionList.get(questionPosition).getUserSelectedAnswer())){
                    Toast.makeText(QuizActivity.this, "উত্তর চয়ন করুন!", Toast.LENGTH_SHORT).show();
                    return;
                }
                    String action = "next";
                    changeNextQuestion(action);
            }
        });

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String action = "prev";
                changeNextQuestion(action);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExitConfirmationDialog();
            }
        });
    }

    private void changeNextQuestion(String action) {

        if(action == "next") {
            questionPosition++;
            txtQuestions.setText((questionPosition + 1) + ""+"/"+(questionList.size()));
            prevBtn.setBackgroundResource(R.drawable.round_back_green10);
            prevBtn.setEnabled(true);


            if((questionPosition+1) == questionList.size()) {
                nextBtn.setText("শেষ");
            }


            if(questionPosition >= questionList.size()) {
                Intent intent = new Intent(QuizActivity.this,ButtonReviewActivity.class);

                quiztimer.purge();
                quiztimer.cancel();

                intent.putExtra("correct",getCorrectAnswers());
                intent.putExtra("incorrect",getInCorrectAnswers());
                intent.putExtra("categoryId",getIntent().getIntExtra("categoryId",0));
                intent.putExtra("categoryName",getIntent().getStringExtra("categoryName"));

                startActivity(intent);
                finish();
            }
            else  {
                getAnswer = questionList.get(questionPosition).getAnswer_cr();

                selectedOptionByUser = "";
                option1.setTextColor(Color.parseColor("#1F6BB8"));
                option1.setBackgroundResource(R.drawable.round_back_white_stroke_10);

                option2.setTextColor(Color.parseColor("#1F6BB8"));
                option2.setBackgroundResource(R.drawable.round_back_white_stroke_10);

                option3.setTextColor(Color.parseColor("#1F6BB8"));
                option3.setBackgroundResource(R.drawable.round_back_white_stroke_10);

                option4.setTextColor(Color.parseColor("#1F6BB8"));
                option4.setBackgroundResource(R.drawable.round_back_white_stroke_10);

                txtQuestion.setText(questionList.get(questionPosition).getQuestion());
                option1.setText(questionList.get(questionPosition).getOption1());
                option2.setText(questionList.get(questionPosition).getOption2());
                option3.setText(questionList.get(questionPosition).getOption3());
                option4.setText(questionList.get(questionPosition).getOption4());
                txtQuestions.setText((questionPosition + 1) + ""+"/"+questionList.size());
                getAnswerSelected();



            }
        }
        else {
            questionPosition--;
            nextBtn.setText("Sau");
            txtQuestions.setText((questionPosition + 1) + ""+"/"+(questionList.size()));
            if(questionPosition-1 < 0) {
                prevBtn.setEnabled(false);
                prevBtn.setBackgroundResource(R.drawable.round_black_green_disable);
            }

                getAnswer = questionList.get(questionPosition).getAnswer_cr();

                selectedOptionByUser = "";
                option1.setTextColor(Color.parseColor("#1F6BB8"));
                option1.setBackgroundResource(R.drawable.round_back_white_stroke_10);

                option2.setTextColor(Color.parseColor("#1F6BB8"));
                option2.setBackgroundResource(R.drawable.round_back_white_stroke_10);

                option3.setTextColor(Color.parseColor("#1F6BB8"));
                option3.setBackgroundResource(R.drawable.round_back_white_stroke_10);

                option4.setTextColor(Color.parseColor("#1F6BB8"));
                option4.setBackgroundResource(R.drawable.round_back_white_stroke_10);

                txtQuestion.setText(questionList.get(questionPosition).getQuestion());
                option1.setText(questionList.get(questionPosition).getOption1());
                option2.setText(questionList.get(questionPosition).getOption2());
                option3.setText(questionList.get(questionPosition).getOption3());
                option4.setText(questionList.get(questionPosition).getOption4());

                txtQuestions.setText((questionPosition + 1) + ""+"/"+questionList.size());
                getAnswerSelected();
        }

    }

    private void startTimer(TextView timerTextView) {
        quiztimer = new Timer();
        quiztimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if(seconds <11 && totalTimeInMins ==0) {
                    timerTextView.setTextColor(Color.RED);
                }

                if(seconds == 0 && totalTimeInMins == 00) {
                    quiztimer.purge();
                    quiztimer.cancel();
                    Intent intent = new Intent(QuizActivity.this,ButtonReviewActivity.class);
                    intent.putExtra("correct",getCorrectAnswers());
                    intent.putExtra("incorrect",getInCorrectAnswers());
                    startActivity(intent);
                    finish();
                }
                else if(seconds == 0) {
                    totalTimeInMins--;
                    seconds = 59;
                }
                else {
                    seconds--;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                         String finalMinutes = String.valueOf(totalTimeInMins);
                         String finalSeconds = String.valueOf(seconds);

                        if(finalMinutes.length() == 1) {
                            finalMinutes = "0"+finalMinutes;
                        }
                        if(finalSeconds.length() == 1) {
                            finalSeconds = "0"+finalSeconds;
                        }

                        timerTextView.setText((finalMinutes+":"+finalSeconds));

                    }
                });
            }
        },1000,1000);
    }


    @Override
    public void onBackPressed() {
        showExitConfirmationDialog();
    }


    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<>();
        // Select All Query
        Intent intent = getIntent();
        int value2 = intent.getIntExtra("categoryId",0);
        db = openOrCreateDatabase(LoginActivity.DATABASE_NAME, MODE_PRIVATE,null);
        Cursor c = db.rawQuery("SELECT  * FROM " + LoginActivity.TABLE_QUESTION + "   where " + LoginActivity.COLUMN_SUBJECT_ID_RF + " =? ORDER BY RANDOM() LIMIT 20"  ,new String[]{String.valueOf(value2)});
        // looping through all rows and adding to list
        c.moveToFirst();

        while(!c.isAfterLast()) {
            quesList.add(new Question(c.getInt(0),c.getString(1),c.getString(2),
                    c.getString(3),c.getString(4),c.getString(5),c.getInt(6),c.getString(7)));
            c.moveToNext();
        }
        return quesList;
    }

    public void updateUserSelected() {

        try {
            db = openOrCreateDatabase(LoginActivity.DATABASE_NAME, MODE_PRIVATE,null);

            ContentValues values = new ContentValues();
            values.put(LoginActivity.COLUMN_USER_SELECTED, selectedOptionByUser);
            db.update(LoginActivity.TABLE_QUESTION, values, "question=?", new String[]{questionList.get(questionPosition).getQuestion()});

        } catch (Exception ex) {
            Toast.makeText(QuizActivity.this, "err", Toast.LENGTH_SHORT).show();
        }
    }

    public void getAnswerSelected () {
        String userGetSelected = "";
        Cursor c = db.rawQuery("SELECT userSelectedAnswer FROM " + LoginActivity.TABLE_QUESTION + "  where " + LoginActivity.COLUMN_QUESTION + " = ? "  ,new String[]{questionList.get(questionPosition).getQuestion()});
        c.moveToFirst();
        userGetSelected = c.getString(0);

        if(userGetSelected.equals(option1.getText())) {
            option1.setBackgroundResource(R.drawable.round_back_selected);
            option1.setTextColor(Color.WHITE);
        }
        else if(userGetSelected.equals(option2.getText())) {
            option2.setBackgroundResource(R.drawable.round_back_selected);
            option2.setTextColor(Color.WHITE);
        }
        else if(userGetSelected.equals(option3.getText())) {
            option3.setBackgroundResource(R.drawable.round_back_selected);
            option3.setTextColor(Color.WHITE);
        }
        else if(userGetSelected.equals(option4.getText())) {
            option4.setBackgroundResource(R.drawable.round_back_selected);
            option4.setTextColor(Color.WHITE);
        }
        else {
            selectedOptionByUser = "";
            updateUserSelected();
        }
    }

    private int getCorrectAnswers() {
        int correctAnswer = 0;
        for(int i = 0 ;i < questionList.size();i++) {



            String getUserSelected = questionList.get(i).getUserSelectedAnswer();
            String getAnswer = questionList.get(i).getAnswer_cr();
            if(getUserSelected.equals(getAnswer)) {
                correctAnswer++;
            }

        }
        return correctAnswer;
    }

    private int getInCorrectAnswers() {
        int inCorrectAnswer = 0;
        for(int i = 0 ;i < questionList.size();i++) {
            String getUserSelected = questionList.get(i).getUserSelectedAnswer();
            String getAnswer = questionList.get(i).getAnswer_cr();

             if(!getUserSelected.equals(getAnswer)) {
                inCorrectAnswer++;
            }

        }
        return inCorrectAnswer;
    }
    private void showExitConfirmationDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(QuizActivity.this);
        alertDialogBuilder.setTitle("প্রস্থান করলে পরীক্ষা বাতিল হবে!");
        alertDialogBuilder.setIcon(R.drawable.question);
        alertDialogBuilder.setMessage("আপনি কি প্রস্থান করতে চান?");
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("নিশ্চিত করুন", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int argl) {
                quiztimer.purge();
                quiztimer.cancel();
                startActivity(new Intent(QuizActivity.this, HomeActivity.class));
                finish();
            }
        });

        alertDialogBuilder.setNegativeButton("বাতিল করুন", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialogBuilder.create().show();
    }
}