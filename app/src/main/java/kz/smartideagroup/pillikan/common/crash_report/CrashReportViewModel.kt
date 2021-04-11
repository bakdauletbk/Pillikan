package kz.smartideagroup.pillikan.common.crash_report

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CrashReportViewModel(application: Application): AndroidViewModel(application) {

    private val repository: CrashRepository = CrashRepository(application)

    fun regNewViewModel(crashBody: CrashBody){
        viewModelScope.launch {
            repository.regNewCrash(crashBody)
        }
    }

}