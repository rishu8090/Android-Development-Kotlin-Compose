package com.example.week8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.week8.day1_workmanager.BackgroundWorkScreen
import com.example.week8.day2_upload_image_workmanager.ImageUploadScreen
import com.example.week8.day3_multilanguage_support.MultiLanguageScreen
import com.example.week8.day3_multilanguage_support.MultiLanguageViewModel
import com.example.week8.day3_multilanguage_support.updateResources
import com.example.week8.ui.theme.Intro_to_jetpack_composeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel by viewModels<MultiLanguageViewModel>()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Intro_to_jetpack_composeTheme {
//                BackgroundWorkScreen()
                ImageUploadScreen()
//                MultiLanguageScreen()
            }
        }

        lifecycleScope.launch{// whenever, the activity starts, we monitor the stateflow, go and retrieve the current language from the dataStore and updateResources,
                viewModel.language.collect { language ->
                    this@MainActivity.updateResources(language)
                }
        }
    }
}

