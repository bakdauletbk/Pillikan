package kz.smartideagroup.pillikan.content.home.welcome.models

import com.google.gson.annotations.SerializedName

data class BannerModel(
    @SerializedName("title")
    val title: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("retailId")
    val retailId: Int?,
    @SerializedName("type")
    val type: Int?,
)
