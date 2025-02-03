package com.project.namu.data.model

import com.google.gson.annotations.SerializedName

data class StoreData(
    @SerializedName("storeId") val storeId: Int,
    @SerializedName("storeName") val storeName: String,
    @SerializedName("storeCategory") val storeCategory: String,
    @SerializedName("pickupTimes") val pickupTimes: String,
    @SerializedName("minPrice") val minPrice: Int,
    @SerializedName("reviewCount") val reviewCount: Int,
    @SerializedName("orderCount") val orderCount: Int,
    @SerializedName("storeRating") val storeRating: Float,
    @SerializedName("location") val location: Int,

    @SerializedName("open") val open: Boolean,
    @SerializedName("setNames") val setNames: List<SetInfo>
)

data class StoreDetailData(
    @SerializedName("storeId") val storeId: Int,
    @SerializedName("storeName") val storeName: String,
    @SerializedName("storeCategory") val storeCategory: String,
    @SerializedName("pickupTimes") val pickupTimes: String,
    @SerializedName("minPrice") val minPrice: Int,
    @SerializedName("reviewCount") val reviewCount: Int,
    @SerializedName("orderCount") val orderCount: Int,

    // storeRating: 서버가 int로만 준다면 Int로 받고 UI에서 toFloat()로 변환해도 됩니다.
    @SerializedName("storeRating") val storeRating: Float,

    @SerializedName("location") val location: Int,

    // Swagger: "address" → 여긴 storeAddress로 매핑
    @SerializedName("address") val storeAddress: String? = null,

    // Swagger: "phoneNumber" → 여긴 storePhone으로 매핑
    @SerializedName("phoneNumber") val storePhone: String? = null,

    // Swagger: "storePictureUrls" (배열)
    @SerializedName("storePictureUrls") val storePictureUrls: List<String>? = null,

    // 간단 정보 - SetInfo
    @SerializedName("setNames") val setNames: List<SetInfo> = emptyList(),

    // 실제 메뉴 정보 - DetailMenu
    @SerializedName("menus") val menus: List<DetailMenu> = emptyList(),

    @SerializedName("open") val open: Boolean
)

data class SetInfo(
    @SerializedName("setName") val setName: String,
    @SerializedName("menuNames") val menuNames: String = ""
)

data class DetailMenu(
    @SerializedName("set_name") val setName: String,
    @SerializedName("menu_names") val menuNames: String,
    @SerializedName("menu_price") val menuPrice: Int,
    @SerializedName("menu_discount_price") val menuDiscountPrice: Int,
    @SerializedName("menu_picture_url") val menuPictureUrl: String,
    @SerializedName("popularity") val popularity: Boolean,
    @SerializedName("menu_detail") val menuDetail: String
)
