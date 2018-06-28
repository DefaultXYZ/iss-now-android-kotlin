package com.defaultxyz.issnow.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.defaultxyz.issnow.data.model.IssAstro
import com.defaultxyz.issnow.data.model.IssPosition

@Dao
interface IssDao {
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updatePosition(position: IssPosition)

    @Query("SELECT * FROM tb_iss_position ORDER BY timestamp LIMIT 1")
    fun getLastPosition(): LiveData<IssPosition>

    @Query("DELETE FROM tb_iss_position WHERE timestamp < :now")
    fun clearOld(now: Long)

    @Query("SELECT * FROM tb_iss_astro WHERE issId = :issId")
    fun getAstros(issId: Int): LiveData<List<IssAstro>>
}