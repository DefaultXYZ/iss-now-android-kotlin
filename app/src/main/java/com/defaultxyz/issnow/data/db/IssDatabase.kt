package com.defaultxyz.issnow.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.defaultxyz.issnow.data.model.IssAstro
import com.defaultxyz.issnow.data.model.IssPosition

@Database(entities = [IssPosition::class, IssAstro::class], version = 1)
abstract class IssDatabase : RoomDatabase() {
    abstract fun issDao(): IssDao

    companion object {
        const val DB_NAME = "db_iss_now"
    }
}