package com.defaultxyz.issnow.utils

import com.defaultxyz.issnow.data.model.IssPosition
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapboxMap

object MapboxUtils {

    fun upsertIss(issPosition: IssPosition, mapboxMap: MapboxMap) {
        if (mapboxMap.markers.isEmpty()) {
            val marker = createMarker(issPosition)
            mapboxMap.addMarker(marker)
        } else {
            val marker = mapboxMap.markers[0]
            marker.position = LatLng(issPosition.latitude, issPosition.longitude)
            mapboxMap.updateMarker(marker)
        }
    }

    private fun createMarker(issPosition: IssPosition): MarkerOptions {
        val title = "There're ${issPosition.astros.size} people on the ISS"
        val astros = issPosition.astros.joinToString("\n") { it.name }
        return MarkerOptions()
                .setTitle(title)
                .setSnippet(astros)
                .setPosition(LatLng(issPosition.latitude, issPosition.longitude))
    }
}