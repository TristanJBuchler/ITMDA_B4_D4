package com.example.itmda_b4d4.contactDatabase

import androidx.room3.Entity
import androidx.room3.PrimaryKey


@Entity(tableName = "Contacts")
data class Contact(
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0, //Can be changed later to allow last spoken member to appear on top.
    val contactName: String? = "",
    val contactID: Int = -1 //Not inserted by default.

)
