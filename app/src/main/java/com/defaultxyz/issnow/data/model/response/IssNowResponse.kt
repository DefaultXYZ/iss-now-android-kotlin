package com.defaultxyz.issnow.data.model.response

import com.defaultxyz.issnow.data.model.IssPosition
import com.google.gson.annotations.SerializedName

data class IssNowResponse(val timestamp: Long,
                          @field:SerializedName("iss_position")
                          val issPosition: IssPositionResponse) {
    fun toIssPosition(): IssPosition {
        return IssPosition(timestamp, issPosition.latitude, issPosition.longitude)
    }

    companion object {
        val TAG = IssNowResponse::class.java.simpleName
    }
}

data class IssPositionResponse(val latitude: Double,
                               val longitude: Double)