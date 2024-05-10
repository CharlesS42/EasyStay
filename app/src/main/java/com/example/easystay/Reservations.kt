package com.example.easystay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Reservations : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservations)

        val intent: Intent = intent
        // details from map search activity
        val hotelReservationDetails = intent.getStringExtra("message key 02")

        val btnHotelSelect = findViewById<Button>(R.id.btnTransaction)


    }
}