package com.example.week9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.week9.authentication.signin.SignInScreen
import com.example.week9.authentication.signup.SignUpScreen
import com.example.week9.ui.theme.Intro_to_jetpack_composeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseApp()
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

*/


