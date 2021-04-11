package kz.smartideagroup.pillikan.common.crash_report

import android.app.Application
import kz.smartideagroup.pillikan.common.remote.ApiConstants
import kz.smartideagroup.pillikan.common.base_vmmv.BaseRepository

class CrashRepository(application: Application) :
    BaseRepository(application, ApiConstants.PHP_BASE_URL) {

    suspend fun regNewCrash(crashBody: CrashBody) {
        networkService.regNewCrash(crashBody)
    }
}