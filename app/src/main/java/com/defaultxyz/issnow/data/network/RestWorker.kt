package com.defaultxyz.issnow.data.network

import android.os.Handler
import androidx.work.Data
import androidx.work.Worker
import com.defaultxyz.issnow.MainApplication
import com.defaultxyz.issnow.data.model.response.AstrosResponse
import javax.inject.Inject

class RestWorker : Worker() {
    @Inject lateinit var restClient: RestClient

    override fun doWork(): Result {
        (applicationContext as MainApplication).injector.inject(this)
        val data = Data.Builder()
        val astrosJson = restClient.getAstros()
        data.putString(AstrosResponse::class.java.simpleName, astrosJson)

        val handler = Handler()
        handler.post(IssPositionTask(handler, data))

        return Result.SUCCESS
    }

    companion object {
        const val TAG = "RestStatus"
        const val WORK_NAME = "com.defaultxyz.issnow.RestWorker"
    }

    private inner class IssPositionTask(private val handler: Handler,
                                        private val data: Data.Builder) : Runnable {
        private val interval = 5000L

        override fun run() {
            val issPositionJson = restClient.getIssPosition()
            data.putString(AstrosResponse::class.java.simpleName, issPositionJson)
            outputData = data.build()
            handler.postDelayed(this, interval)
        }
    }
}