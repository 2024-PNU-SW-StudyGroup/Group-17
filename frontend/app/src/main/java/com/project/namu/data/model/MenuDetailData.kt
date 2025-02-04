package com.project.namu.data.model

import com.google.gson.annotations.SerializedName

data class MenuDetailResponse(
    val message: String,
    val data: MenuDetailData,
    val errorCode: String,
    val isSuccess: Boolean
)

data class MenuDetailData(
    val popularity: Boolean,

    @SerializedName("menu_id")
    val menuId: Int,

    @SerializedName("set_name")
    val setName: String,

    @SerializedName("menu_names")
    val menuNames: String,

    @SerializedName("menu_price")
    val menuPrice: Int,

    @SerializedName("menu_discount_price")
    val menuDiscountPrice: Int,

    @SerializedName("menu_picture_url")
    val menuPictureUrl: String,

    @SerializedName("menu_detail")
    val menuDetail: String
)