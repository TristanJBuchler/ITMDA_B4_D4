package com.example.itmda_b4d4.userRoomDatabase

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "Users")
data class User(
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    val Name: String,
    val Password: String
)
