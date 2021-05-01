package kz.smartideagroup.pillikan.content.home.retails.details

import android.os.Bundle
import android.view.View
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.base_interfaces.FragmentImpl
import kz.smartideagroup.pillikan.common.base_vmmv.BaseFragment
import kz.smartideagroup.pillikan.common.views.viewBinding
import kz.smartideagroup.pillikan.databinding.FragmentRetailDetailsBinding
import org.jetbrains.anko.sdk27.coroutines.onClick

class RetailDetailsFragment: BaseFragment(R.layout.fragment_retail_details, R.id.home_container), FragmentImpl {

    private val binding by viewBinding(FragmentRetailDetailsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    override fun lets() {
        setupViewModel()
        setupObservers()
        setupListeners()
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