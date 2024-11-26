package com.example.canicomhandheld


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.canicomhandheld.fragments.CalendarFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class GpsLocation : AppCompatActivity(), OnMapReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gps_location)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val calendarimg = findViewById<ImageButton>(R.id.calendarimg)
        calendarimg.setOnClickListener {
          openFragment(CalendarFragment())
        }

        val compassimg = findViewById<ImageButton>(R.id.compassimg)
        compassimg.setOnClickListener {
            val intent = Intent(this, CompassActivity::class.java)
            startActivity(intent)
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()

    }

    override fun onMapReady(googleMap: GoogleMap) {
         var map = googleMap

        val location = LatLng(18.5074, 73.8077)
        map.addMarker(MarkerOptions().position(location).title("Rajyog society"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
    }
}