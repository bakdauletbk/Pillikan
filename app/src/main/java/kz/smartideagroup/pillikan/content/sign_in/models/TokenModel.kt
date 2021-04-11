package kz.smartideagroup.pillikan.content.sign_in.models

import com.google.gson.annotations.SerializedName

data class TokenModel(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String
)
