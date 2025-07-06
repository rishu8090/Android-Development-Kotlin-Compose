package com.example.week8.day1_workmanager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackgroundWorkScreen(
    viewModel: BackgroundWorkViewModel = hiltViewModel()
){
    Scaffold(topBar = {TopAppBar(title = {Text(text = "BackgroundWorkScreen")})
    }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {viewModel.createOneTimeWorkRequest()}) {// Purpose: Used to run a task only once.
                Text(text = "Create OneTime work Request")
            }

            Button(onClick = {viewModel.createOneTimeWorkRequestWithDataAndConstraints()}) {
                Text(text = "Create OneTime work Request with Data and Constraints")
            }

            Button(onClick = {viewModel.createPeriodicWorkRequest()}) {//Purpose: Used to run a task repeatedly at specified intervals (minimum 15 minutes).
                Text(text = "Create Periodic Work Request")
            }
        }
    }
}

//                                                            BGWorkModule  LogWorker
   // flow of this file  Screen -> ViewModel -> Repository -> WorkManager -> Worker -> doWork()

// flow of workManager  App -> Gives workRequest to WorkManager -> WorkManager -> Worker -> doWork()





// 3rd type of work is, which not used occasionally // In Android, deferrable work means background tasks that don't need to run immediately, but should eventually run,

/*
*
Types of background work
Background work falls into one of three primary categories:
Immediate: Needs to execute right away and complete soon.
Long Running: May take some time to complete.
Deferrable: Does not need to run right away.

Likewise, background work in each of these three categories can be either persistent or impersistent:
Persistent work: Remains scheduled through app restarts and device reboots.
Impersistent work: No longer scheduled after the process ends.
*
*
* All persistent work: You should use WorkManager for all forms of persistent work.
Immediate impersistent work: You should use Kotlin coroutines for immediate impersistent work.
Long-running and deferrable impersistent work: You shouldn't use long-running and deferrable impersistent work. You should instead complete such tasks through persistent work using WorkManager.
*
The WorkManager API is the recommended replacement for all previous Android background scheduling APIs, including FirebaseJobDispatcher, GcmNetworkManager, and JobScheduler.


 *
 *
 *
 * Work is defined using the Worker class. The doWork() method runs asynchronously on a background thread provided by WorkManager.
To create some work for WorkManager to run, extend the Worker class and override the doWork() method. For example, to create a Worker that uploads images, you can do the following:
*
class UploadWorker(appContext: Context, workerParams: WorkerParameters):
       Worker(appContext, workerParams) {
   override fun doWork(): Result {
       uploadImages()
       return Result.success()
   }
}
* */