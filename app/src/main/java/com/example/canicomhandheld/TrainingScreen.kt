package com.example.canicomhandheld

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView

class TrainingScreen : AppCompatActivity() {
    lateinit var vibrationButton: ImageButton
    lateinit var wifiButton: ImageButton
    lateinit var notificationButton: ImageButton
    lateinit var flashButton: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_screen)

        vibrationButton = findViewById(R.id.btnVibration)
        wifiButton = findViewById(R.id.btnWifi)
        notificationButton = findViewById(R.id.btnNotification)
        flashButton = findViewById(R.id.btnFlash)

        setButtonClickListener(vibrationButton)
        setButtonClickListener(wifiButton)
        setButtonClickListener(notificationButton)
        setButtonClickListener(flashButton)

    }

    private fun setButtonClickListener(button: ImageButton) {
        button.setOnClickListener {
            // Reset all button colors to default
            resetButtonColors()

            // Change the background color of the clicked button
//            button.setBackgroundColor(Color.parseColor("#03AA00"))
            button.drawable.setColorFilter(Color.parseColor("#03AA00"), PorterDuff.Mode.SRC_IN)
        }
    }

    private fun resetButtonColors() {
        // Reset all buttons to their default color
        vibrationButton.drawable.clearColorFilter()
        wifiButton.drawable.clearColorFilter()
        notificationButton.drawable.clearColorFilter()
        flashButton.drawable.clearColorFilter()
    }
    }
