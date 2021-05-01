package kz.smartideagroup.pillikan.content.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.bottom_app_bar_menu.*
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.base_interfaces.FragmentImpl
import kz.smartideagroup.pillikan.common.base_vmmv.BaseFragment
import kz.smartideagroup.pillikan.common.utils.*
import kz.smartideagroup.pillikan.common.views.viewBinding
import kz.smartideagroup.pillikan.content.home.bottom_sheet.NavigationBottomSheet
import kz.smartideagroup.pillikan.content.home.welcome.models.OptionItem
import kz.smartideagroup.pillikan.databinding.FragmentFoundationHomeBinding
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.lang.Exception

class HomeFoundationFragment : BaseFragment(R.layout.fragment_foundation_home, R.id.main_container),
    FragmentImpl {

    private lateinit var viewModel: HomeFoundationViewModel
    private val binding by viewBinding(FragmentFoundationHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    override fun lets() {
        try {
            setupViewModel()
            setupListeners()
            setupObservers()
            setupUserData()
        } catch (e: Exception) {
            handleCrashAndReport(this.javaClass.name, e.message.toString())
        }
    }

    private fun setupUserData() {
        viewModel.getUserName()
        viewModel.getUserBalance()
    }

    override fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(HomeFoundationViewModel::class.java)
    }

    override fun setupListeners() {
        binding.profileView.onClick {
            showOptionMenu(ApplicationPreferences.getProfileOptions(requireContext()))
        }
        binding.balanceView.onClick {
            showOptionMenu(ApplicationPreferences.getBalanceOptions(requireContext()))
        }

        binding.fabQr.onClick {
            softNavigation(R.id.qrScannerFragment)
        }
    }

    override fun setupObservers() {
        viewModel.userName.observe(viewLifecycleOwner, {
            binding.userName.text = it
        })

        viewModel.balance.observe(viewLifecycleOwner, {
            binding.userBalance.text = it.toString()
        })
    }

    private fun showOptionMenu(options: List<OptionItem>) {
        NavigationBottomSheet(this, options)
            .show(parentFragmentManager, this.javaClass.toString())
    }

    fun onOptionClick(optionId: Int) {
        val destinationList = ApplicationPreferences.getDestinationList()
        when (destinationList[optionId] == requireActivity()
            .findNavController(R.id.home_container).currentDestination?.id) {
            true -> return
            false -> softNavigation(destinationList[optionId])
        }
    }

    private fun softNavigation(fragmentId: Int?) {
        when (fragmentId == null) {
            false -> requireActivity().findNavController(R.id.home_container).navigate(fragmentId)
        }
    }


}