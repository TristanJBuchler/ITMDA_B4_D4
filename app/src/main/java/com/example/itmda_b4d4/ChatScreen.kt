package com.example.itmda_b4d4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChatScreen : AppCompatActivity() {

    companion object {
        const val EXTRA_CONTACT_NAME = "contact_name"
    }

    private val messageList = ArrayList<Message>()
    private lateinit var adapter: MessageAdapter
    private lateinit var rvMessages: RecyclerView
    private lateinit var etMessage: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat_screen)
        // added ime so the keyboard pushes the message box up
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        val btnBack = findViewById<TextView>(R.id.btnBack)
        val tvName = findViewById<TextView>(R.id.txtContactName)
        val tvStatus = findViewById<TextView>(R.id.txtStatus)
        val btnSend = findViewById<Button>(R.id.btnSend)
        etMessage = findViewById(R.id.edtMessage)
        rvMessages = findViewById(R.id.recyclerMessages)

        val name = intent.getStringExtra(EXTRA_CONTACT_NAME) ?: "John"
        tvName.text = name
        tvStatus.text = "Online"

        btnBack.setOnClickListener { finish() }

        val manager = LinearLayoutManager(this)
        manager.stackFromEnd = true
        rvMessages.layoutManager = manager
        adapter = MessageAdapter(messageList)
        rvMessages.adapter = adapter

        loadMessages()

        btnSend.setOnClickListener { sendMessage() }
    }

    // test messages for now, will load from database later
    private fun loadMessages() {
        val time = System.currentTimeMillis()
        messageList.add(Message("Hey, how are you?", false, time - 120000))
        messageList.add(Message("I'm good thanks, and you?", true, time - 60000))
        adapter.notifyDataSetChanged()
    }

    private fun sendMessage() {
        val text = etMessage.text.toString().trim()
        if (text.isEmpty()) return

        addMessage(Message(text, true, System.currentTimeMillis()))
        etMessage.setText("")

        // TODO send to server (primary api, switch to secondary if it fails)
    }

    fun addMessage(msg: Message) {
        messageList.add(msg)
                adapter.notifyItemInserted(messageList.size - 1)
        rvMessages.scrollToPosition(messageList.size - 1)
    }
}
