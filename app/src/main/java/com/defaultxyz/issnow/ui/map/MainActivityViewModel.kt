package com.defaultxyz.issnow.ui.map

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.defaultxyz.issnow.data.AppRepository
import com.defaultxyz.issnow.data.model.IssPosition
import javax.inject.Inject
import javax.inject.Singleton

class MainActivityViewModel(repository: AppRepository) : ViewModel() {
    private var position = repository.getLastPosition()

    fun getLastPosition(): LiveData<IssPosition> {
        return position
    }

    @Suppress("UNCHECKED_CAST")
    @Singleton
    class Factory @Inject constructor(private val repository: AppRepository)
        : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainActivityViewModel(repository) as T
        }
    }
}