package com.sultonbek1547.alarmwithbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sultonbek1547.alarmwithbroadcast.databinding.ActivityAlarmActivatedBinding
import java.text.SimpleDateFormat
import java.util.*

class AlarmActivatedActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAlarmActivatedBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        binding.tvTimeCurrent.text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())


        registerReceiver(dismissReceiver, IntentFilter("DISMISS_ALARM"))
        binding.btnDismiss.setOnClickListener {
            sendBroadcast(Intent("DISMISS_ALARM"))
        }

    }

    private val dismissReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == "DISMISS_ALARM") {
                // sending broadcast to MyReceiver
                val dismissIntent = Intent(context, MyReceiver::class.java)
                dismissIntent.action = "DISMISS_ALARM"
                context?.sendBroadcast(dismissIntent)
                // Finish the activity
                finish()
            }
        }
    }
}