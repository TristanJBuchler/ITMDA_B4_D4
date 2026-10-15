package com.example.itmda_b4d4

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChatList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat_list)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewChats)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val chatList = arrayListOf(
            Message("Hey, how are you?", true, System.currentTimeMillis()),
            Message("Please check the gradle build", false, System.currentTimeMillis()),
            Message("Let's work on the UI today", true, System.currentTimeMillis())
        )

        recyclerView.adapter = MessageAdapter(chatList)
    }
}