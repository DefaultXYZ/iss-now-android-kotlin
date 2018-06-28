package com.defaultxyz.issnow.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "tb_iss_astro")
data class IssAstro @Ignore constructor(val name: String,
                                        val issId: Int) {
    @field:PrimaryKey(autoGenerate = true)
    var id: Int? = null
        private set

    constructor(id: Int, name: String, issId: Int) : this(name, issId) {
        this.id = id
    }
}