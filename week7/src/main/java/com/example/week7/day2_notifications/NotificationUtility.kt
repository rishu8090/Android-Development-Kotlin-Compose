package com.example.week7.day2_notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

import com.example.week7.R
import kotlin.random.Random

object NotificationUtility {
        private  const val NOTIFICATION_CHANNEL_id = "12345"
        const val MARK_AS_READ_RECEIVER_ACTION = "MARK_AS_READ_ACTION"

    fun createNotificationChannel(context: Context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_id,
                    "General Notification",
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = "This is a channel for showing general Notification."
                }
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    fun createSimpleNotification(
        context: Context
    ){
        val notification: Notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_id)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Simple Notification")
            .setContentText("This is a simple notification")
            .build()

        NotificationManagerCompat.from(context).notify(Random.nextInt(), notification)
    }

    fun createClickableNotification(
        context: Context
    ){
        val notificationTitle = "Clickable Notification title"
        val notificationText = "This is a Clickable notification text"

        val intent = Intent(context, NotificationDetailActivity::class.java).apply {
            putExtra("title",notificationTitle)
            putExtra("text", notificationText)
        }
        val pendingIntent = PendingIntent.getActivity(context,0,intent, PendingIntent.FLAG_IMMUTABLE)  // it you want an activity then use getActivity(), just like for service use getService()
        val notification: Notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_id)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(notificationTitle)
            .setContentText(notificationText)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)            //  pendingIntent is used to launch the activity when the notification is clicked, it is a android feature, in which it do some work on user behalf.
            .build()                           // this is setting of pendingIntent.

        NotificationManagerCompat.from(context).notify(Random.nextInt(), notification)

    }

    fun  createActionableNotification(
        context: Context
    ){
        val notificationTitle = "Actionable Notification title"
        val notificationText = "This is a Actionable Notification text"

        val intent = Intent(context, NotificationReceiver::class.java).apply {
            action = MARK_AS_READ_RECEIVER_ACTION  // Set the general action to be performed.
            putExtra("title",notificationTitle)
            putExtra("text", notificationText)
        }
        val pendingIntent =
            PendingIntent.getBroadcast(context,0,intent, PendingIntent.FLAG_IMMUTABLE)  // it you want an activity then use getActivity(), just like for service use getService()
        val notification: Notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_id)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(notificationTitle)
            .setContentText(notificationText)
            .addAction(R.drawable.ic_launcher_foreground,MARK_AS_READ_RECEIVER_ACTION, pendingIntent)
            .setAutoCancel(true)
           // .setOngoing(true)  // it is used to make notification sticky. works on devices lower than API 34
            .build()

        NotificationManagerCompat.from(context).notify(Random.nextInt(), notification)
    }
}