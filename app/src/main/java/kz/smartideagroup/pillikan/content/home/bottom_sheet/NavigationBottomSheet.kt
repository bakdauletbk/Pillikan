package kz.smartideagroup.pillikan.content.home.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomsheet_options_list.*
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.content.home.HomeFoundationFragment
import kz.smartideagroup.pillikan.content.home.welcome.models.OptionItem

class NavigationBottomSheet(private val callback: HomeFoundationFragment, private val options: List<OptionItem>) : BottomSheetDialogFragment() {

    val adapter: NavigateOptionsAdapter = NavigateOptionsAdapter(this)

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
        adapter.addBannerList(options)
    }

    private fun setupRecyclerView() {
        rvOptions.layoutManager = LinearLayoutManager(context)
        rvOptions.adapter = adapter
    }

    fun onOptionClick(actionId: Int) {
        callback.onOptionClick(actionId)
        dismiss()
    }


}

