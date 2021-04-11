package kz.smartideagroup.pillikan.content.splash

import android.app.Application
import android.content.Context
import kz.smartideagroup.pillikan.common.helpers.NetworkHelper
import kz.smartideagroup.pillikan.common.base_vmmv.BaseRepository

class SplashRepository(application: Application) : BaseRepository(application) {

    fun getIsNetworkConnected(context: Context): Boolean {
        return NetworkHelper.isNetworkConnected(context)
    }

    fun getIsAuthorized(): Boolean {
        return userSession.getIsAuthorize()
    }

}