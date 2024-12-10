package com.example.canicomhandheld

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.canicomhandheld.fragments.CalendarFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions

class GpsLocation : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private var polyline: Polyline? = null
    private var isPathVisible = false // To track if the path is displayed

    private lateinit var imageButtons: List<ImageButton>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gps_location)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        val calendarimg = findViewById<ImageButton>(R.id.calendarimg)
        val compassimg = findViewById<ImageButton>(R.id.compassimg)
        val btnFence = findViewById<ImageButton>(R.id.btnFence)
        val btnFlag = findViewById<ImageButton>(R.id.btnFlag)
        val btnMap = findViewById<ImageButton>(R.id.btnMap)
       imageButtons = listOf(calendarimg, compassimg, btnFence, btnFlag, btnMap)



        calendarimg.setOnClickListener {
            activateButton(it as ImageButton)
            togglePathOnMap()
        }


        compassimg.setOnClickListener {
            activateButton(it as ImageButton)
//            val intent = Intent(this, CompassActivity::class.java)
//            startActivity(intent)
        }


        btnFence.setOnClickListener {
            activateButton(it as ImageButton)
            showFenceDialog()
        }


        btnFlag.setOnClickListener {
            activateButton(it as ImageButton)
            showFlagDialog()
        }


        btnMap.setOnClickListener {
            activateButton(it as ImageButton)
            showMapTypeDialog()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        val location = LatLng(18.5074, 73.8077)
        map.addMarker(MarkerOptions().position(location).title("Rajyog society"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
    }

    private fun togglePathOnMap() {
        if (isPathVisible) {
            // Remove the path if it is already visible
            polyline?.remove()
            isPathVisible = false
        } else {
            // Add a path to the map
            val pathPoints = listOf(
                LatLng(18.5074, 73.8077),
                LatLng(18.5204, 73.8567) // Example path point
            )
            polyline = map.addPolyline(
                PolylineOptions()
                    .addAll(pathPoints)
                    .color(Color.BLUE)
                    .width(5f)
            )
            isPathVisible = true
        }
    }

    private fun showFenceDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_fence, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        val closeIcon = dialogView.findViewById<ImageView>(R.id.closeicon)
        closeIcon.setOnClickListener {
            dialog.dismiss()
        }

        val cancelbtn = dialogView.findViewById<Button>(R.id.btnCancel)
        val saveBtn = dialogView.findViewById<Button>(R.id.btnSave)
        cancelbtn.setOnClickListener {
            dialog.dismiss()
        }

        saveBtn.setOnClickListener {
            val fenceName = dialogView.findViewById<EditText>(R.id.etFenceName).text.toString()
            if (fenceName.isNotEmpty()) {
                Toast.makeText(this, "Fence Name: $fenceName", Toast.LENGTH_LONG).show()
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Please enter a fence name", Toast.LENGTH_LONG).show()
            }
        }

        dialog.show()
    }

    private fun showFlagDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_flagpoint, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        val closeIcon = dialogView.findViewById<ImageView>(R.id.closeicon)
        closeIcon.setOnClickListener {
            dialog.dismiss()
        }

        val cancelbtn = dialogView.findViewById<Button>(R.id.btnCancel)
        val saveBtn = dialogView.findViewById<Button>(R.id.btnSave)
        cancelbtn.setOnClickListener {
            dialog.dismiss()
        }

        saveBtn.setOnClickListener {
            val flagName = dialogView.findViewById<EditText>(R.id.etFenceName).text.toString()
            if (flagName.isNotEmpty()) {
                Toast.makeText(this, "Flag Name: $flagName", Toast.LENGTH_LONG).show()
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Please enter a flag name", Toast.LENGTH_LONG).show()
            }
        }

        dialog.show()
    }

    private fun showMapTypeDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_map, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        val closeIcon = dialogView.findViewById<ImageView>(R.id.closeicon)
        closeIcon.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun activateButton(selectedButton: ImageButton) {
        // Reset all buttons to default state
        for (button in imageButtons) {
            button.setBackgroundResource(android.R.color.transparent) // Remove background
            button.setColorFilter(null) // Reset the icon color
        }

        // Set the background and icon color of the selected button
//        selectedButton.setBackgroundColor(getColor(R.color.green)) // Background color
//        selectedButton.setColorFilter(getColor(R.color.white), PorterDuff.Mode.SRC_IN) // Icon color
    }
}
