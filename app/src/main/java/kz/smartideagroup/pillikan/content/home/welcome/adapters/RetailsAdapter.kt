package kz.smartideagroup.pillikan.content.home.welcome.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.remote.ApiConstants
import kz.smartideagroup.pillikan.content.home.welcome.WelcomeFragment
import kz.smartideagroup.pillikan.content.home.welcome.models.RetailModel

class RetailsAdapter(private var callback: WelcomeFragment) :
    RecyclerView.Adapter<RetailsAdapter.RetailsHolder>() {

    private val retailItems: ArrayList<RetailModel> = ArrayList()

    fun addRetailList(retailItems: List<RetailModel>) {
        this.retailItems.clear()
        this.retailItems.addAll(retailItems)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetailsHolder {
        val root: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_new_retail, parent, false)
        return RetailsHolder(root)
    }

    override fun onBindViewHolder(holder: RetailsHolder, position: Int) {
        holder.bind(retailItems[position], callback)
    }

    override fun getItemCount(): Int {
        return retailItems.count()
    }

    class RetailsHolder(root: View) : RecyclerView.ViewHolder(root) {
        private val animPlaceholder: LottieAnimationView =
            root.findViewById(R.id.retail_item_animation_view)
        private val image: ImageView = root.findViewById(R.id.retail_image)
        private val title: TextView = root.findViewById(R.id.retail_title)
        private val desc: TextView = root.findViewById(R.id.retail_description)
        private val cashBack: TextView = root.findViewById(R.id.retail_cash_back_value)
        private val status: TextView = root.findViewById(R.id.retail_status)
        fun bind(
            retail: RetailModel,
            callback: WelcomeFragment
        ) {
            title.text = retail.name
            desc.text = retail.address
            cashBack.text = retail.cashBack.toString() + "%"
            when(retail.isWork == 1){
                true -> {
                    status.background = callback.requireContext().getDrawable(R.drawable.rounded_green_shape_6dp)
                    status.text = callback.requireContext().getString(R.string.retail_status_open)
                    status.setTextColor(callback.requireContext().resources.getColor(R.color.green))
                }
                false -> {
                    status.background = callback.requireContext().getDrawable(R.drawable.rounded_red_shape_6dp)
                    status.text = callback.requireContext().getString(R.string.retail_status_close)
                    status.setTextColor(callback.requireContext().resources.getColor(R.color.red))
                }
            }
            Glide
                .with(callback.requireContext())
                .load(ApiConstants.IMAGE_BASE_URL + "/retail/logo" + retail.logo)
                .addListener(imageLoadingListener(animPlaceholder))
                .centerCrop()
                .into(image)

        }

        private fun imageLoadingListener(pendingImage: LottieAnimationView): RequestListener<Drawable?> {
            return object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable?>,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable?>,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    CoroutineScope(Dispatchers.Main).launch {
                        pendingImage.pauseAnimation()
                        pendingImage.visibility = View.GONE
                    }
                    return false
                }
            }
        }


    }


}