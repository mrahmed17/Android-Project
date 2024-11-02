package com.mrahmed.isdbsquizexam

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var useremail: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var signupButton: Button
    private lateinit var clickLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()

        // Initialize views
        useremail = findViewById(R.id.useremail)
        password = findViewById(R.id.password)
        confirmPassword = findViewById(R.id.confirm_password)
        signupButton = findViewById(R.id.btn_signup)
        clickLogin = findViewById(R.id.clickLogin)

        // Sign Up button click listener
        signupButton.setOnClickListener { handleSignUp() }

        // Navigate to LoginActivity when clicking the "Already have an account?" link
        clickLogin.setOnClickListener { onLoginClick(it) }
    }

    private fun handleSignUp() {
        val email = useremail.text.toString().trim()
        val pass = password.text.toString().trim()
        val confirmPass = confirmPassword.text.toString().trim()

        when {
            email.isEmpty() || pass.isEmpty() || confirmPass.isEmpty() -> {
                showToast("Please fill out all fields")
            }
            pass != confirmPass -> {
                showToast("Passwords do not match!")
            }
            else -> {
                signUpUser(email, pass)
            }
        }
    }

    private fun signUpUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    showToast("Successfully signed up")
                    navigateToMainActivity()
                } else {
                    showToast("Sign up failed: ${task.exception?.message}")
                }
            }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Close the SignupActivity
    }

    fun onLoginClick(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Close the SignupActivity when navigating to login
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}