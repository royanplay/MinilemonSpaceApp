package com.example.minilemonspaceapp.Management

import android.content.Context
import android.database.Cursor
import com.example.minilemonspaceapp.DatabaseHelper

class UserAPI(context: Context) {
    private val dbHelper: DatabaseHelper = DatabaseHelper(context)

    // Metode untuk menyimpan data pengguna ke database
    fun addUser(user: UserProfile): Long {
        return dbHelper.addUser(user)
    }

    // Metode untuk mengambil semua data pengguna dari database
    fun getAllUsers(): Cursor? {
        return dbHelper.getAllUsers()
    }
}
