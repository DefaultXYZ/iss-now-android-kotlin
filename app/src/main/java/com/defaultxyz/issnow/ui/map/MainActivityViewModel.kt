package com.defaultxyz.issnow.ui.map

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.defaultxyz.issnow.data.model.IssPosition
import javax.inject.Inject
import javax.inject.Singleton

class MainActivityViewModel : ViewModel() {
    lateinit var position: LiveData<IssPosition>

    fun getLastPosition(): LiveData<IssPosition> {
        return position
    }

    @Suppress("UNCHECKED_CAST")
    @Singleton
    class Factory @Inject constructor() : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainActivityViewModel() as T
        }
    }
}