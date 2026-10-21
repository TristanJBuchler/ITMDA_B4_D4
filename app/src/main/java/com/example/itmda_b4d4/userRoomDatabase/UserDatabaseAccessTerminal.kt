package com.example.itmda_b4d4.userRoomDatabase



class UserDatabaseAccessTerminal {
    suspend fun getUser(db: UserDatabase, username: String): User?{
        val userDAO = db.userDao()
        return userDAO.getUser(username)
    }
    suspend fun addUser(db: UserDatabase, username: String, password: String){
        val userDAO = db.userDao()
        var newUser : User
        newUser = User(0,username,password)

        userDAO.insertUser(newUser)
    }

    suspend fun wipeUsers(db: UserDatabase){ //Wipes the entire database.
        val userdao = db.userDao()
        userdao.wipeBase()
    }





}