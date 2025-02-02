package com.project.namu.model

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthRepository(private val context : Context) {
    // DataStore에서 토큰을 저장하는 함수
    suspend fun saveToken(
        accessToken: String,
        refreshToken : String
    ) {
        context.tokenDataStore.edit { preferences ->
            preferences[stringPreferencesKey("access_token")] = accessToken
            preferences[stringPreferencesKey("refresh_token")] = refreshToken
        }
    }

    // DataStore에서 토큰을 불러오는 함수
    val accessToken: Flow<String?> = context.tokenDataStore.data.map { preferences ->
        preferences[stringPreferencesKey("access_token")]
    }

    val refreshToken: Flow<String?> = context.tokenDataStore.data.map { preferences ->
        preferences[stringPreferencesKey("refresh_token")]
    }

}