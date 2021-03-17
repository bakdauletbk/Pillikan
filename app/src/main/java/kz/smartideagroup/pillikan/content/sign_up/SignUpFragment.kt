package kz.smartideagroup.pillikan.content.sign_up

import android.os.Bundle
import android.view.View
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.base_interfaces.FragmentImpl
import kz.smartideagroup.pillikan.common.views.BaseFragment
import kz.smartideagroup.pillikan.common.views.viewBinding
import kz.smartideagroup.pillikan.databinding.FragmentSignUpBinding
import org.jetbrains.anko.sdk27.coroutines.onClick

class SignUpFragment: BaseFragment(R.layout.fragment_sign_up), FragmentImpl {

    private val binding by viewBinding(FragmentSignUpBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    override fun lets() {
        setupViewModel()
        setupListeners()
        setupObservers()
    }

    override fun setupListeners() {
        binding.authorizationButton.onClick {
            navigateTo(R.id.action_signUpFragment_to_signInFragment)
        }
    }

    override fun setupViewModel() {}
    override fun setupObservers() {}
}