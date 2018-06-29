package com.defaultxyz.issnow.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import androidx.work.WorkManager
import com.defaultxyz.issnow.data.db.IssDatabase
import com.defaultxyz.issnow.data.model.IssPosition
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
            it.astros = dao.getAstros(it.id!!)
        }

    }

    fun getLastPosition(): LiveData<IssPosition> {
        return position
    }

    private fun fetchData() {
        workManager.getStatusesByTag("REST").observeForever {
            if (it == null || it.isEmpty()) return@observeForever
            val status = it[0]
            val function = status.outputData.getString("FUNCTION", null)
            val response = status.outputData.getString("DATA", null)
            NetworkReceiverUtil.handleResponse(function, response)

        }
    }
}