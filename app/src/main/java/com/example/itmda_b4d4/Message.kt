package com.example.itmda_b4d4

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Message(val text: String, val sentByMe: Boolean, val time: Long) {

    fun getTimeString(): String {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        return sdf.format(Date(time))
    }
}
