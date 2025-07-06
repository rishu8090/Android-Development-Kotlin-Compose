package com.example.week8.day3_multilanguage_support.datastore

import com.example.week8.day3_multilanguage_support.Language
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepository @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    val language: Flow<Language> = dataStoreManager.language

    suspend fun saveLanguage(language: Language) {
        dataStoreManager.saveLanguage(language)
    }   
}