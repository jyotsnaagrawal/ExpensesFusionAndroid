package com.jyotsna.expensesfusion

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jyotsna.expensesfusion.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivitySignInBinding.inflate(layoutInflater)
        // Set the content view to the root of the inflated layout
        setContentView(binding.root)

        // Initialize Firebase authentication instance
        firebaseAuth = FirebaseAuth.getInstance()

        // Check if the user is already authenticated
        if (firebaseAuth.currentUser != null) {
            // If authenticated, navigate to MainActivity
            startActivity(Intent(this, MainActivity::class.java))
            // Finish the current activity to prevent returning to it via Back button
            finish()
        }

        // Set click listener for the "Not Registered Yet, Sign Up!" text view
        binding.textView.setOnClickListener {
            // Navigate to SignUpActivity when clicked
            startActivity(Intent(this, SignUpActivity::class.java))
            // Finish the current activity to prevent returning to it via Back button
            finish()
        }

        // Set click listener for the sign-in button
        binding.button.setOnClickListener {
            // Get email and password from the input fields
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()

            // Check if email and password are not empty
            if (email.isNotEmpty() && pass.isNotEmpty()) {
                // Sign in with Firebase Auth
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // If sign-in is successful, navigate to MainActivity
                        startActivity(Intent(this, MainActivity::class.java))
                        // Finish the current activity to prevent returning to it via Back button
                        finish()
                    } else {
                        // If sign-in fails, display error message
                        Toast.makeText(this, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                // If email or password is empty, display error message
                Toast.makeText(this, "Email and password are required", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
