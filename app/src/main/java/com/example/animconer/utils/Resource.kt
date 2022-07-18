package com.example.animconer.utils

sealed class Resource<out T> {
    class Success<out T>(val value: T) : Resource<T>()
    object Loading : Resource<Nothing>()
    data class Error(
        val message: String?
    ) : Resource<Nothing>()
}