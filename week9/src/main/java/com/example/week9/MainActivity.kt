package com.example.week9

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.week9.fcm.MessagingService
import com.example.week9.settings.SettingsViewModel
import com.example.week9.utils.Constants
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val settingsViewModel by viewModels<SettingsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val startDestination by settingsViewModel.startDestination.collectAsState()
            FirebaseApp(startDestination = startDestination)
        }
        retrieveFCMToken()
        // sample token -> dnpLZU9dRpuY5mRLfz7Zp3:APA91bFdkmOrwHAjpVQ6cn6hjGsVLUkUjWNGVd4M4IGhGdSpe7Oj15_JFtccy9G1Grikwrl_eQslpC9GFpfWcxNkTHGGnx6qCg-i9rO2IxakL9T9waRBQFE
        val messagingServiceIntent = Intent(this, MessagingService::class.java)
        startService(messagingServiceIntent)
    }

    fun createNotificationChannel(context: Context){
        val notificationChannel = NotificationChannel(
            Constants.NOTIFICATION_CHANNEL_id,
            "General Notification",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "This is a channel for showing general Notification."
        }

        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(notificationChannel)
    }

    fun retrieveFCMToken() {  // this fun can you copy from docs of firebase messaging.
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.d("TAG", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            Log.d("TAG", "retrieveFCMToken: $token")
        })
    }
}

/*
1. Provide sign Up/ Sign In option (Authentication).
2. Store the user details in firebase.(db storage).
3. Allows users to upload file  (File Storage).
4. Allows users to monitor crashes (Crashlytics).
5. Want to gather user actions related data (Analytics).
*/

/*
how to setup the firebase in your app.
resister your package on the firebase console(com.example.week9)
you can grab debug sign in info from the terminal by typing (./gradlew signingReport) (optional)
download google service json file and put it into the src folder, by applying Project view.
then some dependency you have to include, some in root level build file and some in the module level build file.
Enable the authentication, on the firebase app in build section. you can use any type of authentication or multiple authentication.
add Dependency for authentication, by firebase/docs/auth/android/password-auth. in build module section.

to view  external source codes of the used fun you can go project view and go to the folder of External libraries, where you can find all external files code of the project.

 in firestore,  SQL -> row and columns
        NoSQL -> Not a SQL

        in NoSQL,
        json
        Collection ->  Tables (in SQL)
        Document -> Rows (in SQL)
        Data -> Columns (in SQL)    // this is part of firebase Database.

        learn about external tools.
 */


