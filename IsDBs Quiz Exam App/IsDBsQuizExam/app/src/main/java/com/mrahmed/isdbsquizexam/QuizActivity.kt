package com.mrahmed.isdbsquizexam

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.mrahmed.isdbsquizexam.databinding.ActivityQuizBinding
import com.mrahmed.isdbsquizexam.databinding.ScoreDialogBinding

class QuizActivity : AppCompatActivity() ,View.OnClickListener {

    companion object {
        var questionModelList: List<QuestionModel> = listOf()
        var time : String = ""
    }

    lateinit var binding: ActivityQuizBinding

    var currentQuestionIndex = 0
    var selectedAnswer = ""
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btn0.setOnClickListener(this@QuizActivity)
            btn1.setOnClickListener(this@QuizActivity)
            btn2.setOnClickListener(this@QuizActivity)
            btn3.setOnClickListener(this@QuizActivity)
            nextBtn.setOnClickListener(this@QuizActivity)
        }

        loadQuestions()
        startTimer()
    }

    private fun startTimer(){
        val totalTimeInMillis = time.toInt() * 60 * 1000L
        object : CountDownTimer (totalTimeInMillis, 1000L){
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                val minutes = seconds / 60
                val remainingSeconds = seconds % 60
                binding.timerIndicatorTextview.text = String.format("%02d:%02d", minutes, remainingSeconds)
            }

            override fun onFinish() {
                //finish the quiz
            }

        }.start()
    }

    private fun loadQuestions(){
        selectedAnswer = ""
        if(currentQuestionIndex == questionModelList.size){
            finishQuiz()
            return
        }

        binding.apply {
            questionIndicatorTextview.text = "Question ${currentQuestionIndex+1}/ ${questionModelList.size}"
            questionProgressIndicator.progress =
                (currentQuestionIndex.toFloat()/questionModelList.size.toFloat()* 100).toInt()
            questionTextview.text = questionModelList[currentQuestionIndex].question
            btn0.text= questionModelList[currentQuestionIndex].options[0]
            btn1.text= questionModelList[currentQuestionIndex].options[1]
            btn2.text= questionModelList[currentQuestionIndex].options[2]
            btn3.text= questionModelList[currentQuestionIndex].options[3]
        }
    }

    override fun onClick(view: View?) {
        binding.apply {
            btn0.setBackgroundColor(getColor(R.color.gray))
            btn1.setBackgroundColor(getColor(R.color.gray))
            btn2.setBackgroundColor(getColor(R.color.gray))
            btn3.setBackgroundColor(getColor(R.color.gray))
        }

        val clickedBtn = view as Button
        if (clickedBtn.id == R.id.next_btn){
            //next button is clicked
            if (selectedAnswer.isEmpty()){
                Toast.makeText(applicationContext, "Please select an answer to continue", Toast.LENGTH_SHORT).show()
                return
            }
            if(selectedAnswer == questionModelList[currentQuestionIndex].correct){
                score++
                // For check the score is working or not
                Log.i("Score of quiz", score.toString())
            }
            currentQuestionIndex++
            loadQuestions()
        } else {
            //options button is clicked
            selectedAnswer = clickedBtn.text.toString()
            clickedBtn.setBackgroundColor(getColor(R.color.blue_med))
        }
    }

    private fun finishQuiz(){
        //finish the quiz
        val totalQuestions = questionModelList.size
        val percentage = ((score.toFloat()/totalQuestions.toFloat()) * 100).toInt()

        val dialogBinding = ScoreDialogBinding.inflate(layoutInflater)
        dialogBinding.apply {
            scoreProgressIndicator.progress = percentage
            scoreProgressText.text = "$percentage%"
            if (percentage>80){
                scoreTitle.text = "Wow! You are a genius"
                scoreTitle.setTextColor(getColor(R.color.light_blue_med))
            }
            else if (percentage in 61..80){
                scoreTitle.text = "Congrats! You are a brilliant"
                scoreTitle.setTextColor(getColor(R.color.teal_200))
            } else if (percentage in 30..60){
                scoreTitle.text = "Congrats! Your level is average"
                scoreTitle.setTextColor(getColor(R.color.purple_200))
            } else {
                scoreTitle.text = "Sorry! You have failed"
                scoreTitle.setTextColor(getColor(R.color.error))
            }
            scoreSubtitle.text = "$score out of $totalQuestions questions are correct"
            finishBtn.setOnClickListener{
                finish()
            }
        }

       AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setCancelable(false)
            .show()

      }
}