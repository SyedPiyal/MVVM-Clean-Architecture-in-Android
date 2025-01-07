package com.sample.mvvmcleanarchitecture.domain.usecase


import com.sample.mvvmcleanarchitecture.data.model.AuthResponse
import com.sample.mvvmcleanarchitecture.data.model.User
import com.sample.mvvmcleanarchitecture.domain.domain_repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(user: User): AuthResponse {
        return authRepository.login(user)
    }
}
