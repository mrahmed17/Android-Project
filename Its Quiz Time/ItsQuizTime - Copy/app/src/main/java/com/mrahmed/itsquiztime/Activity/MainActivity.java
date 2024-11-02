package com.mrahmed.itsquiztime.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.mrahmed.itsquiztime.Domain.QuestionModel;
import com.mrahmed.itsquiztime.R;
import com.mrahmed.itsquiztime.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ChipNavigationBar bottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set the status bar color
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.gray));

        // Bottom navigation setup
        binding.bottomMenu.setItemSelected(R.id.home, true);

        binding.bottomMenu.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                if (id == R.id.home) {
                    // Already on home screen, no action needed
                } else if (id == R.id.board) {
                    // Navigate to LeaderActivity
                    startActivity(new Intent(MainActivity.this, LeaderActivity.class));
                }
//                else if (id == R.id.profile) {
//                    // Navigate to ProfileActivity
//                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
//                }
            }
        });

        // Listener for the single-player button
        binding.singleBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
            intent.putParcelableArrayListExtra("list", new ArrayList<>(questionList()));
            startActivity(intent);
        });
    }

    // Example question list
    private List<QuestionModel> questionList() {
        List<QuestionModel> question = new ArrayList<>();

        question.add(new QuestionModel(
                1,
                "Which planet is the largest in our solar system?",
                "Earth",
                "Mars",
                "Jupiter",
                "Saturn",
                "c",
                5,
                "q_1",
                null
        ));

        question.add(new QuestionModel(
                2,
                "What is the capital of France?",
                "Paris",
                "London",
                "Berlin",
                "Madrid",
                "a",
                5,
                "q_2",
                null
        ));

        question.add(new QuestionModel(
                3,
                "Who painted the Mona Lisa?",
                "Leonardo da Vinci",
                "Pablo Picasso",
                "Vincent van Gogh",
                "Michelangelo",
                "a",
                5,
                "q_3",
                null
        ));

        question.add(new QuestionModel(
                4,
                "What is the largest ocean on Earth?",
                "Atlantic Ocean",
                "Indian Ocean",
                "Arctic Ocean",
                "Pacific Ocean",
                "d",
                5,
                "q_4",
                null
        ));

        question.add(new QuestionModel(
                5,
                "Which country is known as the 'Land of the Rising Sun'?",
                "China",
                "Japan",
                "South Korea",
                "Thailand",
                "b",
                5,
                "q_5",
                null
        ));

        question.add(new QuestionModel(
                6,
                "Which country is the largest country in the world by land area?",
                "Russia",
                "Canada",
                "United States",
                "China",
                "1",
                5,
                "q_6",
                null
        ));

        question.add(new QuestionModel(
                7,
                "Which country is the smallest country in the world by land area?",
                "Vatican City",
                "Monaco",
                "San Marino",
                "Liechtenstein",
                "1",
                5,
                "q_7",
                null
        ));

        question.add(new QuestionModel(
                8,
                "Which of the following substances is used as an anti-cancer medication in medical world?",
                "Cheese",
                "Lemon Juice",
                "Cannabis",
                "Pabulum",
                "c",
                5,
                "q_8",
                null
        ));

        question.add(new QuestionModel(
                9,
                "Who is the owner of Android?",
                "Google",
                "Microsoft",
                "Apple",
                "Amazon",
                "a",
                5,
                "q_9",
                null
        ));

        question.add(new QuestionModel(
                10,
                "Which religion is among the most practiced religion in the world?",
                "Islam",
                "Christianity",
                "Hinduism",
                "Buddhism",
                "a",
                5,
                "q_10",
                null
        ));

        return question;
    }
}
