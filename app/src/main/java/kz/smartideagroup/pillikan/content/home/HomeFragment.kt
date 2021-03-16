package kz.smartideagroup.pillikan.content.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.views.BaseFragment

class HomeFragment: BaseFragment(R.layout.fragment_sign_in) {

    companion object {
        const val TAG = "HomeFragment"
    }

    private var root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }


}