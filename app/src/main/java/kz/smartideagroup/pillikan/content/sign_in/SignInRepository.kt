package kz.smartideagroup.pillikan.content.sign_in

import android.app.Application
import kz.smartideagroup.pillikan.BuildConfig
import kz.smartideagroup.pillikan.common.remote.BaseRepository
import kz.smartideagroup.pillikan.common.utils.CLIENT_ID
import kz.smartideagroup.pillikan.common.utils.CONTENT_TYPE_JSON
import kz.smartideagroup.pillikan.content.sign_in.models.SignInRequest
import kz.smartideagroup.pillikan.content.sign_in.models.SignInResponse
import retrofit2.Response

class SignInRepository(application: Application): BaseRepository(application) {

    private val applicationVersion = BuildConfig.VERSION_NAME
    private val contentType = CONTENT_TYPE_JSON
    private val clientId = CLIENT_ID

    companion object{
        const val TAG = "SignInRepository"
    }

    suspend fun getAuthorizationResult(signInData: SignInRequest): Response<SignInResponse> {
        return networkService.signIn(applicationVersion, contentType, clientId, signInData)
    }

    suspend fun saveCurrentUserPreferences(){

    }



}