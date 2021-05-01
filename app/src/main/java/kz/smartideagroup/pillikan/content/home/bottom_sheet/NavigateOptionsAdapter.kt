package kz.smartideagroup.pillikan.content.home.bottom_sheet

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.utils.ApplicationPreferences
import kz.smartideagroup.pillikan.common.utils.NUMBER_ZERO
import kz.smartideagroup.pillikan.content.home.welcome.models.OptionItem
import org.jetbrains.anko.custom.style
import org.jetbrains.anko.sdk27.coroutines.onClick


class NavigateOptionsAdapter(private var callback: NavigationBottomSheet) :
    RecyclerView.Adapter<NavigateOptionsAdapter.OptionHolder>() {

    private val optionItems: ArrayList<OptionItem> = ArrayList()

    fun addBannerList(optionItems: List<OptionItem>) {
        this.optionItems.clear()
        this.optionItems.addAll(optionItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionHolder {
        val root: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_option, parent, false)
        return OptionHolder(root)
    }

    override fun onBindViewHolder(holder: OptionHolder, position: Int) {
        holder.bind(optionItems[position], callback, position)
    }

    override fun getItemCount(): Int {
        return optionItems.count()
    }

    class OptionHolder(root: View) : RecyclerView.ViewHolder(root) {
        private val root = root
        private val title: TextView = root.findViewById(R.id.option_title)
        private val budge: ImageView = root.findViewById(R.id.budge_icon)
        fun bind(option: OptionItem, callback: NavigationBottomSheet, position: Int) {
            val currentVisibleOptionId = callback.requireActivity()
                .findNavController(R.id.home_container).currentDestination?.id
            val currentOptionDestinationId =
                ApplicationPreferences.getDestinationList()[option.actionId]
            when (currentVisibleOptionId == currentOptionDestinationId) {
                true -> title.setTypeface(null, Typeface.BOLD)
            }
            title.text = option.title
            budge.isVisible = (option.budgeCount > NUMBER_ZERO)
            root.onClick {
                callback.onOptionClick(option.actionId)
            }

        }

    }


}