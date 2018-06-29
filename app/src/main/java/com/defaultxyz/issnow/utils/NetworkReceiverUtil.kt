package com.defaultxyz.issnow.utils

import com.defaultxyz.issnow.data.model.response.AstrosResponse
import com.defaultxyz.issnow.data.model.response.IssNowResponse
import com.google.gson.Gson

object NetworkReceiverUtil {
    private val gson = Gson()

    fun handleResponse(function: String?, response: String?) {
        if (function == null || response == null) return

        when (function) {
            "ISS_NOW" -> {
                val issNowResponse = gson.fromJson(response, IssNowResponse::class.java)

            }
            "ASTROS" -> {
                val astrosResponse = gson.fromJson(response, AstrosResponse::class.java)

            }
        }
    }
}