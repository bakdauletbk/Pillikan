package kz.smartideagroup.pillikan.content.sign_in

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.smartideagroup.pillikan.common.utils.*
import kz.smartideagroup.pillikan.content.sign_in.models.SignInRequest
import kz.smartideagroup.pillikan.content.sign_in.models.SignInResponse
import retrofit2.Response

class SignInViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: SignInRepository = SignInRepository(application)
    val isError: MutableLiveData<String?> = MutableLiveData()
    val isSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isPhoneInvalid:  MutableLiveData<Boolean> = MutableLiveData()
    val isPasswordInvalid: MutableLiveData<Boolean> = MutableLiveData()
    val isSmsWasSend: MutableLiveData<Boolean> = MutableLiveData()

    suspend fun validateSmsData(phone: String){
        isPhoneInvalid.postValue(!phone.validatePhone())
        when(phone.validatePhone()){
            true -> sendRestorePasswordSmsCode(UtilsObject.formatNumber(phone))
            false -> isPhoneInvalid.postValue(!phone.validatePhone())
        }
    }

    fun validateAuthData(phone: String, password: String, signInType: Int){
        isPhoneInvalid.postValue(!phone.validatePhone())
        isPasswordInvalid.postValue(!password.validatePassword())
        when(phone.validatePhone() && password.validatePassword()){
            true -> selectSignInType(phone, password, signInType)
        }
    }

    private fun selectSignInType(phone: String, password: String, signInType: Int){
        when(signInType) {
            SIGN_IN_TYPE_PASS -> checkAuthorizationResult(phone, password)
            SIGN_IN_TYPE_SMS -> checkAuthorizationResultWithSms(phone, password)
        }
    }

    private fun checkAuthorizationResult(phone: String, password: String) {
        isLoading.postValue(true)
        viewModelScope.launch {
            val requestBody = SignInRequest(UtilsObject.formatNumber(phone), password)
            val response = repository.getAuthorizationResult(requestBody)
            when (response.isSuccessful) {
                true -> saveCurrentUserPreferences(response)
                false -> handleErrorBody(response)
            }
        }
    }

    private fun checkAuthorizationResultWithSms(phone: String, password: String) {
        isLoading.postValue(true)
        viewModelScope.launch {
            val requestBody = SignInRequest(UtilsObject.formatNumber(phone), password)
            val response = repository.getAuthorizationResultWithSms(requestBody)
            when (response.isSuccessful) {
                true -> saveCurrentUserPreferences(response)
                false -> handleErrorBody(response)
            }
        }
    }

    private suspend fun sendRestorePasswordSmsCode(phone: String) {
        isLoading.postValue(true)
        viewModelScope.launch {
            val response = repository.getRestorePasswordSms(UtilsObject.encodeToBase64(phone))
            when (response.isSuccessful) {
                true -> isSmsWasSend.postValue(response.isSuccessful)
                false -> handleErrorBody(response)
            }
        }
    }

    private suspend fun saveCurrentUserPreferences(response: Response<SignInResponse>) {
        isSuccess.postValue(true)
        repository.saveCurrentUserPreferences(response)
    }

    private fun <T> handleErrorBody(response: Response<T>) {
        isError.postValue(UtilsObject.handleErrorMessage(response))
    }
}