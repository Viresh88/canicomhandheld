package com.example.canicomhandheld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class GetStarted : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)

        val btnGetStarted = findViewById<Button>(R.id.btngetStarted)

        btnGetStarted.setOnClickListener {
            val intent = Intent(this, SetLanguage::class.java)
            startActivity(intent)
        }
    }
}