package kz.smartideagroup.pillikan.content.home.notifications.group

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.smartideagroup.pillikan.R
import org.jetbrains.anko.sdk27.coroutines.onClick

class NotificationGroupAdapter(private var callback: NotificationGroupFragment) :
    RecyclerView.Adapter<NotificationGroupAdapter.GroupHolder>() {

    private val groupItems: ArrayList<NotificationGroupModel> = ArrayList()

    fun addGroups(groupItems: List<NotificationGroupModel>) {
        this.groupItems.clear()
        this.groupItems.addAll(groupItems)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupHolder {
        val root: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_notifications_group, parent, false)
        return GroupHolder(root)
    }

    override fun onBindViewHolder(holder: GroupHolder, position: Int) {
        holder.bind(groupItems[position], callback)
    }

    override fun getItemCount(): Int {
        return groupItems.count()
    }

    class GroupHolder(root: View) : RecyclerView.ViewHolder(root) {
        private val root = root
        private val image: ImageView = root.findViewById(R.id.notification_image)
        private val title: TextView = root.findViewById(R.id.notification_title)
        private val desc: TextView = root.findViewById(R.id.notification_description)
        fun bind(
            group: NotificationGroupModel,
            callback: NotificationGroupFragment
        ) {
            image.setImageDrawable(callback.requireContext().getDrawable(group.imageResourceId))
            title.text = group.title
            desc.text = group.desc
            root.onClick {
                callback.onGroupClick(group.id)
            }
        }


    }


}