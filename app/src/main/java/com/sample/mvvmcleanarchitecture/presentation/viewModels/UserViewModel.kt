package com.sample.mvvmcleanarchitecture.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.mvvmcleanarchitecture.data.model.UserResponse
import com.sample.mvvmcleanarchitecture.domain.usecase.GetUsersUseCase
import com.sample.mvvmcleanarchitecture.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _users = MutableLiveData<ApiResult<UserResponse>>()
    val users: LiveData<ApiResult<UserResponse>> = _users

    fun getUsers(page: Int) = viewModelScope.launch {
        _users.postValue(ApiResult.Loading())
        try {
            val result = getUsersUseCase(page)
            _users.postValue(ApiResult.Success(result))
        } catch (e: Exception) {
            _users.postValue(ApiResult.Error("Failed to fetch users: ${e.message}"))
        }
    }
}
