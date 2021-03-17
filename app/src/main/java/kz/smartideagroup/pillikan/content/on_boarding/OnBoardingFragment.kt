package kz.smartideagroup.pillikan.content.on_boarding

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.on_boarding_fragment.*
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.base_interfaces.FragmentImpl
import kz.smartideagroup.pillikan.common.views.BaseFragment
import kz.smartideagroup.pillikan.common.views.viewBinding
import kz.smartideagroup.pillikan.databinding.OnBoardingFragmentBinding
import org.jetbrains.anko.sdk27.coroutines.onClick

class OnBoardingFragment: BaseFragment(R.layout.on_boarding_fragment), FragmentImpl {

    private val binding by viewBinding(OnBoardingFragmentBinding::bind)
    private var isFirstOnBackPressed = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    override fun lets() {
        setupViewPagerIndicators()
        setupListeners()
    }

    override fun setupObservers() {}
    override fun setupViewModel() {}

    private fun setupViewPagerIndicators() {
        binding.onBoardingFragmentViewPager.adapter = OnBoardingPagerAdapter()
        TabLayoutMediator(on_boarding_fragment_tab_layout,on_boarding_fragment_view_pager)
        { _, _ -> }.attach()
    }

    override fun setupListeners() {
        binding.onBoardingFragmentSkip.onClick {
            navigateTo(R.id.action_onBoardingFragment_to_signInFragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            when(isFirstOnBackPressed){
                true -> applicationFinishNotify()
                false -> requireActivity().finish()
            }
        }
    }

    private fun applicationFinishNotify(){
        showLongToast(getString(R.string.confirm_finish))
        isFirstOnBackPressed = false
    }

}