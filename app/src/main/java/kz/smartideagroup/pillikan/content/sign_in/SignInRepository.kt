package kz.smartideagroup.pillikan.content.sign_in

import android.app.Application
import kz.smartideagroup.pillikan.common.remote.BaseRepository
import kz.smartideagroup.pillikan.content.sign_in.models.SignInRequest
import kz.smartideagroup.pillikan.content.sign_in.models.SignInResponse
import retrofit2.Response

class SignInRepository(application: Application): BaseRepository(application) {

    suspend fun getAuthorizationResult(signInData: SignInRequest): Response<SignInResponse> {
        return networkService.signIn(applicationVersion, contentType, clientId, signInData)
    }

    suspend fun getAuthorizationResultWithSms(signInData: SignInRequest): Response<SignInResponse> {
        return networkService.signInWithSms(applicationVersion, contentType, clientId, signInData)
    }

    suspend fun getRestorePasswordSms(phone: String): Response<Any?> {
        return networkService.sendSMS(phone, applicationVersion)
    }

    suspend fun saveCurrentUserPreferences(currentUser: Response<SignInResponse>) {
        userSession.setIsAuthorize(true)
    }

}