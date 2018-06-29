package com.defaultxyz.issnow.utils

import android.content.Context
import com.defaultxyz.issnow.R
import com.defaultxyz.issnow.data.model.IssPosition
import com.mapbox.mapboxsdk.annotations.IconFactory
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapboxMap

object MapboxUtil {

    fun upsertIss(context: Context, issPosition: IssPosition, mapboxMap: MapboxMap) {
        if (mapboxMap.markers.isEmpty()) {
            val marker = createMarker(context, issPosition)
            mapboxMap.addMarker(marker)
        } else {
            val marker = mapboxMap.markers[0]
            marker.position = LatLng(issPosition.latitude, issPosition.longitude)
            mapboxMap.updateMarker(marker)
        }
    }

    private fun createMarker(context: Context, issPosition: IssPosition): MarkerOptions {
        val icon = IconFactory.getInstance(context).fromResource(R.drawable.ic_iss)
        val title = "There're ${issPosition.astros.size} people on the ISS"
        val astros = issPosition.astros.joinToString("\n") { it.name }
        return MarkerOptions()
                .setIcon(icon)
                .setTitle(title)
                .setSnippet(astros)
                .setPosition(LatLng(issPosition.latitude, issPosition.longitude))
    }
}