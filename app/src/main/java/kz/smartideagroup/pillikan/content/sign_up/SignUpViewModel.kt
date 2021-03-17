package kz.smartideagroup.pillikan.content.sign_up

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kz.smartideagroup.pillikan.common.utils.validateFullName
import kz.smartideagroup.pillikan.common.utils.validatePassword
import kz.smartideagroup.pillikan.common.utils.validatePhone
import kz.smartideagroup.pillikan.common.utils.validatePromo
import kz.smartideagroup.pillikan.content.sign_up.models.SignUpRequest

class SignUpViewModel(application: Application): AndroidViewModel(application) {

    val isError: MutableLiveData<String?> = MutableLiveData()
    val isSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    val isPhoneInvalid:  MutableLiveData<Boolean> = MutableLiveData()
    val isPasswordInvalid: MutableLiveData<Boolean> = MutableLiveData()
    val isFullNameInvalid: MutableLiveData<Boolean> = MutableLiveData()
    val isPromoInvalid: MutableLiveData<Boolean> = MutableLiveData()

    fun validateRegistrationData(signUpRequest: SignUpRequest){
        isPhoneInvalid.postValue(!signUpRequest.username.validatePhone())
        isPasswordInvalid.postValue(!signUpRequest.password.validatePassword())
        isFullNameInvalid.postValue(!signUpRequest.fullName.validateFullName())
        isPromoInvalid.postValue(!signUpRequest.promo.validatePromo())
    }


}