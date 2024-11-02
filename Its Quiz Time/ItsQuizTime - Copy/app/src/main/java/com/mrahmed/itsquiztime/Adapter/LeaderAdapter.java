package com.mrahmed.itsquiztime.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mrahmed.itsquiztime.Domain.UserModel;
import com.mrahmed.itsquiztime.databinding.ViewholderLeadersBinding;

import java.util.List;

public class LeaderAdapter extends RecyclerView.Adapter<LeaderAdapter.ViewHolder> {

    private AsyncListDiffer<UserModel> differ;

    public LeaderAdapter() {
        differ = new AsyncListDiffer<>(this, new DiffUtil.ItemCallback<UserModel>() {
            @Override
            public boolean areItemsTheSame(UserModel oldItem, UserModel newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(UserModel oldItem, UserModel newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ViewholderLeadersBinding binding;

        public ViewHolder(ViewholderLeadersBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewholderLeadersBinding binding = ViewholderLeadersBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserModel user = differ.getCurrentList().get(position);
        holder.binding.userNameTxt.setText(user.getName());
        holder.binding.scoreTxt.setText(String.valueOf(user.getScore()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(
                user.getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.binding.pic);
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }

    public void submitList(List<UserModel> list) {
        differ.submitList(list);
    }
}
