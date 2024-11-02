package com.mrahmed.quiztime.Activity

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mrahmed.quiztime.Activity.MainActivity
import com.mrahmed.quiztime.Domain.UserModel
import com.mrahmed.quiztime.R
import com.mrahmed.quiztime.databinding.ActivityLeaderBinding

class LeaderActivity : AppCompatActivity() {
    lateinit var binding: ActivityLeaderBinding
    private val leaderAdapter by lazy {LeaderAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val window: Window = this@LeaderActivity.window
        window.statusBarColor = ContextCompat.getColor(this@LeaderActivity, R.color.gray)

        binding.apply {
            scoreTop1Txt.text = loadData().get(0).score.toString()
            scoreTop2Txt.text = loadData().get(1).score.toString()
            scoreTop3Txt.text = loadData().get(2).score.toString()
            titleTop1Txt.text = loadData().get(0).name
            titleTop2Txt.text = loadData().get(1).name
            titleTop3Txt.text = loadData().get(2).name

            val drawableResourceId1: Int = binding.root.resources.getIdentifier(
                loadData().get(0).pic, "drawable", binding.root.context.packageName
            )

            Glide.with(root.context)
                .load(drawableResourceId1)
                .into(pic1)

            val drawableResourceId2: Int = binding.root.resources.getIdentifier(
                loadData().get(1).pic, "drawable", binding.root.context.packageName
            )

            Glide.with(root.context)
                .load(drawableResourceId2)
                .into(pic2)

            val drawableResourceId3: Int = binding.root.resources.getIdentifier(
                loadData().get(2).pic, "drawable", binding.root.context.packageName
            )

            Glide.with(root.context)
                .load(drawableResourceId3)
                .into(pic3)

            bottomMenu.setItemSelected(R.id.board) {
                bottomMenu.setOnItemSelectedListener {
                    if (it == R.id.home) {
                        startActivity(Intent(this@LeaderActivity, MainActivity::class.java))
                    }
                }
                var list: MutableList<UserModel> = loadData()
                list.removeAt(0)
                list.removeAt(1)
                list.removeAt(2)

                leaderAdapter.submitList(list)
                leaderView.apply {
                    adapter = leaderAdapter
                    layoutManager = LinearLayoutManager(this@LeaderActivity)
                    adapter=leaderAdapter
                }
            }
        }
    }
}

//you can get below list from your api service, this is a example list
private fun loadData(): MutableList<UserModel> {
    val users: MutableList<UserModel> = mutableListOf()
    users.add(UserModel(1, "Raju", "person1", 4521))
    users.add(UserModel(2, "Ahmed", "person2", 4521))
    users.add(UserModel(3, "Rezvi", "person3", 4521))
    users.add(UserModel(4, "Nusrat", "person4", 4521))
    users.add(UserModel(5, "Towhid", "person5", 4521))
    users.add(UserModel(6, "Shabab", "person6", 4521))
    users.add(UserModel(7, "Shamima", "person7", 4521))
    users.add(UserModel(8, "Neyamul", "person8", 4521))
    users.add(UserModel(9, "Rafiqul", "person9", 4521))
    users.add(UserModel(10, "Sanaullah", "person1", 4521))
    users.add(UserModel(11, "Nirjash", "person2", 4521))
    users.add(UserModel(12, "Najmul", "person3", 4521))
    users.add(UserModel(13, "kutub", "person4", 4521))
    return users

}

