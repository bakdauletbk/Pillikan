package kz.smartideagroup.pillikan.content.sign_in.models

import com.google.gson.annotations.SerializedName

data class ProfileModel(
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("birthDay")
    val birthDay: String,
    @SerializedName("city")
    val city: CityModel,
    @SerializedName("sex")
    val sex: Boolean
)
