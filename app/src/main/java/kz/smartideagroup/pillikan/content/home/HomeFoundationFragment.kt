package kz.smartideagroup.pillikan.content.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.base_interfaces.FragmentImpl
import kz.smartideagroup.pillikan.common.base_vmmv.BaseFragment
import kz.smartideagroup.pillikan.common.views.viewBinding
import kz.smartideagroup.pillikan.databinding.FragmentFoundationHomeBinding
import java.lang.Exception

class HomeFoundationFragment : BaseFragment(R.layout.fragment_foundation_home), FragmentImpl {

    private lateinit var viewModel: HomeFoundationViewModel
    private val binding by viewBinding(FragmentFoundationHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    override fun lets() {
        try {
            setupBottomAppBar()
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

    }

    override fun setupObservers() {
        viewModel.userName.observe(viewLifecycleOwner, {
            binding.tvUserName.text = it
        })

        viewModel.balance.observe(viewLifecycleOwner, {
            binding.tvUserBalance.text = it.toString()
        })
    }

    private fun setupBottomAppBar() {

    }


}