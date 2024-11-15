package com.example.canicomhandheld.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.canicomhandheld.CompassActivity
import com.example.canicomhandheld.GpsLocation
import com.example.canicomhandheld.R
import com.example.canicomhandheld.SettingScreen
import com.example.canicomhandheld.TrainingScreen

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gpsBtn = view.findViewById<LinearLayout>(R.id.gpsBtn)
        gpsBtn.setOnClickListener {
          val intent = Intent(requireContext(), GpsLocation::class.java)
            startActivity(intent)
        }

        val compassBtn = view.findViewById<LinearLayout>(R.id.compassBtn)
        compassBtn.setOnClickListener {
            val intent = Intent(requireContext(), CompassActivity::class.java)
            startActivity(intent)
        }

        val trainingBtn = view.findViewById<LinearLayout>(R.id.btnTraining)
        trainingBtn.setOnClickListener {
            val intent = Intent(requireContext(), TrainingScreen::class.java)
            startActivity(intent)
        }

        val btnSettings = view.findViewById<LinearLayout>(R.id.btnSettings)
        btnSettings.setOnClickListener {
            val intent = Intent(requireContext(), SettingScreen::class.java)
            startActivity(intent)
        }
    }


}