package kz.smartideagroup.pillikan.content.home.welcome

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.smartideagroup.pillikan.common.utils.UtilsObject
import kz.smartideagroup.pillikan.content.home.welcome.models.BannerModel
import retrofit2.Response

class WelcomeViewModel(application: Application): AndroidViewModel(application) {

    private var repository: WelcomeRepository = WelcomeRepository(application)
    val isError: MutableLiveData<String?> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val sliderItems: MutableLiveData<List<BannerModel>> = MutableLiveData()

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

    private fun <T> handleErrorBody(response: Response<T>) {
        isError.postValue(UtilsObject.handleErrorMessage(response))
    }
}