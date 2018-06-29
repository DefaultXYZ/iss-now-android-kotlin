package com.defaultxyz.issnow.data.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface IssNotifyApi {
    @GET("/iss-now.json")
    fun getIssNow(): Call<ResponseBody>

    @GET("/astros.json")
    fun getAstros(): Call<ResponseBody>
}