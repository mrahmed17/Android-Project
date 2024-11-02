package com.mrahmed.isdbsquizexam

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mrahmed.isdbsquizexam.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var leaderboardAdapter: LeaderboardAdapter
    private lateinit var leaderboardList: MutableList<LeaderboardModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth and Database
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        // Set user profile information
        val currentUser = auth.currentUser
        binding.usernameText.text = currentUser?.displayName ?: "Anonymous"
        binding.userEmailText.text = currentUser?.email

        // Setup RecyclerView for leaderboard
        leaderboardList = mutableListOf()
        leaderboardAdapter = LeaderboardAdapter(leaderboardList)
        binding.leaderboardRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.leaderboardRecyclerView.adapter = leaderboardAdapter

        // Load leaderboard data
        loadLeaderboard()

        // Set up button click listeners
        setupButtonListeners()
    }

    private fun loadLeaderboard() {
        database.child("leaderboard").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                leaderboardList.clear()
                for (leaderSnapshot in snapshot.children) {
                    val leader = leaderSnapshot.getValue(LeaderboardModel::class.java)
                    if (leader != null) {
                        leaderboardList.add(leader)
                    }
                }
                leaderboardAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error, e.g., show a toast or log the error
            }
        })
    }

    private fun setupButtonListeners() {
        binding.logoutButton.setOnClickListener {
            // Sign out the user
            FirebaseAuth.getInstance().signOut()
            // Navigate back to login activity
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        binding.accessMainButton.setOnClickListener {
            // Navigate to main screen activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
