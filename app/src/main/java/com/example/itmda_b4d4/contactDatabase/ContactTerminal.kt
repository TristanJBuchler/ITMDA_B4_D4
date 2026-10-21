package com.example.itmda_b4d4.contactDatabase

class ContactTerminal {
    suspend fun insertContact(db: ContactDatabase, name: String?){
        val contactDao = db.contactDatabaseDAO()
        val newContact = Contact(0,name)
        contactDao.insertContact(newContact)
    }
    suspend fun getContacts(db: ContactDatabase): List<Contact>{
        val contactDao = db.contactDatabaseDAO()
        return contactDao.getContacts()
    }

    suspend fun updateContact(db: ContactDatabase, contact: Contact){
        val contactDao = db.contactDatabaseDAO()
        contactDao.updateContact(contact)
    }


}