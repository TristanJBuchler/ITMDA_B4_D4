package com.example.itmda_b4d4.contactDatabase

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update

@Dao
interface ContactDatabaseDAO {
    @Query("Select * FROM Contacts")
    suspend fun getContacts(): List<Contact>

    @Insert
    suspend fun insertContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)
}