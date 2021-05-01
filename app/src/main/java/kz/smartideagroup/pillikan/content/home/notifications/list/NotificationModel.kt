package kz.smartideagroup.pillikan.content.home.notifications.list

import com.google.gson.annotations.SerializedName

data class NotificationModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("createdAt")
    val createdAt: String
)