package kz.smartideagroup.pillikan.content.home.welcome.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import kz.smartideagroup.pillikan.R
import com.bumptech.glide.request.target.Target
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.smartideagroup.pillikan.common.remote.ApiConstants
import kz.smartideagroup.pillikan.common.utils.BANNERS_IMAGE_CONTAINER
import kz.smartideagroup.pillikan.content.home.welcome.WelcomeFragment
import kz.smartideagroup.pillikan.content.home.welcome.models.BannerModel


class BannersAdapter(private var callback: WelcomeFragment) :
    RecyclerView.Adapter<BannersAdapter.BannersHolder>() {

    private val bannerItems: ArrayList<BannerModel> = ArrayList()

    fun addBannerList(bannerItems: List<BannerModel>) {
        this.bannerItems.clear()
        this.bannerItems.addAll(bannerItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannersHolder {
        val root: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_retail_sliders, parent, false)
        return BannersHolder(root)
    }

    override fun onBindViewHolder(holder: BannersHolder, position: Int) {
        holder.bind(bannerItems[position], callback, position)
    }

    override fun getItemCount(): Int {
        return bannerItems.count()
    }

    class BannersHolder(root: View): RecyclerView.ViewHolder(root) {
        private val animPlaceholder: LottieAnimationView = root.findViewById(R.id.banner_item_animation_view)
        private val imageContainer: ImageView = root.findViewById(R.id.banner_item_image)
        fun bind(bannerModel: BannerModel, callback: WelcomeFragment, position: Int) {
            val url = ApiConstants.IMAGE_BASE_URL + BANNERS_IMAGE_CONTAINER + bannerModel.image
            Glide
                .with(callback.requireContext())
                .load(url)
                .addListener(imageLoadingListener(animPlaceholder))
                .centerCrop()
                .into(imageContainer)
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