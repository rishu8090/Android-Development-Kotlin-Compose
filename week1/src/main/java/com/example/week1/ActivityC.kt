package com.example.week1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class ActivityC : AppCompatActivity() {
    lateinit var launchActivityB: Button
    lateinit var launchActivityC: Button
    lateinit var launchActivityD: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c)

        launchActivityB = findViewById(R.id.launchActivityB)
        launchActivityC = findViewById(R.id.launchActivityC)
        launchActivityD = findViewById(R.id.launchActivityD)

        val number = intent.getIntExtra("number", 0)
        Toast.makeText(this, "Number is $number", Toast.LENGTH_SHORT).show()

        launchActivityB.setOnClickListener {
            val intent = Intent(this, ActivityB::class.java)
            startActivity(intent)
        }

        launchActivityC.setOnClickListener {
            val intent = Intent(this, ActivityC::class.java).apply {// it only shows how data is lost, we doesn't here try to recover this data.
                putExtra("number", Random.nextInt(11, 20))  // here, when we go Activity C to B and go to B,it uses same instance that's why no data will shown as Toast ie data is lost.
            }
            startActivity(intent)
        }

        launchActivityD.setOnClickListener {
            val intent = Intent(this, ActivityD::class.java)
            startActivity(intent)
        }
    }
    override fun onNewIntent(intent: Intent){  // to avoid losing data in the same instance is used or no new instance is created.
        super.onNewIntent(intent)   // it guarantee to called if new instance is created or we use previous instance.
        val number = intent.getIntExtra("number", 0)
        Toast.makeText(this, "Number from onNewIntent $number", Toast.LENGTH_SHORT).show()
    }
}