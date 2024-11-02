package com.mrahmed.isdbsquizexam

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var edtUserEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tVSignUp: TextView
    private lateinit var textForgotPassword: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Initialize views
        edtUserEmail = findViewById(R.id.edtUserName)  // Correct ID here
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tVSignUp = findViewById(R.id.tVSignUp)
        textForgotPassword = findViewById(R.id.textForgotPassword)

        // Login button click listener
        btnLogin.setOnClickListener {
            val email = edtUserEmail.text.toString().trim()
            val password = edtPassword.text.toString().trim()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()

            if (!isValidEmail(email)) {
                edtUserEmail.error = "Please enter a valid email"
                edtUserEmail.requestFocus()
            } else if (password.isEmpty()) {
                edtPassword.error = "Please enter your password"
                edtPassword.requestFocus()
            } else {
                loginUser(email, password)
            }
        }

        // Forgot password click listener
        textForgotPassword.setOnClickListener {
            val email = edtUserEmail.text.toString().trim()
            if (!isValidEmail(email)) {
                Toast.makeText(this, "Please enter your email first", Toast.LENGTH_SHORT).show()
            } else {
                resetPassword(email)
            }
            Toast.makeText(this, "Forgot Password Clicked", Toast.LENGTH_SHORT).show()
        }

        // Sign Up text click listener
        tVSignUp.setOnClickListener {
            onSignUpClick(it)
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Navigate to HomeActivity after successful login
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(baseContext, "Login failed. Check your credentials.", Toast.LENGTH_SHORT).show()
                }
            }
    }


    private fun handleLoginError(exception: Exception?) {
        if (exception is FirebaseAuthInvalidCredentialsException) {
            Toast.makeText(baseContext, "Invalid credentials. Please try again.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(baseContext, "Login failed: ${exception?.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun resetPassword(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Password reset email sent to $email", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed to send reset email: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    // Helper method to validate email
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Sign Up function
    fun onSignUpClick(view: View) {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }
}
