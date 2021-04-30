package kz.smartideagroup.pillikan.content.home.notifications.list

import android.app.Application
import android.app.Notification
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.smartideagroup.pillikan.common.utils.UtilsObject
import retrofit2.Response

class NotificationListViewModel(application: Application): AndroidViewModel(application) {

    val isError: MutableLiveData<String?> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val systemNotificationList: MutableLiveData<List<NotificationModel>> = MutableLiveData()
    val paymentNotificationList: MutableLiveData<List<NotificationModel>> = MutableLiveData()
    val totalElements: MutableLiveData<Int> = MutableLiveData()

    private val repository: NotificationRepository = NotificationRepository(application)

    fun getSystemNotifications(notificationRequest: NotificationRequest){
        viewModelScope.launch {
            isLoading.postValue(true)
            val response = repository.getSystemNotification(notificationRequest)
            when (response.isSuccessful) {
                true -> {
                    systemNotificationList.postValue(response.body()!!.list)
                    totalElements.postValue(response.body()!!.totalCount)
                }
                false -> handleErrorBody(response)
            }
        }
    }

    fun getPaymentNotification(notificationRequest: NotificationRequest){
        viewModelScope.launch {
            isLoading.postValue(true)
            val response = repository.getPaymentsNotification(notificationRequest)
            when (response.isSuccessful) {
                true -> {
                    paymentNotificationList.postValue(response.body()!!.list)
                    totalElements.postValue(response.body()!!.totalCount)
                }
                false -> handleErrorBody(response)
            }
        }
    }

    private fun <T> handleErrorBody(response: Response<T>) {
        isError.postValue(UtilsObject.handleErrorMessage(response))
    }

}