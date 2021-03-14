package kz.smartideagroup.pillikan.content.on_boarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.on_boarding_fragment.*
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.views.BaseFragment
import kz.smartideagroup.pillikan.common.views.viewBinding
import kz.smartideagroup.pillikan.databinding.OnBoardingFragmentBinding
import org.jetbrains.anko.sdk27.coroutines.onClick

class OnBoardingFragment: BaseFragment() {

    companion object {
        const val TAG = "OnBoardingFragment"
    }


    private var root: View? = null
    private val binding by viewBinding(OnBoardingFragmentBinding::bind)

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
        lets()
    }

    private fun lets() {
        setUpViewPagerWithIndicators()
        setupListeners()
    }

    private fun setUpViewPagerWithIndicators() {
        on_boarding_fragment_view_pager.adapter = OnBoardingPagerAdapter()

        TabLayoutMediator(
            on_boarding_fragment_tab_layout,
            on_boarding_fragment_view_pager
        ) { _, _ -> }.attach()
    }

    private fun setupListeners() {
        binding.onBoardingFragmentSkip.onClick {
            requireActivity().findNavController(R.id.main_container)
                .navigate(R.id.action_onBoardingFragment_to_signInFragment)
        }
    }




}