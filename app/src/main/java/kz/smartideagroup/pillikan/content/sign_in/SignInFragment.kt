package kz.smartideagroup.pillikan.content.sign_in

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.utils.validatePassword
import kz.smartideagroup.pillikan.common.utils.validatePhone
import kz.smartideagroup.pillikan.common.views.BaseFragment
import kz.smartideagroup.pillikan.common.views.viewBinding
import kz.smartideagroup.pillikan.content.sign_in.models.SignInRequest
import kz.smartideagroup.pillikan.databinding.FragmentSignInBinding
import org.jetbrains.anko.sdk27.coroutines.onClick

class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

    companion object {
        const val TAG = "SignInFragment"
    }

    private lateinit var viewModel: SignInViewModel
    private val binding by viewBinding(FragmentSignInBinding::bind)

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

        binding.restorePasswordButton.onClick {
            prepareSmsCode()
        }

        binding.authorizationConfirmButton.onClick {
            sendAuthData()
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
    }

    private fun sendAuthData() {
        val phone = binding.authorizationFragmentPhoneValue.text.toString()
        val password = binding.authorizationInputPasswordValue.text.toString()
        when (phone.validatePhone()) {
            false -> {
                binding.authorizationFragmentPhoneTil.error =
                    getString(R.string.error_wrong_phone_number)
                return@sendAuthData
            }
        }
        when (password.validatePassword()) {
            false -> {
                binding.authorizationInputPasswordLayout.error =
                    getString(R.string.error_wrong_password)
                return@sendAuthData
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.checkAuthorizationResult(
                SignInRequest(
                    formatNumber(phone),
                    password
                )
            )
        }

    }

    private fun prepareSmsCode() {
        val phone = binding.authorizationFragmentPhoneValue.text.toString()
        if (!phone.validatePhone()) {
            binding.authorizationFragmentPhoneTil.error =
                getString(R.string.error_wrong_phone_number)
            return
        }

        when(sendSmsCode()){
            true -> {
                showSuggest(
                    message = "На номер $phone отправлено СМС с временным паролем",
                    actionButtonTitle = getString(R.string.sms_was_sended_action)
                )
            }
            false -> {
                showException(
                    message = "Не удалось отправить сообщение, попробуйте пожалуйста попозже"
                )
            }
        }


    }

    private fun sendSmsCode(): Boolean{

        return false
    }


}