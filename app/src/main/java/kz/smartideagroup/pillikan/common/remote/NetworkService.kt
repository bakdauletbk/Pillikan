package kz.smartideagroup.pillikan.common.remote

import kz.smartideagroup.pillikan.content.sign_in.models.SignInRequest
import kz.smartideagroup.pillikan.content.sign_in.models.SignInResponse
import kz.smartideagroup.pillikan.content.sign_up.models.SignUpRequest
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


    @POST(EndPoints.SIGN_UP_SMS)
    suspend fun sendSMStoSignUp(
        @Header("username") phone: String,
        @Header("appver") applicationVersion: String
    ): Response<Any?>

    @POST(EndPoints.SIGN_UP)
    suspend fun signUp(
        @Header("appver") applicationVersion: String,
        @Header("Content-Type") contentType: String,
        @Header("clientId") clientId: String,
        @Body signUpRequest: SignUpRequest
    ): Response<Any?>



}