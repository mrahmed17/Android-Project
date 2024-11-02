package com.mrahmed.quiztime.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.mrahmed.quiztime.Domain.UserModel;
import com.mrahmed.quiztime.databinding.ViewholderLeadersBinding;

public class LeaderAdapter extends RecyclerView.Adapter<LeaderAdapter.ViewHolder> {

    private ViewholderLeadersBinding binding;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ViewholderLeadersBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserModel user = differ.getCurrentList().get(position);
        binding.titleTxt.setText(user.getName());

        int drawableResourceId = binding.getRoot().getContext().getResources().getIdentifier(
                user.getPic(), "drawable", binding.getRoot().getContext().getPackageName());

        Glide.with(binding.getRoot().getContext())
                .load(drawableResourceId)
                .into(binding.pic);

        binding.rowTxt.setText(String.valueOf(position + 4));
        binding.scoreTxt.setText(String.valueOf(user.getScore()));
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(ViewholderLeadersBinding binding) {
            super(binding.getRoot());
        }
    }

    private final DiffUtil.ItemCallback<UserModel> differCallback = new DiffUtil.ItemCallback<UserModel>() {
        @Override
        public boolean areItemsTheSame(UserModel oldItem, UserModel newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(UserModel oldItem, UserModel newItem) {
            return oldItem.equals(newItem);
        }
    };

    private final AsyncListDiffer<UserModel> differ = new AsyncListDiffer<>(this, differCallback);
}
