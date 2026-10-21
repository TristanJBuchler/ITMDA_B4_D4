package com.example.itmda_b4d4.userRoomDatabase

import androidx.room3.Database
import androidx.room3.RoomDatabase
@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase(){
    abstract fun userDao(): UserDAO

}