package com.defaultxyz.issnow.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
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

    @Query("DELETE FROM tb_iss_position WHERE timestamp < :now")
    fun clearOld(now: Long)

    @Query("SELECT * FROM tb_iss_astro WHERE issId = :issId")
    fun getAstros(issId: Int): LiveData<List<IssAstro>>

    @Query("DELETE FROM tb_iss_astro")
    fun clearAstros()
}