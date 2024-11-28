package com.example.canicomhandheld


import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

        val btnFence = findViewById<ImageButton>(R.id.btnFence)
        btnFence.setOnClickListener {
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
                 cancelbtn.setTextColor(Color.parseColor("#FFFFFF"))
                 cancelbtn.setBackgroundColor(Color.parseColor("#3B82F6"))
                 saveBtn.setBackgroundColor(Color.parseColor("#CCCCCC"))
                 dialog.dismiss()
             }


             saveBtn.setOnClickListener {
                 saveBtn.setBackgroundColor(Color.parseColor("#3B82F6"))
                 saveBtn.setTextColor(Color.parseColor("#FFFFFF"))
                 cancelbtn.setBackgroundColor(Color.parseColor("#CCCCCC"))
                val fenceName = dialogView.findViewById<EditText>(R.id.etFenceName).text.toString()
                 if (fenceName.isNotEmpty()){
                     Toast.makeText(this, "Fence Name: $fenceName", Toast.LENGTH_LONG).show()
                     dialog.dismiss()
                 }
                 else {
                     Toast.makeText(this, "Please enter a fence name", Toast.LENGTH_LONG).show()
                 }
             }

            dialog.show()
        }

        val btnFlag = findViewById<ImageButton>(R.id.btnFlag)
        btnFlag.setOnClickListener {
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
                cancelbtn.setTextColor(Color.parseColor("#FFFFFF"))
                cancelbtn.setBackgroundColor(Color.parseColor("#3B82F6"))
                saveBtn.setBackgroundColor(Color.parseColor("#CCCCCC"))
                dialog.dismiss()
            }

            saveBtn.setOnClickListener {
                saveBtn.setBackgroundColor(Color.parseColor("#3B82F6"))
                saveBtn.setTextColor(Color.parseColor("#FFFFFF"))
                cancelbtn.setBackgroundColor(Color.parseColor("#CCCCCC"))
                val flagName = dialogView.findViewById<EditText>(R.id.etFenceName).text.toString()
                if (flagName.isNotEmpty()){
                    Toast.makeText(this, "Flag Name: $flagName", Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                }
                else {
                    Toast.makeText(this, "Please enter a fence name", Toast.LENGTH_LONG).show()
                }
            }

            dialog.show()

        }

     val btnMap = findViewById<ImageButton>(R.id.btnMap)
        btnMap.setOnClickListener {
            showMapTypeDialog()
        }

    }

    private fun showMapTypeDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_map, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        val closeicon = dialogView.findViewById<ImageView>(R.id.closeicon)
        closeicon.setOnClickListener {
            dialog.dismiss()
        }

//        val imgDefault = findViewById<ImageView>(R.id.imgDefault)
//        val imgRelief = findViewById<ImageView>(R.id.imgRelief)
//        val imgSatellite = findViewById<ImageView>(R.id.imgSatellite)
//

        dialog.show()
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