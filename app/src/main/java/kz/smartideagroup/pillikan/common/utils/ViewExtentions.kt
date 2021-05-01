package kz.smartideagroup.pillikan.common.utils

import android.graphics.drawable.GradientDrawable
import android.view.View

fun View.setGradient(startColor: Int, endColor: Int){
    val res = this.context.resources
    val gd = GradientDrawable(
        GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(res.getColor(startColor), res.getColor(endColor))
    )
    gd.cornerRadius = 0f
    this.background = gd
}