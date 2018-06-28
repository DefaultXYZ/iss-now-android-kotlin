package com.defaultxyz.issnow.ui.map

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.defaultxyz.issnow.data.model.IssPosition

class MainActivityViewModel : ViewModel() {
    lateinit var position: LiveData<IssPosition>

    fun getLastPosition(): LiveData<IssPosition> {
        return position
    }

    @Suppress("UNCHECKED_CAST")
    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainActivityViewModel() as T
        }
    }
}