package com.defaultxyz.issnow.injection

import android.content.Context
import com.defaultxyz.issnow.R
import com.defaultxyz.issnow.data.network.IssNotifyApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesApi(context: Context): IssNotifyApi {
        val baseUrl = context.getString(R.string.api_url)
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .build()
                .create(IssNotifyApi::class.java)
    }
}