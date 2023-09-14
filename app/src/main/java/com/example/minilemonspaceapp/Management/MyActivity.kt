package com.example.minilemonspaceapp.Management

import RewardManagement

class MyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my)

        // Mendefinisikan ID pengguna (userId) dan ID hadiah (rewardId) jika diperlukan
        val userId = 1
        val rewardId = 2

        // Menambahkan hadiah baru
        val rewardManagement = RewardManagement(this)
        val newRewardId = rewardManagement.insertReward(userId, 50) // 50 menit
        if (newRewardId > 0) {
            // Hadiah berhasil ditambahkan
        } else {
            // Gagal menambahkan hadiah
        }

        // Memperbarui hadiah
        val updatedRows = rewardManagement.updateReward(rewardId, userId, 75) // 75 menit
        if (updatedRows > 0) {
            // Hadiah berhasil diperbarui
        } else {
            // Gagal memperbarui hadiah
        }

        // Menghapus hadiah
        val deletedRows = rewardManagement.deleteReward(rewardId)
        if (deletedRows > 0) {
            // Hadiah berhasil dihapus
        } else {
            // Gagal menghapus hadiah
        }

        // Mendapatkan semua hadiah berdasarkan userID
        val rewards = rewardManagement.getRewardsByUserId(userId)

        // Lanjutkan dengan logika aplikasi Anda di sini
    }

    private fun setContentView(activityMy: Any) {

    }
}
