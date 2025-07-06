package com.example.week1

import android.content.ActivityNotFoundException
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    var counter: Int = 0
    lateinit var textview: TextView   // 'lateinit' allows initializing a not-null
    lateinit var button: Button
    lateinit var launchActivityB: Button
    lateinit var launchBrowser: Button
    lateinit var wifiReceiver: BroadcastReceiver
    lateinit var    startService: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textview = findViewById(R.id.textView)
        button = findViewById(R.id.button)
        launchActivityB = findViewById(R.id.launchActivityB)
        launchBrowser = findViewById(R.id.launchBrowser)
        startService  = findViewById(R.id.startService)

//        textview.text = (savedInstanceState?.getInt("counter") ?: 0).toString()   // if we use this line, it doesn't guarantee that it doesn't give a null value whereas onRestoreInstance() gives it.

        button.setOnClickListener {
            counter++
            textview.text = "Count : $counter"
        }

        launchActivityB.setOnClickListener {  // this is a explicit intent where, we define what is our target screen.
            val intent = Intent(this, ActivityB::class.java)
            startActivity(intent)   // to view activity stack use (adb shell dumpsys activity activities | grep package_name | grep Hist ) and don't forget to run package on device.
        }

        launchBrowser.setOnClickListener {  // this is a implicit intent, where we doesn't tell the target screen.
            try {
                val url = "https://www.google.com"
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(url)
                }
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "No Browser App Found", Toast.LENGTH_SHORT).show()
            }
        }

       wifiReceiver = WifiReceiver()  // after android oreo, you can(and not compulsory, but it is a good practice.) define receiver in manifest, as well as define it dynamically in app.
//        this.registerReceiver(
//            wifiReceiver,
//            IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION)
//        )

        ContextCompat.registerReceiver(  // its a better way to register the receiver, it gives same response in all android version by apis, unlike other methods, it self update acq to version.
            this,
            wifiReceiver,
            IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION),
            ContextCompat.RECEIVER_NOT_EXPORTED  // : The receiver can receive broadcasts from other Apps. Has the same behavior as marking a statically registered receiver with "exported=true"
        )

        startService.setOnClickListener {
            val intent = Intent(this, LoggingService::class.java)
            startService(intent)
        }
    }


    override fun onStop(){
        super.onStop()
        this.unregisterReceiver(wifiReceiver)  // it is a good practice to unregister the receiver after use to avoid memory leaks. // it you not unregister it will live in background and increase battery consumption.
    }

    // when we rotate our device, counter value will reset bcz activity is destroyed and a new activity created to encounter this problem this is used.
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("counter", counter)   // this will store our counter value not in activity in place of application space,this way we save our counter's value in form a bundle.
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {   // in that way, we retrieve our value from onSaveInstanceState.
        super.onRestoreInstanceState(savedInstanceState)
        val count = savedInstanceState.getInt("counter")
        textview.text = "Count : $counter"
    }


    /*
    *  created  - As soon as activity is launched.
    *  started - After created - you can see Ui but can't interact with it.
    *  Resumed - After created - you cab see UI and also can interact with it.
    *  paused - after resume and lost the focus.
    *  stopped - activity gets invisible
    *  destroyed - when activity destroyed.
    *
    *  finish - same as destroyed but developer can use it.
    *
    *  // what will happens if we call finish method in the created method,
    *    in that case, after created directly destroyed method will call bcz, in created we have to setup our UI in it (in start and resumed internally) or getting resources for UI setup.
    *    but before setting up UI finish is called that, why he jumps all mid methods and calls destroy method.
    *
    *  gragle - is a build automation software ie. it combines all the codes, packages, resources and creating a final software.
    *  agp (android gradle plugin) - have many work,
    * 1.compile java/kotlin code.
    * 2.combine all resources,
    * 3. gives runtime warning and errors
    * 4. combine everything together,
    *
    *  jvm languages - (java, kotlin, groovy, scala)
    * */



    /*
    * launch Modes ->
    * standard - Every time the activity is launched, a new instance is created, regardless of whether an instance of the activity already exists in the stack.
    * singleTop - If an instance of the activity already exists at the top of the stack (i.e., the activity is already running in the foreground), a new instance won't be created.
    * singleTask - If an instance of the activity already exists in the stack, the system brings it to the foreground instead of creating a new instance
    * singleInstance - If an instance of the activity already exists in the stack, the system doesn't create a new instance, and ie it creates a new activity stack for only that activity or self.
    *  If another app tries to launch an activity with singleInstance mode, a new task will be created and the activity will be placed at the root of that task.
    *
    * */

    /*
        *in broadCast, you can also use localBroadCastManager to broadcast the event from one module and other module of the app can receive the event. and can do other operations.

    *Types of Broadcasts
    *System Broadcasts: Sent by the Android system to notify about system events (e.g., device boot completed, network connectivity changes).
    *Custom Broadcasts: Created by the application to send specific messages within the application or to other applications.
    *Static Registration: Declare the BroadcastReceiver and its intent filter(s) in the AndroidManifest.xml file.
    *Dynamic Registration: Register the BroadcastReceiver programmatically using the registerReceiver() method
    *
    */
}