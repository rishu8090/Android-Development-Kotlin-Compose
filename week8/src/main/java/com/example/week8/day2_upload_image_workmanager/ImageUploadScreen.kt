package com.example.week8.day2_upload_image_workmanager

import android.Manifest
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun ImageUploadScreen(
    viewModel: ImageUploadViewModel = hiltViewModel()
) {

    val imageUrl by viewModel.imageUrl.collectAsState()
    val uriHandler = LocalUriHandler.current

    val permissionToRequest = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Manifest.permission.READ_MEDIA_IMAGES
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }

    val readImagesPermission = rememberPermissionState(permissionToRequest)
    val imagePicker =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
            uri?.let {
                // process the image.
                Log.d("TAG", "Image Uri -> $uri")
                viewModel.uploadImage(uri)
            }

        }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Image Upload Screen") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                if (readImagesPermission.status.isGranted) {
                    // Launch Image Picker
                    imagePicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                } else {
                    readImagesPermission.launchPermissionRequest()
                }
            }
            ) {
                Text(text = "Upload Image")
            }

            AnimatedVisibility(
                modifier = Modifier.padding(16.dp),
                visible = imageUrl != null
            ) {

                imageUrl?.let { url ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Image URL:", style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            modifier = Modifier.clickable {
                                uriHandler.openUri(url)
                            },
                            text = url,
                            textDecoration = TextDecoration.Underline,
                            color = Color.Blue
                        )
                    }
                }
            }
        }
    }
}