package com.sample.mvvmcleanarchitecture.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.sample.mvvmcleanarchitecture.R
import com.sample.mvvmcleanarchitecture.data.model.User
import com.sample.mvvmcleanarchitecture.databinding.ActivityLoginBinding
import com.sample.mvvmcleanarchitecture.databinding.ActivitySignupBinding
import com.sample.mvvmcleanarchitecture.presentation.ui.home.MainActivity
import com.sample.mvvmcleanarchitecture.presentation.viewModels.AuthViewModel
import com.sample.mvvmcleanarchitecture.utils.ApiResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the login button click listener
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            // Validate input
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            // Create a User object to send to the ViewModel
            val user = User(email = email, password = password)

            // Show the ProgressBar while the login request is being processed
            binding.loadingProgressBar.visibility = android.view.View.VISIBLE

            // Call the login function in the ViewModel and observe the result
            authViewModel.login(user).observe(this, Observer { result ->
                // Hide the ProgressBar once the request is complete
                binding.loadingProgressBar.visibility = View.GONE

                when (result) {
                    is ApiResult.Loading -> {
                        // handle loading state here
                    }

                    is ApiResult.Success -> {
                        // Successful login
                        Toast.makeText(
                            this,
                            "Login successful! Token: ${result.data.token}",
                            Toast.LENGTH_LONG
                        ).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                    is ApiResult.Error -> {
                        // Failed login
                        Toast.makeText(
                            this,
                            result.message ?: "An error occurred",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}