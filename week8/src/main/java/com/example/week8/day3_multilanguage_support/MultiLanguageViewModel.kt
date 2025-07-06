package com.example.week8.day3_multilanguage_support

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week8.day3_multilanguage_support.datastore.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MultiLanguageViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {
    // in viewModel we usually use stateflow, bcz stateflow is stateHolder, it contains only one value at a time.
    val language: StateFlow<Language> =    // this value give us the current language.
        repository.language
            .stateIn(   // to change flow to stateflow we use stateIn()
            scope = viewModelScope,
            started = SharingStarted.Companion.Eagerly,
            initialValue = Language.English
        )

    fun saveLanguage(language: Language){  // this will used to save the language in datastore.
     viewModelScope.launch(Dispatchers.IO) {
         repository.saveLanguage(language)
     }
    }
}