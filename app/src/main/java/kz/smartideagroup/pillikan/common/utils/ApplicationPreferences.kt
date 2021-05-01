package kz.smartideagroup.pillikan.common.utils

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.content.home.notifications.group.NotificationGroupModel
import kz.smartideagroup.pillikan.content.home.welcome.models.MainCategories
import kz.smartideagroup.pillikan.content.home.welcome.models.OptionItem

class ApplicationPreferences {
    companion object {
        fun getMainCategories(context: Context): List<MainCategories> {
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

        fun getDestinationList(): HashMap<Int,Int>{
            val list: HashMap<Int,Int> = HashMap()
            list[MENU_HOME] = R.id.welcomeFragment
            list[MENU_PROFILE] = R.id.profileFragment
            list[MENU_BONUSES] = R.id.volunteerFragment
            list[MENU_FAQ] = R.id.faqFragment
            list[MENU_ABOUT_US] = R.id.aboutUsFragment
            list[MENU_CARDS] = R.id.myCardsFragment
            list[MENU_ADD_BALANCE] = R.id.addABonusFragment
            list[MENU_PAYMENTS_HISTORY] = R.id.paymentHistoryFragment
            return list
        }

        fun getProfileOptions(context: Context): List<OptionItem> {
            val res = context.resources
            val home = OptionItem(MENU_HOME, res.getString(R.string.item_main), null, R.color.black)
            val accountOption =
                OptionItem(MENU_PROFILE, res.getString(R.string.item_account), null, R.color.black)
            val volunteerOption = OptionItem(
                MENU_BONUSES,
                res.getString(R.string.item_volunteer),
                null,
                R.color.black
            )
            val pillikanGuideOption =
                OptionItem(MENU_FAQ, res.getString(R.string.pillikan_guide), null, R.color.black)
            val aboutUsOption =
                OptionItem(MENU_ABOUT_US, res.getString(R.string.item_about), null, R.color.black)
            return listOf(home, accountOption, volunteerOption, pillikanGuideOption, aboutUsOption)
        }

        fun getBalanceOptions(context: Context): List<OptionItem> {
            val res = context.resources
            val cardsOption =
                OptionItem(MENU_CARDS, res.getString(R.string.my_cards), null, R.color.black)
            val replenishOption = OptionItem(
                MENU_ADD_BALANCE,
                res.getString(R.string.replenish_pilican_balance),
                null,
                R.color.black
            )
            val historyOption = OptionItem(
                MENU_PAYMENTS_HISTORY,
                res.getString(R.string.history_balance),
                null,
                R.color.black
            )
            return listOf(cardsOption, replenishOption, historyOption)
        }

        fun getNotificationsGroup(context: Context): List<NotificationGroupModel> {
            val res = context.resources
            val systemNotifications = NotificationGroupModel(
                NOTIFICATION_TYPE_SYSTEM,
                R.drawable.ic_system_notification, context.getString(
                    R.string.notification_type_system
                ), context.getString(R.string.system_type_desc)
            )
            val paymentNotifications = NotificationGroupModel(
                NOTIFICATION_TYPE_PAYMENT,
                R.drawable.ic_payment_notification, context.getString(
                    R.string.notification_type_payment
                ), context.getString(R.string.payment_type_desc)
            )
            return listOf(systemNotifications, paymentNotifications)
        }

        fun getReversRecyclerView(context: Context): LinearLayoutManager{
            val manager = LinearLayoutManager(context)
            manager.stackFromEnd = true
            manager.reverseLayout = true
            manager.isSmoothScrollbarEnabled = true
            return manager
        }


    }
}