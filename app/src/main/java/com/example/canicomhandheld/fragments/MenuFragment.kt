package com.example.canicomhandheld.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.canicomhandheld.CompassActivity
import com.example.canicomhandheld.DogList
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

        val calendarimg =view.findViewById<ImageView>(R.id.calendarimg)
        calendarimg.setOnClickListener {
            openCalendarFragment()
        }

        val dogListBtn = view.findViewById<LinearLayout>(R.id.dogListBtn)
        dogListBtn.setOnClickListener {
            val intent = Intent(requireContext(),DogList::class.java)
            startActivity(intent)

        }
    }
    private fun openCalendarFragment() {
        // Create an instance of the fragment you want to switch to
        val anotherFragment = CalendarFragment()

        // Use FragmentManager and begin the transaction
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, anotherFragment) // Use your fragment container id
            .addToBackStack(null) // Optionally, add this transaction to the back stack
            .commit()
    }


}