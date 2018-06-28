package com.defaultxyz.issnow.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.defaultxyz.issnow.data.db.IssDatabase
import com.defaultxyz.issnow.data.model.IssPosition

class AppRepository(db: IssDatabase) {
    private val dao = db.issDao()

    private var position: LiveData<IssPosition>

    init {
        position = Transformations.switchMap(dao.getLastPosition()) { pos ->
            Transformations.map(dao.getAstros(pos.id!!)) { astros ->
                pos.astros = astros
                pos
            }
        }
    }

    fun getLastPosition(): LiveData<IssPosition> {
        return position
    }
}