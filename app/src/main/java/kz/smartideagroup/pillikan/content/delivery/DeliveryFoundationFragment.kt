package kz.smartideagroup.pillikan.content.delivery

import android.os.Bundle
import android.view.View
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.base_interfaces.FragmentImpl
import kz.smartideagroup.pillikan.common.base_vmmv.BaseFragment
import kz.smartideagroup.pillikan.common.views.viewBinding
import kz.smartideagroup.pillikan.databinding.FragmentAddABonusBinding
import kz.smartideagroup.pillikan.databinding.FragmentDeliveryFoundationBinding
import org.jetbrains.anko.sdk27.coroutines.onClick

class DeliveryFoundationFragment: BaseFragment(R.layout.fragment_delivery_foundation, R.id.main_container), FragmentImpl {

    private val binding by viewBinding(FragmentDeliveryFoundationBinding::bind)

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

    }

    override fun setupObservers() {

    }
}