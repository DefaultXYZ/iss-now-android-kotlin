package com.defaultxyz.issnow.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.os.Handler
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.defaultxyz.issnow.data.db.IssDatabase
import com.defaultxyz.issnow.data.model.IssAstro
import com.defaultxyz.issnow.data.model.IssPosition
import com.defaultxyz.issnow.data.network.RestWorker
import com.defaultxyz.issnow.utils.DbExecutor
import com.defaultxyz.issnow.utils.NetworkReceiverUtil
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(db: IssDatabase) {
    private val dao = db.issDao()
    private val workManager = WorkManager.getInstance()

    private var position = MediatorLiveData<IssPosition>()

    init {
        position.addSource(dao.getLastPosition()) {
            if (it == null) return@addSource
            DbExecutor {
                val astros: List<IssAstro> = dao.getAstros()
                it.astros = astros
                position.postValue(it)
            }.execute()
            DbExecutor { dao.clearPositions() }.execute()
        }
        position.addSource(workManager.getStatusesByTag(RestWorker.TAG)) {
            if (it == null || it.isEmpty()) return@addSource
            val status = it[0]

            val astros = NetworkReceiverUtil.handleAstros(status.outputData)
            if (astros != null)
                DbExecutor {
                    dao.clearAstros()
                    dao.insertAstros(astros)
                }.execute()

            val issPosition = NetworkReceiverUtil.handleIssPosition(status.outputData)
            if (issPosition != null) {
                astros?.let { issPosition.astros = it }
                DbExecutor {
                    dao.clearPositions()
                    dao.insertLastPosition(issPosition)
                }.execute()
            }
        }
        fetchData()
    }

    fun getLastPosition(): LiveData<IssPosition> {
        return position
    }

    private fun fetchData() {
        val handler = Handler()
        val r: Runnable = object : Runnable {
            private var shouldLoadAstros = true

            override fun run() {
                val requestBuilder = OneTimeWorkRequest.Builder(RestWorker::class.java)
                        .addTag(RestWorker.TAG)
                if (shouldLoadAstros) {
                    val inputData = Data.Builder()
                            .putBoolean(RestWorker.LOAD_ASTRO_KEY, shouldLoadAstros)
                            .build()
                    requestBuilder.setInputData(inputData)
                    shouldLoadAstros = false
                }
                workManager.beginUniqueWork(RestWorker.WORK_NAME,
                        ExistingWorkPolicy.REPLACE, requestBuilder.build()).enqueue()
                handler.postDelayed(this, 5000L)
            }

        }
        handler.post(r)
    }
}