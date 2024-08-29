package com.example.myapplication.network

/**
 * Sealed class used when type of response is confirmed that will be one of subclass.
 * Sealed class ensure type safety which match at compile time rather than run time
 */
sealed class Resource<T>(val data: T? = null, val throwable: Throwable? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Failure<T>(data: T? = null, throwable: Throwable) : Resource<T>(data, throwable)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}