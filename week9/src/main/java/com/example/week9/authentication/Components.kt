package com.example.week9.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.week9.authentication.signin.CustomTextField
import com.example.week9.components.VerticalSpacer

@Composable
fun CompanyInfo(modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Rishu Choudhary",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Composable
fun EmailAndPasswordContent(
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onEmailClear: () -> Unit,
    onPasswordClear: () -> Unit,
    actionButtonText: String,  // Sign in OR Sign up
    onActionButtonClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = onEmailChanged,
            placeHolderText = "Enter your Email ",
            onClear = onEmailClear
        )

        VerticalSpacer(12)

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = onPasswordChanged,
            placeHolderText = "Enter your Password ",
            isPasswordField = true,
            onClear = onPasswordClear
        )

        VerticalSpacer(16)

        Button(onClick = onActionButtonClick) {
            Text(text = actionButtonText)
        }
    }
}