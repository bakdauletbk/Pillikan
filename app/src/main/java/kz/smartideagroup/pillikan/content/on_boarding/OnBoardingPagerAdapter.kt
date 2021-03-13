package kz.smartideagroup.pillikan.content.on_boarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kz.smartideagroup.pillikan.databinding.OnBoardingPageOneBinding
import kz.smartideagroup.pillikan.databinding.OnBoardingPageThreeBinding
import kz.smartideagroup.pillikan.databinding.OnBoardingPageTwoBinding

class OnBoardingPagerAdapter : RecyclerView.Adapter<OnBoardingPagerAdapter.OnBoardingVH>() {

    private val layouts = listOf(
        OnBoardingLayout.ON_BOARDING_PAGE_ONE,
        OnBoardingLayout.ON_BOARDING_PAGE_TWO,
        OnBoardingLayout.ON_BOARDING_PAGE_THREE
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            OnBoardingLayout.ON_BOARDING_PAGE_ONE.ordinal -> {
                OnBoardingVH(OnBoardingPageOneBinding.inflate(layoutInflater, parent, false))
            }

            OnBoardingLayout.ON_BOARDING_PAGE_TWO.ordinal -> {
                OnBoardingVH(OnBoardingPageTwoBinding.inflate(layoutInflater, parent, false))
            }

            else -> {
                OnBoardingVH(OnBoardingPageThreeBinding.inflate(layoutInflater, parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: OnBoardingVH, position: Int) {

    }

    override fun getItemViewType(position: Int): Int {
        return layouts[position].ordinal
    }

    override fun getItemCount(): Int = layouts.size

    class OnBoardingVH(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

    private enum class OnBoardingLayout {
        ON_BOARDING_PAGE_ONE,
        ON_BOARDING_PAGE_TWO,
        ON_BOARDING_PAGE_THREE
    }
}
