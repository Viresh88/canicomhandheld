package com.example.canicomhandheld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class CompassActivity : AppCompatActivity() {
    private lateinit var compassNeedle: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compass)

        compassNeedle = findViewById(R.id.compass_needle)

        compassNeedle.rotation = 360f
    }
}