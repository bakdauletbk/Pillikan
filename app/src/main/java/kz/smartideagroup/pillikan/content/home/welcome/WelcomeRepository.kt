package kz.smartideagroup.pillikan.content.home.welcome

import android.app.Application
import kz.smartideagroup.pillikan.common.base_vmmv.BaseRepository
import kz.smartideagroup.pillikan.common.utils.BANNERS_DEFAULT_TYPE
import kz.smartideagroup.pillikan.content.home.welcome.models.BannerListResponse
import kz.smartideagroup.pillikan.content.home.welcome.models.NewRetailListRequest
import kz.smartideagroup.pillikan.content.home.welcome.models.NewRetailListResponse
import retrofit2.Response

class WelcomeRepository(application: Application) : BaseRepository(application) {

    suspend fun getSliderItems(): Response<BannerListResponse> {
        return networkService.getSliderItems(
            BANNERS_DEFAULT_TYPE,
            userSession.getAccessToken()!!,
            applicationVersion,
            contentType
        )
    }

    suspend fun getNewRetailList(pageNumber: Int, size: Int): Response<NewRetailListResponse> {
        val filterModel: NewRetailListRequest.RetailFilterModel =
            NewRetailListRequest.RetailFilterModel(userSession.getCityId())
        val newRetailListRequest = NewRetailListRequest(filterModel, pageNumber, size)
        return networkService.getNewRetailList(
            userSession.getAccessToken(),
            applicationVersion,
            contentType,
            newRetailListRequest
        )
    }

}