package com.defaultxyz.issnow.utils

import androidx.work.Data
import com.defaultxyz.issnow.data.model.IssAstro
import com.defaultxyz.issnow.data.model.IssPosition
import com.defaultxyz.issnow.data.model.response.AstrosResponse
import com.defaultxyz.issnow.data.model.response.IssNowResponse
import com.google.gson.Gson

object NetworkReceiverUtil {
    private val gson = Gson()

    fun handleIssPosition(outputData: Data): IssPosition? {
        val issPositionJson = outputData.getString(IssNowResponse.TAG, null) ?: return null
        val issPositionResponse = gson.fromJson(issPositionJson, IssNowResponse::class.java)
        return issPositionResponse.toIssPosition()
    }

    fun handleAstros(outputData: Data): List<IssAstro>? {
        val astrosJson = outputData.getString(AstrosResponse.TAG, null) ?: return null
        val astrosResponse = gson.fromJson(astrosJson, AstrosResponse::class.java)
        return astrosResponse.toAstroList()
    }
}