package com.defaultxyz.issnow.ui.map

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.defaultxyz.issnow.MainApplication
import com.defaultxyz.issnow.R
import com.defaultxyz.issnow.ui.utils.MapActivity
import com.defaultxyz.issnow.utils.MapboxUtil
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.maps.MapboxMap
import javax.inject.Inject

class MainActivity : MapActivity() {
    @Inject lateinit var vmFactory: MainActivityViewModel.Factory
    private lateinit var viewModel: MainActivityViewModel

    private var shouldChangeCameraPosition = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        super.onCreateMap(savedInstanceState)
        (application as MainApplication).injector.inject(this)
        viewModel = ViewModelProviders.of(this, vmFactory)[MainActivityViewModel::class.java]
    }

    override fun onMapReady(mapboxMap: MapboxMap?) {
        if (mapboxMap == null) return
        viewModel.getLastPosition().observe(this, Observer {
            if (it == null) return@Observer

            MapboxUtil.upsertIss(applicationContext, it, mapboxMap)

            if (shouldChangeCameraPosition) {
                val cameraPosition = CameraPosition.Builder()
                        .target(mapboxMap.markers[0].position)
                        .build()
                mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
                shouldChangeCameraPosition = false
            }
        })
    }
}
