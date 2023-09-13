package com.example.minilemonspaceapp

class MissionManager {

    private val rewardManager: RewardManager = RewardManager()

    // Daftar misi dan kemajuan pengguna
    private val missions = mutableListOf<Mission>()
    private val userProgress = mutableMapOf<String, Int>() // ID misi dan kemajuan

    fun addMission(mission: Mission) {
        missions.add(mission)
    }

    fun updateMissionProgress(missionId: String, progress: Int) {
        userProgress[missionId] = progress
    }

    fun checkMissionCompletion(userId: String) {
        for (mission in missions) {
            val missionId = mission.id
            val progress = userProgress[missionId] ?: 0
            if (progress >= mission.targetProgress) {
                // Misi selesai, berikan reward
                val reward = Reward(mission.rewardItem, mission.rewardAmount)
                rewardManager.addReward(reward)
                // Tambahkan logika lain yang sesuai
            }
        }
    }
}
