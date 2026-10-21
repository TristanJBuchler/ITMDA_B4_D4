package com.example.itmda_b4d4.contactDatabase

import androidx.room3.Database
import androidx.room3.RoomDatabase


@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase(){
    abstract fun contactDatabaseDAO(): ContactDatabaseDAO
}