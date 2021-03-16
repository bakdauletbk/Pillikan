package kz.smartideagroup.pillikan.content.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.views.BaseFragment


class SplashFragment : BaseFragment(R.layout.fragment_sign_in) {

    companion object {
        const val TAG = "SplashFragment"
    }

    private lateinit var viewModel: SplashViewModel
    private var isFirstLaunch = true

    private var root: View? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_splash, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    private fun lets() {
        initViewModel()
        initNetWorkChecker()
        observer()
    }


    private fun initNetWorkChecker() {
        CoroutineScope(Dispatchers.IO).launch { checkNetwork() }
    }


    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
    }


    private fun observer() {
        viewModel.isNetworkConnection.observe(viewLifecycleOwner, Observer {
            if (it) {
                checkAuthorize()
            } else {
                showException(getString(R.string.error_unknown_body))
            }
        })
        viewModel.isAuthorize.observe(viewLifecycleOwner, Observer {
            showLoading()
            if (it) {
                navigateTo(R.id.action_splashFragment_to_homeFragment)
            } else {
                navigateTo(R.id.action_splashFragment_to_onBoardingFragment)
            }
            hideLoading()
        })
    }

    private fun checkAuthorize() {
        viewModel.checkAuthorize()
    }

    private suspend fun checkNetwork() {
        if (isFirstLaunch) {
            delay(2500L)
            isFirstLaunch = false
        }
        viewModel.checkNetwork(requireContext())
    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.IO).launch {
            checkNetwork()
        }
    }

}