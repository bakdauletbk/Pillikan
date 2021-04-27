package kz.smartideagroup.pillikan.common.remote

import kz.smartideagroup.pillikan.common.crash_report.CrashBody
import kz.smartideagroup.pillikan.content.home.notifications.list.NotificationRequest
import kz.smartideagroup.pillikan.content.home.notifications.list.NotificationsListResponse
import kz.smartideagroup.pillikan.content.home.welcome.models.BannerListResponse
import kz.smartideagroup.pillikan.content.home.welcome.models.NewRetailListRequest
import kz.smartideagroup.pillikan.content.home.welcome.models.NewRetailListResponse
import kz.smartideagroup.pillikan.content.sign_in.models.SignInRequest
import kz.smartideagroup.pillikan.content.sign_in.models.SignInResponse
import kz.smartideagroup.pillikan.content.sign_up.models.SignUpRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface NetworkService {

    @POST(EndPoints.SIGN_IN)
    suspend fun signIn(
        @Header("appver") applicationVersion: String,
        @Header("Content-Type") contentType: String,
        @Header("clientId") clientId: String,
        @Body signInRequest: SignInRequest
    ): Response<SignInResponse>

    @POST(EndPoints.SIGN_IN_SMS)
    suspend fun signInWithSms(
        @Header("appver") applicationVersion: String,
        @Header("Content-Type") contentType: String,
        @Header("clientId") clientId: String,
        @Body signInRequest: SignInRequest
    ): Response<SignInResponse>

    @POST(EndPoints.SEND_SMS)
    suspend fun sendSMS(
        @Header("username") phone: String,
        @Header("appver") applicationVersion: String
    ): Response<Any?>

    @POST(EndPoints.REG_NEW_CRASH)
    suspend fun regNewCrash(
        @Body crashBody: CrashBody
    ): Response<Any?>

    @POST(EndPoints.SIGN_UP_SMS)
    suspend fun sendSMStoSignUp(
        @Header("username") phone: String,
        @Header("appver") applicationVersion: String
    ): Response<Any?>

    @POST(EndPoints.SIGN_UP)
    suspend fun signUp(
        @Header("Authorization") token: String,
        @Header("appver") applicationVersion: String,
        @Header("Content-Type") contentType: String,
        @Header("clientId") clientId: String,
        @Body signUpRequest: SignUpRequest
    ): Response<Any?>

    @GET(EndPoints.GET_SLIDER_ITEMS)
    suspend fun getSliderItems(
        @Query("type") type: Int,
        @Header("authorization") token: String,
        @Header("appver") applicationVersion: String,
        @Header("Content-Type") contentType: String,
    ): Response<BannerListResponse>

    @POST(EndPoints.NEW_RETAIL_LIST)
    suspend fun getNewRetailList(
        @Header("authorization") token: String?,
        @Header("appver") appVer: String?,
        @Header("Content-type") contentType: String?,
        @Body newRetailListRequest: NewRetailListRequest?
    ): Response<NewRetailListResponse>

    @POST(EndPoints.GET_SYSTEM_NOTIFICATION)
    suspend fun getSystemNotification(
        @Header("authorization") token: String?,
        @Header("appver") appVer: String?,
        @Body pageNumber: NotificationRequest
    ): Response<NotificationsListResponse>

    @POST(EndPoints.GET_PAYMENT_NOTIFICATION)
    suspend fun getPaymentNotification(
        @Header("authorization") token: String?,
        @Header("appver") appVer: String?,
        @Body pageNumber: NotificationRequest
    ): Response<NotificationsListResponse>
}