package com.defaultxyz.issnow.data.network

import androidx.work.Data
import androidx.work.Worker
import com.defaultxyz.issnow.MainApplication
import com.defaultxyz.issnow.data.model.response.AstrosResponse
import com.defaultxyz.issnow.data.model.response.IssNowResponse
import javax.inject.Inject

class RestWorker : Worker() {
    @Inject lateinit var restClient: RestClient

    override fun doWork(): Result {
        (applicationContext as MainApplication).injector.inject(this)
        val shouldLoadAstros = inputData.getBoolean(LOAD_ASTRO_KEY, false)

        val data = Data.Builder()

        if (shouldLoadAstros) {
            val astrosJson = restClient.getAstros()
            data.putString(AstrosResponse.TAG, astrosJson)
        }

        val issPositionJson = restClient.getIssPosition()
        data.putString(IssNowResponse.TAG, issPositionJson)

        outputData = data.build()
        return Result.SUCCESS
    }

    companion object {
        const val TAG = "RestStatus"
        const val WORK_NAME = "com.defaultxyz.issnow.RestWorker"
        const val LOAD_ASTRO_KEY = "Load_Astro"
    }
}