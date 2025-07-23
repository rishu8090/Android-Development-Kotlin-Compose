package com.example.week9.fcm

import android.Manifest
import android.app.Notification
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.week9.R
import com.example.week9.utils.Constants
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random

class MessagingService : FirebaseMessagingService() {

//    override fun onCreate() {
//        super.onCreate()
//        Log.d("TAG", "${this::class.simpleName} created.")  // to check message Service is working properly or not.
//    }

    override fun onNewToken(token: String) {   // on app install, it will be called only once, it generates a unique token for the app, which is sent to the server and used to identify the device
        super.onNewToken(token)
        Log.d("TAG", "onNewToken : $token")  // and not to forgot to add service in the manifest.
    }

    override fun onMessageReceived(message: RemoteMessage){ // this is used to receive the notification from the server.
        super.onMessageReceived(message)
        val title = message.notification?.title
        val body = message.notification?.body
        Log.d("TAG", "onMessageReceived : $title $body")

        showNotification(title ?: "Default Title", body ?:"Default Body")
    }

    fun Context.showNotification(
       title: String, body: String
    ){
        val notification: Notification = NotificationCompat.Builder( this, Constants.NOTIFICATION_CHANNEL_id)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(body)
            .build()

        if (ActivityCompat.checkSelfPermission(  // to check permission is allowed or not.
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("TAG", "Permission is not available.")
            return
        }
        
        NotificationManagerCompat
            .from(this)
            .notify(Random.nextInt(), notification)
    }

}