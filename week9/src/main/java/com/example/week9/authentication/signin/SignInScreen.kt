package com.example.week9.authentication.signin

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.week9.R
import com.example.week9.authentication.CompanyInfo
import com.example.week9.authentication.EmailAndPasswordContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Sign In") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CompanyInfo(modifier = Modifier.weight(1f))

            EmailAndPasswordContent(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                email = email,
                password = password,
                onEmailChanged = { email = it },
                onPasswordChanged = { password = it },
                onEmailClear = { email = "" },
                onPasswordClear = { password = "" },
                actionButtonText = "Sign In",
                onActionButtonClick = {}
            )

            SignUpBox(modifier = Modifier.weight(1f))
        }
    }
}

//@Preview
//@Composable
//fun SignInScreenPreview(){
//    SignInScreen()
//}


@Composable
fun SignUpBox(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 16.dp),
        contentAlignment = Alignment.BottomCenter
    )
    {
        Text(
            text = "Sign Up instead?",
            style = MaterialTheme.typography.titleMedium,
            textDecoration = TextDecoration.Underline,
            color = Color.Blue
        )

    }
}

@Composable
fun VerticalSpacer(size: Int) = Spacer(modifier = Modifier.height(size.dp))

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeHolderText: String,
    onClear: () -> Unit,
    // showPassword: () -> Unit = {},  // here is something
    isPasswordField: Boolean = false
) {

    var showPassword by remember { mutableStateOf(false) }   // next to next line is a state with key, if key change composition will occur.
    var passwordIconResource by remember(showPassword) { mutableIntStateOf(if (showPassword) R.drawable.ic_eye_filled else R.drawable.ic_eye_outlined) }
    var visualTransformation by remember(showPassword) { mutableStateOf(if (isPasswordField && !showPassword) PasswordVisualTransformation() else VisualTransformation.None) }

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeHolderText) },
        shape = RoundedCornerShape(16.dp),
        visualTransformation = visualTransformation,
        trailingIcon = {
            AnimatedVisibility(
                visible = value.isNotEmpty(),
                enter = expandHorizontally(expandFrom = Alignment.Start),  // by default, it is at the Alignment.End
                exit = shrinkHorizontally(shrinkTowards = Alignment.Start)
            ) {
                if (isPasswordField) {
                    IconButton(onClick = { showPassword = !showPassword }) {
                        Icon(
                            painter = painterResource(passwordIconResource),
//                                if (showPassword) painterResource(R.drawable.ic_eye_filled)
//                            else painterResource(R.drawable.ic_eye_outlined),

                            // imageVector = Icons.Default.Check,
                            contentDescription = "Show Password"
                        )
                    }
                } else {
                    IconButton(onClick = onClear) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear"
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun SignInScreenPreview(){
    SignInScreen()
}