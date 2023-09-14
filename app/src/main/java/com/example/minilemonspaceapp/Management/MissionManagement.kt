package com.example.minilemonspaceapp.Management

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MissionManagement(context: Context) {

    private val dbName = "MissionDatabase"
    private val dbTable = "missions"
    private val colId = "id"
    private val colName = "nama"
    private val colLevel = "level"
    private val colAge = "age"

    private val dbVersion = 1
    private val dbCreateTable =
        "CREATE TABLE IF NOT EXISTS $dbTable ($colId INTEGER PRIMARY KEY, $colName TEXT, $colLevel INTEGER, $colAge INTEGER);"

    private var db: SQLiteDatabase? = null

    init {
        val dbHelper = DatabaseHelper(context)
        db = dbHelper.writableDatabase
    }

    inner class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, dbName, null, dbVersion) {
        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL(dbCreateTable)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL("DROP TABLE IF EXISTS $dbTable")
            onCreate(db)
        }
    }

    // Fungsi untuk menambahkan misi baru
    fun insertMission(name: String, level: Int, age: Int): Long {
        val values = ContentValues()
        values.put(colName, name)
        values.put(colLevel, level)
        values.put(colAge, age)
        return db!!.insert(dbTable, null, values)
    }

    // Fungsi untuk memperbarui misi
    fun updateMission(id: Int, name: String, level: Int, age: Int): Int {
        val values = ContentValues()
        values.put(colName, name)
        values.put(colLevel, level)
        values.put(colAge, age)
        return db!!.update(dbTable, values, "$colId=?", arrayOf(id.toString()))
    }

    // Fungsi untuk menghapus misi berdasarkan ID
    fun deleteMission(id: Int): Int {
        return db!!.delete(dbTable, "$colId=?", arrayOf(id.toString()))
    }

    // Fungsi untuk mendapatkan semua misi
    @SuppressLint("Range")
    fun getAllMissions(): ArrayList<Mission> {
        val missions = ArrayList<Mission>()
        val cursor: Cursor?
        try {
            cursor = db!!.rawQuery("SELECT * FROM $dbTable", null)
        } catch (e: SQLException) {
            db!!.execSQL(dbCreateTable)
            return ArrayList()
        }

        var id: Int
        var name: String
        var level: Int
        var age: Int

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(colId))
                name = cursor.getString(cursor.getColumnIndex(colName))
                level = cursor.getInt(cursor.getColumnIndex(colLevel))
                age = cursor.getInt(cursor.getColumnIndex(colAge))
                missions.add(Mission(id, name, level, age))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return missions
    }

    data class Mission(val id: Int, val name: String, val level: Int, val age: Int)
}
