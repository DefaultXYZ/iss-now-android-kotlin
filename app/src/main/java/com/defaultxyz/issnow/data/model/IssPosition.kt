package com.defaultxyz.issnow.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "tb_iss_position", indices = [(Index(value = ["timestamp"], unique = true))])
data class IssPosition @Ignore constructor(val timestamp: Long,
                                           val longitude: Double,
                                           val latitude: Double) {
    @field:PrimaryKey(autoGenerate = true)
    var id: Int? = null
        private set

    @Ignore
    var astros: List<IssAstro> = emptyList()

    constructor(id: Int, timestamp: Long, longitude: Double,
                latitude: Double) : this(timestamp, longitude, latitude) {
        this.id = id
    }

}