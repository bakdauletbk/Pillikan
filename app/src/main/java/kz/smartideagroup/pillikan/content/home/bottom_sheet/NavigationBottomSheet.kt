package kz.smartideagroup.pillikan.content.home.bottom_sheet

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationView
import kz.smartideagroup.pillikan.R

open class NavigationBottomSheet : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(): NavigationBottomSheet {
            val args = Bundle()
            val bottomSheetFragment: NavigationBottomSheet = NavigationBottomSheet()
            bottomSheetFragment.arguments = args
            return bottomSheetFragment
        }
    }

    private val bottomSheetCallback: BottomSheetCallback = object : BottomSheetCallback() {
        override fun onStateChanged(view: View, i: Int) {
            if (i == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss()
            }
        }

        override fun onSlide(view: View, v: Float) {
            if (v > 0.5) {
            }
        }
    }

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val view = View.inflate(context, R.layout.bottom_app_bar_menu, null)
        dialog.setContentView(view)
        val navigationView: NavigationView = view.findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> Toast.makeText(context, "nav_home is Clicked", Toast.LENGTH_LONG)
                    .show()
                R.id.nav_cabinet -> Toast.makeText(
                    context,
                    "nav_cabinet is Clicked",
                    Toast.LENGTH_LONG
                )
                    .show()
                R.id.nav_bonuses -> Toast.makeText(
                    context,
                    "nav_bonuses is Clicked",
                    Toast.LENGTH_LONG
                )
                    .show()
                R.id.nav_faq -> Toast.makeText(context, "nav_faq is Clicked", Toast.LENGTH_LONG)
                    .show()
                R.id.nav_about_us -> Toast.makeText(
                    context,
                    "nav_about_us is Clicked",
                    Toast.LENGTH_LONG
                )
                    .show()
            }
            false
        }
        val close = view.findViewById<ImageView>(R.id.navigation_bottom_sheet_close)
        close.setOnClickListener(View.OnClickListener { dismiss() })
        val layoutParams = (view.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = layoutParams.behavior
        if (behavior is BottomSheetBehavior<*>) {
            behavior.setBottomSheetCallback(bottomSheetCallback)
        }
    }

}