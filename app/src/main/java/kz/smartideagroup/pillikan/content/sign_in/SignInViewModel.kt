package kz.smartideagroup.pillikan.content.sign_in

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import kz.smartideagroup.pillikan.common.remote.exceptions_util.ExceptionModel
import kz.smartideagroup.pillikan.common.utils.RESPONSE_SUCCESS_CODE
import kz.smartideagroup.pillikan.content.sign_in.models.SignInRequest
import org.json.JSONObject
import java.lang.Exception
import java.util.logging.ErrorManager

class SignInViewModel(application: Application): AndroidViewModel(application) {

    companion object{
        const val TAG = "SignInViewModel"
    }

    private var repository: SignInRepository = SignInRepository(application)
    val isError: MutableLiveData<String?> = MutableLiveData()
    val isSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun checkAuthorizationResult(signInData: SignInRequest){
        isLoading.postValue(true)
        viewModelScope.launch {
            try {
                val response = repository.getAuthorizationResult(signInData)
                if (response.isSuccessful){
                        isSuccess.postValue(true)
                }else{
                    if (response.errorBody() != null) {
                        val gson = Gson()
                        val type = object : TypeToken<ExceptionModel>() {}.type
                        var errorResponse: ExceptionModel? = gson.fromJson(response.errorBody()!!.charStream(), type)
                        isError.postValue(errorResponse?.message())
                    }
                }
            }
            catch (e: Exception){
                isError.postValue(null)
            }
        }
    }

}