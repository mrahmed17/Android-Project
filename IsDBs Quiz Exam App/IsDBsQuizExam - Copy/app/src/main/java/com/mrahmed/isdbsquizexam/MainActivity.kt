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
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Check if the user is logged in
        if (auth.currentUser == null) {
            // If not logged in, redirect to LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        // Redirect to HomeActivity instead of MainActivity if logged in
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
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