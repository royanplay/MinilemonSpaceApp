package com.example.minilemonspaceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val rewardManager = RewardManager()
    private val missionManager = MissionManager()
    private lateinit var userAPI: UserAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //api
        userAPI = UserAPI(this)

        // Menyimpan data pengguna ke database
        val user = UserProfile(
            id = 0,
            jenisProfil = "Orang Tua",
            nama = "royan",
            email = "royan@example.com",
            usia = 24,
            jenisKelamin = "Laki-Laki"
        )

        val userId = userAPI.addUser(user)
        if (userId > 0) {
            Toast.makeText(this, "Profil pengguna berhasil disimpan", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Gagal menyimpan profil pengguna", Toast.LENGTH_SHORT).show()
        }

        // Mengambil semua data pengguna dari database
        val usersCursor = userAPI.getAllUsers()
        if (usersCursor != null) {
            // Lakukan sesuatu dengan data pengguna (misalnya, tampilkan dalam daftar)
        }

        // Contoh: Menambahkan misi ke MissionManager saat tombol "Tambah Misi" ditekan
        addMissionButton.setOnClickListener {
            val mission = Mission("Misi Edukasi", 1, "Item A", 10)
            missionManager.addMission(mission)
            // Implementasikan logika lain yang diperlukan saat menambahkan misi
        }

        // Contoh: Memperbarui kemajuan misi saat pengguna menyelesaikan level
        val userId = "user123" // ID pengguna
        val missionId = "mission1" // ID misi
        val levelCompleted = 3 // Level yang diselesaikan
        val missionProgress = levelCompleted // Misinya mungkin memerlukan level yang sama dengan progress
        missionManager.updateMissionProgress(missionId, missionProgress)

        // Contoh: Memeriksa dan memberikan reward jika misi selesai
        missionManager.checkMissionCompletion(userId)

        // ...
    }

    // ...
}

        // Menampilkan waktu bermain awal
        updatePlayTime()

        addRewardButton.setOnClickListener {
            val reward = Reward("Item A", 10)
            rewardManager.addReward(reward)
            // Implementasikan logika lain yang diperlukan saat menambahkan reward
        }

        // Tempatkan logika lainnya di sini
    }

    private fun updatePlayTime() {
        val playTime = rewardManager.calculatePlayTime()
        playTimeTextView.text = "Waktu Bermain: $playTime menit"

    }
}