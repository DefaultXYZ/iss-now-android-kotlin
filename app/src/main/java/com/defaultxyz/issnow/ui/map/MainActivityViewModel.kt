package com.defaultxyz.issnow.ui.map

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.defaultxyz.issnow.data.model.IssAstro
import com.defaultxyz.issnow.data.model.IssPosition
import javax.inject.Inject
import javax.inject.Singleton

class MainActivityViewModel : ViewModel() {
    private var position = MutableLiveData<IssPosition>()

    init {
        val sample = IssPosition(0, 52.1436363, 21.0538923)
        sample.astros = listOf(
                IssAstro("Astro1", 1),
                IssAstro("Astro2", 1)
        )
        position.postValue(sample)
    }

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