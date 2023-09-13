package com.example.minilemonspaceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import java.util.concurrent.TimeUnit


class RewardManager : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reward_manager)
//
        private var ageInYears: Int = 2
        private var levelCompleted: Int = 5

        fun addReward(reward: Reward) {
            // Implementasi penambahan reward ke dalam database atau penyimpanan yang sesuai
        }

        fun updateReward(reward: Reward) {
            // Implementasi pembaruan reward di dalam database atau penyimpanan yang sesuai
        }

        fun deleteReward(rewardId: String) {
            // Implementasi penghapusan reward dari database atau penyimpanan yang sesuai
        }

        fun calculatePlayTime(): Int {
            val basePlayTime = ageInYears * 10
            val additionalPlayTime = levelCompleted * 5
            return basePlayTime + additionalPlayTime
        }

//reward
        fun giveMissionReward(reward: Reward) {
            // Implementasi pemberian reward dari misi ke pengguna
            // Misalnya, tambahkan reward ke saldo pengguna atau penyimpanan yang sesuai
        }


        fun scheduleBreakNotification(workManager: WorkManager) {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                .build()

            val notificationWorkRequest = PeriodicWorkRequestBuilder<BreakNotificationWorker>(
                1, TimeUnit.HOURS
            )

            val rewardManager = RewardManager()

            val reward = Reward("Item A", 10)
            rewardManager.addReward(reward)

            val playTime = rewardManager.calculatePlayTime()

            rewardManager.scheduleBreakNotification(WorkManager.getInstance(this))


                .setConstraints(constraints)
                .build()

            workManager.enqueue(notificationWorkRequest)
        }
    }

    }
}