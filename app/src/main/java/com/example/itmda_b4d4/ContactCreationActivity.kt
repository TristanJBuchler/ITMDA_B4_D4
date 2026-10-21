package com.example.itmda_b4d4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContactCreationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contact_creation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val cancelButton = findViewById<Button>(R.id.ContactCancelButton)
        val createButton = findViewById<Button>(R.id.ContactCreateContactButton)

        cancelButton.setOnClickListener {
            val cancelIntent = Intent(this, ChatListActivity::class.java)
            startActivity(cancelIntent)
        }
        createButton.setOnClickListener {
            val createIntent = Intent(this, ChatListActivity::class.java)
            val contactNameEditText = findViewById<EditText>(R.id.ContactIDEditText)
            createIntent.putExtra("NEW",contactNameEditText.text.toString()) //Sends the Name to the activity.

            startActivity(createIntent)
        }
    }





}