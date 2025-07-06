package com.example.week1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class ActivityB : AppCompatActivity() {
    lateinit var launchActivityB: Button
    lateinit var launchActivityC: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        launchActivityB = findViewById(R.id.launchActivityB)
        launchActivityC = findViewById(R.id.launchActivityC)

        launchActivityB.setOnClickListener {
            val intent = Intent(this, ActivityB::class.java)  // if we launch activity B many times, then in each click it will create a new activityB instance  in activity stack. to avoid that we use launchModes in the program.
            startActivity(intent)
        }

        launchActivityC.setOnClickListener {
            val intent = Intent(this, ActivityC::class.java).apply {
                putExtra("number", Random.nextInt(5,10))   // in that way, you can also pass data to another activity but you have to retrieve it.
            }
            startActivity(intent)
        }

    }
}