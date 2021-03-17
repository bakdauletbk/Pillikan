package kz.smartideagroup.pillikan.content.sign_in

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.base_interfaces.FragmentImpl
import kz.smartideagroup.pillikan.common.views.BaseFragment
import kz.smartideagroup.pillikan.common.views.viewBinding
import kz.smartideagroup.pillikan.databinding.FragmentSignInBinding
import org.jetbrains.anko.sdk27.coroutines.onClick

class SignInFragment : BaseFragment(R.layout.fragment_sign_in), FragmentImpl {

    private lateinit var viewModel: SignInViewModel
    private val binding by viewBinding(FragmentSignInBinding::bind)

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
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
    }

    override fun setupListeners() {
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
        binding.registrationButton.onClick {
            navigateTo(R.id.action_signInFragment_to_signUpFragment)
        }
        binding.restorePasswordButton.onClick {
            validateSmsData()
        }
        binding.authorizationConfirmButton.onClick {
            validateAuthData()
        }
    }

    override fun setupObservers(){
        viewModel.isLoading.observe(viewLifecycleOwner, {
            when (it) {
                true -> showLoading()
                false -> hideLoading()
            }
        })
        viewModel.isError.observe(viewLifecycleOwner, {
            when (it == null) {
                true -> showException(getString(R.string.unknown))
                false -> showException(it)
            }
        })
        viewModel.isSuccess.observe(viewLifecycleOwner, {
            navigateTo(R.id.action_signInFragment_to_homeFragment)
        })

        viewModel.isPhoneInvalid.observe(viewLifecycleOwner, {
            when (it) {
                true -> {
                    binding.authorizationFragmentPhoneTil.error =
                        getString(R.string.error_wrong_phone_number)
                }
            }
        })

        viewModel.isPasswordInvalid.observe(viewLifecycleOwner, {
            when (it) {
                true -> {
                    binding.authorizationInputPasswordLayout.error =
                        getString(R.string.error_wrong_password)
                }
            }
        })

        viewModel.isSmsWasSend.observe(viewLifecycleOwner, {
            val phone = binding.authorizationFragmentPhoneValue.text.toString()
            when (it) {
                true -> {
                    confirmOfSmsReceipt(phone = phone)
                }
                false -> {
                    showException(
                        message = getString(R.string.error_sms_not_sended)
                    )
                }
            }
        })
    }

    private fun confirmOfSmsReceipt(phone: String) {
        showSuggest(
            message = getString(R.string.sms_was_sended) + phone,
            getString(R.string.sms_was_sended_action)
        )
    }

    private fun validateAuthData() {
        CoroutineScope(Dispatchers.IO).launch {
            val phone = binding.authorizationFragmentPhoneValue.text.toString()
            val password = binding.authorizationInputPasswordValue.text.toString()
            viewModel.validateAuthData(phone, password)
        }
    }

    private fun validateSmsData() {
        CoroutineScope(Dispatchers.IO).launch {
            val phone = binding.authorizationFragmentPhoneValue.text.toString()
            viewModel.validateSmsData(phone)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
        }
    }


}