package kz.smartideagroup.pillikan.content.splash

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.base_interfaces.FragmentImpl
import kz.smartideagroup.pillikan.common.utils.DELAY_THREE_SECOND
import kz.smartideagroup.pillikan.common.views.BaseFragment


class SplashFragment : BaseFragment(R.layout.fragment_splash), FragmentImpl {

    private lateinit var viewModel: SplashViewModel
    private var isFirstLaunch = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    override fun lets() {
        setupViewModel()
        setupListeners()
        setupObservers()
        initNetWorkChecker()
    }

    override fun setupViewModel() {
        viewModel = ViewModelProvider(this)
            .get(SplashViewModel::class.java)
    }

    override fun setupListeners() {}

    private fun initNetWorkChecker() {
        CoroutineScope(Dispatchers.IO).launch {
            checkNetworkConnection()
        }
    }

    override fun setupObservers() {
        viewModel.isNetworkConnected.observe(viewLifecycleOwner, {
            when(it){
                true -> getIsAuthorized()
                false -> showException(getString(R.string.error_unknown_body))
            }
        })
        viewModel.isAuthorized.observe(viewLifecycleOwner, {
            when(it){
                true -> navigateTo(R.id.action_splashFragment_to_homeFragment)
                false -> navigateTo(R.id.action_splashFragment_to_onBoardingFragment)
            }
        })
    }

    private fun getIsAuthorized() {
        viewModel.getIsAuthorized()
    }

    private suspend fun checkNetworkConnection() {
        when(isFirstLaunch){
            true -> firstLaunch()
        }
    }

    private suspend fun firstLaunch(){
        isFirstLaunch = false
        delay(DELAY_THREE_SECOND)
        getIsNetworkConnected()
    }

    private fun getIsNetworkConnected() {
        viewModel.getIsNetworkConnected(requireContext())
    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.IO).launch {
            checkNetworkConnection()
        }
    }


}