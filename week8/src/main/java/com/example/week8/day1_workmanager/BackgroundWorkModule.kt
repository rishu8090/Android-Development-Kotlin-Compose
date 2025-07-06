package com.example.week8.day1_workmanager

import android.content.Context
import androidx.startup.Initializer
import androidx.work.Configuration
import androidx.work.WorkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class BackgroundWorkModule {   // to provide @Provides hilt methods to workManager we use this Module.
    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager {
//         by default, workManager initialize it at the app starts, that's why you doesn't have to initialize it manually.
//        WorkManager.initialize(context, Configuration.Builder().build())  // The singleton instance of WorkManager;
//        this may be null in unusual circumstances where you have disabled automatic initialization and have failed to manually call initialize.
        return WorkManager.getInstance(context)
    }
}


//@Module
//@InstallIn(SingletonComponent::class)  // by this we controls which component will starts on app starts and which not.
//class BackgroundWorkModule :
//    Initializer<WorkManager> {   // Initializers can be used to initialize libraries during app startup, without the need to use additional android.content.ContentProviders.
//    override fun create(context: Context): WorkManager {
//        return WorkManager.getInstance(context)
//    }
//
//    override fun dependencies(): List<Class<out Initializer<*>?>?> {
//        TODO("Not yet implemented")  // return emptyList()
//
//    }
//}
