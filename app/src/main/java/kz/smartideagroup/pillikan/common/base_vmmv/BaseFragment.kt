package kz.smartideagroup.pillikan.common.base_vmmv

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.custom_exception_dialog.view.*
import kotlinx.android.synthetic.main.custom_success_dialog.view.*
import kotlinx.android.synthetic.main.custom_suggest_dialog.view.dialog_suggest_close
import kotlinx.android.synthetic.main.custom_suggest_dialog.view.dialog_suggest_message
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.services.VibrateService
import kz.smartideagroup.pillikan.common.views.CustomProgressDialog
import kz.smartideagroup.pillikan.content.FoundationActivity
import org.jetbrains.anko.sdk27.coroutines.onClick


open class BaseFragment(val layout: Int, val containerId: Int) : Fragment() {

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

    fun navigateTo(navDirections: Int) {
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
            R.layout.custom_suggest_dialog,
            null
        )
        val mBuilder = AlertDialog.Builder(requireContext())
            .setView(mDialogView)
            .setCancelable(true)
        val mAlertDialog = mBuilder.show()
        mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialogView.dialog_suggest_message.text = message
        mDialogView.dialog_suggest_close.text = actionButtonTitle
        mDialogView.dialog_suggest_close.onClick {
            mAlertDialog.dismiss()
        }
    }

    @SuppressLint("SetTextI18n")
    fun showSuccess(message: String, actionButtonTitle: String? = null) {
        hideLoading()
        val mDialogView = LayoutInflater.from(requireContext()).inflate(
            R.layout.custom_success_dialog,
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
            R.layout.custom_exception_dialog,
            null
        )
        val mBuilder = AlertDialog.Builder(requireContext())
            .setView(mDialogView)
            .setCancelable(false)
        val mAlertDialog = mBuilder.show()
        mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialogView.dialog_exception_message.text = message
        mDialogView.dialog_exception_close.onClick {
            mAlertDialog.dismiss()
        }
    }

    private fun activateVibrate() {
        val intentVibrate = Intent(
            activity?.applicationContext,
            VibrateService::class.java
        )
        activity?.startService(intentVibrate)
    }

    fun handleCrashAndReport(action: String, extraData: String = " ", level: Int = 1){
        val activity = requireActivity() as FoundationActivity
        activity.regNewCrash(action, extraData, level)
    }


}