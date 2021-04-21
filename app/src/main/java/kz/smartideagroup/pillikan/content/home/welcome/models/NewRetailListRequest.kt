package kz.smartideagroup.pillikan.content.home.welcome.models

import com.google.gson.annotations.SerializedName

data class NewRetailListRequest(
    @SerializedName("filter")
    val filter: RetailFilterModel,
    @SerializedName("pageNumber")
    val pageNumber: Int,
    @SerializedName("size")
    val size: Int
){
    data class RetailFilterModel(
        @SerializedName("cityId")
        val cityId: Int,
        @SerializedName("categoryId")
        val categoryId: Int? = null,
        @SerializedName("name")
        val name: String = ""
    )
}