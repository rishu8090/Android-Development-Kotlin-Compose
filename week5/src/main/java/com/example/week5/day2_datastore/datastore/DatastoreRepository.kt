package com.example.week5.day2_datastore.datastore

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepository @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
        val authenticated :Flow<Boolean> = dataStoreManager.authenticated
        suspend fun saveUserAuthenticated(authenticated : Boolean){
        dataStoreManager.saveUserAuthenticated(authenticated)
 }

    suspend fun logout() {
         dataStoreManager.logout()
    }
}