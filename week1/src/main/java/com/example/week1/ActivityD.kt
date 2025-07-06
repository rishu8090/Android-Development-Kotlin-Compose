package com.example.week1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityD : AppCompatActivity() {
    lateinit var launchActivityB: Button
    lateinit var launchActivityC: Button
    lateinit var launchActivityD: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d)

        launchActivityB = findViewById(R.id.launchActivityB)
        launchActivityC = findViewById(R.id.launchActivityC)
        launchActivityD = findViewById(R.id.launchActivityD)

        launchActivityB.setOnClickListener {
            val intent = Intent(this, ActivityB::class.java)  // if we launch activity B many times, then in each click it will create a new activityB instance  in activity stack. to avoid that we use launchModes in the program.
            startActivity(intent)
        }

        launchActivityC.setOnClickListener {
            val intent = Intent(this, ActivityC::class.java)
            startActivity(intent)
        }

        launchActivityD.setOnClickListener {
            val intent = Intent(this, ActivityD::class.java)
            startActivity(intent)
        }


    }
}