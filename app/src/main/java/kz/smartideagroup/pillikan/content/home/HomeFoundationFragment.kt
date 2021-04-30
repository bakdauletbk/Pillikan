package kz.smartideagroup.pillikan.content.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.base_interfaces.FragmentImpl
import kz.smartideagroup.pillikan.common.base_vmmv.BaseFragment
import kz.smartideagroup.pillikan.common.utils.ApplicationPreferences
import kz.smartideagroup.pillikan.common.views.viewBinding
import kz.smartideagroup.pillikan.content.home.bottom_sheet.NavigationBottomSheet
import kz.smartideagroup.pillikan.content.home.welcome.models.OptionItem
import kz.smartideagroup.pillikan.databinding.FragmentFoundationHomeBinding
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.lang.Exception

class HomeFoundationFragment : BaseFragment(R.layout.fragment_foundation_home, R.id.main_container), FragmentImpl {

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
        NavigationBottomSheet.newInstance(options)
            .show(parentFragmentManager, this.javaClass.toString())
    }


}