package kz.smartideagroup.pillikan.common.remote

import android.app.Application
import android.content.Context
import kz.smartideagroup.pillikan.common.preferences.UserSession

open class BaseRepository(application: Application) {

    val networkService =
        Networking.create(ApiConstants.APP_BASE_URL)
    private var sharedPreferences =
        application.getSharedPreferences("userSession", Context.MODE_PRIVATE)
    var userSession: UserSession =
        UserSession(sharedPreferences)

}