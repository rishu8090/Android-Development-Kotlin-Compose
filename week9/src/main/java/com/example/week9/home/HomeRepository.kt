package com.example.week9.home

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

interface HomeRepository {
    fun logDetailScreenViewEvent()
}

class HomeRepositoryImpl @Inject constructor(
    private val analytics: FirebaseAnalytics
) : HomeRepository {
    override fun logDetailScreenViewEvent() {
        val metadata = Bundle().apply {
            putString(FirebaseAnalytics.Param.SCREEN_NAME, "detail")  // screen name
        }
        analytics.logEvent(
            FirebaseAnalytics.Event.SCREEN_VIEW,  // event type
            metadata
        )
    }
}