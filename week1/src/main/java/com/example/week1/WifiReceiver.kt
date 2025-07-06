package com.example.week1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.util.Log
import android.widget.Toast

class WifiReceiver : BroadcastReceiver() {  // for activity, service , broadCast Receiver and content provider all you must have to declare in manifest.

    override fun onReceive(context: Context, intent: Intent) {  // in android app all App compacts communicate through the intent.
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val action = intent.action
        when (action) {
            WifiManager.WIFI_STATE_CHANGED_ACTION -> {
                val wifiState: Int  = intent.getIntExtra(
                    WifiManager.EXTRA_WIFI_STATE,
                    WifiManager.WIFI_STATE_UNKNOWN
                )
                when (wifiState) {
                    WifiManager.WIFI_STATE_ENABLED -> {
                        Log.d("WifiReceiver", "Wi-Fi is enabled")
                        Toast.makeText(context,"Wifi is enabled", Toast.LENGTH_SHORT).show()
                    }
                    WifiManager.WIFI_STATE_DISABLED -> {
                        Log.d("WifiReceiver", "Wi-Fi is disabled")
                        Toast.makeText(context,"Wifi is disabled", Toast.LENGTH_SHORT).show()
                    }
                    WifiManager.WIFI_STATE_UNKNOWN -> {
                        Log.d("WifiReceiver", "Wi-Fi state is unknown")
                        Toast.makeText(context,"Wifi state is unknown", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            else -> {
                Log.w("WifiReceiver", "Received an unhandled action: ${intent.action}")
            }
        }
    }
    }
