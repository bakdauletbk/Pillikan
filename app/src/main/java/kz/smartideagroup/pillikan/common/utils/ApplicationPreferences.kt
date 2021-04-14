package kz.smartideagroup.pillikan.common.utils

import android.content.Context
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.content.home.welcome.models.MainCategories

class ApplicationPreferences {
    companion object {
        fun getMainCategories(context: Context): List<MainCategories>{
            var categories: ArrayList<MainCategories> = ArrayList()

            categories.add(
                MainCategories(
                    id = CATEGORY_ID_QR,
                    title = context.getString(R.string.main_category_qr),
                    iconResource = R.drawable.ic_qr_code,
                    startColor = R.color.cashback_start_color,
                    endColor = R.color.cashback_end_color
                )
            )

            categories.add(
                MainCategories(
                    id = CATEGORY_ID_BUS,
                    title = context.getString(R.string.main_category_bus),
                    iconResource = R.drawable.ic_bus,
                    startColor = R.color.bus_start_color,
                    endColor = R.color.bus_end_color
                )
            )

            categories.add(
                MainCategories(
                    id = CATEGORY_ID_DELIVERY,
                    title = context.getString(R.string.main_category_delivery),
                    iconResource = R.drawable.ic_delivery,
                    startColor = R.color.delivery_start_color,
                    endColor = R.color.delivery_end_color
                )
            )

            categories.add(
                MainCategories(
                    id = CATEGORY_ID_TAXI,
                    title = context.getString(R.string.main_category_taxi),
                    iconResource = R.drawable.ic_taxi,
                    startColor = R.color.taxi_start_color,
                    endColor = R.color.taxi_end_color,
                    isNew = true
                )
            )

            return categories
        }

    }
}