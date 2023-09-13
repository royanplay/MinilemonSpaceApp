package com.example.minilemonspaceapp

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class BreakNotificationWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        showNotification()
        return Result.success()
    }

    private fun showNotification() {
        val notificationBuilder = NotificationCompat.Builder(applicationContext, "break_channel")
            .setSmallIcon(R.drawable.logo_notif)
            .setContentTitle("Berhenti sejenak!")
            .setContentText("Sudah waktunya berhenti bermain selama 5 menit.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationManager = NotificationManagerCompat.from(applicationContext)
        notificationManager.notify(1, notificationBuilder.build())
    }
}
