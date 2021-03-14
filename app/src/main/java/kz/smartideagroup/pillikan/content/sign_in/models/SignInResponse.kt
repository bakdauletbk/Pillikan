package kz.smartideagroup.pillikan.content.sign_in.models

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("code")
    val username: Int,
    @SerializedName("message")
    val password: String,
    @SerializedName("status")
    val status: Int
)
