package kz.smartideagroup.pillikan.content.home.welcome.models

import com.google.gson.annotations.SerializedName

data class NewRetailListResponse(
    @SerializedName("retailList")
    val retailList: List<RetailModel>,
    @SerializedName("totalElements")
    val totalElements: Int
)
