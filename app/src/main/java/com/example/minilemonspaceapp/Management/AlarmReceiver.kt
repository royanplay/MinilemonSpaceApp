package com.example.minilemonspaceapp.Management

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Waktunya istirahat!", Toast.LENGTH_SHORT).show()
        // Lakukan tindakan berhenti di sini
    }

    companion object {
        fun setRepeatingAlarm(context: Context) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val alarmIntent = Intent(context, AlarmReceiver::class.java).let { intent ->
                PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            }

            val startTime = SystemClock.elapsedRealtime()
            val intervalMillis = 60 * 60 * 1000 // 1 jam
            alarmManager.setRepeating(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                startTime,
                intervalMillis.toLong(),
                alarmIntent
            )
        }

        fun cancelAlarm(context: Context) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val alarmIntent = Intent(context, AlarmReceiver::class.java).let { intent ->
                PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            }
            alarmManager.cancel(alarmIntent)
        }
    }
}
