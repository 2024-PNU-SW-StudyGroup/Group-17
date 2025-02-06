package com.project.namu.model

import com.project.namu.data.model.StoreData

data class SearchResponse(
    val isSuccess: Boolean,
    val message: String,
    val data: List<StoreData>
)
