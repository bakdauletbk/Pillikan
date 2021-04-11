package kz.smartideagroup.pillikan.common.base_vmmv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.smartideagroup.pillikan.common.crash_report.CrashBody
import kz.smartideagroup.pillikan.common.crash_report.CrashReportViewModel

open class BaseActivity : AppCompatActivity() {

    private lateinit var crashViewModel : CrashReportViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crashViewModel = ViewModelProvider(this).get(CrashReportViewModel::class.java)
    }

    fun regNewCrash(action: String, extraData: String, bugLevel: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val crashBody = CrashBody(
                Action = action,
                ExtraData = extraData,
                DeviceInfo = "DeviceInfo",
                Level = "-"
            )
            crashViewModel.regNewViewModel(crashBody)
        }
    }

}