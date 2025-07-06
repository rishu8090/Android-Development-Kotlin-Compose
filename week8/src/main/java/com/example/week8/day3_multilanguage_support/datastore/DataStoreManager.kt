package com.example.week8.day3_multilanguage_support.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.week8.day3_multilanguage_support.Language
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

//Use the property delegate created by preferences DataStore to create an instance of DataStore<Preferences> .
// Call it once at the top level of your kotlin file, and access it through this property throughout the rest of your application

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_preference")    // all this thing for this.

class DataStoreManager @Inject constructor(                  // here, context is required, but hilt doesn't know to how to provide it,
    @ApplicationContext private val context: Context         // you can provide context in hilt by two methods 1. @Provide 2. @ApplicationContext
) {
    val languageCodeKey = stringPreferencesKey(name = "language_code_key")  // reading

    val language: Flow<Language> =
        context.dataStore.data.map { preferences ->      // Flow is used bcz whenever the value of authenticatedKey changes, authenticated read and write dynamically,
            val languageCode = preferences[languageCodeKey]
//            Language.valueOf(languageCode ?: Language.English.code)
            Language.entries.firstOrNull {it.code == languageCode} ?: Language.English // from Language enum class entries will return all the list of values, and firstOrNull will return first value.
                                                                                            // firstOrNull -> * Returns the first element matching the given [predicate], or `null` if element was not found.
        }

    suspend fun saveLanguage(language: Language) {  // here changes preferences,
        context.dataStore.edit { preferences ->
            preferences[languageCodeKey] = language.code  // works here as a key-value pair.
        }
    }
}