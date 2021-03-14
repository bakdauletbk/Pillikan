package kz.smartideagroup.pillikan.common.views

import android.app.Activity
import android.app.Dialog
import android.content.Context
import kz.smartideagroup.pillikan.R

class CustomProgressDialog {
    lateinit var dialog: CustomDialog

    fun show(context: Context): Dialog {
        return show(context, null)
    }

    fun show(context: Context, title: CharSequence?): Dialog {
        val inflater = (context as Activity).layoutInflater
        val view = inflater.inflate(R.layout.custom_loading_dialog, null)
        dialog = CustomDialog(context)
        dialog.setContentView(view)
        dialog.show()
        return dialog
    }

    class CustomDialog(context: Context) : Dialog(context, R.style.CustomDialogTheme) {
        init {
            window?.decorView?.rootView?.setBackgroundResource(R.color.white)
            window?.decorView?.setOnApplyWindowInsetsListener { _, insets ->
                insets.consumeSystemWindowInsets()
            }
        }
    }
}