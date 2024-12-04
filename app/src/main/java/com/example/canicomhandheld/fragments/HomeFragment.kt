package com.example.canicomhandheld.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.canicomhandheld.ActivityDeviceList
import com.example.canicomhandheld.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class HomeFragment : Fragment() {
    private lateinit var googleMap: GoogleMap

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

            // Set custom info window adapter
            googleMap.setInfoWindowAdapter(CustomInfoWindowAdapter())
        }

        val addNewDeviceLayout = view.findViewById<LinearLayout>(R.id.addNewDeviceLayout)
        addNewDeviceLayout.setOnClickListener {
            val intent = Intent(requireContext(), ActivityDeviceList::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun addCustomDogMarkers() {
        val dog1Location = LatLng(18.5074, 73.8077)
        val dog2Location = LatLng(18.5080, 73.8090)
        val dog3Location = LatLng(18.5090, 73.8100)

        // Add markers with custom dog icons
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
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.dog2)) // Custom dog image
        )

        googleMap.addMarker(
            MarkerOptions()
                .position(dog3Location)
                .title("Dog 3")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.dog3)) // Custom dog image
        )

        // Move the camera to the first dog's location
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dog1Location, 10f))
    }


    // Custom Info Window Adapter
    inner class CustomInfoWindowAdapter : GoogleMap.InfoWindowAdapter {
        private val infoWindowView: View =
            LayoutInflater.from(requireContext()).inflate(R.layout.custom_info_window, null)

        override fun getInfoWindow(marker: Marker): View? {
            return null // Use the default info window frame
        }

        override fun getInfoContents(marker: Marker): View {
            // Populate info window content
            val latitudeTextView: TextView = infoWindowView.findViewById(R.id.latitudeTextView)
            val longitudeTextView: TextView = infoWindowView.findViewById(R.id.longitudeTextView)

            val position = marker.position
            latitudeTextView.text = "Latitude: ${position.latitude}"
            longitudeTextView.text = "Altitude: ${position.longitude}"

            return infoWindowView
        }
    }
}

