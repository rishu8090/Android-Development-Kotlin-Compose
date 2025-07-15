package com.example.week9.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

//Use the property delegate created by preferences DataStore to create an instance of DataStore<Preferences> .
// Call it once at the top level of your kotlin file, and access it through this property throughout the rest of your application

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_preference")    // all this thing for this.

class DataStoreManager @Inject constructor(                  // here, context is required, but hilt doesn't know to how to provide it,
    @param:ApplicationContext private val context: Context         // you can provide context in hilt by two methods 1. @Provide 2. @ApplicationContext
) {
    val authenticatedKey = booleanPreferencesKey(name = "authenticated")  // reading

    val authenticated: Flow<Boolean> =
        context.dataStore.data.map { preferences ->      // Flow is used bcz whenever the value of authenticatedKey changes, authenticated read and write dynamically,
            preferences[authenticatedKey] ?: false
        }

    suspend fun saveIsAuthenticated(authenticated: Boolean) {  // here changes preferences,
        context.dataStore.edit { preferences ->
            preferences[authenticatedKey] = authenticated // works here as a key-value pair.
        }
    }
}