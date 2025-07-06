package com.example.week8.day3_multilanguage_support

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.week8.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiLanguageScreen(
    viewModel: MultiLanguageViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    fun updateLanguage(language: Language){
        context.updateResources(language)
        viewModel.saveLanguage(language)
    }
//    var currentLanguage by remember { mutableStateOf<Language?>(Language.English) }
    val currentLanguage by viewModel.language.collectAsState()
    val english by remember(currentLanguage) { mutableIntStateOf(R.string.language_english) }  // this is called Recomposition by a key, whenever  key change recomposition will happens.
    val hindi by remember(currentLanguage) { mutableIntStateOf(R.string.language_hindi) }  // it uses bcz, Compose change Ui when recomposition happens or state change.
    val title by remember(currentLanguage) { mutableIntStateOf(R.string.multi_language_screen_title) }

    Scaffold(
        topBar = {
//            TopAppBar(title = { Text(text = context.getString(R.string.multi_language_screen_title)) })
            TopAppBar(title = { Text(text = stringResource(title)) })  // Alt + enter and then extract string resource
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(text = stringResource(R.string.select_language))
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                AssistChip(
                    onClick = {
//                        context.updateResources(Language.English)
//                        currentLanguage = Language.English
                        updateLanguage(language = Language.English)
                    },
                    label = { Text(stringResource(english)) },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = if (currentLanguage == Language.English)
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.65f)
                        else
                            Color.Unspecified
                    )
                )

                Spacer(Modifier.width(16.dp))

                AssistChip(
                    onClick = {
//                        context.updateResources(Language.Hindi)
//                        currentLanguage = Language.Hindi
                        updateLanguage(language = Language.Hindi)
                    },
                    label = { Text(stringResource(hindi)) },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = if (currentLanguage == Language.Hindi)
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.65f)
                        else
                            Color.Unspecified
                    )
                )
            }
        }
    }
}