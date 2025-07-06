package com.example.week7.day1_pagination

import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class PaginationViewModel @Inject constructor(
    private val repository: PaginationRepository
) : ViewModel() {
    val repositories: Flow<PagingData<Repository>> = repository
        .searchRepositories()
        .cachedIn(viewModelScope)   // read documentation, for cachedIn
}