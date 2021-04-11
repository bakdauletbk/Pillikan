package kz.smartideagroup.pillikan.content.sign_in.models

import com.google.gson.annotations.SerializedName

data class SmsRequest(
    @SerializedName("username")
    val phone: String
)