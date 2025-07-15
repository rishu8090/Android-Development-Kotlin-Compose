package com.example.week9.datastore

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepository @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    val authenticated: Flow<Boolean> = dataStoreManager.authenticated

    suspend fun saveIsAuthenticated(authenticated: Boolean) {
        dataStoreManager.saveIsAuthenticated(authenticated)
    }
}