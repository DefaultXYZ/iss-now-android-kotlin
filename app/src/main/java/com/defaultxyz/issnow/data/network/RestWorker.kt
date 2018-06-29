package com.defaultxyz.issnow.data.network

import androidx.work.Worker
import com.defaultxyz.issnow.MainApplication
import javax.inject.Inject

class RestWorker : Worker() {

    @Inject lateinit var restClient: RestClient

    override fun doWork(): Result {
        (applicationContext as MainApplication).injector.inject(this)

        return Result.SUCCESS
    }
}