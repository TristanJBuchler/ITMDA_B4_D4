package com.example.itmda_b4d4.userRoomDatabase

import androidx.room3.*

@Dao
interface UserDAO { // Users table is called "Users"
    @Query("SELECT * FROM Users WHERE Name = :target") //Selects a user by name, returns all information
    suspend fun getUser(target: String): User?

    @Query("SELECT * from Users")
    suspend fun getAllUsers(): List<User>
    @Insert
    suspend fun insertUser(target: User)

    @Delete
    suspend fun delete(target: User)

    @Update
    suspend fun updateUser(target: User)

    @Query ("DELETE FROM Users")
    suspend fun wipeBase()
}