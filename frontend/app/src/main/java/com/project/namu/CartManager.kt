package com.project.namu

// CartManager.kt
import androidx.compose.runtime.mutableStateListOf
import com.project.namu.data.model.CartItemModel

object CartManager {
    // mutableStateListOf를 사용하면 Compose recomposition이 발생합니다.
    private val _cartItems = mutableStateListOf<CartItemModel>()
    val cartItems: List<CartItemModel> get() = _cartItems

    fun addItem(item: CartItemModel) {
        // 이미 같은 상품이 있다면 수량 증가, 없으면 새 항목 추가
        val existing = _cartItems.find { it.id == item.id }
        if (existing != null) {
            existing.quantity += item.quantity
        } else {
            _cartItems.add(item)
        }
    }

    fun removeItem(item: CartItemModel) {
        _cartItems.remove(item)
    }

    fun clearCart() {
        _cartItems.clear()
    }

    fun getTotalPrice(): Int {
        return _cartItems.sumOf { it.price * it.quantity }
    }
}
