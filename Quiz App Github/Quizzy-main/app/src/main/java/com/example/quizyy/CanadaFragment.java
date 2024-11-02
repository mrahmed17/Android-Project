package com.example.quizyy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class CanadaFragment extends Fragment {

    private int currentQuestionIndex = 0;
    private int score = 0;
    private Button buttonOption1, buttonOption2, buttonOption3, buttonOption4, buttonNext;
    private TextView textViewQuestion, textViewQuestionNumber;
    private ImageView imageView;

    private String[][] questions = {
            {"What is the capital city of Canada?", "Toronto", "Ottawa", "Vancouver", "Montreal"},
            {"Which animal is an official symbol of Canada?", "Moose", "Bear", "Beaver", "Wolf"},
            {"Which Canadian province is the largest by area?", "Quebec", "Alberta", "Ontario", "British Columbia"},
            {"What is the national sport of Canada?", "Hockey", "Lacrosse", "Football", "Soccer"},
            {"Who is the current Prime Minister of Canada?", "Justin Trudeau", "Stephen Harper", "Jean Chrétien", "Brian Mulroney"}
    };

    private int[] correctAnswers = {1, 2, 3, 0, 0}; // Correct answer indexes (starting from 0)

    public CanadaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_canada, container, false);

        textViewQuestion = view.findViewById(R.id.text_question);
        buttonOption1 = view.findViewById(R.id.btn_choose1);
        buttonOption2 = view.findViewById(R.id.btn_choose2);
        buttonOption3 = view.findViewById(R.id.btn_choose3);
        buttonOption4 = view.findViewById(R.id.btn_choose4);
        buttonNext = view.findViewById(R.id.btn_next);
        imageView = view.findViewById(R.id.image_back);
        textViewQuestionNumber = view.findViewById(R.id.cpt_question);

        displayQuestion();

        View.OnClickListener optionListener = v -> {
            Button selectedButton = (Button) v;
            int selectedOptionIndex = -1;

            if (selectedButton == buttonOption1) selectedOptionIndex = 0;
            if (selectedButton == buttonOption2) selectedOptionIndex = 1;
            if (selectedButton == buttonOption3) selectedOptionIndex = 2;
            if (selectedButton == buttonOption4) selectedOptionIndex = 3;

            if (selectedOptionIndex == correctAnswers[currentQuestionIndex]) {
                score++;
            }

            if (currentQuestionIndex < questions.length - 1) {
                currentQuestionIndex++;
                displayQuestion();
                imageView.setVisibility(View.VISIBLE);
            } else {
                showResultAlertDialog();
            }
        };

        buttonOption1.setOnClickListener(optionListener);
        buttonOption2.setOnClickListener(optionListener);
        buttonOption3.setOnClickListener(optionListener);
        buttonOption4.setOnClickListener(optionListener);

        buttonNext.setOnClickListener(v -> {
            if (currentQuestionIndex < questions.length - 1) {
                currentQuestionIndex++;
                displayQuestion();
                imageView.setVisibility(View.VISIBLE);
            } else {
                showResultAlertDialog();
            }
        });

        imageView.setOnClickListener(v -> {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--;
                displayQuestion();
                if (currentQuestionIndex == 0) {
                    imageView.setVisibility(View.GONE);
                }
            }
        });

        return view;
    }

    private void displayQuestion() {
        String[] currentQuestion = questions[currentQuestionIndex];
        textViewQuestion.setText(currentQuestion[0]);
        buttonOption1.setText(currentQuestion[1]);
        buttonOption2.setText(currentQuestion[2]);
        buttonOption3.setText(currentQuestion[3]);
        buttonOption4.setText(currentQuestion[4]);
        displayQuestionNumber();
    }

    private void displayQuestionNumber() {
        String questionNumberText = "Question " + (currentQuestionIndex + 1) + "/5";
        textViewQuestionNumber.setText(questionNumberText);
    }

    private void showResultAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("🎉 Congratulations! 🎉\nYour score is: " + score + " out of 5")
                .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Start MainActivity
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Select Other Country", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Navigate to HomeActivity
                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                        startActivity(intent);
                    }
                })
                .setCancelable(false)
                .show();
    }

    private void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }
}
