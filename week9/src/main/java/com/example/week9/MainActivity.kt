package com.example.week9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.week9.settings.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val settingsViewModel by viewModels<SettingsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val startDestination by settingsViewModel.startDestination.collectAsState()
            FirebaseApp(startDestination)
        }
    }
}

/*
1. Provide sign Up/ Sign In option (Authentication).
2. Store the user details in firebase.(db storage).
3. Allows users to upload file  (File Storage).
4. Allows users to monitor crashes (Crashlytics).
5. Want to gather user actions related data (Analytics).
*/

/*
how to setup the firebase in your app.
resister your package on the firebase console(com.example.week9)
you can grab debug sign in info from the terminal by typing (./gradlew signingReport) (optional)
download google service json file and put it into the src folder, by applying Project view.
then some dependency you have to include, some in root level build file and some in the module level build file.
Enable the authentication, on the firebase app in build section. you can use any type of authentication or multiple authentication.
add Dependency for authentication, by firebase/docs/auth/android/password-auth. in build module section.

to view  external source codes of the used fun you can go project view and go to the folder of External libraries, where you can find all external files code of the project.

 in firestore,  SQL -> row and columns
        NoSQL -> Not a SQL

        in NoSQL,
        json
        Collection ->  Tables (in SQL)
        Document -> Rows (in SQL)
        Data -> Columns (in SQL)
 */


