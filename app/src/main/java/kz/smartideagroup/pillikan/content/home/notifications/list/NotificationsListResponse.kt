package kz.smartideagroup.pillikan.content.home.notifications.list

import android.app.Notification
import com.google.gson.annotations.SerializedName

data class NotificationsListResponse(
    @SerializedName("list")
    val list: List<NotificationModel>,
    @SerializedName("totalCount")
    val totalCount: Int
)