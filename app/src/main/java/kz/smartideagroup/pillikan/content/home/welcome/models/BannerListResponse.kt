package kz.smartideagroup.pillikan.content.home.welcome.models

import com.google.gson.annotations.SerializedName

data class BannerListResponse(
    @SerializedName("sliders")
    val sliders: List<BannerModel>
)
