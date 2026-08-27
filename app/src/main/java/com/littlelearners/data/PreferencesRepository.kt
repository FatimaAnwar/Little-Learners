package com.littlelearners.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(
    name = "little_learners_preferences"
)

class PreferencesRepository(
    private val context: Context
) {

    private val musicKey =
        booleanPreferencesKey("music_enabled")

    val musicEnabled: Flow<Boolean> =
        context.dataStore.data.map { preferences ->

            preferences[musicKey] ?: true
        }

    suspend fun setMusicEnabled(
        enabled: Boolean
    ) {

        context.dataStore.edit { preferences ->

            preferences[musicKey] = enabled
        }
    }
}