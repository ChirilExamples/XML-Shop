package com.example.afinal.data

import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.afinal.data.sync.SyncLocalDataService
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class ShoppingApp : Application(), Configuration.Provider {

    override fun onCreate() {
        super.onCreate()
        WorkManager.initialize(this, workManagerConfiguration)
        CoroutineScope(Dispatchers.Default).launch {
            setUpWorkManager()
        }
    }

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration(): Configuration {
        //  builder pattern
        return Configuration.Builder().setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(Log.DEBUG).build()
    }

    private fun setUpWorkManager() {
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresCharging(false).setRequiresDeviceIdle(true).setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(false).build()

        val requestMode =
            PeriodicWorkRequestBuilder<SyncLocalDataService>(24, TimeUnit.HOURS).setConstraints(
                    constraints
                ).build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            SyncLocalDataService.WORK_NAME, ExistingPeriodicWorkPolicy.KEEP, requestMode
        )
    }
}
