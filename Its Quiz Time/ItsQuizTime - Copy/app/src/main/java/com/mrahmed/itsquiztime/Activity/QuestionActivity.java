package com.mrahmed.itsquiztime.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mrahmed.itsquiztime.Adapter.QuestionAdapter;
import com.mrahmed.itsquiztime.Domain.QuestionModel;
import com.mrahmed.itsquiztime.R;
import com.mrahmed.itsquiztime.databinding.ActivityQuestionBinding;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity implements QuestionAdapter.Score {
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

        // Set up views and click listeners
        binding.backBtn.setOnClickListener(v -> finish());

        binding.progressBar.setProgress(1);
        binding.questionTxt.setText(receivedList.get(position).getQuestion());

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

        binding.rightArrow.setOnClickListener(v -> {
            if (binding.progressBar.getProgress() == 10) {
                Intent intent = new Intent(QuestionActivity.this, ScoreActivity.class);
                intent.putExtra("Score", allScore);
                startActivity(intent);
                finish();
                return;
            }
            position++;
            binding.progressBar.setProgress(binding.progressBar.getProgress() + 1);
            binding.questionNumberTxt.setText("Question " + binding.progressBar.getProgress() + "/10");
            binding.questionTxt.setText(receivedList.get(position).getQuestion());

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

        binding.leftArrow.setOnClickListener(v -> {
            if (binding.progressBar.getProgress() == 1) {
                return;
            }
            position--;
            binding.progressBar.setProgress(binding.progressBar.getProgress() - 1);
            binding.questionNumberTxt.setText("Question " + binding.progressBar.getProgress() + "/10");
            binding.questionTxt.setText(receivedList.get(position).getQuestion());

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

        // Initialize the adapter
        List<String> answers = new ArrayList<>(); // Populate with the answers
        QuestionAdapter adapter = new QuestionAdapter(receivedList.get(position).getCorrectAnswer(), answers, this);
        // Set the adapter to your RecyclerView here
    }

    private void loadAnswer() {
        List<String> users = new ArrayList<>();
        users.add(receivedList.get(position).getAnswer_1());
        users.add(receivedList.get(position).getAnswer_2());
        users.add(receivedList.get(position).getAnswer_3());
        users.add(receivedList.get(position).getAnswer_4());

        // Create and set the adapter for the RecyclerView
        QuestionAdapter questionAdapter = new QuestionAdapter(
                receivedList.get(position).getCorrectAnswer(), users, this
        );
        // Set the adapter to your RecyclerView here
    }

    @Override
    public void amount(int number, String clickedAnswer) {
        allScore = number;
        receivedList.get(position).setClickedAnswer(clickedAnswer);
    }
}
