package com.sultonbek1547.alarmwithbroadcast

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sultonbek1547.alarmwithbroadcast.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSetAlarm.setOnClickListener {
            val currentTime = Calendar.getInstance()
            val hour = currentTime.get(Calendar.HOUR_OF_DAY)
            val minute = currentTime.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(
                this,
                { p0, p1, p2 ->


                    val alarmTime = Calendar.getInstance().apply {
                        set(Calendar.HOUR_OF_DAY, p1)
                        set(Calendar.MINUTE, p2)
                        set(Calendar.SECOND, 0)
                    }

                    val alarmIntent = Intent(this, MyReceiver::class.java)
                    alarmIntent.action = "START_RINGTONE"

                    val pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0)

                    val alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    alarmManager.setExact(
                        AlarmManager.RTC_WAKEUP,
                        alarmTime.timeInMillis,
                        pendingIntent
                    )

                },
                hour, minute, true
            )
            timePickerDialog.show()

        }


    }
}