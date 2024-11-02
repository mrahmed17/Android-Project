package com.mrahmed.isdbsquizexam

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.mrahmed.isdbsquizexam.databinding.ActivityMainBinding


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
                       if (quizModel != null){
                           quizModelList.add(quizModel)
                       }
                    }
                }
                setupRecyclerView()
            }
    }
}