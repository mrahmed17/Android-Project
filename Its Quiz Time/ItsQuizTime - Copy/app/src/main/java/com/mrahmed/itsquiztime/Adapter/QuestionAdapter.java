package com.mrahmed.itsquiztime.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mrahmed.itsquiztime.R;
import com.mrahmed.itsquiztime.databinding.ViewholderQuestionBinding;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private String correctAnswer;
    private List<String> users; // List to hold clicked answers
    private Score returnScore;
    private AsyncListDiffer<String> differ;

    public interface Score {
        void amount(int number, String clickedAnswer);
    }

    public QuestionAdapter(String correctAnswer, List<String> users, Score returnScore) {
        this.correctAnswer = correctAnswer;
        this.users = (users != null) ? users : new ArrayList<>(); // Ensure users list is not null
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

        // Determine current and clicked positions
        int currentPos = getCurrentPosition();
        int clickedPos = getClickedPosition();

        // Check if the answer is correct
        if (currentPos == position) {
            holder.binding.answerTxt.setBackgroundResource(R.drawable.green_background);
            holder.binding.answerTxt.setTextColor(Color.WHITE);
            holder.binding.answerTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null, null, ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.tick), null);
        }

        // Handle clicked answer
        if (clickedPos == position) {
            holder.binding.answerTxt.setBackgroundResource(R.drawable.red_background);
            holder.binding.answerTxt.setTextColor(Color.WHITE);
            holder.binding.answerTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null, null, ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.thieves), null);
        }

        // Hide the 5th position
        if (position == 4) {
            holder.itemView.setVisibility(View.GONE);
        }

        // Handle click event
        holder.itemView.setOnClickListener(v -> {
            users.add(answer); // Add clicked answer to the users list
            notifyDataSetChanged();

            if (currentPos == position) {
                // Correct answer clicked
                holder.binding.answerTxt.setBackgroundResource(R.drawable.green_background);
                holder.binding.answerTxt.setTextColor(Color.WHITE);
                holder.binding.answerTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        null, null, ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.tick), null);
            } else {
                // Incorrect answer clicked
                holder.binding.answerTxt.setBackgroundResource(R.drawable.red_background);
                holder.binding.answerTxt.setTextColor(Color.WHITE);
                holder.binding.answerTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        null, null, ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.thieves), null);
                returnScore.amount(0, answer); // Assuming 0 for wrong answer; adjust as needed
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
        if (!users.isEmpty()) {
            String lastClickedAnswer = users.get(users.size() - 1);
            return getAnswerString(lastClickedAnswer);
        }
        return -1;
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

    public void submitList(List<String> list) {
        differ.submitList(list);
    }
}
