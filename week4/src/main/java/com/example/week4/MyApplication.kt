package com.example.week4

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp   // this will tell the hilt that from this class you should start looking for the dependency.
class MyApplication : Application()