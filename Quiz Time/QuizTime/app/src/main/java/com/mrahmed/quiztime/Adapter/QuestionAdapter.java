package com.mrahmed.quiztime.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.mrahmed.quiztime.Domain.QuestionModel;
import com.mrahmed.quiztime.Domain.UserModel;
import com.mrahmed.quiztime.R;
import com.mrahmed.quiztime.databinding.ViewholderQuestionBinding;
import androidx.core.content.ContextCompat;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private String correctAnswer;
    private List<String> users; // Change MutableList to List
    private Score returnScore;
    private AsyncListDiffer<String> differ;

    public interface Score {
        void amount(int number, String clickedAnswer);
    }

    public QuestionAdapter(String correctAnswer, List<String> users, Score returnScore) {
        this.correctAnswer = correctAnswer;
        this.users = (users != null) ? users : new ArrayList<>(); // Use ArrayList instead of MutableList
        this.returnScore = returnScore;

        differ = new AsyncListDiffer<>(this, new DiffUtil.ItemCallback<String>() {
            @Override
            public boolean areContentsTheSame(String oldItem, String newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(String oldItem, String newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ViewholderQuestionBinding binding;

        public ViewHolder(ViewholderQuestionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewholderQuestionBinding binding = ViewholderQuestionBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String answer = differ.getCurrentList().get(position);
        holder.binding.answerTxt.setText(answer);

        int currentPos = getCurrentPosition();

        // Check if the answer is correct
        if (differ.getCurrentList().size() == 5 && currentPos == position) {
            holder.binding.answerTxt.setBackgroundResource(R.drawable.green_background);
            holder.binding.answerTxt.setTextColor(Color.WHITE);
            holder.binding.answerTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null, null, ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.tick), null);
        }

        // Handle clicked answer
        if (differ.getCurrentList().size() == 5) {
            int clickedPos = getClickedPosition();
            if (clickedPos == position && clickedPos != currentPos) {
                holder.binding.answerTxt.setBackgroundResource(R.drawable.red_background);
                holder.binding.answerTxt.setTextColor(Color.WHITE);
                holder.binding.answerTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        null, null, ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.thieves), null);
            }
        }

        // Hide the 5th position
        if (position == 4) {
            holder.itemView.setVisibility(View.GONE);
        }

        // Handle click event
        holder.itemView.setOnClickListener(v -> {
            String str = String.valueOf(getAnswerString(String.valueOf(position)));
            users.add(str); // Add clicked answer to the users list
            notifyDataSetChanged();

            if (currentPos == position) {
                holder.binding.answerTxt.setBackgroundResource(R.drawable.green_background);
                holder.binding.answerTxt.setTextColor(Color.WHITE);
                holder.binding.answerTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        null, null, ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.tick), null);
            } else {
                holder.binding.answerTxt.setBackgroundResource(R.drawable.red_background);
                holder.binding.answerTxt.setTextColor(Color.WHITE);
                holder.binding.answerTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        null, null, ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.thieves), null);
                returnScore.amount(0, str);
            }
        });
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }

    private int getCurrentPosition() {
        // Logic to get the current position based on correctAnswer
        switch (correctAnswer) {
            case "a":
                return 0;
            case "b":
                return 1;
            case "c":
                return 2;
            case "d":
                return 3;
            default:
                return -1;
        }
    }

    private int getClickedPosition() {
        // Logic to get the clicked position based on the last answer clicked
        return users.size() > 0 ? getAnswerString(users.get(users.size() - 1)) : -1;
    }

    private int getAnswerString(String answer) {
        // Map answer string (like "a", "b", etc.) to corresponding position
        switch (answer) {
            case "a":
                return 0;
            case "b":
                return 1;
            case "c":
                return 2;
            case "d":
                return 3;
            default:
                return -1;
        }
    }

}
