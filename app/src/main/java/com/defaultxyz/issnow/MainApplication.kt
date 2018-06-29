package com.defaultxyz.issnow

import android.app.Application
import com.defaultxyz.issnow.injection.AppModule
import com.defaultxyz.issnow.injection.DaggerInjector
import com.defaultxyz.issnow.injection.DataModule
import com.defaultxyz.issnow.injection.Injector
import com.mapbox.mapboxsdk.Mapbox

class MainApplication : Application() {
    lateinit var injector: Injector

    override fun onCreate() {
        super.onCreate()
        Mapbox.getInstance(applicationContext, getString(R.string.mapbox_access_token))
        injector = DaggerInjector.builder()
                .appModule(AppModule(this))
                .dataModule(DataModule())

                .build()
    }
}