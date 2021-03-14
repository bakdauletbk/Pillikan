package kz.smartideagroup.pillikan.content.sign_in.models

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("userName")
    val username: String,
    @SerializedName("password")
    val password: String
)
