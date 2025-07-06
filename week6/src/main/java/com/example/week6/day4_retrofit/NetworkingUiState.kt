package com.example.week6.day4_retrofit

sealed interface NetworkingUiState {
    object None : NetworkingUiState // this is used to show nothing.
    object Loading : NetworkingUiState
    data class Success(val posts: List<Post>) : NetworkingUiState
    data class Error(val message: String) : NetworkingUiState

}