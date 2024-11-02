package com.mrahmed.itsquiztime.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mrahmed.itsquiztime.R;
import com.mrahmed.itsquiztime.databinding.ActivityScoreBinding;

public class ScoreActivity extends AppCompatActivity {
    private int score = 0;
    private ActivityScoreBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        score = getIntent().getIntExtra("score", 0);
        binding.scoreTxt.setText(String.valueOf(score));

        binding.backBtn.setOnClickListener(v -> {
            startActivity(new Intent(ScoreActivity.this, MainActivity.class));
            finish();
        });
    }
}