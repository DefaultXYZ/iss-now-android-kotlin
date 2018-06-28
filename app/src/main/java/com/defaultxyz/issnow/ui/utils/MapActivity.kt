package com.defaultxyz.issnow.ui.utils

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.defaultxyz.issnow.R
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback

/**
 * Ready-to-use activity with map on it, handled map's lifecycle methods and map async.
 *
 * Child activity must have MapView component with `R.id.map_view` and invoke method `onCreateMap()`
 * after `setContentView()`.
 */
abstract class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var mapView: MapView

    fun onCreateMap(savedInstanceState: Bundle?) {
        mapView = findViewById(R.id.map_view)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if (outState != null) mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }
}