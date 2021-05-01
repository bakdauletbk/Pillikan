package kz.smartideagroup.pillikan.content.sign_up

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.base_interfaces.FragmentImpl
import kz.smartideagroup.pillikan.common.utils.SHYMKENT_CITY_ID
import kz.smartideagroup.pillikan.common.utils.UtilsObject
import kz.smartideagroup.pillikan.common.base_vmmv.BaseFragment
import kz.smartideagroup.pillikan.common.views.viewBinding
import kz.smartideagroup.pillikan.content.sign_up.models.SignUpRequest
import kz.smartideagroup.pillikan.databinding.FragmentSignUpBinding
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.lang.Exception

class SignUpFragment : BaseFragment(R.layout.fragment_sign_up, R.id.main_container), FragmentImpl {

    private val binding by viewBinding(FragmentSignUpBinding::bind)
    private lateinit var viewModel: SignUpViewModel
    private var registrationFirstStep = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    override fun lets() {
        try {
            setupViewModel()
            setupListeners()
            setupObservers()
        } catch (e: Exception) {
            handleCrashAndReport(this.javaClass.name, e.message.toString())
        }
    }

    override fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
    }

    override fun setupListeners() {
        binding.registrationFragmentPhoneValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.registrationFragmentPhoneTil.error = null
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        binding.registrationInputPasswordValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.registrationInputPasswordLayout.error = null
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        binding.authorizationButton.onClick {
            activity?.onBackPressed()
        }
        binding.registrationInputCityLayout.onClick {
            showSuggest(
                message = getString(R.string.only_shymkent_text),
                actionButtonTitle = getString(R.string.welcome_ok_text)
            )
        }
        binding.registrationButton.onClick {
            when (registrationFirstStep) {
                true -> prepareToSendSms()
                false -> confirmRegistration()
            }
        }
    }

    override fun setupObservers() {
        viewModel.isSuccess.observe(viewLifecycleOwner, {
            showSuccess(
                message = getString(R.string.success_registration_text),
                actionButtonTitle = getString(R.string.welcome_ok_text)
            )
            requireActivity().onBackPressed()
        })

        viewModel.isLoading.observe(viewLifecycleOwner, {
            when (it) {
                true -> showLoading()
                false -> hideLoading()
            }
        })

        viewModel.isError.observe(viewLifecycleOwner, {
            handleCrashAndReport(this.javaClass.name, it.toString())
            when (it == null) {
                true -> showException(getString(R.string.unknown))
                false -> showException(it)
            }
        })

        viewModel.isPhoneInvalid.observe(viewLifecycleOwner, {
            when (it) {
                true -> {
                    binding.registrationFragmentPhoneTil.error =
                        getString(R.string.error_wrong_phone_number)
                }
            }
        })

        viewModel.isFullNameInvalid.observe(viewLifecycleOwner, {
            when (it) {
                true -> {
                    binding.registrationInputFioLayout.error =
                        getString(R.string.error_wrong_fio)
                }
            }
        })

        viewModel.isPasswordInvalid.observe(viewLifecycleOwner, {
            when (it) {
                true -> {
                    binding.registrationInputPasswordLayout.error =
                        getString(R.string.error_wrong_password)
                }
            }
        })

        viewModel.isSmsWasSend.observe(viewLifecycleOwner, {
            val phone = binding.registrationFragmentPhoneValue.text.toString()
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

    private fun prepareToSendSms() {
        CoroutineScope(Dispatchers.IO).launch {
            val phone = binding.registrationFragmentPhoneValue.text.toString()
            viewModel.getSmsCode(phone)
        }
    }

    private fun confirmOfSmsReceipt(phone: String) {
        binding.registrationInputCityLayout.isEnabled = false
        binding.registrationFragmentPhoneTil.isEnabled = false
        registrationFirstStep = false
        binding.registrationButton.text = getString(R.string.registration)
        binding.registrationInputPasswordLayout.isVisible = true
        binding.registrationInputFioLayout.isVisible = true
        binding.registrationInputPasswordLayout.isVisible = true
        showSuccess(
            message = getString(R.string.sms_was_sended) + phone,
            getString(R.string.sms_was_sended_action)
        )
    }

    private fun confirmRegistration() {
        val phone = UtilsObject.formatNumber(binding.registrationFragmentPhoneValue.text.toString())
        val password = binding.registrationInputPasswordValue.text.toString()
        val cityId = SHYMKENT_CITY_ID
        val promo = binding.registrationInputPromoValue.text.toString()
        val fullName = binding.registrationInputFioValue.text.toString()
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.validateRegistrationData(
                SignUpRequest(
                    username = phone,
                    password = password,
                    fullName = fullName,
                    cityId = cityId.toString(),
                    promo = promo
                )
            )
        }
    }


}