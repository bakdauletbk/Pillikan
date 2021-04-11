package kz.smartideagroup.pillikan.content.sign_in.models

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("username")
    val username: String,
    @SerializedName("balance")
    val balance: String,
    @SerializedName("isPin")
    val isPin: Int,
    @SerializedName("qrCode")
    val qrCode: String,
    @SerializedName("isCard")
    val isCard: Int,
    @SerializedName("isAds")
    val isAds: Int,
    @SerializedName("promoCode")
    val promoCode: String
)
