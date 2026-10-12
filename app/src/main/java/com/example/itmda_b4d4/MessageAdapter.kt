package com.example.itmda_b4d4

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessageAdapter(private val messages: ArrayList<Message>) :
    RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val row: LinearLayout = itemView.findViewById(R.id.messageRow)
        val bubble: LinearLayout = itemView.findViewById(R.id.bubble)
        val tvMessage: TextView = itemView.findViewById(R.id.txtMessage)
        val tvTime: TextView = itemView.findViewById(R.id.txtTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val msg = messages[position]
        holder.tvMessage.text = msg.text
        holder.tvTime.text = msg.getTimeString()

        // my messages on the right, other person on the left
        if (msg.sentByMe) {
            holder.row.gravity = Gravity.END
            holder.bubble.setBackgroundResource(R.drawable.bubble_sent)
        } else {
            holder.row.gravity = Gravity.START
            holder.bubble.setBackgroundResource(R.drawable.bubble_received)
        }
    }

    override fun getItemCount(): Int = messages.size
}
