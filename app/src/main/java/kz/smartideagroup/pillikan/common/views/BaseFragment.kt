package kz.smartideagroup.pillikan.common.views

import android.content.Context
import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {

    fun getApplicationContext(): Context? {
        return if (activity != null) {
            requireActivity().applicationContext
        } else {
            null
        }
    }
}