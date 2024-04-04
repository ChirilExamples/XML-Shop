package com.example.afinal.data.sync

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.afinal.data.ShoppingRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import javax.inject.Inject

@HiltWorker
class SyncLocalDataService @AssistedInject constructor(
    @Assisted context: Context, @Assisted params: WorkerParameters
) : CoroutineWorker(context, params) {

    companion object {
        const val WORK_NAME = "com.example.afinal.sync_schedule.SyncLocalDataService"
    }

    @Inject
    lateinit var repository: ShoppingRepository

    override suspend fun doWork(): Result {
        try {
            repository.getShoppings()
            Log.i("Sync", "Trying to update local DB")
        } catch (e: Exception) {
            Log.e("DataserviceSyncError", "" + e.message.toString())
            return Result.retry()
        }
        return Result.success() // returns "200 http"
    }
}
