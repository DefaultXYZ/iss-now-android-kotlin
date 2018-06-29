package com.defaultxyz.issnow.injection

import com.defaultxyz.issnow.data.network.RestWorker
import com.defaultxyz.issnow.ui.map.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, NetworkModule::class])
interface Injector {
    fun inject(activity: MainActivity)
    fun inject(worker: RestWorker)
}