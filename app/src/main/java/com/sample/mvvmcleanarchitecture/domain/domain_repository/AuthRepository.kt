package com.sample.mvvmcleanarchitecture.domain.domain_repository

import com.sample.mvvmcleanarchitecture.data.model.AuthResponse
import com.sample.mvvmcleanarchitecture.data.model.User


interface AuthRepository {
    suspend fun login(user: User): AuthResponse
    suspend fun signup(user: User): AuthResponse
}
