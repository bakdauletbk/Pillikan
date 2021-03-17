package kz.smartideagroup.pillikan.content.sign_up

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kz.smartideagroup.pillikan.common.utils.*
import kz.smartideagroup.pillikan.content.sign_up.models.SignUpRequest
import retrofit2.Response

class SignUpViewModel(application: Application) : AndroidViewModel(application) {


    private var repository: SignUpRepository = SignUpRepository(application)

    val isError: MutableLiveData<String?> = MutableLiveData()
    val isSmsWasSend: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    val isPhoneInvalid: MutableLiveData<Boolean> = MutableLiveData()
    val isPasswordInvalid: MutableLiveData<Boolean> = MutableLiveData()
    val isFullNameInvalid: MutableLiveData<Boolean> = MutableLiveData()

    suspend fun validateRegistrationData(signUpRequest: SignUpRequest) {
        isFullNameInvalid.postValue(!signUpRequest.fullName.validateFullName())
        isPasswordInvalid.postValue(!signUpRequest.password.validatePassword())
        when (signUpRequest.fullName.validateFullName() && signUpRequest.password.validatePassword()) {
            true -> sendRegistrationData(signUpRequest)
        }
    }

    private suspend fun sendRegistrationData(signUpRequest: SignUpRequest){
        val requestBase64 = SignUpRequest(
            UtilsObject.encodeToBase64(signUpRequest.username),
            UtilsObject.encodeToBase64(signUpRequest.password),
            UtilsObject.encodeToBase64(signUpRequest.username),
            UtilsObject.encodeToBase64(signUpRequest.cityId.toString()),
            UtilsObject.encodeToBase64(signUpRequest.promo.toString())
        )
        isLoading.postValue(true)
        val response = repository.sendSignUpData(requestBase64)
        when (response.isSuccessful) {
            true -> isSuccess.postValue(true)
            false -> handleErrorBody(response)
        }

    }

    suspend fun getSmsCode(phone: String) {
        isLoading.postValue(true)
        when (phone.validatePhone()) {
            true -> {
                val response = repository.sendSmsToSignUp(UtilsObject.encodeToBase64(phone))
                when (response.isSuccessful) {
                    true -> isSmsWasSend.postValue(true)
                    false -> handleErrorBody(response)
                }
            }
            false -> isPhoneInvalid.postValue(!phone.validatePhone())
        }
    }

    private fun <T> handleErrorBody(response: Response<T>) {
        isError.postValue(UtilsObject.handleErrorMessage(response))
    }


}