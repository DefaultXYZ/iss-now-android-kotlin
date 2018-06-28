package com.defaultxyz.issnow.ui.map

import android.os.Bundle
import com.defaultxyz.issnow.R
import com.defaultxyz.issnow.ui.utils.MapActivity
import com.mapbox.mapboxsdk.maps.MapboxMap

class MainActivity : MapActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        super.onCreateMap(savedInstanceState)
    }

    override fun onMapReady(mapboxMap: MapboxMap?) {
        if (mapboxMap == null) return

    }
}
