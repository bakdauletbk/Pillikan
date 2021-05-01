package kz.smartideagroup.pillikan.common.base_vmmv

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.custom_exception_dialog.view.*
import kotlinx.android.synthetic.main.custom_success_dialog.view.*
import kotlinx.android.synthetic.main.custom_success_dialog.view.dialog_success_close
import kotlinx.android.synthetic.main.custom_success_dialog.view.dialog_success_message
import kotlinx.android.synthetic.main.static_error_alert.view.*
import kotlinx.android.synthetic.main.static_success_alert.view.*
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.services.VibrateService
import kz.smartideagroup.pillikan.common.utils.MIN_LENGTH_FIVE
import kz.smartideagroup.pillikan.common.views.CustomProgressDialog
import kz.smartideagroup.pillikan.content.FoundationActivity
import org.jetbrains.anko.sdk27.coroutines.onClick


open class BaseFragment(val layout: Int, private val containerId: Int) : Fragment() {

    private val progressDialog = CustomProgressDialog()
    private var isDialogActive = false


    private var root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(layout, container, false)
        return root
    }

    fun navigateTo(navDirections: Int, fromMain: Int = containerId) {
        hideLoading()
        requireActivity().findNavController(containerId)
            .navigate(navDirections)
    }

    fun showLoading() {
        when (isDialogActive) {
            false -> {
                hideLoading()
                isDialogActive = true
                progressDialog.show(requireContext())
            }
        }
    }

    fun hideLoading() {
        when (isDialogActive) {
            true -> progressDialog.dialog.dismiss()
        }
    }

    fun showLongToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    fun showShortToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    fun showSuggest(message: String, actionButtonTitle: String? = null) {
        hideLoading()
        activateVibrate()
        val mDialogView = LayoutInflater.from(requireContext()).inflate(
            R.layout.static_success_alert,
            null
        )
        val mBuilder = AlertDialog.Builder(requireContext())
            .setView(mDialogView)
            .setCancelable(true)
        val mAlertDialog = mBuilder.show()
        mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialogView.dialog_success_message.text = message
        mDialogView.dialog_success_close.text = actionButtonTitle
        mDialogView.dialog_success_close.onClick {
            mAlertDialog.dismiss()
        }
    }

    @SuppressLint("SetTextI18n")
    fun showSuccess(message: String, actionButtonTitle: String? = null) {
        hideLoading()
        val mDialogView = LayoutInflater.from(requireContext()).inflate(
            R.layout.static_success_alert,
            null
        )
        val mBuilder = AlertDialog.Builder(requireContext())
            .setView(mDialogView)
            .setCancelable(true)
        val mAlertDialog = mBuilder.show()
        mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialogView.dialog_success_message.text = message
        mDialogView.dialog_success_close.text = actionButtonTitle
        mDialogView.dialog_success_close.onClick {
            mAlertDialog.dismiss()
        }
    }

    fun showException(message: String) {
        handleCrashAndReport(requireActivity().javaClass.toString(), message, 2)
        hideLoading()
        activateVibrate()
        val mDialogView = LayoutInflater.from(requireContext()).inflate(
            R.layout.static_error_alert,
            null
        )
        val mBuilder = AlertDialog.Builder(requireContext())
            .setView(mDialogView)
            .setCancelable(false)
        val mAlertDialog = mBuilder.show()

        val displayRectangle = Rect()
        val window: Window = requireActivity().window
        window.decorView.getWindowVisibleDisplayFrame(displayRectangle)

        mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mAlertDialog.window?.setLayout(
            (displayRectangle.width() * 0.9f).toInt(),
            mAlertDialog.window?.attributes!!.height,
        )
        mAlertDialog.window?.setGravity(Gravity.CENTER)

        mDialogView.dialog_error_message.text = message
        mDialogView.dialog_error_close.onClick {
            mAlertDialog.dismiss()
        }
        mDialogView.send_report_prepare.onClick {
            when (mDialogView.report_hint.isVisible) {
                true -> {
                    mDialogView.send_report_prepare.setImageDrawable(requireActivity().getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24))
                    mDialogView.report_hint.isVisible = false
                    mDialogView.extra_data.isVisible = false
                    mDialogView.length_count.isVisible = false
                    mDialogView.send_report_button.isVisible = false
                    mDialogView.image_error.isVisible = true
                    mDialogView.dialog_error_close.isVisible = true
                    mDialogView.dialog_error_message.isVisible = true
                    mDialogView.error_title.isVisible = true
                }
                false -> {
                    mDialogView.send_report_prepare.setImageDrawable(requireActivity().getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24))
                    mDialogView.report_hint.isVisible = true
                    mDialogView.extra_data.isVisible = true
                    mDialogView.send_report_button.isVisible = true
                    mDialogView.length_count.isVisible = true
                    mDialogView.image_error.isVisible = false
                    mDialogView.dialog_error_message.isVisible = false
                    mDialogView.dialog_error_close.isVisible = false
                    mDialogView.error_title.isVisible = false
                }
            }
        }

        mDialogView.extra_data.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mDialogView.length_count.text =  mDialogView.extra_data.text.length.toString() + "/255"
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        mDialogView.send_report_button.onClick {
            when (mDialogView.extra_data.text.length < MIN_LENGTH_FIVE) {
                true -> {
                    Toast.makeText(
                        requireContext(),
                        "Опишите проблему подробнее...",
                        Toast.LENGTH_LONG
                    ).show()
                }
                false -> {
                    val reportMessage: String =
                        requireActivity().javaClass.toString() + " Системное сообщение: " + message + " Сообщение от пользователя: " + mDialogView.extra_data.text.toString()
                    handleCrashAndReport("Сообщение от пользователя", reportMessage, 7)
                    mAlertDialog.dismiss()
                    showSuccess(
                        "Благодарим за обращение! Постараемся мигом разобраться в ситуации!",
                        "Хорошо"
                    )
                }
            }
        }
    }

    private fun activateVibrate() {
        val intentVibrate = Intent(
            activity?.applicationContext,
            VibrateService::class.java
        )
        activity?.startService(intentVibrate)
    }

    fun handleCrashAndReport(action: String, extraData: String = " ", level: Int = 1) {
        val activity = requireActivity() as FoundationActivity
        activity.regNewCrash(action, extraData, level)
    }


}