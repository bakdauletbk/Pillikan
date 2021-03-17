package kz.smartideagroup.pillikan.common.remote

import android.app.Application
import android.content.Context
import kz.smartideagroup.pillikan.BuildConfig
import kz.smartideagroup.pillikan.common.preferences.UserSession
import kz.smartideagroup.pillikan.common.utils.CLIENT_ID
import kz.smartideagroup.pillikan.common.utils.CONTENT_TYPE_JSON

open class BaseRepository(application: Application) {

    val applicationVersion = BuildConfig.VERSION_NAME
    val contentType = CONTENT_TYPE_JSON
    val clientId = CLIENT_ID

    val networkService =
        Networking.create(ApiConstants.APP_BASE_URL)
    private var sharedPreferences =
        application.getSharedPreferences("userSession", Context.MODE_PRIVATE)
    var userSession: UserSession =
        UserSession(sharedPreferences)

}