package kz.smartideagroup.pillikan.common.views

import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.custom_exception_dialog.view.*
import kz.smartideagroup.pillikan.R
import org.jetbrains.anko.sdk27.coroutines.onClick

open class BaseFragment: Fragment() {

    private val progressDialog = CustomProgressDialog()

    fun navigateTo(navDirections: Int){
        requireActivity().findNavController(R.id.main_container)
            .navigate(navDirections)
    }

    fun showLoading(){
        progressDialog.show(requireContext())
    }

    fun hideLoading(){
        progressDialog.dialog.dismiss()
    }

    fun showException(message: String){
        hideLoading()
        val mDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.custom_exception_dialog, null)
        val mBuilder = AlertDialog.Builder(requireContext())
            .setView(mDialogView)
            .setCancelable(false)
        val mAlertDialog = mBuilder.show()
        mDialogView.dialog_exception_message.text = message
        mDialogView.dialog_exception_close.onClick {
            mAlertDialog.dismiss()
        }
    }

    fun formatNumber(phone: String): String {
        var num: String = ""
        var c: Char
        for (i in phone.indices) {
            c = phone.get(i)
            if (c == '(' || c == ')' || c == ' ' || c == '+' || c == '-') continue
            num += c
        }
        return num
    }




}