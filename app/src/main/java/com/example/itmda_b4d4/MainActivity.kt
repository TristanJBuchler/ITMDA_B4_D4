package com.example.itmda_b4d4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.ui.AppBarConfiguration
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.room3.*
import androidx.sqlite.driver.AndroidSQLiteDriver
import com.example.itmda_b4d4.userRoomDatabase.UserDatabase
import com.example.itmda_b4d4.userRoomDatabase.UserDatabaseAccessTerminal
import com.example.itmda_b4d4.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val db = Room.databaseBuilder<UserDatabase>(applicationContext, "Users").setDriver(AndroidSQLiteDriver()).build()
        var loginButton = findViewById<Button>(R.id.loginButton);

        loginButton.setOnClickListener {
            var intent = Intent(this, ChatListActivity::class.java)


            lifecycleScope.launch {
                val terminal = UserDatabaseAccessTerminal()
                val username = findViewById<EditText>(R.id.NameEditText).text.toString()
                val password = findViewById<EditText>(R.id.PasswordEditText).text.toString()
                // Hash password here
                if(terminal.getUser(db,username)?.Password == password){
                    db.close()
                    startActivity(intent)
                }
                else{
                    var textStatus = findViewById<TextView>(R.id.textStatus)
                    var status = "Information is entered incorrectly or user doesn't exist.";
                    textStatus.text = status
                }

            }



        }
        var registrationButton = findViewById<Button>(R.id.registerButton)




        registrationButton.setOnClickListener {
            val inputName = findViewById<EditText>(R.id.NameEditText).text.toString()
            val inputPassword = findViewById<EditText>(R.id.PasswordEditText).text.toString()
            if(inputName != "" && inputPassword != ""){

                lifecycleScope.launch {
                    var terminal = UserDatabaseAccessTerminal()
                    if(terminal.getUser(db,inputName)?.Name == null){ //Add user if the name isn't present.
                        terminal.addUser(db,inputName,inputPassword)
                        val statusId = terminal.getUser(db,inputName)?.id
                        var textStatus = findViewById<TextView>(R.id.textStatus)
                        var status = "User Created.";
                        textStatus.text = status
                    }
                    else{
                        var textStatus = findViewById<TextView>(R.id.textStatus)
                        var status = "User already exists. Please choose another name.";
                        textStatus.text = status
                    }


                    //terminal.addUser(db,inputName,inputPassword)
                    //var textStatus = findViewById<TextView>(R.id.textStatus)
                    //var status = terminal.getUser(db,inputName)
                    //textStatus.text = status.id.toString()
                }
            }


        }

    }
}