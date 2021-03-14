package kz.smartideagroup.pillikan.common.remote

import kz.smartideagroup.pillikan.content.sign_in.models.SignInRequest
import kz.smartideagroup.pillikan.content.sign_in.models.SignInResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface NetworkService {

    @POST(EndPoints.LOGIN)
    suspend fun signIn(
        @Header("appver") applicationVersion: String,
        @Header("Content-Type") contentType: String,
        @Header("clientId") clientId: String,
        @Body signInRequest: SignInRequest
    ): Response<SignInResponse>

}