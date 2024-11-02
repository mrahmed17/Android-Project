package com.mrahmed.isdbquiztest

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.FirebaseDatabase
import com.mrahmed.isdbquiztest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var quizModelList: MutableList<QuizModel>
    lateinit var adapter: QuizListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quizModelList = mutableListOf()
        getDataFromFirebase()

    }

    private fun setupRecyclerView() {
        binding.progressBar.visibility = View.GONE
        adapter = QuizListAdapter(quizModelList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun getDataFromFirebase() {
        binding.progressBar.visibility = View.VISIBLE

        FirebaseDatabase.getInstance().reference
            .get()
            .addOnSuccessListener{ dataSnapshot ->
                if ( dataSnapshot.exists()){
                    for (quizSnapshot in dataSnapshot.children){
                        val quizModel = quizSnapshot.getValue(QuizModel::class.java)
                        quizModelList.add(quizModel!!)
                    }
                    setupRecyclerView()
                }
            }

//        //dummy data
//
//        val listQuestionModel = mutableListOf<QuestionModel>()
//        listQuestionModel.add(QuestionModel("What is android?", mutableListOf("Language", "OS", "Product", "None"), "OS"))
//        listQuestionModel.add(QuestionModel("Who owns android?", mutableListOf("Apple", "Google", "Samsung", "Microsoft"), "Google"))
//        listQuestionModel.add(QuestionModel("Which assistant android uses?", mutableListOf("Siri", "Cortana", "Google Assistant", "Alexa"), "Google Assistant"))
//
//        quizModelList.add(
//            QuizModel(
//                id = "1",
//                title = "Programming",
//                subtitle = "All the basic programming",
//                time = "10",
//                listQuestionModel
//            )
//        )
////        quizModelList.add(
////            QuizModel(
////                id = "2",
////                title = "Computer",
////                subtitle = "All the computer questions",
////                time = "20",
////                listQuestionModel
////            )
////        )
////        quizModelList.add(
////            QuizModel(
////                id = "3",
////                title = "Geography",
////                subtitle = "Boost your geography knowledge",
////                time = "15",
////                listQuestionModel
////            )
////        )

        setupRecyclerView()

    }

}