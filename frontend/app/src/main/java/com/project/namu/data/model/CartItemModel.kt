package com.project.namu.data.model

data class CartItemModel(
    val id: String,           // 상품 또는 가게 ID
    val name: String,         // 상품명
    val imageUrl: String,     // 상품 이미지 URL
    val price: Int,           // 원래 가격 (원 단위)
    val discountPrice: Int? = null, // 할인 가격 (할인이 있을 경우, 없으면 null)
    val description: String? = null, // 상품 설명
    var quantity: Int = 1     // 수량 (초기값 1)
)

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
