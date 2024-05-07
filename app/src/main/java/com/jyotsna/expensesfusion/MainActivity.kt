package com.jyotsna.expensesfusion

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge display for the activity
        enableEdgeToEdge()

        // Set the layout of the activity
        setContentView(R.layout.activity_main)

        // Initialize Firebase authentication instance
        firebaseAuth = FirebaseAuth.getInstance()

        // Check if the user is not logged in
        if (firebaseAuth.currentUser == null) {
            // If user is not logged in, navigate to SignInActivity
            startActivity(Intent(this, SignInActivity::class.java))
            // Finish MainActivity to prevent returning to it via Back button
            finish()
        }
    }
}
