package com.mrahmed.quiztime.Adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mrahmed.quiztime.Domain.UserModel
import com.mrahmed.quiztime.databinding.ViewholderLeadersBinding

class LeaderAdapter: RecyclerView.Adapter<LeaderAdapter.ViewHolder>() {
    class ViewHolder{

    }

    private lateinit var binding: ViewholderLeadersBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LeaderAdapter.ViewHolder {
        val layoutInflater = null
        val inflater=layoutInflater.from(parent.context)
        binding=ViewholderLeadersBinding.inflate(inflater,parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: LeaderAdapter.ViewHolder, position: Int) {
        val binding= ViewholderLeadersBinding.bind(holder.itemView)
        binding.titleTxt.text=differ.currentList[position].name

        val drawableReourceId: Int=binding.root.context.resources.getIdentifier(
            differ.currentList[position].pic,
            "drawable",
            binding.root.context.packageName
        )
        Glide.with(binding.root.context)
            .load(dreaableResourceId)
            .into(binding.pic)

        binding.rowTxt.text=(position+4).toString()
        binding.scoreTxt.text=differ.currentList[position].score.toString()

    }

    override fun getItemCount():differ.currentList.size

    inner class ViewHolder:RecyclerView.ViewHolder(binding.root)

    private val differCallback=object : DiffUtil.ItemCallback<UserModel>(){
        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem==newItem
        }

    }

    val differ= AsyncListDiffer(this,differCallback)
}