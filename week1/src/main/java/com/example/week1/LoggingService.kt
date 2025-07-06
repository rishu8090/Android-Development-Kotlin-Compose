package com.example.week1

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log


class LoggingService : Service() {

    val handler = Handler(Looper.getMainLooper())  // used for multithreading in android . handler and looper are the main pilers of multithreading.
    val runnable = Runnable{   //  The Runnable interface should be implemented by any class whose instances are intended to be executed by a thread
        Log.d("TAG", "This log is printed from the ${this::class.simpleName}")
        stopSelf()  // Stop the service, if it was previously started. This is the same as calling Context.stopService for this particular service
    }

    override fun onCreate() {
        Log.d("TAG", "onCreate is printed from the ${this::class.simpleName}")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("Intent", "Intent is null: ${intent == null}") // this will help you to check if the intent is null or not, and you found out the main difference in all that return values
        handler.postDelayed(runnable, 5000)
//        return START_NOT_STICKY // if this service's process is killed while it is started (after returning from onStartCommand), and there are no new start intents to deliver to it, then take the service out of the started state and don't recreate until a future explicit call
//        return START_STICKY  // if this service's process is killed while it is started (after returning from onStartCommand), then leave it in the started state but don't retain this delivered intent
        return START_REDELIVER_INTENT  // if this service's process is killed while it is started (after returning from onStartCommand), then it will be scheduled for a restart and the last delivered Intent re-delivered to it again via onStartCommand.
    }

    override fun onBind(intent: Intent): IBinder? {
        TODO("Return the communication channel to the service.")
       return null
    }

    override fun onDestroy() {
        Log.d("TAG", "onDestroy is printed from the ${this::class.simpleName}")
        super.onDestroy()
    }
}


// in general, like audio player we use foreground services bcz it can run longer time than background, and also user should aware about that.