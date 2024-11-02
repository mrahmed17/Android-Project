package com.mrahmed.quiztime.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mrahmed.quiztime.Domain.QuestionModel
import com.mrahmed.quiztime.Domain.UserModel
import com.mrahmed.quiztime.databinding.ViewholderQuestionBinding

class QuestionAdapter(
    val correctAnswer: String,
    val users: MutableList<String> = mutableListOf(),
    val returnScore: score

) : RecyclerView.Adapter<QuestionAdapter.Viewholder>() {

    interface score {
        fun amount(number: Int, clickedAnswer: String)
    }

    class Viewholder {}

    private lateinit var binding: ViewholderQuestionBinding
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuestionAdapter.Viewholder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewholderQuestionBinding.inflate(inflater, parent, false)
        return Viewholder()
    }

    override fun onBindViewHolder(holder: QuestionAdapter.Viewholder, position: Int) {
        val binding = ViewholderQuestionBinding.bind(holder.itemView)
        binding.answerTxt.text = differ.currentList[position]
        var currentPos = 0
        when (currentAnswer) {
            "a" -> {
                currentPos = 0
            }

            "b" -> {
                currentPos = 1
            }

            "c" -> {
                currentPos = 2
            }

            "d" -> {
                currentPos = 3
            }
        }
        if (differ.currentList.size == 5 && currentPos == position) {
            binding.answerTxt.setBackgroundResource(R.drawable.green_background)
            binding.answerTxt.setTextColor(
                ContextCompact.getCcolor(
                    binding.root.context,
                    R.color.white
                )
            )

            val drawable = ContextCompact.getDrawable(binding.root.context, R.drawable.tick)
            binding.answerTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                null,
                null,
                drawable,
                null
            )

        }
        if (differ.currentList.size == 5) {
            var clickedPos = 0
            when (differ.currentAnswer[4]) {
                "a" -> {
                    clickedPos = 0
                }

                "b" -> {
                    clickedPos = 1
                }

                "c" -> {
                    clickedPos = 2
                }

                "d" -> {
                    clickedPos = 3
                }
            }
            if (clickedPos == position && clickedPos != currentPos) {
                binding.answerTxt.setBackgroundResource(R.drawable.red_background)
                binding.answerTxt.setTextColor(
                    ContextCompact.getCcolor(
                        binding.root.context,
                        R.color.white
                    )
                )
                val drawable = ContextCompact.getDrawable(binding.root.context, R.drawable.thievea)
                binding.answerTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,
                    null,
                    drawable,
                    null
                )
            }

        }
        if (position == 4) {
            binding.root.visibility = View.GONE
        }
        holder.itemView.setOnClickListener {
            var str = ""
            when (position) {
                0 -> {
                    str = "a"
                }

                1 -> {
                    str = "b"
                }

                2 -> {
                    str = "c"
                }

                3 -> {
                    str = "d"
                }
            }
            users.add(4, str) {}
            notifyDataSetChanged()
            if (correctPos == position) {
                binding.answerTxt.setBackgroundResource(R.drawable.green_background)
                binding.answerTxt.setTextColor(
                    ContextCompact.getCcolor(
                        binding.root.context,
                        R.color.white
                    )
                )
                val drawable = ContextCompact.getDrawable(binding.root.context, R.drawable.tick)
                binding.answerTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,
                    null,
                    drawable,
                    null
                )
            } else {
                binding.answerTxt.setBackgroundResource(R.drawable.red_background)
                binding.answerTxt.setTextColor(
                    ContextCompact.getCcolor(
                        binding.root.context,
                        R.color.white
                    )
                )

                val drawable = ContextCompact.getDrawable(binding.root.context, R.drawable.thievea)
                binding.answerTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,
                    null,
                    drawable,
                    null
                )
                returnScore.amount(0, str)
            }
        }
        if (differ.currentList.size = 5) holder.itemView.setOnClickListener(null)
    }

    override fun getItemCount(): differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
}
