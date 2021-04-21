package kz.smartideagroup.pillikan.content.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeFoundationViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = HomeFoundationRepository(application)
    val userName: MutableLiveData<String> = MutableLiveData()
    val balance: MutableLiveData<Int> = MutableLiveData()

    fun getUserName() {
        viewModelScope.launch {
            userName.postValue(repository.getUserName())
        }
    }

    fun getUserBalance() {
        viewModelScope.launch {
            balance.postValue(repository.getUserBalance())
        }
    }

}