package com.example.QuizApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    EditText txtusername, txtpassword, txtconfirmpassword;
    Button btnSignUp;
    TextView tVLogin;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnSignUp = (Button)findViewById(R.id.btnSignup);
        txtusername = (EditText)findViewById(R.id.username);
        txtpassword = (EditText)findViewById(R.id.password);
        txtconfirmpassword = (EditText)findViewById(R.id.confirmpassword);

        databaseHelper = new DatabaseHelper(this);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ইনপুট ক্ষেত্র থেকে তথ্য পান
                String username = txtusername.getText().toString().trim();
                String password = txtpassword.getText().toString().trim();
                String confirmPassword = txtconfirmpassword.getText().toString().trim();

                // ইনপুট তথ্য চেক করুন
                if (TextUtils.isEmpty(username)) {
                    txtusername.setError("ব্যবহারকারীর নাম ফাঁকা হতে পারে না");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    txtpassword.setError("পাসওয়ার্ড ফাঁকা হতে পারে না");
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    txtconfirmpassword.setError("নিশ্চিতকরণ পাসওয়ার্ড ভুল");
                    return;
                }

                // ডাটাবেসে ব্যবহারকারীদের যোগ করুন
                long result = databaseHelper.addUser(username, password);
                if (result > 0) {
                    // সফলভাবে নিবন্ধিত হয়েছে, লগইন পৃষ্ঠায় পুনঃনির্দেশিত হয়েছে৷
                    Toast.makeText(SignUpActivity.this, "সফলভাবে নিবন্ধিত", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                    finish();
                } else {
                    // নিবন্ধন ব্যর্থ হয়েছে
                    Toast.makeText(SignUpActivity.this, "নিবন্ধন ব্যর্থ হয়েছে", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tVLogin = findViewById(R.id.tVLogin);
        tVLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin=new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intentLogin);
            }
        });
    }

    public void tVLogin_onClick(View view) {
    }
}