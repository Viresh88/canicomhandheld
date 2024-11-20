package com.example.canicomhandheld.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.canicomhandheld.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class HomeFragment : Fragment() {
    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize the map
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync { map ->
            googleMap = map

            // Add custom dog markers
            addCustomDogMarkers()
        }

        return view
    }

    private fun addCustomDogMarkers() {
        // Example coordinates
        val dog1Location = LatLng(18.5074, 73.8077)
        val dog2Location = LatLng(18.5074, 73.8077)
        val dog3Location = LatLng(18.5074, 73.8077)

        // Add markers with custom icons
        googleMap.addMarker(
            MarkerOptions()
                .position(dog1Location)
                .title("Dog 1")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.dog1)) // Custom dog image
        )

        googleMap.addMarker(
            MarkerOptions()
                .position(dog2Location)
                .title("Dog 2")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.dog2))
        )

        googleMap.addMarker(
            MarkerOptions()
                .position(dog3Location)
                .title("Dog 3")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.dog3))
        )

        // Move the camera to the first dog's location
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dog1Location, 10f))
    }

}