package kz.smartideagroup.pillikan.common.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImage(url: String?) {
    Glide
        .with(this.context)
        .load(url)
        .centerCrop()
        .into(this)
}