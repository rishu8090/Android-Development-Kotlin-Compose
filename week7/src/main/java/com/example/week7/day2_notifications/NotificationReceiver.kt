package com.example.week7.day2_notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val intentExtra = intent.extras
        val title = intentExtra?.getString("title")
        val text = intentExtra?.getString("text")

        Log.d("NotificationReceiver", "Title: $title")
        Log.d("NotificationReceiver", "Text: $text")


    }
}