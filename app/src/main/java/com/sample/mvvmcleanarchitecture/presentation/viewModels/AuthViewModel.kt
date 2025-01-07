package com.sample.mvvmcleanarchitecture.presentation.viewModels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sample.mvvmcleanarchitecture.data.model.User
import com.sample.mvvmcleanarchitecture.domain.usecase.LoginUseCase
import com.sample.mvvmcleanarchitecture.domain.usecase.SignupUseCase
import com.sample.mvvmcleanarchitecture.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val signupUseCase: SignupUseCase
) : ViewModel() {

    // Login function returning ApiResult
    fun login(user: User) = liveData(Dispatchers.IO) {
        emit(ApiResult.Loading())  // Emit loading state
        try {
            val result = loginUseCase(user)
            emit(ApiResult.Success(result))  // Emit success state with result
        } catch (e: Exception) {
            emit(ApiResult.Error("Login failed: ${e.message}"))  // Emit error state
        }
    }

    // Signup function returning ApiResult
    fun signup(user: User) = liveData(Dispatchers.IO) {
        emit(ApiResult.Loading())  // Emit loading state
        try {
            val result = signupUseCase(user)
            emit(ApiResult.Success(result))  // Emit success state with result
        } catch (e: Exception) {
            emit(ApiResult.Error("Signup failed: ${e.message}"))  // Emit error state
        }
    }
}

