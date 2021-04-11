package kz.smartideagroup.pillikan.common.base_vmmv

import android.app.Application
import android.content.Context
import kz.smartideagroup.pillikan.BuildConfig
import kz.smartideagroup.pillikan.common.preferences.UserSession
import kz.smartideagroup.pillikan.common.remote.ApiConstants
import kz.smartideagroup.pillikan.common.remote.Networking
import kz.smartideagroup.pillikan.common.utils.CLIENT_ID
import kz.smartideagroup.pillikan.common.utils.CONTENT_TYPE_JSON

open class BaseRepository(application: Application, baseUrl: String = ApiConstants.JAVA_BASE_URL) {

    val applicationVersion = BuildConfig.VERSION_NAME
    val contentType = CONTENT_TYPE_JSON
    val clientId = CLIENT_ID

    val networkService =
        Networking.create(baseUrl)
    private var sharedPreferences =
        application.getSharedPreferences("userSession", Context.MODE_PRIVATE)
    var userSession: UserSession =
        UserSession(sharedPreferences)
}