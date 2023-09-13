package com.example.minilemonspaceapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.minilemon_space.UserProfile

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "minilemon_space.db"
        private const val DATABASE_VERSION = 1

        // Nama tabel dan kolom
        private const val TABLE_USERS = "user"
        private const val COLUMN_ID = "id"
        private const val COLUMN_PROFILE_TYPE = "Jenis profil"
        private const val COLUMN_NAME = "Nama"
        private const val COLUMN_EMAIL = "Email"
        private const val COLUMN_AGE = "Umur"
        private const val COLUMN_GENDER = "jenis kelamin"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableSQL = ("""
                CREATE TABLE """ + TABLE_USERS + """ (
                    """ + COLUMN_ID + """ INTEGER PRIMARY KEY AUTOINCREMENT,
                    """ + COLUMN_PROFILE_TYPE + """ TEXT,
                    """ + COLUMN_NAME + """ TEXT,
                    """ + COLUMN_EMAIL + """ TEXT,
                    """ + COLUMN_AGE + """ INTEGER,
                    """ + COLUMN_GENDER + """ TEXT
                )
            """).trimIndent()

        db?.execSQL(createTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Implementasi pembaruan database
    }

    // menambahkan data pengguna ke database
    fun addUser(user: UserProfile): Long {
        val values = ContentValues()
        values.put(COLUMN_PROFILE_TYPE, user.jenisProfil)
        values.put(COLUMN_NAME, user.nama)
        values.put(COLUMN_EMAIL, user.email)
        values.put(COLUMN_AGE, user.usia)
        values.put(COLUMN_GENDER, user.jenisKelamin)

        val db = this.writableDatabase
        return db.insert(TABLE_USERS, null, values)
    }

    // untuk mengambil semua data pengguna dari database
    fun getAllUsers(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_USERS", null)
    }
    //DATABASE DAO
    @Database(entities = [Mission::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun missionDao(): MissionDao
    }
}
