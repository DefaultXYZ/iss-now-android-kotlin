package com.defaultxyz.issnow

import android.app.Application
import com.defaultxyz.issnow.injection.*
import com.mapbox.mapboxsdk.Mapbox

class MainApplication : Application() {
    lateinit var injector: Injector

    override fun onCreate() {
        super.onCreate()
        Mapbox.getInstance(applicationContext, getString(R.string.mapbox_access_token))
        injector = DaggerInjector.builder()
                .appModule(AppModule(this))
                .dataModule(DataModule())
                .networkModule(NetworkModule())
                .build()
    }
}