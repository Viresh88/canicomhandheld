package com.example.canicomhandheld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.canicomhandheld.fragments.HomeFragment

class SetLanguage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_language)

        val btn_getStarted = findViewById<Button>(R.id.btn_getStarted)
        btn_getStarted.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}