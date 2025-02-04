package com.project.namu.data.model

data class CartItemModel(
    val id: String,         // 상품 또는 가게 ID
    val name: String,       // 상품명 또는 가게명
    val price: Int,         // 상품 가격 (원 단위)
    val imageUrl: String,   // 상품 이미지 URL
    var quantity: Int = 1   // 수량 (초기값 1)
)
