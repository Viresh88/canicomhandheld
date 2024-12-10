package com.example.canicomhandheld

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.canicomhandheld.fragments.HomeFragment
import com.example.canicomhandheld.fragments.MenuFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView : BottomNavigationView = findViewById(R.id.bottomNavigationView)

        loadFragment(HomeFragment())

        bottomNavigationView.setOnNavigationItemSelectedListener{menuItem ->
          when (menuItem.itemId){
              R.id.home -> {
              loadFragment(HomeFragment())
                  true
              }

              R.id.menu -> {
                  loadFragment(MenuFragment())
                  true
              }

              R.id.compass -> {
                  val intent = Intent(this, CompassActivity::class.java)
                  startActivity(intent)
                  true
              }

              R.id.training -> {
                  val intent = Intent(this, TrainingScreen::class.java)
                  startActivity(intent)
                  true
              }

              R.id.settings -> {
                  val intent = Intent(this, SettingScreen::class.java)
                  startActivity(intent)
                  true
              }

              else -> false
          }


        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            // If there is, pop the back stack and go to the previous fragment
            supportFragmentManager.popBackStack()
        } else {
            // If no fragments in back stack, follow normal back press (e.g., exit the activity)
            super.onBackPressed()
        }
    }

    private fun loadFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()

    }

    private fun loadActivity(activity: Activity){

    }
}