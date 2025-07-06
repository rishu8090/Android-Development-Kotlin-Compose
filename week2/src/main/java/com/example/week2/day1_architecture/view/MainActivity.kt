package com.example.week2.day1_architecture.view

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.week2.R
import com.example.week2.day1_architecture.viewModel.GoogleViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var users: TextView
    lateinit var addUser: Button
//    val viewModel = ViewModel()
lateinit var viewModel: GoogleViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        addUser = findViewById(R.id.addUser)
        users = findViewById(R.id.users)

        viewModel = ViewModelProvider(this).get(GoogleViewModel::class.java)  // You need to create an instance of your ViewModel and assign it to the viewModel property, typically within the onCreate method of your Activity or Fragment.

        addUser.setOnClickListener {
            viewModel.addUser()
        }
        lifecycleScope.launch {
            viewModel.userFlow.collect {
                Log.d("TAG", "usersFlow: $it")
               users.text = it.joinToString { "$it\n" }  // join the lists into a single string.
            }
        }
    }
}