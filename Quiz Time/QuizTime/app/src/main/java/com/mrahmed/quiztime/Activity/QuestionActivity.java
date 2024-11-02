package com.mrahmed.quiztime.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mrahmed.quiztime.Adapter.QuestionAdapter;
import com.mrahmed.quiztime.Domain.QuestionModel;
import com.mrahmed.quiztime.R;
import com.mrahmed.quiztime.databinding.ActivityQuestionBinding;
import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    private ActivityQuestionBinding binding;
    private int position = 0;
    private List<QuestionModel> receivedList = new ArrayList<>();
    private int allScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.gray));

        receivedList = getIntent().getParcelableArrayListExtra("list");

        binding.apply {
            backBtn.setOnClickListener(v -> finish());

            progressBar.setProgress(1);
            questionTxt.setText(receivedList.get(position).getQuestion());

            int drawableResourceId = getResources().getIdentifier(
                    receivedList.get(position).getPicPath(),
                    "drawable",
                    getPackageName()
            );

            Glide.with(QuestionActivity.this)
                    .load(drawableResourceId)
                    .centerCrop()
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(60)))
                    .into(binding.pic);

            loadAnswer();

            rightArrow.setOnClickListener(v -> {
                if (progressBar.getProgress() == 10) {
                    Intent intent = new Intent(QuestionActivity.this, ScoreActivity.class);
                    intent.putExtra("Score", allScore);
                    startActivity(intent);
                    finish();
                    return;
                }
                position++;
                progressBar.setProgress(progressBar.getProgress() + 1);
                questionNumberTxt.setText("Question " + progressBar.getProgress() + "/10");
                questionTxt.setText(receivedList.get(position).getQuestion());

                int drawableResourceIdNext = getResources().getIdentifier(
                        receivedList.get(position).getPicPath(),
                        "drawable", getPackageName()
                );

                Glide.with(QuestionActivity.this)
                        .load(drawableResourceIdNext)
                        .centerCrop()
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(60)))
                        .into(binding.pic);

                loadAnswer();
            });

            leftArrow.setOnClickListener(v -> {
                if (progressBar.getProgress() == 1) {
                    return;
                }
                position--;
                progressBar.setProgress(progressBar.getProgress() - 1);
                questionNumberTxt.setText("Question " + progressBar.getProgress() + "/10");
                questionTxt.setText(receivedList.get(position).getQuestion());

                int drawableResourceIdPrev = getResources().getIdentifier(
                        receivedList.get(position).getPicPath(),
                        "drawable", getPackageName()
                );

                Glide.with(QuestionActivity.this)
                        .load(drawableResourceIdPrev)
                        .centerCrop()
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(60)))
                        .into(binding.pic);

                loadAnswer();
            });
        }
    }

    private void loadAnswer() {
        List<String> users = new ArrayList<>();
        users.add(receivedList.get(position).getAnswer_1());
        users.add(receivedList.get(position).getAnswer_2());
        users.add(receivedList.get(position).getAnswer_3());
        users.add(receivedList.get(position).getAnswer_4());

        if (receivedList.get(position).getClickedAnswer() != null) {
            users.add(receivedList.get(position).getClickedAnswer());
        }

        QuestionAdapter questionAdapter = new QuestionAdapter(
                receivedList.get(position).getCorrectAnswer(), users, this
        );
    }

    public void amount(int number, String clickedAnswer) {
        allScore = number;
        receivedList.get(position).setClickedAnswer(clickedAnswer);
    }
}
