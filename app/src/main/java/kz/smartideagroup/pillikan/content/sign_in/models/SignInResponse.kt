package kz.smartideagroup.pillikan.content.sign_in.models

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("user")
    val user: UserModel,
    @SerializedName("token")
    val token: TokenModel,
    @SerializedName("profile")
    val profile: ProfileModel
)
