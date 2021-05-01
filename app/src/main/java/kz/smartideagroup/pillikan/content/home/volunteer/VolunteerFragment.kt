package kz.smartideagroup.pillikan.content.home.volunteer

import android.os.Bundle
import android.view.View
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.base_interfaces.FragmentImpl
import kz.smartideagroup.pillikan.common.base_vmmv.BaseFragment
import kz.smartideagroup.pillikan.common.views.viewBinding
import kz.smartideagroup.pillikan.databinding.FragmentVolunteerBinding
import org.jetbrains.anko.sdk27.coroutines.onClick

class VolunteerFragment: BaseFragment(R.layout.fragment_volunteer, R.id.home_container), FragmentImpl {

    private val binding by viewBinding(FragmentVolunteerBinding::bind)

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