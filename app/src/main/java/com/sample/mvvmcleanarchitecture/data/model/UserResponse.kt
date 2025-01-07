package com.sample.mvvmcleanarchitecture.data.model

data class UserResponse(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<UserItem>
)
