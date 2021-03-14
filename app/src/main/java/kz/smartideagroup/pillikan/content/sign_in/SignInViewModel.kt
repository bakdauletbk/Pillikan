package kz.smartideagroup.pillikan.content.sign_in

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.smartideagroup.pillikan.common.utils.RESPONSE_SUCCESS_CODE
import kz.smartideagroup.pillikan.content.sign_in.models.SignInRequest
import java.lang.Exception

class SignInViewModel(application: Application): AndroidViewModel(application) {

    companion object{
        const val TAG = "SignInViewModel"
    }

    private var repository: SignInRepository = SignInRepository(application)
    val isError: MutableLiveData<String> = MutableLiveData()
    val isSuccess: MutableLiveData<Boolean> = MutableLiveData()

    fun checkAuthorizationResult(signInData: SignInRequest){
        viewModelScope.launch {
            try {
                val response = repository.getAuthorizationResult(signInData)
                if (response.isSuccessful){
                    if (response.code() == RESPONSE_SUCCESS_CODE){
                        isSuccess.postValue(true)
                    }else{
                        isError.postValue(response.body().toString())
                    }
                }else{
                    isError.postValue(response.message().toString())
                }
            }catch (e: Exception){
                isError.postValue(e.message.toString())
            }
        }
    }

}