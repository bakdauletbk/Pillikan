package kz.smartideagroup.pillikan.content.home.notifications.list

import com.google.gson.annotations.SerializedName

data class NotificationRequest(
    @SerializedName("pageNumber")
    val pageNumber: String
)
