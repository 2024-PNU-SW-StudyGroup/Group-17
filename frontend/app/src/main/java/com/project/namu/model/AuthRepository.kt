package com.project.namu.model

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import java.io.File
import javax.inject.Inject

class AuthRepository @Inject constructor(
    @ApplicationContext private val context : Context) {
    // DataStore에서 토큰을 저장하는 함수




    private val _authAccess = MutableStateFlow<String?>(null)
    val authAccess: StateFlow<String?> = _authAccess
    suspend fun saveToken(
        accessToken: String,
        refreshToken : String
    ) {
        context.tokenDataStore.edit { preferences ->
            preferences[stringPreferencesKey("access_token")] = accessToken
            preferences[stringPreferencesKey("refresh_token")] = refreshToken
            Log.d("token", "$accessToken")
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