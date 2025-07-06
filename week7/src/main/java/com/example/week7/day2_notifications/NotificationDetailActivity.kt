package com.example.week7.day2_notifications

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.week7.R

class NotificationDetailActivity : AppCompatActivity() {

    lateinit var notificationTitle : TextView
    lateinit var notificationText : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notification_detail)

        val  intentExtra =  intent.extras  // this is a receiving of pendingIntent.
        val title =  intentExtra?.getString("title")
        val text = intentExtra?.getString("text")

       notificationTitle =  findViewById(R.id.notificationTitle)
        notificationText = findViewById(R.id.notificationText)

        notificationTitle.text = title   // in this way we set our intent's data on the textViews.
        notificationText.text = text     // android:theme="@style/Theme.AppCompat.Light">  add this line to view  intent data.
    }
}