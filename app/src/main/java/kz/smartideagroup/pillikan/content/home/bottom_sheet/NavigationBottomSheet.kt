package kz.smartideagroup.pillikan.content.home.bottom_sheet

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.bottomsheet_options_list.*
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.content.home.HomeFoundationFragment
import kz.smartideagroup.pillikan.content.home.welcome.models.OptionItem
import java.io.Serializable

class NavigationBottomSheet : BottomSheetDialogFragment() {

    val adapter: NavigateOptionsAdapter = NavigateOptionsAdapter(this)
    var optionsList: List<OptionItem> = listOf()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getOptionListFromBundle()
    }

    private fun getOptionListFromBundle() {
        val optionList = arguments?.getSerializable("options") as List<OptionItem>
        optionList.let {
            optionsList = it
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bottomsheet_options_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lets()
    }

    private fun lets() {
        setupRecyclerView()
        addOptionList()
    }

    private fun addOptionList() {
        adapter.addBannerList(optionsList)
    }

    private fun setupRecyclerView() {
        rvOptions.layoutManager = LinearLayoutManager(context)
        rvOptions.adapter = adapter
    }

    fun onOptionClick(actionId: Int) {
        dismiss()
    }


    companion object {

        @JvmStatic
        fun newInstance(options: List<OptionItem>?) = NavigationBottomSheet().apply {
            arguments = Bundle().apply {
                putSerializable("options", options as Serializable)
            }
        }
    }

}

