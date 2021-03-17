package kz.smartideagroup.pillikan.common.views

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.custom_exception_dialog.view.*
import kotlinx.android.synthetic.main.custom_suggest_dialog.view.*
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.services.VibrateService
import org.jetbrains.anko.sdk27.coroutines.onClick


open class BaseFragment(val layout: Int) : Fragment() {

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
        requireActivity().findNavController(R.id.main_container)
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
        mDialogView.dialog_suggest_message.text = message
        mDialogView.dialog_suggest_close.text = actionButtonTitle
        mDialogView.dialog_suggest_close.onClick {
            mAlertDialog.dismiss()
        }
    }

    fun showException(message: String) {
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


}