package com.example.QuizApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    TextView tVSignUp;


//    public static final String DATABASE_NAME = "quizapp.db";
    public static final String DATABASE_NAME = "quiz.db";
    SQLiteDatabase db;
    EditText edtUsername, edtPassword;
    Button btnClose;

    //column table question
    public static final String TABLE_QUESTION = "tblquestion";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_OPTION1 = "option1";
    public static final String COLUMN_OPTION2 = "option2";
    public static final String COLUMN_OPTION3 = "option3";
    public static final String COLUMN_OPTION4 = "option4";
    public static final String COLUMN_SUBJECT_ID_RF = "subject_id";
    public static final String COLUMN_ANSWER_NR = "answer_cr";
    public static final String COLUMN_USER_SELECTED = "userSelectedAnswer";
    //end question

    //table subject
    private static final String TABLE_SUBJECTS = "subjects";
    private static final String COLUMN_SUBJECT_ID = "subject_id";
    private static final String COLUMN_SUBJECT_NAME = "subject_name";
    private boolean isUser(String username, String password) {
        try {
            db = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
            Cursor c = db.rawQuery("Select * from tbluser where username=? and password = ?",
                    new String[] {username, password});
            c.moveToFirst();
            if(c.getCount() > 0) {
                return true;
            }
        }
        catch (Exception ex) {
            Toast.makeText(this, "err", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = Database.initDatabase(this,DATABASE_NAME);
      edtUsername = (EditText) findViewById(R.id.edtUserName);
      edtPassword = (EditText) findViewById(R.id.edtPassword);
        tVSignUp=(TextView) findViewById(R.id.tVSignUp);
        tVSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intentLogin);
            }
        });
        btnLogin=(Button) findViewById(R.id.btnSignup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                if(username.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "আপনার ব্যবহারকারীর নাম লিখুন!", Toast.LENGTH_SHORT).show();
                    edtUsername.requestFocus();
                }
                else if(password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "পাসওয়ার্ড লিখুন!", Toast.LENGTH_SHORT).show();
                    edtPassword.requestFocus();
                }
                else if(isUser(username, password)) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("name",username);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this, "ব্যবহারকারীর নাম বা পাসওয়ার্ড ভুল!", Toast.LENGTH_SHORT).show();
                }

            }


        });


    }

    public void btnLogin_onClick(View view) {
    }

    public void tVSignUp_onClick(View view) {
    }
}