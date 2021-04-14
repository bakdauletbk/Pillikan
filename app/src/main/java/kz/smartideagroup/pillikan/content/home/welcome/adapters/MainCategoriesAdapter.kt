package kz.smartideagroup.pillikan.content.home.welcome.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.utils.setGradient
import kz.smartideagroup.pillikan.content.home.welcome.WelcomeFragment
import kz.smartideagroup.pillikan.content.home.welcome.models.MainCategories

class MainCategoriesAdapter(private var callback: WelcomeFragment) :
    RecyclerView.Adapter<MainCategoriesAdapter.CategoriesHolder>() {

    private val categoryItems: ArrayList<MainCategories> = ArrayList()

    fun addCategoryList(categoryItems: List<MainCategories>) {
        this.categoryItems.clear()
        this.categoryItems.addAll(categoryItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        val root: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoriesHolder(root)
    }

    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        holder.bind(categoryItems[position], callback)
    }

    override fun getItemCount(): Int {
        return categoryItems.count()
    }

    class CategoriesHolder(root: View) : RecyclerView.ViewHolder(root) {
        private val background: LinearLayout = root.findViewById(R.id.category_item_main_view)
        private val icon: ImageView = root.findViewById(R.id.category_item_icon)
        private val title: TextView = root.findViewById(R.id.category_item_title)
        private val newMarket: ImageView = root.findViewById(R.id.category_new_mark)
        fun bind(
            category: MainCategories,
            callback: WelcomeFragment
        ) {
            background.setGradient(category.startColor, category.endColor)
            icon.setImageDrawable(callback.requireContext().getDrawable(category.iconResource))
            title.text = category.title
            newMarket.isVisible = category.isNew
        }


    }


}