package com.sample.mvvmcleanarchitecture.domain.usecase

import com.sample.mvvmcleanarchitecture.data.model.UserResponse
import com.sample.mvvmcleanarchitecture.domain.domain_repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(page: Int): UserResponse {
        return userRepository.getUsers(page)
    }
}
