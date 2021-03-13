package kz.smartideagroup.pillikan.content.on_boarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.on_boarding_fragment.*
import kz.smartideagroup.pillikan.R

class OnBoardingFragment: Fragment() {

    companion object {
        const val TAG = "SignInFragment"
    }


    private var root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.on_boarding_fragment, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPagerWithIndicators()
    }

    private fun setUpViewPagerWithIndicators() {
        on_boarding_fragment_view_pager.adapter = OnBoardingPagerAdapter()

        TabLayoutMediator(
            on_boarding_fragment_tab_layout,
            on_boarding_fragment_view_pager
        ) { _, _ -> }.attach()
    }

}