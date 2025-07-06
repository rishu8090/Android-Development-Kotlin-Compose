package com.example.week6.day4_retrofit

import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {
    private  val _posts = MutableStateFlow<NetworkingUiState>(NetworkingUiState.None)
    val posts: StateFlow<NetworkingUiState> get() = _posts   // : means return type of StateFlow<List<Post>>
    fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            _posts.value = NetworkingUiState.Loading
            try {
                val retrievedPosts = postRepository.getPosts()
                _posts.value = NetworkingUiState.Success(retrievedPosts)
            } catch (e: Exception) {
                e.printStackTrace()
                _posts.value = NetworkingUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}