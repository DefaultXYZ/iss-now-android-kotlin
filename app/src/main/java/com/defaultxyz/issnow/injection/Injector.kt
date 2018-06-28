package com.defaultxyz.issnow.injection

import com.defaultxyz.issnow.ui.map.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface Injector {
    fun inject(activity: MainActivity)
}