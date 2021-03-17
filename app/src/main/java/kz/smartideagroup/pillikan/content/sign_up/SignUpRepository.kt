package kz.smartideagroup.pillikan.content.sign_up

import android.app.Application
import kz.smartideagroup.pillikan.common.remote.BaseRepository
import kz.smartideagroup.pillikan.content.sign_up.models.SignUpRequest
import retrofit2.Response

class SignUpRepository(application: Application): BaseRepository(application) {

    suspend fun sendSignUpData(signUpRequest: SignUpRequest): Response<Any?>{
        return networkService.signUp(applicationVersion, contentType, clientId, signUpRequest)
    }
}