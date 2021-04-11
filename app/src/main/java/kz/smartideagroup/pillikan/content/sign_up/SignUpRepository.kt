package kz.smartideagroup.pillikan.content.sign_up

import android.app.Application
import kz.smartideagroup.pillikan.common.base_vmmv.BaseRepository
import kz.smartideagroup.pillikan.common.utils.TOKEN_WORD
import kz.smartideagroup.pillikan.common.utils.UtilsObject
import kz.smartideagroup.pillikan.content.sign_up.models.SignUpRequest
import retrofit2.Response

class SignUpRepository(application: Application): BaseRepository(application) {

    suspend fun sendSmsToSignUp(phone: String): Response<Any?> {
        return networkService.sendSMStoSignUp(phone, applicationVersion)
    }

    suspend fun sendSignUpData(signUpRequest: SignUpRequest): Response<Any?>{
        val tempToken = UtilsObject.encodeToBase64(TOKEN_WORD)
        return networkService.signUp(tempToken, applicationVersion, contentType, clientId, signUpRequest)
    }
}