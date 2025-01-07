package com.sample.mvvmcleanarchitecture.data.repository


import com.sample.mvvmcleanarchitecture.data.model.AuthResponse
import com.sample.mvvmcleanarchitecture.data.model.User
import com.sample.mvvmcleanarchitecture.data.remote.ApiService
import com.sample.mvvmcleanarchitecture.domain.domain_repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AuthRepository {

    override suspend fun login(user: User): AuthResponse {
        return apiService.login(user)
    }

    override suspend fun signup(user: User): AuthResponse {
        return apiService.signup(user)
    }
}