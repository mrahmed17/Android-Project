package com.example.QuizApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    TextView txtTitle,txtTotalQuestion,txtTotalTime;
    Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Intent intent = getIntent();
        txtTitle = (TextView) findViewById(R.id.txtTitle);

        txtTitle.setText(intent.getStringExtra("categoryName"));
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNew = new Intent(StartActivity.this, QuizActivity.class);
                intentNew.putExtra("categoryId",intent.getIntExtra("categoryId",0));
                intentNew.putExtra( "categoryName",txtTitle.getText());
                startActivity(intentNew);
            }
        });
    }
}