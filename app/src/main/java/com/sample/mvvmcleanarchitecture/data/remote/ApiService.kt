package com.sample.mvvmcleanarchitecture.data.remote


import com.sample.mvvmcleanarchitecture.data.model.AuthResponse
import com.sample.mvvmcleanarchitecture.data.model.User
import com.sample.mvvmcleanarchitecture.data.model.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("register")
    suspend fun signup(@Body user: User): AuthResponse

    @POST("login")
    suspend fun login(@Body user: User): AuthResponse

    @GET("users")
    suspend fun getUsers(@Query("page") page: Int): UserResponse
}
