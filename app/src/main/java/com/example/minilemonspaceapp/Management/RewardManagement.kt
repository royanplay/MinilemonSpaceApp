import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class RewardManagement(context: Context) {

    private val dbName = "RewardDatabase"
    private val dbTable = "rewards"
    private val colId = "id"
    private val colUserId = "userId"
    private val colTimePlayed = "timePlayed"

    private val dbVersion = 1
    private val dbCreateTable =
        "CREATE TABLE IF NOT EXISTS $dbTable ($colId INTEGER PRIMARY KEY, $colUserId INTEGER, $colTimePlayed INTEGER);"

    private var db: SQLiteDatabase? = null

    // Inisialisasi database
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

    // Fungsi untuk menambahkan hadiah
    fun insertReward(userId: Int, timePlayed: Int): Long {
        val values = ContentValues()
        values.put(colUserId, userId)
        values.put(colTimePlayed, timePlayed)
        return db!!.insert(dbTable, null, values)
    }

    // Fungsi untuk memperbarui hadiah
    fun updateReward(id: Int, userId: Int, timePlayed: Int): Int {
        val values = ContentValues()
        values.put(colUserId, userId)
        values.put(colTimePlayed, timePlayed)
        return db!!.update(dbTable, values, "$colId=?", arrayOf(id.toString()))
    }

    // Fungsi untuk menghapus hadiah
    fun deleteReward(id: Int): Int {
        return db!!.delete(dbTable, "$colId=?", arrayOf(id.toString()))
    }

    // Fungsi untuk mendapatkan semua hadiah berdasarkan userID
    @SuppressLint("Range")
    fun getRewardsByUserId(userId: Int): ArrayList<Reward> {
        val rewards = ArrayList<Reward>()
        val cursor: Cursor?
        try {
            cursor = db!!.rawQuery("SELECT * FROM $dbTable WHERE $colUserId=?", arrayOf(userId.toString()))
        } catch (e: SQLException) {
            db!!.execSQL(dbCreateTable)
            return ArrayList()
        }

        var id: Int
        var timePlayed: Int

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(colId))
                timePlayed = cursor.getInt(cursor.getColumnIndex(colTimePlayed))
                rewards.add(Reward(id, userId, timePlayed))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return rewards

    }

    data class Reward(val id: Int, val userId: Int, val timePlayed: Int)
}
