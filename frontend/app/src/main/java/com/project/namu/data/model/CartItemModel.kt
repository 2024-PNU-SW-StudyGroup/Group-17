package com.project.namu.data.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class CartItemModel(
    val id: String,
    val name: String,
    val imageUrl: String,
    val price: Int,
    val discountPrice: Int? = null,
    val description: String? = null,
    var quantity: Int = 1
) {
    // 수량을 mutableStateOf로 관리하도록 변환한 예 (클래스 형태로 변경 필요)
    var quantityState by mutableStateOf(quantity)
        private set

    fun increaseQuantity() {
        quantityState++
        quantity = quantityState  // 동기화
    }

    fun decreaseQuantity() {
        if (quantityState > 1) {
            quantityState--
            quantity = quantityState
        }
    }
}


// 예: com.project.namu.data.model 패키지 내에 작성
fun MenuDetailData.toCartItem(): CartItemModel {
    return CartItemModel(
        id = menuId.toString(),
        name = setName, // 또는 필요에 따라 menuNames 등으로 수정
        imageUrl = menuPictureUrl,
        price = menuPrice,
        discountPrice = menuDiscountPrice,
        description = menuDetail,
        quantity = 1
    )
}
