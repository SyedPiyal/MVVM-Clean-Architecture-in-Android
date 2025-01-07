package com.sample.mvvmcleanarchitecture.presentation.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.mvvmcleanarchitecture.R
import com.sample.mvvmcleanarchitecture.databinding.ActivityMainBinding
import com.sample.mvvmcleanarchitecture.presentation.adapters.UserAdapter
import com.sample.mvvmcleanarchitecture.presentation.viewModels.UserViewModel
import com.sample.mvvmcleanarchitecture.utils.ApiResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up RecyclerView
        userAdapter = UserAdapter(emptyList())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }

        // Observe the user data from ViewModel
        userViewModel.users.observe(this, Observer { result ->
            when (result) {
                is ApiResult.Loading -> {
                    // Show a loading indicator
                    binding.loadingProgressBar.visibility = View.VISIBLE
                }
                is ApiResult.Success -> {
                    // Update RecyclerView with the user list
                    binding.loadingProgressBar.visibility = View.GONE
                    userAdapter = UserAdapter(result.data.data) // Update the data
                    binding.recyclerView.adapter = userAdapter
                }
                is ApiResult.Error -> {
                    binding.loadingProgressBar.visibility = View.GONE
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

        // Fetch users on activity creation
        userViewModel.getUsers(page = 2)
    }
}
