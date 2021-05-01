package kz.smartideagroup.pillikan.content.home.taxi

import android.os.Bundle
import android.view.View
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.base_interfaces.FragmentImpl
import kz.smartideagroup.pillikan.common.base_vmmv.BaseFragment
import kz.smartideagroup.pillikan.common.views.viewBinding
import kz.smartideagroup.pillikan.databinding.FragmentScannerBinding
import kz.smartideagroup.pillikan.databinding.FragmentTaxiBinding
import org.jetbrains.anko.sdk27.coroutines.onClick

class TaxiFragment: BaseFragment(R.layout.fragment_taxi, R.id.home_container), FragmentImpl {

    private val binding by viewBinding(FragmentTaxiBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    override fun lets() {
        setupViewModel()
        setupListeners()
        setupObservers()
    }

    override fun setupViewModel() {
    }

    override fun setupListeners() {
        binding.backButton.onClick {
            requireActivity().onBackPressed()
        }
    }

    override fun setupObservers() {
    }
}