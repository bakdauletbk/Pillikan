package kz.smartideagroup.pillikan.content.home.welcome.models

import com.google.gson.annotations.SerializedName

data class RetailModel(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("address")
    var address: String = "",
    @SerializedName("img")
    var img: String? = null,
    @SerializedName("logo")
    var logo: String? = null,
    @SerializedName("latitude")
    var latitude: Double? = null,
    @SerializedName("longitude")
    var longitude: Double? = null,
    @SerializedName("phone")
    var phone: String? = null,
    @SerializedName("qrImg")
    var qrImg: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("webUrl")
    var webUrl: String? = null,
    @SerializedName("booking")
    var booking: Int? = null,
    @SerializedName("card")
    var card: Int? = null,
    @SerializedName("cash")
    var cash: Int? = null,
    @SerializedName("cashBack")
    var cashBack: Int? = null,
    @SerializedName("dlvCashBack")
    var dlvCashBack: Int? = null,
    @SerializedName("delivery")
    var delivery: Int? = null,
    @SerializedName("wifi")
    var wifi: Int? = null,
    @SerializedName("kaspi")
    var kaspi: Int? = null,
    @SerializedName("type")
    var type: Int? = null,
    @SerializedName("youtube")
    var youtube: String? = null,
    @SerializedName("instagram")
    var instagram: String? = null,
    @SerializedName("whatsappUrl")
    var whatsappUrl: String? = null,
    @SerializedName("telegram")
    var telegram: String? = null,
    @SerializedName("vk")
    var vk: String? = null,
    @SerializedName("facebook")
    var facebook: String? = null,
    @SerializedName("avgAmount")
    var avgAmount: String? = null,
    @SerializedName("workDays")
    var workDays: List<WorkDay>? = null,
    @SerializedName("images")
    var images: List<ImageItem>? = null,
    @SerializedName("deliveryCategories")
    var deliveryCategories: ArrayList<FoodCategory>? = null,
    @SerializedName("pillikanDelivery")
    var pillikanDelivery: Int? = null,
    @SerializedName("status")
    var status: Int? = 0,
    @SerializedName("favorite")
    var favorite: Boolean = false,
    @SerializedName("rating")
    var rating: Float = 0f,
    @SerializedName("isWork")
    var isWork: Int = 0,
    @SerializedName("payIsWork")
    var payIsWork: Int = 0
){
    data class WorkDay(
        @SerializedName("around")
        val around: Boolean? = null,
        @SerializedName("day")
        val day: Int? = null,
        @SerializedName("timeEnd")
        val timeEnd: String? = null,
        @SerializedName("timeStart")
        val timeStart: String? = null,
        @SerializedName("work")
        val work: Boolean = false
    )
    data class ImageItem(
        @SerializedName("main")
        val main: Boolean = false,
        @SerializedName("path")
        val path: String? = null
    )
    data class FoodCategory(
        @SerializedName("name")
        var name: String = "",
        @SerializedName("age_access")
        var age_access: Int = 0,
        @SerializedName("dishes")
        var dishes: ArrayList<Food>
    ){
        data class Food(
            @SerializedName("id")
            var id: String = "",
            @SerializedName("name")
            var name: String = "",
            @SerializedName("composition")
            var composition: String = "",
            @SerializedName("img")
            var img: String? = null,
            @SerializedName("price")
            var price: Int? = null,
            @SerializedName("status")
            var status: Int? = null
        )
    }
}