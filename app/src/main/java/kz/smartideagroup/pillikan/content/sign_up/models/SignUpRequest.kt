package kz.smartideagroup.pillikan.content.sign_up.models

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("cityId")
    val cityId: String,
    @SerializedName("promo")
    val promo: String
)
