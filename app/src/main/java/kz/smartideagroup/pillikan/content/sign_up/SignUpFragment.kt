package kz.smartideagroup.pillikan.content.sign_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.views.BaseFragment
import kz.smartideagroup.pillikan.common.views.viewBinding
import kz.smartideagroup.pillikan.databinding.FragmentSignUpBinding
import org.jetbrains.anko.sdk27.coroutines.onClick

class SignUpFragment: BaseFragment(R.layout.fragment_sign_in) {

    companion object {
        const val TAG = "SignUpFragment"
    }

    private var root: View? = null
    private val binding by viewBinding(FragmentSignUpBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_sign_up, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    private fun lets() {
        binding.authorizationButton.onClick {
            navigateTo(R.id.action_signUpFragment_to_signInFragment)
        }
    }

}