package com.sample.mvvmcleanarchitecture.data.repository

import com.sample.mvvmcleanarchitecture.data.model.UserResponse
import com.sample.mvvmcleanarchitecture.data.remote.ApiService
import com.sample.mvvmcleanarchitecture.domain.domain_repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {
    override suspend fun getUsers(page: Int): UserResponse {
        return apiService.getUsers(page)
    }
}



