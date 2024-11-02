package com.mrahmed.isdbsquizexam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mrahmed.isdbsquizexam.databinding.LeaderboardItemBinding

class LeaderboardAdapter(private val leaderboardList: List<LeaderboardModel>) :
    RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder>() {

    class LeaderboardViewHolder(private val binding: LeaderboardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(leader: LeaderboardModel) {
            binding.usernameText.text = leader.username
            binding.scoreText.text = leader.score.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        val binding = LeaderboardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LeaderboardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        holder.bind(leaderboardList[position])
    }

    override fun getItemCount(): Int = leaderboardList.size
}
