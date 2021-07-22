package com.e.amicummobile.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.amicummobile.R
import com.e.amicummobile.modelAmicum.Notification

class RvNotificationAdapter(private val notification: ArrayList<Notification>, private val notification_id: String) : RecyclerView.Adapter<RvNotificationAdapter.NotificationHolder>
    () {

    class NotificationHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvFirstField: TextView? = null
        var tvSecondField: TextView? = null
        var viewNotificationStatus: View? = null
        var viewNotificationLine: View? = null
        var context: Context? = null

        init {
            context = itemView.context
            tvFirstField = itemView.findViewById(R.id.tvFirstField)
            tvSecondField = itemView.findViewById(R.id.tvSecondField)
            viewNotificationStatus = itemView.findViewById(R.id.viewNotificationStatus)
            viewNotificationLine = itemView.findViewById(R.id.viewNotificationLine)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.notification_item_fragment, parent, false)
        return NotificationHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotificationHolder, position: Int) {
        if (notification[position].flag == true) {
            holder.viewNotificationStatus?.setBackgroundResource(R.drawable.status_notification_color_true)
        } else if (notification[position].flag == false) {
            holder.viewNotificationStatus?.setBackgroundResource(R.drawable.status_notification_color_false)
        } else {
            holder.viewNotificationStatus?.setBackgroundResource(R.drawable.status_notification_color_null)
        }

        when (notification_id) {
            "medicalExam" -> {
                holder.tvFirstField?.text = notification[position].worker_full_name + " | " + notification[position].worker_staff_number
                holder.tvSecondField?.text = notification[position].checkup_date_start + " - " + notification[position].checkup_date_start
            }
            "siz" -> {
                holder.tvFirstField?.text = notification[position].siz_title
                holder.tvSecondField?.text = notification[position].worker_full_name + " | " + notification[position].worker_staff_number
                holder.tvSecondField?.textSize = 14f
            }
        }

        if (position == notification.size - 1) {
            holder.viewNotificationLine?.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return notification.size
    }
}