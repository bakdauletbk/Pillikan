package kz.smartideagroup.pillikan.content.sign_in

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.on_boarding_fragment.*
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.utils.validatePassword
import kz.smartideagroup.pillikan.common.utils.validatePhone
import kz.smartideagroup.pillikan.common.views.BaseFragment
import kz.smartideagroup.pillikan.common.views.viewBinding
import kz.smartideagroup.pillikan.content.sign_in.models.SignInRequest
import kz.smartideagroup.pillikan.databinding.FragmentSignInBinding
import kz.smartideagroup.pillikan.databinding.OnBoardingFragmentBinding
import org.jetbrains.anko.sdk27.coroutines.onClick

class SignInFragment : BaseFragment() {

    companion object {
        const val TAG = "SignInFragment"
    }

    private lateinit var viewModel: SignInViewModel
    private var root: View? = null
    private val binding by viewBinding(FragmentSignInBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_sign_in, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    private fun lets() {
        initViewModel()
        initListeners()
        initObservers()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
    }

    private fun initListeners() {
        binding.registrationButton.onClick {
            navigateTo(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.authorizationConfirmButton.onClick {
            val phone = binding.authorizationFragmentPhoneValue.text.toString()
            val password = binding.authorizationInputPasswordValue.text.toString()
            if (!phone.validatePhone()) {
                binding.authorizationFragmentPhoneTil.error =
                    getString(R.string.error_wrong_phone_number)
                return@onClick
            }
            if (!password.validatePassword()) {
                binding.authorizationInputPasswordLayout.error =
                    getString(R.string.error_wrong_password)
                return@onClick
            }
            showLoading()
            viewModel
                .checkAuthorizationResult(SignInRequest(formatNumber(phone), password))
        }

        binding.authorizationFragmentPhoneValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.authorizationFragmentPhoneTil.error = null
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.authorizationInputPasswordValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.authorizationInputPasswordLayout.error = null
            }

            override fun afterTextChanged(s: Editable?) {}

        })
    }

    private fun initObservers() {
        viewModel.isError.observe(viewLifecycleOwner,  {
            showException(it)
        })
        viewModel.isSuccess.observe(viewLifecycleOwner, {
            hideLoading()
            navigateTo(R.id.action_signInFragment_to_homeFragment)
        })
    }

}