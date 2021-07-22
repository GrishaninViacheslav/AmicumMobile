package com.e.amicummobile.adapters

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.e.amicummobile.R
import com.e.amicummobile.modelAmicum.Notification

class RvNotificationAdapter(private val notification: ArrayList<Notification>) : RecyclerView.Adapter<RvNotificationAdapter.NotificationHolder>() {

    class NotificationHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvFIOMedical: TextView? = null
        var viewNotificationStatus: View? = null
        var context: Context? = null

        init {
            context = itemView.context
            tvFIOMedical = itemView.findViewById(R.id.tvFIOMedical)
            viewNotificationStatus = itemView.findViewById(R.id.viewNotificationStatus)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.notification_item_fragment, parent, false)
        return NotificationHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotificationHolder, position: Int) {
        holder.tvFIOMedical?.text = notification[position].worker_full_name

        if (notification[position].flag == true) {
            holder.viewNotificationStatus?.setBackgroundResource(R.drawable.status_notification_color_true)
        } else if (notification[position].flag == false) {
            holder.viewNotificationStatus?.setBackgroundResource(R.drawable.status_notification_color_false)
        } else {
            holder.viewNotificationStatus?.setBackgroundResource(R.drawable.status_notification_color_null)
        }
    }

    override fun getItemCount(): Int {
        return notification.size
    }
}