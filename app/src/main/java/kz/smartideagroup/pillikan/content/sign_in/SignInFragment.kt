package kz.smartideagroup.pillikan.content.sign_in

import android.app.Activity
import android.content.*
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.base_interfaces.FragmentImpl
import kz.smartideagroup.pillikan.common.base_vmmv.BaseFragment
import kz.smartideagroup.pillikan.common.utils.SIGN_IN_TYPE_PASS
import kz.smartideagroup.pillikan.common.utils.SIGN_IN_TYPE_SMS
import kz.smartideagroup.pillikan.common.utils.SMS_CONSENT_REQUEST
import kz.smartideagroup.pillikan.common.utils.UtilsObject
import kz.smartideagroup.pillikan.common.views.viewBinding
import kz.smartideagroup.pillikan.databinding.FragmentSignInBinding
import org.jetbrains.anko.sdk27.coroutines.onClick

class SignInFragment : BaseFragment(R.layout.fragment_sign_in, R.id.main_container), FragmentImpl {

    private lateinit var viewModel: SignInViewModel
    private val binding by viewBinding(FragmentSignInBinding::bind)
    private var signInType = SIGN_IN_TYPE_PASS

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    override fun lets() {
        try {
            setupViewModel()
            setupListeners()
            setupObservers()
            setupUserPhoneNumber()
            setupSmsReceiver()
        } catch (e: Exception) {
            handleCrashAndReport(this.javaClass.name, e.message.toString())
        }
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
        binding.authTypeChangeButton.onClick {
            changeSignInType()
        }
        binding.authorizationConfirmButton.onClick {
            validateAuthData()
        }
    }

    override fun setupObservers() {
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

        viewModel.isSuccess.observe(viewLifecycleOwner, {
            navigateTo(R.id.action_signInFragment_to_homeFragment)
        })

        viewModel.isPhoneInvalid.observe(viewLifecycleOwner, {
            when (it) {
                true -> {
                    changeSignInTypeToPass()
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
                    clearPreviousPassword()
                    confirmOfSmsReceipt(phone = phone)
                    waitAndAutoFillSmsCode()
                }
                false -> {
                    showException(
                        message = getString(R.string.error_sms_not_sended)
                    )
                }
            }
        })
    }

    private fun setupUserPhoneNumber() {
        val currentUserPhoneNumber: String? =
            UtilsObject.getCurrentUserPhoneNumber(requireContext())
        when (!currentUserPhoneNumber.isNullOrEmpty()) {
            true -> {
                binding.authorizationFragmentPhoneValue.setText(currentUserPhoneNumber)
            }
        }
    }

    private fun setupSmsReceiver() {
        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
        requireActivity().registerReceiver(smsVerificationReceiver, intentFilter)
    }


    private fun clearPreviousPassword() {
        binding.authorizationInputPasswordLayout.error = null
        binding.authorizationInputPasswordValue.text.clear()
    }

    private fun waitAndAutoFillSmsCode() {
        SmsRetriever.getClient(requireActivity()).startSmsUserConsent(null)
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
            viewModel.validateAuthData(phone, password, signInType)
        }
    }

    private fun changeSignInType() {
        when (signInType) {
            SIGN_IN_TYPE_PASS -> {
                changeSignInTypeToSms()
            }
            SIGN_IN_TYPE_SMS -> {
                changeSignInTypeToPass()
            }
        }
    }

    private fun changeSignInTypeToPass() {
        signInType = SIGN_IN_TYPE_PASS
        binding.authTypeChangeButton.setText(getString(R.string.sign_in_type_sms))
    }

    private fun changeSignInTypeToSms() {
        signInType = SIGN_IN_TYPE_SMS
        binding.authTypeChangeButton.setText(getString(R.string.sign_in_type_password))
        validateSmsData()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            SMS_CONSENT_REQUEST -> {
                when (resultCode == Activity.RESULT_OK) {
                    true -> {
                        val message = data!!.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE)
                        binding.authorizationInputPasswordValue.setText(
                            message!!.split(":".toRegex()).toTypedArray()[1].trim { it <= ' ' })
                        validateAuthData()
                    }
                }
            }
        }
    }

    private val smsVerificationReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (SmsRetriever.SMS_RETRIEVED_ACTION == intent.action) {
                val extras = intent.extras
                val smsRetrieverStatus = extras!![SmsRetriever.EXTRA_STATUS] as Status?
                when (smsRetrieverStatus!!.statusCode) {
                    CommonStatusCodes.SUCCESS -> {
                        val consentIntent =
                            extras.getParcelable<Intent>(SmsRetriever.EXTRA_CONSENT_INTENT)
                        try {
                            startActivityForResult(
                                consentIntent,
                                SMS_CONSENT_REQUEST
                            )
                        } catch (e: ActivityNotFoundException) {
                        }
                    }
                    CommonStatusCodes.TIMEOUT -> {
                    }
                }
            }
        }
    }


}