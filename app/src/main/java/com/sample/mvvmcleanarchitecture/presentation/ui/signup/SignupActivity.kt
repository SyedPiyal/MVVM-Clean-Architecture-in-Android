package com.sample.mvvmcleanarchitecture.presentation.ui.signup

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
import com.sample.mvvmcleanarchitecture.databinding.ActivitySignupBinding
import com.sample.mvvmcleanarchitecture.presentation.viewModels.AuthViewModel
import com.sample.mvvmcleanarchitecture.utils.ApiResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {

    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var binding: ActivitySignupBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            // Validation
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = User(email = email, password = password)

            // Show loading indicator
            binding.loadingProgressBar.visibility = android.view.View.VISIBLE

            // Call signup UseCase via ViewModel
            authViewModel.signup(user).observe(this, Observer { response ->
                binding.loadingProgressBar.visibility = View.GONE

                when (response) {
                    is ApiResult.Loading -> {
                        // handle loading state here
                    }

                    is ApiResult.Success -> {
                        // Successful login
                        Toast.makeText(this, "Registration successful!", Toast.LENGTH_LONG).show()
                        // Optionally, navigate to the next screen
                        // e.g., startActivity(Intent(this, MainActivity::class.java))
                    }

                    is ApiResult.Error -> {
                        // Failed login
                        Toast.makeText(
                            this, response.message ?: "An error occurred", Toast.LENGTH_SHORT
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