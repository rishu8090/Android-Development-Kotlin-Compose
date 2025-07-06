package com.example.week6.day1_day2_room

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatabaseScreen(
    viewModel: UserViewModel = hiltViewModel()
) {
    var id by remember { mutableStateOf<Long?>(null) }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var aadhaarCardNumber by remember { mutableStateOf("") }

    val users by viewModel.users.collectAsState(emptyList())  // by using this val recomposition will occur in UI as soon as the new data is added in the users

    var showInsertUserUI by remember { mutableStateOf(false) }

    var isUserNameError by remember { mutableStateOf(false) }
    var isEmailError by remember { mutableStateOf(false) }
    var isFullNameError by remember { mutableStateOf(false) }
    var isCityError by remember { mutableStateOf(false) }
    var isAadhaarCardError by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    fun clearData() {  // kotlin allows you to define nested fun inside another fun, which is not in the java having only nested class.
        id = null
        username = ""
        email = ""
        fullName = ""
        city = ""
        aadhaarCardNumber = ""
        showInsertUserUI = false
    }

    fun clearErrors() {
        isUserNameError = false
        isEmailError = false
        isFullNameError = false
        isCityError = false
        isAadhaarCardError = false
    }

//    LaunchedEffect(Unit) {
//        userViewModel.getAllUsers()
//    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "DatabaseScreen") })
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
            ) {

            UserList(
                modifier = Modifier.weight(1f),
                users = users,
                onInsertUserClick = {
                    showInsertUserUI = true
                },
                onEditClick = { user ->
                    id = user.user.id
                    username = user.user.username
                    email = user.user.email
                    fullName = user.user.fullName
                    city = user.user.address.city
                    showInsertUserUI =
                        true  // by making this true, after clicking data will be filled the previous data.
                },
                onDeleteClick = { user ->
                    viewModel.deleteUser(user.user)
                }
            )

            AnimatedVisibility(visible = (showInsertUserUI == true)) {//
                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))
                InsertUser(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    username = username,
                    onUsernameChange = { username = it },
                    email = email,
                    onEmailChange = { email = it },
                    fullName = fullName,
                    onFullNameChange = { fullName = it },
                    city = city,
                    onCityChange = { city = it },
                    aadhaarCardNumber = aadhaarCardNumber,
                    onAadhaarCardNumberChange = { aadhaarCardNumber = it },
                    isUserNameError = isUserNameError,
                    isEmailError = isEmailError,
                    isFullNameError = isFullNameError,
                    isCityError = isCityError,
                    isAadhaarCardError = isAadhaarCardError,
                    onSavedUser = {
                        clearErrors()
                        if (username.isBlank()) isUserNameError = true
                        if (email.isBlank()) isEmailError = true
                        if (fullName.isBlank()) isFullNameError = true
                        if (city.isBlank()) isCityError = true
                        if (aadhaarCardNumber.isBlank()) isAadhaarCardError = true

                        if (isUserNameError || isEmailError || isFullNameError || isCityError || isAadhaarCardError) return@InsertUser

                        viewModel.insertUser(id, username, email, fullName, city, aadhaarCardNumber)
                        clearData()
//                        userViewModel.getAllUsers()
                    },
                    onCancelButtonClicked = {
                        clearData()
                    })

            }

//            HorizontalDivider()
//            Spacer(modifier = Modifier.height(6.dp))
//
//            Button(onClick = {
//                userViewModel.getAllUsers()
//            }) {
//                Text("Get All Users")
//            }

        }
    }
}


@Preview(name = "Screen", device = "id:pixel_5")
@Composable
fun DatabaseScreenPreview() {
    DatabaseScreen()
}

@Composable
fun InsertUser(
    modifier: Modifier = Modifier,
    username: String,
    onUsernameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    fullName: String,
    onFullNameChange: (String) -> Unit,
    city: String,
    onCityChange: (String) -> Unit,
    aadhaarCardNumber: String,
    onAadhaarCardNumberChange: (String) -> Unit,
    isUserNameError: Boolean,
    isEmailError: Boolean,
    isFullNameError: Boolean,
    isCityError: Boolean,
    isAadhaarCardError: Boolean,
    onSavedUser: () -> Unit,
    onCancelButtonClicked: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MyTextField(
            modifier = modifier,
            value = username,
            onValueChange = onUsernameChange,
            placeholderText = "Enter your username",
            isError = isUserNameError,
            supportingText = "Username can't be empty"
        )

        MyTextField(
            modifier = modifier,
            value = email,
            onValueChange = onEmailChange,
            placeholderText = "Enter your eMail",
            isError = isEmailError,
            supportingText = "Email can't be empty"
        )

        MyTextField(
            modifier = modifier,
            value = fullName,
            onValueChange = onFullNameChange,
            placeholderText = "Enter your Full Name",
            isError = isFullNameError,
            supportingText = "Full Name can't be empty"
        )

        MyTextField(
            modifier = modifier,
            value = city,
            onValueChange = onCityChange,
            placeholderText = "Enter your City",
            isError = isCityError,
            supportingText = "City can't be empty"
        )

        MyTextField(
            modifier = modifier,
            value = aadhaarCardNumber,
            onValueChange = onAadhaarCardNumberChange,
            placeholderText = "Enter your Aadhaar Card Number",
            isError = isAadhaarCardError,
            supportingText = "Aadhaar Card Number can't be empty"
        )

//

        Row(
            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceEvenly
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                onSavedUser()
                // userViewModel.insertUser(username = username, email = email, fullName = fullName)
            }) {
                Text("Save User")
            }

            Spacer(modifier = Modifier.width(12.dp))
            Button(onClick = {
                onCancelButtonClicked()
            }) {
                Text(text = "Cancel")
            }
        }
    }
}

@Composable
fun MyTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    isError: Boolean,
    supportingText: String,

    ) {
    TextField(
        modifier = modifier
            .padding(1.dp)
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholderText) },
        singleLine = true,
        isError = isError,
        supportingText = {
            if (isError) {
                Text(text = supportingText)
            }
        }

    )
}

@Composable
fun UserList(
    modifier: Modifier = Modifier,
    users: List<UserAndAadhaarCard>,
    onInsertUserClick: () -> Unit,
    onEditClick: (UserAndAadhaarCard) -> Unit,
    onDeleteClick: (UserAndAadhaarCard) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(users) { user ->
            UserCard(
                modifier = Modifier.fillMaxWidth(),
                user = user,
                onEditClick = onEditClick,
                onDeleteClick = onDeleteClick
            )
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { onInsertUserClick() }
                ) {
                    Text(text = "Insert User")
                }
            }
        }

    }
}

@Composable
fun UserCard(
    modifier: Modifier = Modifier,
    user: UserAndAadhaarCard,
    onEditClick: (UserAndAadhaarCard) -> Unit,
    onDeleteClick: (UserAndAadhaarCard) -> Unit
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(1.dp)
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "User Id: ${user.user.id}")
                    Text(text = "Username: ${user.user.username}")
                    Text(text = "City: ${user.user.address.city}")
                    Text(text = "Created At: ${user.user.createdAt}")
                    Text(text = "Aadhaar id: ${user.aadhaarCard?.id}")
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = { onEditClick(user) }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null
                    )
                }
                IconButton(onClick = { onDeleteClick(user) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
            }
        }
    }
}
