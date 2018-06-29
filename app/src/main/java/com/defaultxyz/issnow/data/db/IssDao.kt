package com.defaultxyz.issnow.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.defaultxyz.issnow.data.model.IssAstro
import com.defaultxyz.issnow.data.model.IssPosition

@Dao
interface IssDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLastPosition(position: IssPosition)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAstros(astros: List<IssAstro>)

    @Query("SELECT * FROM tb_iss_position ORDER BY timestamp LIMIT 1")
    fun getLastPosition(): LiveData<IssPosition>

    @Query("DELETE FROM tb_iss_position")
    fun clearPositions()

    @Query("SELECT * FROM tb_iss_astro")
    fun getAstros(): List<IssAstro>

    @Query("DELETE FROM tb_iss_astro")
    fun clearAstros()
}