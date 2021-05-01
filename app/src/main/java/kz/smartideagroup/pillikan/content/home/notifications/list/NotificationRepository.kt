package kz.smartideagroup.pillikan.content.home.notifications.list

import android.app.Application
import android.app.Notification
import kz.smartideagroup.pillikan.common.base_vmmv.BaseRepository
import retrofit2.Response

class NotificationRepository(application: Application) : BaseRepository(application) {

    suspend fun getSystemNotification(notificationRequest: NotificationRequest): Response<NotificationsListResponse> {
        return networkService.getSystemNotification(
            userSession.getAccessToken(),
            applicationVersion,
            notificationRequest
        )
    }

    suspend fun getPaymentsNotification(notificationRequest: NotificationRequest): Response<NotificationsListResponse> {
        return networkService.getPaymentNotification(
            userSession.getAccessToken(),
            applicationVersion,
            notificationRequest
        )
    }

}