package com.mrahmed.quiztime.Activity

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mrahmed.quiztime.Domain.QuestionModel
import com.mrahmed.quiztime.R
import com.mrahmed.quiztime.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val window: Window = this@MainActivity.window
        window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.gray)

        binding.apply {
            bottomMenu.setItemSelected(R.id.home)
            bottomMenu.setOnItemSelectedListener {
                if (it == R.id.home) {
                    startActivity(Intent(this@MainActivity, LeaderActivity::class.java))
                }
            }

            singleBtn.setOnClickListener{
                val intent= Intent(this@MainActivity, QuestionActivity::class.java)
                intent.putParcelableArrayListExtra("list", ArrayList(questionList()))
                startActivity(intent)
            }

        }
    }

    // This is a list ot questions that will be used in the quiz as an example. Replace it with your own questions api.
    private fun questionList(): MutableList<QuestionModel> {
        val question: MutableList<QuestionModel> = mutableListOf()
        question.add(
            QuestionModel(
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
            )
        )
        question.add(
            QuestionModel(
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
            )
        )
        question.add(
            QuestionModel(
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
            )
        )
        question.add(
            QuestionModel(
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
            )
        )
        question.add(
            QuestionModel(
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
            )
        )
        question.add(QuestionModel(
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
            ))
        question.add(QuestionModel(
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
        ))
        question.add(QuestionModel(
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

        ))
        question.add(QuestionModel(
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
        ))
        question.add(QuestionModel(
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
        ))
        return question
    }
}