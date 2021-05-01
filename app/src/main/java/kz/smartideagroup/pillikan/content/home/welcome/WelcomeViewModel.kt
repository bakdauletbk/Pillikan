package kz.smartideagroup.pillikan.content.home.welcome

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.smartideagroup.pillikan.common.utils.UtilsObject
import kz.smartideagroup.pillikan.content.home.welcome.models.BannerModel
import kz.smartideagroup.pillikan.content.home.welcome.models.RetailModel
import retrofit2.Response

class WelcomeViewModel(application: Application): AndroidViewModel(application) {

    private var repository: WelcomeRepository = WelcomeRepository(application)
    val isError: MutableLiveData<String?> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val sliderItems: MutableLiveData<List<BannerModel>> = MutableLiveData()
    val newRetailList: MutableLiveData<List<RetailModel>> = MutableLiveData()

    suspend fun getSliderItems() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val response = repository.getSliderItems()
            when (response.isSuccessful) {
                true -> sliderItems.postValue(response.body()!!.sliders)
                false -> handleErrorBody(response)
            }
        }
    }

    suspend fun getNewRetailList(pageNumber: Int, size: Int){
        viewModelScope.launch {
            val response = repository.getNewRetailList(pageNumber, size)
            when (response.isSuccessful) {
                true -> newRetailList.postValue(response.body()!!.retailList)
                false -> handleErrorBody(response)
            }
        }
    }

    private fun <T> handleErrorBody(response: Response<T>) {
        isError.postValue(UtilsObject.handleErrorMessage(response))
    }
}