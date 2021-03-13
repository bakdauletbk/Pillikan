package kz.smartideagroup.pillikan.content.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.views.BaseFragment
import org.jetbrains.anko.support.v4.alert

class SplashFragment: BaseFragment() {

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
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        CoroutineScope(Dispatchers.IO).launch { checkNetwork() }
        observer()
    }


    private fun observer() {
        viewModel.isNetworkConnection.observe(viewLifecycleOwner, Observer {
            if (it) {
                checkAuthorize()
            } else {
                showNetworkDialogue()
            }
        })
        viewModel.isAuthorize.observe(viewLifecycleOwner, Observer {
            if (it) {
                requireActivity().findNavController(R.id.main_container).navigate(R.id.action_splashFragment_to_homeFragment)
            } else {
                requireActivity().findNavController(R.id.main_container).navigate(R.id.action_splashFragment_to_onBoardingFragment)
            }
        })
    }

    private fun checkAuthorize() {
        viewModel.checkAuthorize()
    }

    private fun showNetworkDialogue() {
        alert {

            isCancelable = false
            title = getString(R.string.error_no_internet_title)
            message = getString(R.string.error_no_internet_msg)
            negativeButton(getString(R.string.dialog_exit)) {
                requireActivity().finish()
            }
            positiveButton(getString(R.string.dialog_retry)) {
                it.dismiss()
                onResume()
            }
        }.show()
    }

    private suspend fun checkNetwork() {
        if (isFirstLaunch) {
            delay(4000L)
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