package com.project.namu.model

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.tokenDataStore by preferencesDataStore(name = "user_prefs")

