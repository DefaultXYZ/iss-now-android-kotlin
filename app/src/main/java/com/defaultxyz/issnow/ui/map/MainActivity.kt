package com.defaultxyz.issnow.ui.map

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.defaultxyz.issnow.R
import com.defaultxyz.issnow.ui.utils.MapActivity
import com.mapbox.mapboxsdk.maps.MapboxMap
import javax.inject.Inject

class MainActivity : MapActivity() {
    @Inject lateinit var vmFactory: MainActivityViewModel.Factory
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        super.onCreateMap(savedInstanceState)
        viewModel = ViewModelProviders.of(this, vmFactory)[MainActivityViewModel::class.java]
    }

    override fun onMapReady(mapboxMap: MapboxMap?) {
        if (mapboxMap == null) return
        viewModel.getLastPosition().observe(this, Observer {
            if (it == null) return@Observer
            with(mapboxMap.cameraPosition.target) {
                latitude = it.latitude
                longitude = it.longitude
            }
        })
    }
}
