package kz.smartideagroup.pillikan.content.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.base_interfaces.FragmentImpl
import kz.smartideagroup.pillikan.common.base_vmmv.BaseFragment
import kz.smartideagroup.pillikan.common.views.viewBinding
import kz.smartideagroup.pillikan.content.home.bottom_sheet.NavigationBottomSheet
import kz.smartideagroup.pillikan.databinding.FragmentFoundationHomeBinding
import java.lang.Exception

class HomeFoundationFragment : BaseFragment(R.layout.fragment_foundation_home), FragmentImpl {

    private val binding by viewBinding(FragmentFoundationHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    override fun lets() {
        try {
            setupBottomAppBar()
            setupViewModel()
            setupListeners()
            setupObservers()
        } catch (e: Exception) {
            handleCrashAndReport(this.javaClass.name, e.message.toString())
        }
    }

    override fun setupViewModel() {
    }

    override fun setupListeners() {
        binding.bottomAppBar.setNavigationOnClickListener {
            val bottomSheetDialogFragment = NavigationBottomSheet.newInstance()
            bottomSheetDialogFragment.show(
                requireActivity().supportFragmentManager,
                "Bottom Sheet Dialog"
            )
        }
    }

    override fun setupObservers() {
    }

    private fun setupBottomAppBar() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.bottomAppBar)

    }


}