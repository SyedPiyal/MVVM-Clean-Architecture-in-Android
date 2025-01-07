package com.sample.mvvmcleanarchitecture.utils


// Sealed class to represent different states of an API response
sealed class ApiResult<T> {
    // Represents a successful response with the data
    data class Success<T>(val data: T) : ApiResult<T>()

    // Represents a loading state
    class Loading<T> : ApiResult<T>()

    // Represents an error or failure state with an optional error message
    data class Error<T>(val message: String?) : ApiResult<T>()
}
