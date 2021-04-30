package kz.smartideagroup.pillikan.content.home.notifications.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.utils.NUMBER_ZERO
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NotificationListAdapter(private var callback: NotificationListFragment) :
    RecyclerView.Adapter<NotificationListAdapter.NotificationHolder>() {

    private val notifications: ArrayList<NotificationModel> = ArrayList()
    private var imageId: Int = NUMBER_ZERO

    fun addNotifications(notifications: List<NotificationModel>, imageId: Int) {
        this.imageId = imageId
        this.notifications.addAll(notifications)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationHolder {
        val root: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent, false)
        return NotificationHolder(root)
    }

    override fun onBindViewHolder(holder: NotificationHolder, position: Int) {
        holder.bind(notifications[position], callback, imageId)
    }

    override fun getItemCount(): Int {
        return notifications.count()
    }

    class NotificationHolder(root: View) : RecyclerView.ViewHolder(root) {
        private val image: ImageView = root.findViewById(R.id.notification_list_image)
        private val title: TextView = root.findViewById(R.id.notification_list_title)
        private val desc: TextView = root.findViewById(R.id.notification_list_description)
        private val createdAt: TextView = root.findViewById(R.id.notification_created_at)
        fun bind(
            notification: NotificationModel,
            callback: NotificationListFragment,
            imageId: Int
        ) {
            image.setImageDrawable(callback.requireContext().getDrawable(imageId))
            title.text = notification.title
            desc.text = notification.description
            createdAt.text = getDate(notification.createdAt, callback)
        }

        private fun getDate(date: String, callback: NotificationListFragment): String? {
            val timeStamp = date.toLong() * 1000L
            val sdf = SimpleDateFormat("yyyy-MM-dd' 'HH:mm")
            val netDate = Date(timeStamp)
            return sdf.format(netDate)

        }

    }


}