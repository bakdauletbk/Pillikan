package kz.smartideagroup.pillikan.content.home

import android.app.Application
import kz.smartideagroup.pillikan.common.base_vmmv.BaseRepository

class HomeFoundationRepository(application: Application): BaseRepository(application) {

    fun getUserName(): String {
        return userSession.getUserName().toString()
    }

    fun getUserBalance(): Float {
        return userSession.getBalance()
    }

}