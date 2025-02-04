package com.project.namu

import android.content.Context

object FavoriteManager {
    private const val PREFS_NAME = "favorite_prefs"
    private const val FAVORITES_KEY = "favorite_store_ids"

    // 즐겨찾기 토글: 저장되어 있다면 제거, 없으면 추가
    fun toggleFavorite(context: Context, storeId: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val favorites = prefs.getStringSet(FAVORITES_KEY, mutableSetOf())?.toMutableSet() ?: mutableSetOf()
        if (favorites.contains(storeId)) {
            favorites.remove(storeId)
        } else {
            favorites.add(storeId)
        }
        prefs.edit().putStringSet(FAVORITES_KEY, favorites).apply()
    }

    // 특정 가게가 즐겨찾기 상태인지 확인
    fun isFavorite(context: Context, storeId: String): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getStringSet(FAVORITES_KEY, mutableSetOf())?.contains(storeId) ?: false
    }

    // 로컬 저장소에 저장된 모든 즐겨찾기 가게 ID 목록 반환
    fun getFavorites(context: Context): Set<String> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getStringSet(FAVORITES_KEY, mutableSetOf()) ?: emptySet()
    }
}
