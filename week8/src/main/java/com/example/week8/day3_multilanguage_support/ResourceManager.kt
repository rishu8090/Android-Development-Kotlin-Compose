package com.example.week8.day3_multilanguage_support

import android.content.Context
import java.util.Locale

//object ResourceManager {
    fun Context.updateResources(language: Language) {
        val locale = Locale(language.code).apply {
            Locale.setDefault(this)  // this helps us to change our default language to any language in a app.
        }

        // after changing default we have to change app configuration.
        val configuration = resources.configuration.apply {
            setLocale(locale)
            setLayoutDirection(locale) // direction means that particular language written left to right or right to left.
        }


        resources.updateConfiguration(configuration, resources.displayMetrics)
    }
//}