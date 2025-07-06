package com.example.week6.day3_filestorage

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState


@OptIn(ExperimentalMaterial3Api::class,
    ExperimentalPermissionsApi::class)
@Composable
fun FileStorageScreen() {
    val context = LocalContext.current
    val readMediaVideoPermission = rememberPermissionState(android.Manifest.permission.READ_MEDIA_VIDEO)    // shouldShowRational is used to request permission when first time user deny permission.
    var videos by remember { mutableStateOf<List<Video>>(emptyList()) }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "FileStorageScreen") })
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
                FileWriter.writeToFile(
                    context = context,
                    fileName = "internal_cache.txt",
                     directoryType = DirectoryType.Cache,
                    storageType = StorageType.Internal,
                    content = "This is a content of internal cache"
                )
                context.showToast("Successfully wrote to internal cache directory")
            }) {
                Text(text = "Write to file in Internal cache directory")
            }

            Button(onClick = {
                val content = FileReader.readFromFile(
                     context = context,
                     fileName = "internal_cache.txt",
                     directoryType = DirectoryType.Cache,
                     storageType = StorageType.Internal)
                context.showToast(content.toString())
            }) {
                Text(text = "Read from  file in Internal cache directory")
            }

            Spacer(modifier = Modifier.height(16.dp))



            Button(onClick = {
                FileWriter.writeToFile(
                    context = context,
                    fileName = "internal_files.txt",
                    directoryType = DirectoryType.Files,
                    storageType = StorageType.Internal,
                    content = "This is a content of internal file"
                )
                context.showToast("Successfully wrote to internal files directory")
            }) {
                Text(text = "Write to file in Internal file directory")
            }

            Button(onClick = {
                val content = FileReader.readFromFile(
                    context = context,
                    fileName = "internal_files.txt",
                    directoryType = DirectoryType.Files,
                    storageType = StorageType.Internal)
                context.showToast(content.toString())
            }) {
                Text(text = "Read from  file in Internal files directory")
            }

            Spacer(modifier = Modifier.height(16.dp))



            Button(onClick = {
                FileWriter.writeToFile(
                    context = context,
                    fileName = "external_cache.txt",
                    directoryType = DirectoryType.Cache,
                    storageType = StorageType.External,
                    content = "This is a content of external cache"
                )
                context.showToast("Successfully wrote to  external cache directory")
            }) {
                Text(text = "Write to file in external cache directory")
            }

            Button(onClick = {
                val content = FileReader.readFromFile(
                    context = context,
                    fileName = "external_cache.txt",
                    directoryType = DirectoryType.Cache,
                    storageType = StorageType.External)
                context.showToast(content.toString())
            }) {
                Text(text = "Read from  file in external cache directory")
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                    onClick = {
                if(readMediaVideoPermission.status == PermissionStatus.Granted){
                     videos = context.getVideoFromStorage().toMutableList()
                    Log.d("TAG", "Video count: ${videos.size}")
                    videos.forEach { video ->
                        Log.d("TAG", "Video: $video")
                    }
                }else{
                    readMediaVideoPermission.launchPermissionRequest()
                }

            }) {
                Text(text = "Retrieve videos from external storage")
            }
            Spacer(modifier = Modifier.height(16.dp))
            AnimatedVisibility(visible = videos.isNotEmpty()) {
                Column {
                    Text(text = "Video List", style = MaterialTheme.typography.titleMedium)
                    videos.forEach { video ->
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = video.name)
                    }
                }
            }
        }
    }
}


fun Context.showToast(  message: String) {   // it is a use of extension function in which context is supplied in the function.  and use context as this,
    Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
}
