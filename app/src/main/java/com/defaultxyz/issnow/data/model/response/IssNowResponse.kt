package com.defaultxyz.issnow.data.model.response

import com.google.gson.annotations.SerializedName

data class IssNowResponse(val timestamp: Long,
                          @field:SerializedName("iss_position")
                          val issPosition: IssPositionResponse)

data class IssPositionResponse(val latitude: Double,
                               val longitude: Double)