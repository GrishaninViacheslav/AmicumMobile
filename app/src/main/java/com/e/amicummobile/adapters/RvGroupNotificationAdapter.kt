package com.e.amicummobile.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.amicummobile.R
import com.e.amicummobile.modelAmicum.Notification
import com.e.amicummobile.modelAmicum.NotificationList

class RvGroupNotificationAdapter(private val notificationList: ArrayList<NotificationList<Notification>>) :
    RecyclerView.Adapter<RvGroupNotificationAdapter.GroupNotificationHolder>() {

    class GroupNotificationHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var notificationTitle: TextView? = null
        var rvNotificationItem: RecyclerView? = null
        var context: Context? = null

        init {
            context = itemView.context
            notificationTitle = itemView.findViewById(R.id.notification_title)
            rvNotificationItem = itemView.findViewById(R.id.rvNotificationItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupNotificationHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.group_notification_list_item_fragment, parent, false)
        return GroupNotificationHolder(itemView)
    }

    override fun onBindViewHolder(holder: GroupNotificationHolder, position: Int) {
        holder.notificationTitle?.text = notificationList[position].title

        holder.rvNotificationItem?.layoutManager = LinearLayoutManager(holder.context)

        holder.rvNotificationItem?.adapter = RvNotificationAdapter(notificationList[position].notifications)
    }

    override fun getItemCount(): Int {
        return notificationList.size
    }
}