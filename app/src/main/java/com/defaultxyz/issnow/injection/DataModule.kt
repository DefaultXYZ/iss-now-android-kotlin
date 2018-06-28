package com.defaultxyz.issnow.injection

import android.arch.persistence.room.Room
import android.content.Context
import com.defaultxyz.issnow.data.db.IssDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun providesDatabase(context: Context): IssDatabase {
        return Room.databaseBuilder(context, IssDatabase::class.java, IssDatabase.DB_NAME)
                .build()
    }
}