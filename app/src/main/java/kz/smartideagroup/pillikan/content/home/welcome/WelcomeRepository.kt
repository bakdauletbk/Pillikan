package kz.smartideagroup.pillikan.content.home.welcome

import android.app.Application
import kz.smartideagroup.pillikan.common.base_vmmv.BaseRepository
import kz.smartideagroup.pillikan.common.utils.BANNERS_DEFAULT_TYPE
import kz.smartideagroup.pillikan.content.home.welcome.models.BannerListResponse
import retrofit2.Response

class WelcomeRepository(application: Application): BaseRepository(application) {

    suspend fun getSliderItems(): Response<BannerListResponse>{
        return networkService.getSliderItems(
            BANNERS_DEFAULT_TYPE,
            userSession.getAccessToken()!!,
            applicationVersion,
            contentType
        )
    }

}