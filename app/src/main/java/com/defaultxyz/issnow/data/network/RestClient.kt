package com.defaultxyz.issnow.data.network

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestClient @Inject constructor(private val api: IssNotifyApi) {

    fun getIssPosition(): String? {
        val response = api.getIssNow().execute()
        if (response.isSuccessful) {
            return response.body()?.string()
        }
        return null
    }

    fun getAstros(): String? {
        val response = api.getAstros().execute()
        if (response.isSuccessful) {
            return response.body()?.string()
        }
        return null
    }
}