package com.example.itmda_b4d4

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room3.Room
import androidx.sqlite.driver.AndroidSQLiteDriver
import com.example.itmda_b4d4.contactDatabase.ContactDatabase
import com.example.itmda_b4d4.contactDatabase.ContactTerminal
import com.example.itmda_b4d4.userRoomDatabase.UserDatabase
import com.piesocket.channels.PieSocket
import com.piesocket.channels.misc.PieSocketOptions
import kotlinx.coroutines.launch
import org.webrtc.ApplicationContextProvider

class ChatListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var signOutActivityButton = findViewById<Button>(R.id.signOutButton)
        var newContactActivityButton = findViewById<Button>(R.id.newContactButton)
        var settingsActivityButton = findViewById<Button>(R.id.settingsButton)
        val db = Room.databaseBuilder<ContactDatabase>(applicationContext, "Contacts").setDriver(AndroidSQLiteDriver()).build()
        val contactTerminal = ContactTerminal() //Wrapper for contact to make it easier to work with.

        var pieSocketOptions1 = PieSocketOptions() // Piesocket integration for cluster 1
        pieSocketOptions1.version = "4"
        pieSocketOptions1.clusterId = ""
        pieSocketOptions1.apiKey = ""
        var pieSocketOptions2 = PieSocketOptions() // Piesocket integration for cluster 2
        pieSocketOptions2.version = "4"
        pieSocketOptions2.clusterId = ""
        pieSocketOptions2.apiKey = ""
        var pieSocket1 = PieSocket(pieSocketOptions1)
        var pieSocket2 = PieSocket(pieSocketOptions2)

        lifecycleScope.launch { //Creates the contacts used in the program.

            for (contact in contactTerminal.getContacts(db)){
                createButton(contact.contactName)

             }
        }


        signOutActivityButton.setOnClickListener {
            var Intent = Intent(this, MainActivity::class.java)
            startActivity(Intent)
        }
        newContactActivityButton.setOnClickListener {
            val contactIntent = Intent(this, ContactCreationActivity::class.java)
            startActivity(contactIntent)
        }
        settingsActivityButton.setOnClickListener {
            var Intent = Intent(this, SettingsMenu::class.java)
            startActivity(Intent)
        }
        val CreateIntent = intent
        if (CreateIntent.getStringExtra("NEW") != "") {
            val intentExtra = CreateIntent.getStringExtra("NEW")
            lifecycleScope.launch {
                Log.w("The ID has been registered as : ", CreateIntent.getStringExtra("NEW").toString())

                contactTerminal.insertContact(db,intentExtra)
                db.close() //Closes DB to prevent multiple connections.
            }

        }
    }
    fun createButton(name: String?){
        val newButton = Button(this)
        newButton.height = 50

    }
}