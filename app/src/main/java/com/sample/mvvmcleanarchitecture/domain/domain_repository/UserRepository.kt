package com.sample.mvvmcleanarchitecture.domain.domain_repository

import com.sample.mvvmcleanarchitecture.data.model.UserResponse

interface UserRepository {
    suspend fun getUsers(page: Int): UserResponse
}


